package com.serkanguner.service;

import com.serkanguner.dto.response.LoginResponseDto;
import com.serkanguner.entity.Auth;
import com.serkanguner.exception.AuthMicroServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.mapper.AuthMapper;
import com.serkanguner.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public Auth save(Auth auth) {
        if (authRepository.existsByUsername(auth.getUsername())) {
            throw new AuthMicroServiceException(ErrorType.USERNAME_ALREADY_TAKEN);
        }
        return authRepository.save(auth);
    }

    public Auth findOptionalByUsernameAndPassword(String username, String password) {
       return  authRepository.findOptionalByUsernameAndPassword(username,
                        password)
                .orElseThrow(() -> new AuthMicroServiceException(ErrorType.USERNAME_OR_PASSWORD_WRONG));
    }


}
