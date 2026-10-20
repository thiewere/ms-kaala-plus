package com.thifuge.kaala_plus.shared.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvice {

    /**
     * Global Not_Found exceptions handler for the entire application
     * @param exception the catched exception
     * @return an object of ErrorEntity customised Record
     */
    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorEntity entityNotFoundException(EntityNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorEntity(
                LocalDateTime.now(),
                NOT_FOUND.value(),
                null,
                exception.getMessage()
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorEntity> responseStatusException(ResponseStatusException exception) {
        log.warn("Business error: {}", exception.getReason());

        HttpStatusCode status = exception.getStatusCode();

        ErrorEntity errorEntity = new ErrorEntity(
                LocalDateTime.now(),
                status.value(),
                null,
                exception.getReason()
        );
        return new ResponseEntity<>(errorEntity, status);
    }
}
