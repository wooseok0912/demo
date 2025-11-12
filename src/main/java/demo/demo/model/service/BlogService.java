package demo.demo.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.demo.model.domain.Article;
import demo.demo.model.domain.Board;
import demo.demo.model.repository.BlogRepository;
import demo.demo.model.repository.BoardRepository;

import jakarta.transaction.Transactional; // @Transactional 임포트
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자 자동 생성(부분)
public class BlogService {
    @Autowired // 객체 주입 자동화, 생성자 1개면 생략 가능
    private final BlogRepository blogRepository; // 리포지토리 선언
    private final BoardRepository blogRepository2; // 리포지토리 선언
    
    public List<Board> findAll() { // 게시판 전체 목록 조회
        return blogRepository2.findAll();
    }

    /**
     * [기존] Article 저장용 메소드 (그대로 둡니다)
     */
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    /**
     * [추가됨] Board 저장용 메소드
     * BlogController의 'saveBoard' 오류를 해결합니다.
     */
    public Board saveBoard(AddArticleRequest request) {
        // DTO의 toBoardEntity() 메소드를 호출하여 Board 객체를 받아 저장
        return blogRepository2.save(request.toBoardEntity());
    }


    public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
        return blogRepository2.findById(id);
    }

    /**
     * [최종 수정 완료] Board 'update' 메소드 (Null 문제 해결)
     */
    @Transactional
    public Board update(Long id, AddArticleRequest request) {
        
        Board board = blogRepository2.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + id));

        // 6개 인자 모두 채워서 update (Null 문제 해결)
        board.update(
            request.getTitle(),   // (1) 새 '제목'
            request.getContent(), // (2) 새 '내용'
            board.getUser(),      // (3) 기존 'user'
            board.getNewdate(),   // (4) 기존 'newdate'
            board.getCount(),     // (5) 기존 'count'
            board.getLikec()      // (6) 기존 'likec'
        );
        
        return blogRepository2.save(board);
    }

    /**
     * [수정 완료] Board 'delete' 메소드
     */
    public void delete(Long id) {
        blogRepository2.deleteById(id); // (O) Board용
    }

}