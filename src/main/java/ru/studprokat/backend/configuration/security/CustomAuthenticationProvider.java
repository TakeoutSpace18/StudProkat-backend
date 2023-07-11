package ru.studprokat.backend.configuration.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.studprokat.backend.dto.UserLoginDto;
import ru.studprokat.backend.exception.UserNotFoundException;
import ru.studprokat.backend.service.UsersService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final static Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private PasswordEncoder passwordEncoder;
    private UsersService usersService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = authentication.getName();
        UserLoginDto userLoginDto;
        try {
            userLoginDto = this.usersService.findLoginDataByEmail(userEmail);
        } catch (UserNotFoundException exception) {
            log.error("{} authentication rejected: user not found", userEmail);
            throw new BadCredentialsException("username not found");
        }

        if (!this.passwordEncoder.matches(authentication.getCredentials().toString(), userLoginDto.getPassword())) {
            log.error("{} authentication rejected: wrong password", userEmail);
            throw new BadCredentialsException("Wrong password");
        }

        UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(userLoginDto, authentication.getCredentials(), authentication.getAuthorities());
        result.setDetails(userLoginDto);
        log.info("User with email {} authenticated", userEmail);

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public CustomAuthenticationProvider setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    public CustomAuthenticationProvider setUsersService(UsersService usersService) {
        this.usersService = usersService;
        return this;
    }
}
