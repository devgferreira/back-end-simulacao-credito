package br.com.mscartoes.apresentation;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.application.interfaces.ICartaoService;
import br.com.mscartoes.domain.model.CartaoSaveResponse;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class CartoesController {

    private final ICartaoService _cartaoService;

    @Autowired
    public CartoesController(ICartaoService cartaoService) {
        _cartaoService = cartaoService;
    }

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<CartaoSaveResponse> cadastra(@RequestBody CartaoDTO cartaoDTO){
        CartaoDTO cartao = _cartaoService.save(cartaoDTO);
        CartaoSaveResponse cartaoSaveResponse = new CartaoSaveResponse(cartao);
        return new ResponseEntity<>(cartaoSaveResponse, HttpStatus.CREATED);
    }
    @GetMapping("/{renda}")
    public ResponseEntity<List<CartaoDTO>> getCartoesRendaAte(@PathVariable("renda") Long renda){
        List<CartaoDTO> cartoes = _cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }
}
