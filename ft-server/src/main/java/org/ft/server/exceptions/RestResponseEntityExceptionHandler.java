package org.ft.server.exceptions;

import org.ft.core.exceptions.FeatureToggleException;
import org.ft.core.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = { FeatureToggleException.class, Exception.class })
    protected ResponseEntity<Object> handleFeatureExceptions (RuntimeException ex,
                                                              WebRequest request)
    {
        return handleExceptionInternal(
            ex,
            new Response(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request);
    }
}