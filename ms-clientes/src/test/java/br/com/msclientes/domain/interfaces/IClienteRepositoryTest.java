package br.com.msclientes.domain.interfaces;

import br.com.msclientes.domain.model.Cliente;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static br.com.msclientes.common.ClienteConstatns.CLIENTEDTO_VALIDO;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class IClienteRepositoryTest {


    @Autowired
    private IClienteRepository _clienteRepository;
    @Spy
    private ModelMapper _mapper;
    @Test
    void criarCliente_ComOCpfNaoUsado_RetorandoCliente() {
        Cliente cliente = _mapper.map(CLIENTEDTO_VALIDO, Cliente.class);
        Cliente clienteSalvado = _clienteRepository.save(cliente);
        assertEquals(cliente, clienteSalvado);
    }
}