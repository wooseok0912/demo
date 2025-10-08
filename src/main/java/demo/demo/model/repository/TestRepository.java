package demo.demo.model.repository;
import org.springframework.data.jpa.repository.JpaRepository; // JPA 필수 등록
import org.springframework.stereotype.Repository; // 빈 등록
import demo.demo.model.domain.testdb; // 도메인 연동
@Repository // 리포지토리 등록
public interface TestRepository extends JpaRepository<testdb, Long> {
    // Find all TestDB entities by a name
    testdb findByName(String name);
    }