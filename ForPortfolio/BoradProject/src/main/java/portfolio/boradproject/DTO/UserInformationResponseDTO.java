package portfolio.boradproject.DTO;

import lombok.Getter;
import portfolio.boradproject.entity.UserEntity;

@Getter
public class UserInformationResponseDTO {
    private String userId;
    private String nickName;

    public UserInformationResponseDTO(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.nickName = userEntity.getNickName();
    }
}
