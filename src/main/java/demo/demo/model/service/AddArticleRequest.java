package demo.demo.model.service;

import lombok.*; // 어노테이션 자동 생성
import demo.demo.model.domain.Article;
import demo.demo.model.domain.Board; // [추가] Board 임포트

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성
public class AddArticleRequest {
    private String title;
    private String content;

    /**
     * 'Article'을 생성하는 기존 메소드 (그대로 둡니다)
     */
    public Article toEntity(){ // Article 객체 생성
        return Article.builder()
            .title(title)
            .content(content)
            .build();
    }

    /**
     * [새로 추가된 메소드]
     * 'Board'를 생성하는 메소드입니다.
     * PDF 강의자료에 맞춰 'user', 'newdate' 등의 기본값을 설정합니다.
     */
    public Board toBoardEntity() {
        
        String currentDate = java.time.LocalDate.now().toString(); 
        
        return Board.builder()
                .title(title)
                .content(content)
                .user("GUEST") // 기본값 설정
                .newdate(currentDate) // 기본값 설정
                .count("0") // 기본값 설정
                .likec("0") // 기본값 설정
                .build();
    }
}