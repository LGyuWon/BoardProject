package portfolio.boradproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.boradproject.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
}
