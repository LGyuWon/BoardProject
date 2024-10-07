package portfolio.boradproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolio.boradproject.DTO.BoardResponseDTO;
import portfolio.boradproject.DTO.BoardUpdateDTO;
import portfolio.boradproject.DTO.PostInsertRequestDTO;
import portfolio.boradproject.DTO.ResponseDTO;
import portfolio.boradproject.config.security.UserDetailsImpl;
import portfolio.boradproject.service.BoardService;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @PostMapping(value = "/post/insert", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO> addPost(@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "postInsertRequestDTO") PostInsertRequestDTO postInsertRequestDTO,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(boardService.addPost(file, postInsertRequestDTO, userDetails));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<BoardResponseDTO> getPost(@PathVariable("postId") Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(boardService.getPost(postId, userDetails));
    }

    @PatchMapping("/post/{postId}")
    @Transactional
    public ResponseEntity<ResponseDTO> updatePost(@PathVariable("postId") Long postId, @RequestBody BoardUpdateDTO boardUpdateDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(boardService.updatePost(postId, boardUpdateDTO, userDetails));
    }

    @DeleteMapping("/post/{postId}")
    @Transactional
    public ResponseEntity<ResponseDTO> deletePost(@PathVariable("postId") Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(boardService.deletePost(postId, userDetails));
    }
}
