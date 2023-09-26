package br.com.mscartoes.application.dtos;

import br.com.mscartoes.domain.enums.BandeiraCartao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class CartaoDTO {
    private Long id;
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;
}
