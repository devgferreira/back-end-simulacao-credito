package br.com.msclientes.infra.exceptions.handler;


import br.com.msclientes.domain.enums.ErrorCodes;
import br.com.msclientes.infra.exceptions.ClienteJaExisteExeception;
import br.com.msclientes.infra.exceptions.ClienteNaoEncontradoExeception;
import br.com.msclientes.infra.exceptions.CpfInvalidoExeception;
import br.com.msclientes.infra.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ClienteNaoEncontradoExeception.class)
    public final ResponseEntity<Object> handleClienteNaoEncontradoExeception(ClienteNaoEncontradoExeception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CLIENTE_NAO_ENCONTRADO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(ClienteJaExisteExeception.class)
    public final ResponseEntity<Object> handleClienteJaExisteExeception(ClienteJaExisteExeception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CLIENTE_JA_EXISTE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(CpfInvalidoExeception.class)
    public final ResponseEntity<Object> handleCpfInvalidoExeception(CpfInvalidoExeception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CPF_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
