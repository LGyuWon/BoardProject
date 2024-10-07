package portfolio.boradproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import portfolio.boradproject.DTO.ResponseDTO;
import portfolio.boradproject.DTO.UserLoginRequestDTO;
import portfolio.boradproject.DTO.UserSignUpRequestDTO;
import portfolio.boradproject.config.jwt.JwtUtil;
import portfolio.boradproject.entity.UserEntity;
import portfolio.boradproject.exception.CustomException;
import portfolio.boradproject.exception.ErrorCode;
import portfolio.boradproject.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseDTO signup(UserSignUpRequestDTO userSignUpRequestDTO) {
        Optional<UserEntity> existUser = userRepository.findByUserId(userSignUpRequestDTO.getUserId());
        if(existUser.isPresent()) {
            throw new CustomException(ErrorCode.EXIST_USER);
        }
        String encodedPassword = passwordEncoder.encode(userSignUpRequestDTO.getPassword());
        UserEntity userEntity = new UserEntity(userSignUpRequestDTO, encodedPassword);
        userRepository.save(userEntity);

        return ResponseDTO.builder()
                .message("회원가입이 완료되었습니다.")
                .build();
    }

    public ResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        UserEntity userEntity = userRepository.findByUserId(userLoginRequestDTO.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_USER));

        if(!passwordEncoder.matches(userLoginRequestDTO.getPassword(), userEntity.getPassword())) {
            throw new CustomException(ErrorCode.NOT_EQUALS_PASSWORD);
        }

        return ResponseDTO.builder()
                .message("로그인이 완료되었습니다.")
                .body(jwtUtil.createToken(userEntity.getUserId()))
                .build();
    }
}
