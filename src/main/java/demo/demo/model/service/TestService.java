package demo.demo.model.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.demo.model.domain.testdb;
import demo.demo.model.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service // 서비스 등록, 스프링 내부 자동 등록됨
@RequiredArgsConstructor // 생성자 생성
public class TestService {
    @Autowired // 객체 의존성 주입 DI(컨테이너 내부 등록)
    private TestRepository testRepository;
    public testdb findByName(String name) { // 이름 찾기
        return (testdb) testRepository.findByName(name);
        }

    public List<testdb> findAll() { 
        // Repository의 findAll() 메서드를 호출하여 DB의 모든 데이터를 List로 반환
        return testRepository.findAll(); 
    }    
    }