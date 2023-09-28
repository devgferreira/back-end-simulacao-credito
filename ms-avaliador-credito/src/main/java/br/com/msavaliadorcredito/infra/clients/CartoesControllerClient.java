package br.com.msavaliadorcredito.infra.clients;

import br.com.msavaliadorcredito.domian.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ms-cartoes", path = "/cartoes")
public interface CartoesControllerClient {
    @GetMapping(params = "cpf")
    List<CartaoCliente> getCartoesByCliente(@RequestParam("cpf") String cpf);
}
