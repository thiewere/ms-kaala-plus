package com.thifuge.kaala_plus.shared.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
}
