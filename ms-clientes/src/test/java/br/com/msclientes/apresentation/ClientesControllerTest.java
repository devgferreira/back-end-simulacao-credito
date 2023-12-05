package br.com.msclientes.apresentation;

import br.com.msclientes.application.services.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static br.com.msclientes.common.ClienteConstatns.CLIENTEDTO_VALIDO;

@WebMvcTest(ClientesController.class)
class ClientesControllerTest {
    @Autowired
    private MockMvc _mockMvc;
    @Autowired
    private ObjectMapper _objectMapper;
    @MockBean
    private ClienteService _clienteService;
    @Test
    void save() throws Exception {
        when(_clienteService.criarCliente(CLIENTEDTO_VALIDO)).thenReturn(CLIENTEDTO_VALIDO);
        _mockMvc.perform(post("/clientes").content(_objectMapper.writeValueAsString(CLIENTEDTO_VALIDO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(jsonPath("$").value(CLIENTEDTO_VALIDO));
    }

    @Test
    void dadosCliente() throws Exception {
        when(_clienteService.getByCPF(CLIENTEDTO_VALIDO.getCpf())).thenReturn(CLIENTEDTO_VALIDO);

        _mockMvc.perform(get("/clientes?cpf=" + CLIENTEDTO_VALIDO.getCpf())).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CLIENTEDTO_VALIDO));
    }
}