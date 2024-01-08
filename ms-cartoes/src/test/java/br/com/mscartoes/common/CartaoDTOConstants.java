package br.com.mscartoes.common;

import br.com.mscartoes.application.dtos.CartaoDTO;

import java.math.BigDecimal;

public class CartaoDTOConstants {

    public static final CartaoDTO CARTAO_DTO_VALIDO = new CartaoDTO("Cartão");
    public static final CartaoDTO CARTAO_DTO_RENDA_VALIDO = new CartaoDTO(BigDecimal.valueOf(1000));
}
