package br.com.msclientes.apresentation;

import br.com.msclientes.application.dtos.ClienteDTO;
import br.com.msclientes.application.interfaces.IClienteService;
import br.com.msclientes.domain.model.ClienteSaveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClientesController {

    private final IClienteService _clienteService;

    @Autowired
    public ClientesController(IClienteService clienteService) {
        _clienteService = clienteService;
    }

    @GetMapping
    public String status(){
        log.info("Obetando o status do microservice de clientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<ClienteSaveResponse> save(@RequestBody ClienteDTO clienteDTO ){
        ClienteDTO cliente = _clienteService.save(clienteDTO);
        ClienteSaveResponse clienteSaveResponse = new ClienteSaveResponse(cliente);
        return new ResponseEntity<>(clienteSaveResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteDTO> findClienteByCpf(@PathVariable String cpf){
        ClienteDTO cliente = _clienteService.getByCPF(cpf);
        if(cliente.getCpf() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
