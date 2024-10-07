package portfolio.boradproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import portfolio.boradproject.entity.UserEntity;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class BoardResponseDTO {
    private Long boardId;
    private String title;
    private String content;
    private String user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imagePath;
}
