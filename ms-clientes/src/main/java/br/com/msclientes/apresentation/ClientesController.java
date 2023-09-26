package br.com.msclientes.apresentation;

import br.com.msclientes.application.dtos.ClienteDTO;
import br.com.msclientes.application.interfaces.IClienteService;
import br.com.msclientes.domain.model.ClienteSaveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private final IClienteService _clienteService;

    @Autowired
    public ClientesController(IClienteService clienteService) {
        _clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteSaveResponse> save(@RequestBody ClienteDTO clienteDTO ){
        ClienteDTO cliente = _clienteService.save(clienteDTO);
        ClienteSaveResponse clienteSaveResponse = new ClienteSaveResponse(cliente);
        return new ResponseEntity<>(clienteSaveResponse, HttpStatus.CREATED);
    }
}
