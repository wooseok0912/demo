package demo.demo.controller;
import demo.demo.model.domain.testdb;
import demo.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추가
import demo.demo.model.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import java.util.List;

@Controller // 컨트롤러 어노테이션 명시
@RequestMapping("/demo")
public class DemoController {
    // 클래스 하단 작성
    @Autowired
    TestService testService; // DemoController 클래스 아래 객체 생성
    @Autowired
    TestRepository testRepository;

    @GetMapping("/main")
    public String index() {
        return "index"; // index.html 연결
    }

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
    public String testdb(Model model) {
        // TestRepository.findAll()을 사용하여 DB의 모든 testdb 데이터를 List로 가져옵니다.
        List<testdb> userList = testRepository.findAll(); 
        
        // 모델에 "userList"라는 이름으로 목록 데이터를 추가합니다.
        model.addAttribute("userList", userList);
        
        return "testdb"; 
    }
    
}
