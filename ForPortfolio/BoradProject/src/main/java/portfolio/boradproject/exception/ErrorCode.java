package portfolio.boradproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    EXIST_USER("중복된 사용자가 존재합니다.", HttpStatus.BAD_REQUEST),
    NOT_EXIST_USER("사용자가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_EQUALS_PASSWORD("비밀번호가 일치하지 않습니다", HttpStatus.BAD_REQUEST),
    NOT_EXIST_POST("해당하는 게시물이 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_EQUALS_USER_ID("아이디가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    CANNOT_SAVE_FILE("파일을 저장할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_TO_LOAD_FILE("파일을 불러오는 데 실패 하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);


    private final HttpStatus httpStatus;
    private final String errorMessage;

    ErrorCode(String errorMessage, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
