package portfolio.boradproject.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import portfolio.boradproject.exception.CustomException;
import portfolio.boradproject.exception.ErrorCode;

import java.io.*;
import java.util.UUID;

@Component
public class FileMaker {

    public String createFileName() {
        return UUID.randomUUID().toString();
    }

    public void saveFile(String fileName, MultipartFile file) {
        try {
            file.transferTo(new File("/Users/mac/Desktop/" + fileName));
        } catch (IOException e) {
            throw new CustomException(ErrorCode.CANNOT_SAVE_FILE);
        }
    }
}
