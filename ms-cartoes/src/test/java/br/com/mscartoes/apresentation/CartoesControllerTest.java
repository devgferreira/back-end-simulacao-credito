package br.com.mscartoes.apresentation;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.application.dtos.ClienteCartaoDTO;
import br.com.mscartoes.application.services.CartaoService;
import br.com.mscartoes.application.services.ClienteCartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.mscartoes.common.CartaoDTOConstants.CARTAO_DTO_VALIDO;
import static br.com.mscartoes.common.CartaoDTOConstants.CARTAO_DTO_RENDA_VALIDO;
import static br.com.mscartoes.common.ClienteCartaoDTOConstants.CLIENTE_CARTAO_DTO;

@WebMvcTest(CartoesController.class)
class CartoesControllerTest {
    @Autowired
    private MockMvc _mockMvc;
    @Autowired
    private ObjectMapper _objectMapper;
    @MockBean
    private CartaoService _cartaoService;
    @MockBean
    private ClienteCartaoService _clienteCartaoService;
    @Test
    void cadastraCartao() throws Exception {

        when(_cartaoService.criarCartao(CARTAO_DTO_VALIDO)).thenReturn(CARTAO_DTO_VALIDO);
        _mockMvc.perform(post("/cartoes").content(_objectMapper.writeValueAsString(CARTAO_DTO_VALIDO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(jsonPath("$").value(CARTAO_DTO_VALIDO));

    }
    @Test
    void getCartoesByCliente() throws Exception {
        List<ClienteCartaoDTO> clienteCartaoDTOS = new ArrayList<>();

        when(_clienteCartaoService.listCartoesByCpf(CLIENTE_CARTAO_DTO.getCpf())).thenReturn(clienteCartaoDTOS);

        _mockMvc.perform(get("/cartoes?cpf=" + CLIENTE_CARTAO_DTO.getCpf())).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(clienteCartaoDTOS));

    }

    @Test
    void getCartoesRendaAte() throws Exception {
        List<CartaoDTO> cartaoDTOS = new ArrayList<>();

        when(_cartaoService.getCartoesRendaMenorIgual(any(Long.class))).thenReturn(cartaoDTOS);

        _mockMvc.perform(get("/cartoes?renda=" + CARTAO_DTO_RENDA_VALIDO.getRenda())).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(cartaoDTOS));
    }

}

