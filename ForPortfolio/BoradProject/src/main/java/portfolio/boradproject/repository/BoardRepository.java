package portfolio.boradproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.boradproject.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
