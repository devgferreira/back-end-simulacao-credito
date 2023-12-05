package br.com.msclientes.infra.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String CLIENTE_NAO_ENCONTRADO = "[MS-CLIENTE] Cliente não foi encontrado";
    public static final String CLIENTEO_JA_EXISTE = "[MS-CLIENTE] Cliente já existe";
    public static final String CPF_INVALIDO = "[MS-CLIENTE] CPF invalido, o cpf precisa conter 11 caracters";
}
