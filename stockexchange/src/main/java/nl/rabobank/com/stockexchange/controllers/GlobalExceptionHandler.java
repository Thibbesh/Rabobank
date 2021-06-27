package nl.rabobank.com.stockexchange.controllers;

import nl.rabobank.com.stockexchange.payload.response.UnauthorizedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<UnauthorizedErrorResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request){
        UnauthorizedErrorResponse unauthorizedErrorResponse = new UnauthorizedErrorResponse();
        unauthorizedErrorResponse.setTimestamp(LocalDateTime.now());
        unauthorizedErrorResponse.setError(ex.getMessage());
        unauthorizedErrorResponse.setPath(request.getServletPath());
        unauthorizedErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());


        return new ResponseEntity<>(unauthorizedErrorResponse, HttpStatus.UNAUTHORIZED);
    }

}
