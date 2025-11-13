package com.example.projetofinal.exception.handler;

import com.example.projetofinal.exception.BeanValidationException;
import com.example.projetofinal.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BeanValidationException.class)
    public ResponseEntity<RestErrorMessageDTO> handleBeanValidationException(BeanValidationException e) {
        return ResponseEntity.badRequest().body(new RestErrorMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<RestErrorMessageDTO> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorMessageDTO(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessageDTO> handleException(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        String errorMessage = error == null ? "Não foi possível validar a solicitação" : error.getDefaultMessage();

        return ResponseEntity.badRequest().body(new RestErrorMessageDTO(errorMessage));
    }
}
