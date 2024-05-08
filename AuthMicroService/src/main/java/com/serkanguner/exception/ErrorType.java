package com.serkanguner.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    USERNAME_ALREADY_TAKEN(1001,
            "Bu username daha önce kullanılmış. Yeniden deneyiniz.",
            HttpStatus.BAD_REQUEST),
    USERNAME_OR_PASSWORD_WRONG(1002,
            "Kullanıcı adı veya parola yanlış.",
            HttpStatus.BAD_REQUEST),
    PASSWORD_AND_REPASSWORD_NOT_EQUALS(1003,"Sifreler uyusmamaktadir. Tekrar deneyiniz!",HttpStatus.BAD_REQUEST);

    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
