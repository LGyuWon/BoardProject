package portfolio.boradproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.boradproject.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
