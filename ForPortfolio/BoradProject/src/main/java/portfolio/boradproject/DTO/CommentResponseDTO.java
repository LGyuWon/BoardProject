package portfolio.boradproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommentResponseDTO {
    private Long commentId;
    private String comment;
    private String user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
