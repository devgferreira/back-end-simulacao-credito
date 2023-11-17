package br.com.msclientes.application.services;

import br.com.msclientes.application.dtos.ClienteDTO;
import static br.com.msclientes.common.ClienteConstatns.CLIENTEDTO_VALIDO;
import br.com.msclientes.domain.interfaces.IClienteRepository;
import br.com.msclientes.domain.model.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @InjectMocks
    private ClienteService _clienteService;
    @Spy
    private ModelMapper _mapper;
    @Mock
    private IClienteRepository _clienteRepository;

    @Test
    void criarCliente_ComOCpfNaoUsado_RetornandoCLienteDTO() {

        Cliente cliente = _mapper.map(CLIENTEDTO_VALIDO, Cliente.class);
        cliente.setId(1L);
        when(_clienteRepository.save(any())).thenReturn(cliente);

        ClienteDTO result = _clienteService.save(CLIENTEDTO_VALIDO);

        assertEquals(CLIENTEDTO_VALIDO.getCpf(), cliente.getCpf());
    }

    @Test
    void getByCPF() {
    }

    @Test
    void validator() {
    }
}