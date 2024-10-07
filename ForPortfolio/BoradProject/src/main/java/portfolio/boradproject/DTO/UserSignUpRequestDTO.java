package portfolio.boradproject.DTO;

import lombok.Getter;
import portfolio.boradproject.config.enumerate.UserRole;

@Getter
public class UserSignUpRequestDTO {
    private String userId;
    private String password;
    private String nickName;
}
