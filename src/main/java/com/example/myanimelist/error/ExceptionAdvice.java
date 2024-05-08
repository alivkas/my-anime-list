package com.example.myanimelist.error;

import com.example.myanimelist.error.exception.TitleNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Slf4j
@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleRoleNotFound(RoleNotFoundException ex) {
        log.error("Role not found");
        return new ExceptionResponse(ex.getMessage(), "400");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUsernameNotFound(UsernameNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage(), "400");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(x -> new ExceptionResponse(x.getDefaultMessage(), x.getCode()))
                .toList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleTitleNotFound(TitleNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage(), ex.CODE);
    }
}
