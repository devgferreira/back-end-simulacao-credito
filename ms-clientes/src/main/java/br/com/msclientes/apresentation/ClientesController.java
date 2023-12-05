package br.com.msclientes.apresentation;

import br.com.msclientes.application.dtos.ClienteDTO;
import br.com.msclientes.application.interfaces.IClienteService;
import br.com.msclientes.domain.model.ClienteSaveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClientesController {

    private final IClienteService _clienteService;

    @Autowired
    public ClientesController(IClienteService clienteService) {
        _clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteSaveResponse> save(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO cliente = _clienteService.criarCliente(clienteDTO);
        ClienteSaveResponse clienteSaveResponse = new ClienteSaveResponse(cliente);
        return new ResponseEntity<>(clienteSaveResponse, HttpStatus.CREATED);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<ClienteDTO> dadosCliente(@RequestParam("cpf") String cpf) {
        ClienteDTO cliente = _clienteService.getByCPF(cpf);
        return ResponseEntity.ok(cliente);
    }
}
