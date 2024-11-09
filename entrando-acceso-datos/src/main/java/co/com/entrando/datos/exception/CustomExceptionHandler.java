package co.com.entrando.datos.exception;

import co.com.entrando.dto.responses.GenericResponse;
import co.com.entrando.exception.BusinessException;
import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
    // Manejo de excepciones
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handlerBusinessException(BusinessException ex, WebRequest request) {
        return ResponseEntity.internalServerError().body(GenericResponse.builder()
                .code(500L)
                .message(ex.getMessage())
                .type(TYPE_EXCEPTION.ERROR)
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handlerBusinessException(DataIntegrityViolationException ex, WebRequest request) {
        return ResponseEntity.internalServerError().body(GenericResponse.builder()
                .code(500L)
                .message(ex.getMessage())
                .type(TYPE_EXCEPTION.SQLERROR)
                .build());
    }
}
