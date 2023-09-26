package br.com.msclientes.application.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;
}
