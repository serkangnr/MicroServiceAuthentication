package com.serkanguner.movieapp.exception;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Bu sinif tum controller siniflari icin merkezi bir sekilde hata yonetimmi saglayacaktir.
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) // RuntimeException Hata yakalayici bir metod oldugunu belirtmek icin.
    public ResponseEntity<String> handleException(){
        return ResponseEntity.badRequest().body("Uygulamada Runtime Exception Olustu...........");
    }


    @ExceptionHandler(MovieAppException.class)
    public ResponseEntity<ErrorMessage> handleDemoException(MovieAppException movieAppException){
        ErrorType errorType = movieAppException.getErrorType();

        return new ResponseEntity(createErrorMessage(movieAppException),errorType.getHttpStatus());
    }

    private ErrorMessage createErrorMessage(MovieAppException movieAppException) {
       return ErrorMessage.builder()
               .code(movieAppException.getErrorType().getCode())
               .message(movieAppException.getMessage())
               .build();
    }



}
