package kz.project.solva.rest.advice;

import jakarta.persistence.EntityNotFoundException;
import kz.project.solva.exception.InvalidTaskCompletedDateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionAdvice extends ResponseEntityExceptionHandler {
  @ExceptionHandler(InvalidTaskCompletedDateException.class)
  public ResponseEntity<Object> handleInvalidTaskCompletedDate(InvalidTaskCompletedDateException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGlobalException(Exception ex,WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
