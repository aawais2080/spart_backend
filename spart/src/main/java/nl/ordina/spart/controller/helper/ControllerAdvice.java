package nl.ordina.spart.controller.helper;

import nl.ordina.spart.controller.exception.ResourceNotCreatedException;
import nl.ordina.spart.controller.exception.ResourceNotFoundException;
import nl.ordina.spart.controller.exception.ResourceNotUpdatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(ResourceNotCreatedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ErrorMessage resourceNotCreatedException(ResourceNotCreatedException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(ResourceNotUpdatedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ErrorMessage resourceNotUpdatedException(ResourceNotUpdatedException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
