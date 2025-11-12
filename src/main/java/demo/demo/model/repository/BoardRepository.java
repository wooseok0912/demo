package demo.demo.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import demo.demo.model.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
// List<Article> findAll();
}