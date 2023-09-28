package br.com.msavaliadorcredito.infra.clients;

import br.com.msavaliadorcredito.domian.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ms-clientes", path = "/clientes")
public interface ClienteControllerClient {
    @GetMapping(params = "cpf")
    DadosCliente dadosCliente(@RequestParam("cpf") String cpf);
}
