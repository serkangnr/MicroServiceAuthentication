package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.LoginRequestDto;
import com.serkanguner.dto.request.RegisterRequestDto;
import com.serkanguner.dto.response.LoginResponseDto;
import com.serkanguner.dto.response.RegisterResponseDto;
import com.serkanguner.entity.Auth;
import com.serkanguner.exception.AuthMicroServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.mapper.AuthMapper;
import com.serkanguner.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(EndPoints.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Register İşlemleri:
     */
    @PostMapping(EndPoints.REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String repassword = dto.getRepassword();

        if (password.equals(repassword)){
            Auth auth = authService.save(Auth.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                            .state(true)
                            .createAt(LocalDateTime.now())
                    .build());
            return ResponseEntity.ok(AuthMapper.INSTANCE.authToDto(auth));
        }else
            throw new AuthMicroServiceException(ErrorType.PASSWORD_AND_REPASSWORD_NOT_EQUALS);
    }

    /**
     * Login İşlemleri
     */
    @PostMapping(EndPoints.LOGIN)
    public ResponseEntity<LoginResponseDto> dologin(@RequestBody LoginRequestDto dto) {
        Auth auth = authService.findOptionalByUsernameAndPassword(dto.getUsername(),
                dto.getPassword());
        return ResponseEntity.ok(AuthMapper.INSTANCE.loginToDto(auth));
    }
}
