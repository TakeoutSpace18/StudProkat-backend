package ru.studprokat.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.studprokat.backend.dto.ValidationExceptionDto;
import ru.studprokat.backend.exception.*;

import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationExceptionDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ValidationExceptionDto> body = new ArrayList<ValidationExceptionDto>();
        for (FieldError error : fieldErrors) {
            body.add(new ValidationExceptionDto(error.getField(), error.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    private record ErrorMessage(String message) {}

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public  ResponseEntity<ErrorMessage> handleForbiddenException(ForbiddenException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public  ResponseEntity<ErrorMessage> handleForbiddenException(EmailAlreadyRegisteredException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(exception.getMessage()));
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }
    @ExceptionHandler(CouponAlreadyUsedException.class)
    public ResponseEntity<ErrorMessage> handleCouponAlreadyUsedException(ProductNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(exception.getMessage()));
    }
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorMessage> handleInternalServerErrorException(ProductNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(exception.getMessage()));
    }
    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCouponNotFoundException(ProductNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }
}