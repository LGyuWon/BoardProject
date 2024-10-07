package portfolio.boradproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.boradproject.DTO.ResponseDTO;
import portfolio.boradproject.DTO.UserLoginRequestDTO;
import portfolio.boradproject.DTO.UserSignUpRequestDTO;
import portfolio.boradproject.service.UserService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDTO> signup(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
        return ResponseEntity.ok(userService.signup(userSignUpRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return ResponseEntity.ok(userService.login(userLoginRequestDTO));
    }

}
