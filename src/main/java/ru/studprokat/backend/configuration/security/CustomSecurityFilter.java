package ru.studprokat.backend.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.studprokat.backend.controller.ErrorControllerAdvice;

import java.io.IOException;
import java.util.Map;

public class CustomSecurityFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AuthenticationSuccessHandler authenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler("/auth/success");
    private final AuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler("/auth/failure");
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private RequestMatcher requestMatcher;
    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (requestWithoutAuthentification(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Authentication authentication = obtainBody(request);
            authentication = this.authenticationManager.authenticate(authentication);
            successfulAuthentication(request, response, filterChain, authentication);
        } catch (AuthenticationException e) {
            unsuccessfulAuthentication(request, response, e);
        }
    }

    private void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws ServletException, IOException {
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        this.securityContextHolderStrategy.setContext(context);
        this.securityContextRepository.saveContext(context, request, response);

        this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }

    private void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        SecurityContext context = this.securityContextHolderStrategy.getContext();
        this.securityContextHolderStrategy.clearContext();
        context.setAuthentication(null);
        this.authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
    }

    private Authentication obtainBody(HttpServletRequest request) {
        try {
            Map<String, String> map = this.objectMapper.readValue(request.getInputStream(), Map.class);
            String email = map.get("email");
            String password = map.get("password");
            if (email.isBlank()) {
                throw new BadCredentialsException("email is empty");
            }
            if (password.isEmpty()) {
                throw new BadCredentialsException("password is empty");
            }
            return UsernamePasswordAuthenticationToken.unauthenticated(email, password);

        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    public CustomSecurityFilter setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
        return this;
    }

    public CustomSecurityFilter setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        return this;
    }

    public CustomSecurityFilter setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
        return this;
    }

    private boolean requestWithoutAuthentification(HttpServletRequest request) {
        return !requestMatcher.matches(request);
    }
}
