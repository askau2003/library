package ek.alss.library.catalog.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ProblemDetail handleApiExceptions(ApiException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(exception.getStatus(),exception.getMessage());
        problemDetail.setTitle(exception.getCode());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleOther(Exception exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        problemDetail.setTitle("INTERNAL_SERVER_ERROR");

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidExceptions(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        problemDetail.setTitle("BAD_REQUEST");

        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }
        problemDetail.setProperty("error", errors);

        return problemDetail;
    }
}
