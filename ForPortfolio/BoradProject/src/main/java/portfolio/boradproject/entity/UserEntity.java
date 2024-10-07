package portfolio.boradproject.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.boradproject.DTO.UserSignUpRequestDTO;
import portfolio.boradproject.config.enumerate.UserRole;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class UserEntity {
    @Id
    private String  userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardEntity> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentList = new ArrayList<>();

    public UserEntity(UserSignUpRequestDTO userSignUpRequestDTO, String encodedPassword) {
        this.userId = userSignUpRequestDTO.getUserId();
        this.password = encodedPassword;
        this.nickName = userSignUpRequestDTO.getNickName();
        this.userRole = UserRole.USER;
    }
}
