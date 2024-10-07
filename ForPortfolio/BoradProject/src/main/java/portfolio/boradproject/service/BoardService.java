package portfolio.boradproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import portfolio.boradproject.DTO.BoardResponseDTO;
import portfolio.boradproject.DTO.BoardUpdateDTO;
import portfolio.boradproject.DTO.PostInsertRequestDTO;
import portfolio.boradproject.DTO.ResponseDTO;
import portfolio.boradproject.config.security.UserDetailsImpl;
import portfolio.boradproject.entity.BoardEntity;
import portfolio.boradproject.entity.UserEntity;
import portfolio.boradproject.exception.CustomException;
import portfolio.boradproject.exception.ErrorCode;
import portfolio.boradproject.repository.BoardRepository;
import portfolio.boradproject.util.FileMaker;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileMaker fileMaker;

    public ResponseDTO addPost(MultipartFile file, PostInsertRequestDTO postInsertRequestDTO, UserDetailsImpl userDetails) {
        if(file.isEmpty()) {
            UserEntity userEntity = userDetails.getUser();
            boardRepository.save(new BoardEntity(postInsertRequestDTO, userEntity));
            return ResponseDTO.builder()
                    .message("게시물이 저장되었습니다.")
                    .build();
        }
        return saveFile(file, postInsertRequestDTO, userDetails);
    }


    public BoardResponseDTO getPost(Long postId, UserDetailsImpl userDetails) {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_POST));
        if (!boardEntity.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            throw new CustomException(ErrorCode.NOT_EXIST_USER);
        }

        String imagePath = "/Users/mac/Desktop/" + boardEntity.getFileName();

        return BoardResponseDTO.builder()
                .boardId(boardEntity.getBoardId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .user(userDetails.getUser().getNickName())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt())
                .imagePath(imagePath)
                .build();
    }


    public ResponseDTO updatePost(Long postId, BoardUpdateDTO boardUpdateDTO, UserDetailsImpl userDetails) {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_POST));
        if (!boardEntity.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            throw new CustomException(ErrorCode.NOT_EXIST_USER);
        }
        boardEntity.update(boardUpdateDTO);
        return ResponseDTO.builder()
                .message("수정이 완료되었습니다.")
                .build();

    }

    public ResponseDTO deletePost(Long postId, UserDetailsImpl userDetails) {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_POST));
        if (!boardEntity.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            throw new CustomException(ErrorCode.NOT_EXIST_USER);
        }
        boardRepository.deleteById(boardEntity.getBoardId());
        return ResponseDTO.builder()
                .message("삭제가 완료되었습니다")
                .build();
    }

    private ResponseDTO saveFile(MultipartFile file, PostInsertRequestDTO postInsertRequestDTO, UserDetailsImpl userDetails) {
        String fileName = fileMaker.createFileName() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        fileMaker.saveFile(fileName, file);
        UserEntity userEntity = userDetails.getUser();
        boardRepository.save(new BoardEntity(postInsertRequestDTO, userEntity, fileName));
        return ResponseDTO.builder()
                .message("게시물이 저장되었습니다.")
                .build();
    }
}
