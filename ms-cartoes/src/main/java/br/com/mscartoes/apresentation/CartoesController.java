package br.com.mscartoes.apresentation;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.application.dtos.ClienteCartaoDTO;
import br.com.mscartoes.application.dtos.response.CartaoSaveResponse;
import br.com.mscartoes.application.dtos.response.CartoesPorClienteResponse;
import br.com.mscartoes.application.interfaces.ICartaoService;
import br.com.mscartoes.application.interfaces.IClienteCartaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@Slf4j
public class CartoesController {

    private final ICartaoService _cartaoService;
    private final IClienteCartaoService _clienteCartaoService;

    @Autowired
    public CartoesController(ICartaoService cartaoService, IClienteCartaoService clienteCartaoService) {
        _cartaoService = cartaoService;
        _clienteCartaoService = clienteCartaoService;
    }
    @PostMapping
    public ResponseEntity<CartaoSaveResponse> cadastraCartao(@RequestBody CartaoDTO cartaoDTO) {
        CartaoDTO cartao = _cartaoService.criarCartao(cartaoDTO);
        CartaoSaveResponse cartaoSaveResponse = new CartaoSaveResponse(cartao);
        return new ResponseEntity<>(cartaoSaveResponse, HttpStatus.CREATED);
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<CartaoDTO>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<CartaoDTO> cartoes = _cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<ClienteCartaoDTO> clienteCartoes = _clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = clienteCartoes.stream().
                map(CartoesPorClienteResponse::fromModel).toList();
        return ResponseEntity.ok(resultList);
    }
}
