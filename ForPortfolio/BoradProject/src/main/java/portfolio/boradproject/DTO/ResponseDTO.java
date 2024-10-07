package portfolio.boradproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private String message;
    private String body;
}
