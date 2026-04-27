package com.michel.pedidos.api.exceptionhandler;

import com.michel.pedidos.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ProblemDetail> handleNegocio(NegocioException e) {

        var status = HttpStatus.BAD_REQUEST;

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                e.getMessage()
        );

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException e) {
        var status = HttpStatus.BAD_REQUEST;

        String detail = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Erro de validação");

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);

        return ResponseEntity.status(status).body(problem);
    }


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ProblemDetail> handleResponseStatus(ResponseStatusException e) {

        var status = e.getStatusCode();

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                e.getReason() != null ? e.getReason() : "Erro inesperado"
        );

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrityViolation(
            org.springframework.dao.DataIntegrityViolationException e,
            WebRequest request) {

        var status = HttpStatus.CONFLICT;

        String message = "Erro de integridade no banco de dados";

        if (e.getMessage().contains("usuario.email")) {
            message = "Já existe um usuário com este email";
        }

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, message);

        problem.setProperty("path", request.getDescription(false));

        return ResponseEntity.status(status).body(problem);
    }
}