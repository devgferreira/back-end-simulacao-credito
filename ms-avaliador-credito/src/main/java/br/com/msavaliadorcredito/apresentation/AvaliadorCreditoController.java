package br.com.msavaliadorcredito.apresentation;

import br.com.msavaliadorcredito.application.interfaces.IAvaliadorCreditoService;
import br.com.msavaliadorcredito.domian.model.DadosAvaliacao;
import br.com.msavaliadorcredito.domian.model.RetornoAvaliacaoCliente;
import br.com.msavaliadorcredito.domian.model.SituacaoCliente;
import br.com.msavaliadorcredito.infra.clients.exceptions.DadosClienteNotFoundException;
import br.com.msavaliadorcredito.infra.clients.exceptions.ErroComunicacaoMicroserviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final IAvaliadorCreditoService _avaliadorCreditoService;
    @Autowired
    public AvaliadorCreditoController(IAvaliadorCreditoService avaliadorCreditoService) {
      _avaliadorCreditoService = avaliadorCreditoService;
    }

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = _avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = _avaliadorCreditoService.
                    realizarAvalicao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

}
