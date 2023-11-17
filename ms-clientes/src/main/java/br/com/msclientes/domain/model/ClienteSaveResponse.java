package br.com.msclientes.domain.model;

import br.com.msclientes.application.dtos.ClienteDTO;
import lombok.Data;

@Data
public class ClienteSaveResponse {
    private String cpf;
    private String nome;
    private Integer idade;

    public ClienteSaveResponse(ClienteDTO clienteDTO){
        cpf = clienteDTO.getCpf();
        nome = clienteDTO.getNome();
        idade = clienteDTO.getIdade();
    }
}
