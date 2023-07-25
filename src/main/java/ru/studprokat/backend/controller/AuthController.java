package ru.studprokat.backend.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studprokat.backend.dto.user.AuthSuccessDto;
import ru.studprokat.backend.dto.ErrorMessageDto;
import ru.studprokat.backend.dto.user.UserLoginDto;

@Hidden
@RestController
@RequestMapping("auth")
public class AuthController {

    @GetMapping("success")
    public ResponseEntity<AuthSuccessDto> success(Authentication auth) {
        UserLoginDto user = (UserLoginDto) auth.getDetails();
        AuthSuccessDto body = new AuthSuccessDto(user.getId(), user.getPermissionLevel(), user.getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("failure")
    public ResponseEntity<ErrorMessageDto> failed(HttpServletRequest request) {
        AuthenticationException exception = (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessageDto);
    }

}
