package dev.task_manager.web.controller;

import dev.task_manager.entity.exception.AccessDeniedException;
import dev.task_manager.entity.exception.ExceptionBody;
import dev.task_manager.entity.exception.ResourceMappingException;
import dev.task_manager.entity.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handlerResourceNotFound(ResourceNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }


    @ExceptionHandler(ResourceMappingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handlerResourceMapping(ResourceNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }


    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handlerIllegalState(IllegalStateException e) {
        return new ExceptionBody(e.getMessage());
    }


    @ExceptionHandler({AccessDeniedException.class, java.nio.file.AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionBody handlerAccessDenied() {
        return new ExceptionBody("Access denied");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ExceptionBody exceptionBody = new ExceptionBody("Validation failed");
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        exceptionBody.setErrors(errors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
        return exceptionBody;
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handlerConstraintDeclaration(ConstraintViolationException e) {
        ExceptionBody exceptionBody = new ExceptionBody("Validation failed");
        exceptionBody.setErrors(e.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage()
                )));

        return exceptionBody;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handlerException(Exception e){
        return new ExceptionBody(e.getMessage());
    }







}
