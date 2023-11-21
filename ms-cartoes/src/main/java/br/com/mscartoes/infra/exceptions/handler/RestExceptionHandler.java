package br.com.mscartoes.infra.exceptions.handler;

import br.com.mscartoes.domain.enums.ErrorCodes;
import br.com.mscartoes.infra.exceptions.CartaoNaoEncontradoExeception;
import br.com.mscartoes.infra.exceptions.CartoesNaoEncontradosExeception;
import br.com.mscartoes.infra.exceptions.ClienteNaoEncontradoExeception;
import br.com.mscartoes.infra.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CartaoNaoEncontradoExeception.class)
    public final ResponseEntity<Object> handleFuncionarioNaoEncontradoExeception(CartaoNaoEncontradoExeception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CARTAO_NAO_ENCONTRADO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(ClienteNaoEncontradoExeception.class)
    public final ResponseEntity<Object> handleClienteNaoEncontradoExeception(ClienteNaoEncontradoExeception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CLIENTE_NAO_ENCONTRADO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(CartoesNaoEncontradosExeception.class)
    public final ResponseEntity<Object> handleCartoesNaoEncontradosExeception(CartoesNaoEncontradosExeception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CARTOES_NAO_ENCONTRADOS, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

}
