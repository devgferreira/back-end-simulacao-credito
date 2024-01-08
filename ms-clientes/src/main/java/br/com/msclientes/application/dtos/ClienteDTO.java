package br.com.msclientes.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;

    public ClienteDTO(String cpf) {
        this.cpf = cpf;
    }
}
