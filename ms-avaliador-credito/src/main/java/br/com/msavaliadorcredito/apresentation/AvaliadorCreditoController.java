package br.com.msavaliadorcredito.apresentation;

import br.com.msavaliadorcredito.application.interfaces.IAvaliadorCreditoService;
import br.com.msavaliadorcredito.domian.model.SituacaoCliente;
import br.com.msavaliadorcredito.infra.clients.exceptions.DadosClienteNotFoundException;
import br.com.msavaliadorcredito.infra.clients.exceptions.ErroComunicacaoMicroserviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final IAvaliadorCreditoService avaliadorCreditoService;
    @Autowired
    public AvaliadorCreditoController(IAvaliadorCreditoService avaliadorCreditoService) {
        this.avaliadorCreditoService = avaliadorCreditoService;
    }

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = null;
        try {
            situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

}
