package cz.muni.fi.legomanager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    final static Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = { InvalidRequestException.class })
    protected ResponseEntity<Object> invalidRequest(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
            ex,
            new ErrorResource("BadRequest", ex.getMessage()),
            new HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        );
    }

    @ResponseBody
    @ExceptionHandler(value = { ResourceAlreadyExistingException.class })
    protected ResponseEntity<Object> alreadyExists(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
            ex,
            new ErrorResource("ResourceAlreadyExists", ex.getMessage()),
            new HttpHeaders(),
            HttpStatus.UNPROCESSABLE_ENTITY,
            request
        );
    }

    @ResponseBody
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<Object> notFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResource("ResourceNotFound", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ResponseBody
    @ExceptionHandler(value = { ServerProblemException.class })
    protected ResponseEntity<Object> serverProblem(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResource("ServerProblem", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    @ResponseBody
    @ExceptionHandler(value = { JpaSystemException.class })
    protected ResponseEntity<Object> jpaException(JpaSystemException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResource(ex.getClass().getCanonicalName(), ex.getMostSpecificCause().getMessage()),
                new HttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                request
        );
    }

    @ResponseBody
    @ExceptionHandler(value = { FormException.class })
    protected ResponseEntity<Object> formValidation(FormException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new FormValidationResource(
                        "FormValidationError",
                        ex.getMessage(),
                        ex.getResult()
                ),
                new HttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                request
        );
    }

    @ResponseBody
    @ExceptionHandler
    protected ResponseEntity<Object> generalException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
            ex,
            new ErrorResource(ex.getClass().getCanonicalName(), ex.getMessage()),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request
        );
    }
}
