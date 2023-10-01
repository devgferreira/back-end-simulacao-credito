package br.com.msavaliadorcredito.domian.model.cliente;

import lombok.Data;

@Data
public class DadosCliente {
    private Long id;
    private String nome;
    private Integer idade;
}
