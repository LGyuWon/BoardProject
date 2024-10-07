package portfolio.boradproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import portfolio.boradproject.DTO.CommentInsertDTO;
import portfolio.boradproject.DTO.CommentResponseDTO;
import portfolio.boradproject.DTO.CommentUpdateDTO;
import portfolio.boradproject.DTO.ResponseDTO;
import portfolio.boradproject.config.security.UserDetailsImpl;
import portfolio.boradproject.service.CommentService;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{postId}")
    public ResponseEntity<ResponseDTO> addComment(@PathVariable("postId") Long postId, @RequestBody CommentInsertDTO commentInsertDTO,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.addComment(postId, commentInsertDTO, userDetails));
    }

    @GetMapping("/comment/{postId}")
    public ResponseEntity<CommentResponseDTO> getComment(@PathVariable("postId") Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.getComment(postId, userDetails));
    }

    @PatchMapping("/comment/{postId}")
    @Transactional
    public ResponseEntity<ResponseDTO> updateComment(@PathVariable("postId") Long postId, @RequestBody CommentUpdateDTO commentUpdateDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentUpdateDTO, userDetails));
    }

    @DeleteMapping("/comment/{postId}")
    @Transactional
    public ResponseEntity<ResponseDTO> deleteComment(@PathVariable("postId") Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(commentService.deleteComment(postId, userDetails));
    }
}
