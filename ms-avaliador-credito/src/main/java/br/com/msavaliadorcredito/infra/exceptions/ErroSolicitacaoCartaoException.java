package br.com.msavaliadorcredito.infra.exceptions;

public class ErroSolicitacaoCartaoException extends Exception{
    public ErroSolicitacaoCartaoException(String message){
        super(message);
    }
}
