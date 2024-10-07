package portfolio.boradproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.boradproject.DTO.CommentInsertDTO;
import portfolio.boradproject.DTO.CommentResponseDTO;
import portfolio.boradproject.DTO.CommentUpdateDTO;
import portfolio.boradproject.DTO.ResponseDTO;
import portfolio.boradproject.config.security.UserDetailsImpl;
import portfolio.boradproject.entity.BoardEntity;
import portfolio.boradproject.entity.CommentEntity;
import portfolio.boradproject.exception.CustomException;
import portfolio.boradproject.exception.ErrorCode;
import portfolio.boradproject.repository.BoardRepository;
import portfolio.boradproject.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public ResponseDTO addComment(Long postId, CommentInsertDTO commentInsertDTO, UserDetailsImpl userDetails) {
        BoardEntity boardEntity = boardRepository.findById(postId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_POST));
        commentRepository.save(new CommentEntity(commentInsertDTO, boardEntity, userDetails));
        return ResponseDTO.builder()
                .message("댓글이 저장되었습니다.")
                .build();
    }

    public CommentResponseDTO getComment(Long postId, UserDetailsImpl userDetails) {
        CommentEntity commentEntity = commentRepository.findById(postId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_POST));
        if (!commentEntity.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            throw new CustomException(ErrorCode.NOT_EXIST_USER);
        }
        commentRepository.findById(postId);
        return CommentResponseDTO.builder()
                .commentId(commentEntity.getCommentId())
                .comment(commentEntity.getComment())
                .user(userDetails.getUser().getNickName())
                .createdAt(commentEntity.getCreatedAt())
                .updatedAt(commentEntity.getUpdatedAt())
                .build();
    }

    public ResponseDTO updateComment(Long postId, CommentUpdateDTO commentUpdateDTO, UserDetailsImpl userDetails) {
        CommentEntity commentEntity = commentRepository.findById(postId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_POST));
        if(!commentEntity.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            throw new CustomException(ErrorCode.NOT_EXIST_USER);
        }
        commentEntity.update(commentUpdateDTO);
        return ResponseDTO.builder()
                .message("수정이 완료되었습니다.")
                .build();
    }

    public ResponseDTO deleteComment(Long postId, UserDetailsImpl userDetails) {
        CommentEntity commentEntity = commentRepository.findById(postId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_POST));
        if(!commentEntity.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            throw new CustomException(ErrorCode.NOT_EXIST_USER);
        }
        commentRepository.deleteById(commentEntity.getCommentId());
        return ResponseDTO.builder()
                .message("삭제가 완료되었습니다.")
                .build();
    }
}
