package com.serkanguner.movieapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    USER_NOT_FOUND(5001,"Boyle bir USER bulunamadi. ", HttpStatus.NOT_FOUND),
    MOVIE_NOT_FOUND(5002,"Movie not found!",HttpStatus.NOT_FOUND),
    GENRE_NOT_FOUND(5003,"Genre not found!",HttpStatus.NOT_FOUND),
    WRONG_EMAIL_TYPE(5004,"Wrong email type!", HttpStatus.UNAUTHORIZED);

    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
