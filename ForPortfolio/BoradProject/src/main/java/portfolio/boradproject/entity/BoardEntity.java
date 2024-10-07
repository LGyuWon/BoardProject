package portfolio.boradproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import portfolio.boradproject.DTO.BoardUpdateDTO;
import portfolio.boradproject.DTO.PostInsertRequestDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "userInformation")
    private UserEntity user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentList = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String fileName;

    @CreationTimestamp
    @Column(name = "postCreatedAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "postUpdatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    public BoardEntity(PostInsertRequestDTO postInsertRequestDTO, UserEntity userEntity) {
        this.user = userEntity;
        this.title = postInsertRequestDTO.getTitle();
        this.content = postInsertRequestDTO.getContent();
    }

    public BoardEntity(PostInsertRequestDTO postInsertRequestDTO, UserEntity userEntity, String fileName) {
        this.user = userEntity;
        this.title = postInsertRequestDTO.getTitle();
        this.content = postInsertRequestDTO.getContent();
        this.fileName = fileName;
    }

    public void update(BoardUpdateDTO boardUpdateDTO) {
        this.title = boardUpdateDTO.getTitle().isEmpty() ? this.title : boardUpdateDTO.getTitle();
        this.content = boardUpdateDTO.getContent().isEmpty() ? this.content : boardUpdateDTO.getContent();
        this.updatedAt = LocalDateTime.now();
    }
}
