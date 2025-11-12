package demo.demo.controller;
import demo.demo.model.domain.Article;
import demo.demo.model.domain.Board;
import demo.demo.model.domain.testdb;
import demo.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추가
import demo.demo.model.service.AddArticleRequest;
import demo.demo.model.service.BlogService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping; // [수정] PostMapping -> DeleteMapping
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller // 컨트롤러 어노테이션 명시
public class BlogController {
    // 클래스 하단 작성
    @Autowired
    TestService testService; // DemoController 클래스 아래 객체 생성

    @Autowired 
    BlogService blogService;

    @GetMapping("/")
    public String index() {
        return "index"; // index.html 연결
    }

    // ... (hello, hello2, about_detailed, test1, testdb 매핑은 그대로) ...
    @GetMapping("/hello") // 전송 방식 GET
    public String hello(Model model) {
        model.addAttribute("data", "반갑습니다."); // model 설정
        return "hello"; // hello.html 연결
    }

    @GetMapping("/hello2")
    public String hello2(Model model) {
        // 5개의 속성 변수 추가
        model.addAttribute("username", "최우석님");
        model.addAttribute("welcomeMessage", "반갑습니다.");
        model.addAttribute("today", "오늘.");
        model.addAttribute("weather", "날씨는");
        model.addAttribute("weatherDescription", "매우 좋습니다.");
        return "hello2"; // hello2.html 연결
    }

    @GetMapping("/about_detailed")
    public String about() {
        return "about_detailed";
    }
    @GetMapping("/test1")
    public String thymeleaf_test1(Model model) {
    model.addAttribute("data1", "<h2> 반갑습니다 </h2>");
    model.addAttribute("data2", "태그의 속성 값");
    model.addAttribute("link", 01);
    model.addAttribute("name", "홍길동");
    model.addAttribute("para1", "001");
    model.addAttribute("para2", 002);
    return "thymeleaf_test1";
    }

    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        testdb test = testService.findByName("홍길동");
        model.addAttribute("data4", test);
        System.out.println("데이터 출력 디버그 : " + test);
        return "testdb";
    }


    // ==========================================================
    // ▼▼▼ Board 기능 매핑 (최종 수정 완료) ▼▼▼
    // ==========================================================

    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model) {
        List<Board> list = blogService.findAll(); 
        model.addAttribute("boards", list); 
        return "board_list"; 
    }

    @GetMapping("/board_view/{id}") // 게시판 링크 지정
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findById(id); 
        if (list.isPresent()) {
            model.addAttribute("boards", list.get()); 
        } else {
            return "/error_page/article_error"; 
        }
        return "board_view"; 
    }

    @GetMapping("/board_edit/{id}")
    public String showEditBoardForm(@PathVariable Long id, Model model) {
        Optional<Board> boardOptional = blogService.findById(id);
        if (boardOptional.isPresent()) {
            model.addAttribute("boards", boardOptional.get()); // 'boards'로 이름 통일
            return "board_edit";
        } else {
            return "/error_page/article_error";
        }
    }

    @PostMapping("/board_update/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list"; // 'board_list'로 리다이렉트
    }

    /**
     * [최종 수정] 
     * PDF(24p)와 application.properties(.filter.enabled=true)에 맞춰
     * @PostMapping -> @DeleteMapping으로 변경
     */
    @DeleteMapping("/api/board_delete/{id}") // <-- @PostMapping에서 변경
    public String deleteBoard(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list"; // 'board_list'로 리다이렉트
    }

    @GetMapping("/board_add")
    public String showAddBoardForm() {
        return "board_add_form"; 
    }

    @PostMapping("/board_save")
    public String saveBoard(@ModelAttribute AddArticleRequest request) {
        blogService.saveBoard(request); // BlogService의 saveBoard 메소드 호출
        return "redirect:/board_list";
    }


    @PutMapping("/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/board_list"; 
    }

    @DeleteMapping("/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/board_list"; 
    }

    @PostMapping("/articles") 
    public String addArticle(AddArticleRequest request) { 
        blogService.save(request); 
        return "redirect:/board_list"; 
    }

}   