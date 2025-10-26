// src/main/java/demo/demo/controller/GlobalExceptionHandler.java

package demo.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// @ControllerAdvice: 이 클래스가 모든 @Controller에서 발생하는 예외를 전역적으로 처리하도록 지정
@ControllerAdvice 
public class GlobalExceptionHandler {

    /**
     * URL 경로 변수(PathVariable)의 타입이 일치하지 않을 때 발생하는 예외를 처리합니다.
     * 예: @GetMapping("/article_edit/{id}")에서 {id}에 'abc'와 같은 문자열이 들어올 경우 (Long 변환 실패)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        
        // 개발자 콘솔에서 에러 메시지를 확인할 수 있도록 출력합니다.
        System.err.println("🚨 Type Mismatch Exception (400 Bad Request) Caught: " + ex.getMessage());
        
        // 사용자가 제공한 에러 템플릿의 경로를 반환합니다. 
        // Spring Boot는 'templates/' 경로를 자동으로 인식합니다.
        // 파일 경로: templates/error_page/article_error.html
        return "error_page/article_error"; 
    }
}