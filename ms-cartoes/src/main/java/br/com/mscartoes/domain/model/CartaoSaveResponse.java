package br.com.mscartoes.domain.model;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.domain.enums.BandeiraCartao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class CartaoSaveResponse {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;
    public CartaoSaveResponse(CartaoDTO cartaoDTO) {
        nome =cartaoDTO.getNome();
        bandeira = cartaoDTO.getBandeira();
        renda = cartaoDTO.getRenda();
        limiteBasico = cartaoDTO.getLimiteBasico();
    }
}
