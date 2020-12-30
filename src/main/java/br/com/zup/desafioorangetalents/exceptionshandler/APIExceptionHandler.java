package br.com.zup.desafioorangetalents.exceptionshandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
        WebRequest request) {

        var fieldName = handleFieldName(ex.getMostSpecificCause().getMessage());
        var message = formatExceptionMessage(ex.getMostSpecificCause().getMessage());
        List<Field> fields = new ArrayList<>();
        fields.add(new Field(fieldName, message));

        var status = HttpStatus.CONFLICT;
        var title = "One or more fields are invalid!";
        var exceptionMessage = new ExceptionMessage(status.value(), OffsetDateTime.now(), title, fields);

        return super.handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        var message = "Invalid body: " + ex.getCause().getMessage();
        var exceptionMessage = new ExceptionMessage(status.value(), OffsetDateTime.now(), message, null);

        return super.handleExceptionInternal(ex, exceptionMessage, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Field> fields = new ArrayList<>();
        for (ObjectError error : ex.getAllErrors()) {

            var message = error.getDefaultMessage();
            var name = ((FieldError) error).getField();

            fields.add(new Field(name, message));
        }

        var title = "One or more fields are invalid!";
        var exceptionMessage = new ExceptionMessage(status.value(), OffsetDateTime.now(), title, fields);

        return super.handleExceptionInternal(ex, exceptionMessage, headers, status, request);
    }

    private String handleFieldName(String message) {

        String fieldName = "Field name not specified";
        if (message.contains("cpf")) {
            fieldName = "cpf";
        } else if (message.contains("email")) {
            fieldName = "email";
        }
        return fieldName;
    }

    private String formatExceptionMessage(String message) {

        int endIndex = message.indexOf("for key");
        if (endIndex > 0) {
            return message.substring(0, endIndex - 1);
        }

        return message;
    }
}
