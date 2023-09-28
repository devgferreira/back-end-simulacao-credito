package br.com.msavaliadorcredito.application.service;

import br.com.msavaliadorcredito.application.interfaces.IAvaliadorCreditoService;
import br.com.msavaliadorcredito.domian.model.CartaoCliente;
import br.com.msavaliadorcredito.domian.model.DadosCliente;
import br.com.msavaliadorcredito.domian.model.SituacaoCliente;
import br.com.msavaliadorcredito.infra.clients.CartoesControllerClient;
import br.com.msavaliadorcredito.infra.clients.ClienteControllerClient;
import br.com.msavaliadorcredito.infra.clients.exceptions.DadosClienteNotFoundException;
import br.com.msavaliadorcredito.infra.clients.exceptions.ErroComunicacaoMicroserviceException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliadorCreditoService implements IAvaliadorCreditoService {
    private final ClienteControllerClient _clienteControllerClient;
    private final CartoesControllerClient _cartoesControllerClient;
    @Autowired
    public AvaliadorCreditoService(ClienteControllerClient clienteControllerClient, CartoesControllerClient cartoesControllerClient) {
        _clienteControllerClient = clienteControllerClient;
        _cartoesControllerClient = cartoesControllerClient;
    }

    @Override
    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try {
            DadosCliente dadosCliente = _clienteControllerClient.dadosCliente(cpf);
            List<CartaoCliente> cartoesCliente = _cartoesControllerClient.getCartoesByCliente(cpf);

            return SituacaoCliente.builder()
                    .cliente(dadosCliente)
                    .cartoes(cartoesCliente)
                    .build();
        }catch (FeignException.FeignClientException ex){
           int status = ex.status();
           if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
           }
           throw new ErroComunicacaoMicroserviceException(ex.getMessage(), status);
        }
    }
}

