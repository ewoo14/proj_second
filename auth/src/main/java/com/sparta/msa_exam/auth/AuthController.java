package com.sparta.msa_exam.auth;

import com.sparta.msa_exam.auth.user.User;
import com.sparta.msa_exam.auth.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 사용자 ID를 받아 JWT 액세스 토큰을 생성하여 응답합니다.
     *
     * @param userDto 사용자 ID
     * @return JWT 액세스 토큰을 포함한 AuthResponse 객체를 반환합니다.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        User user = authService.registerUser(userDto);
        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) {
        String sessionId = authService.authenticate(userDto.username(), userDto.password());
        if (sessionId != null) {
            return ResponseEntity.ok(new AuthResponse(sessionId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * JWT 액세스 토큰을 포함하는 응답 객체입니다.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String access_token;
    }
}