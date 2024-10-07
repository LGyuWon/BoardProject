package portfolio.boradproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import portfolio.boradproject.DTO.CommentInsertDTO;
import portfolio.boradproject.DTO.CommentUpdateDTO;
import portfolio.boradproject.config.security.UserDetailsImpl;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "boardInformation")
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "userInformation")
    private UserEntity user;

    @Column(nullable = false)
    private String comment;

    @CreationTimestamp
    @Column(name = "commentCreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "commentUpdatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    public CommentEntity(CommentInsertDTO commentInsertDTO, BoardEntity boardEntity, UserDetailsImpl userDetails) {
        this.board = boardEntity;
        this.user = userDetails.getUser();
        this.comment = commentInsertDTO.getComment();
    }

    public void update(CommentUpdateDTO commentUpdateDTO) {
        this.comment = commentUpdateDTO.getComment().isEmpty() ? this.comment : commentUpdateDTO.getComment();
        this.updatedAt = LocalDateTime.now();
    }
}
