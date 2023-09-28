package br.com.msavaliadorcredito.application.service;

import br.com.msavaliadorcredito.application.interfaces.IAvaliadorCreditoService;
import br.com.msavaliadorcredito.domian.model.dados.DadosSolicitacaoEmissaoCartao;
import br.com.msavaliadorcredito.domian.model.dados.RetornoAvaliacaoCliente;
import br.com.msavaliadorcredito.domian.model.cartao.Cartao;
import br.com.msavaliadorcredito.domian.model.cartao.CartaoAprovado;
import br.com.msavaliadorcredito.domian.model.cartao.CartaoCliente;
import br.com.msavaliadorcredito.domian.model.cliente.DadosCliente;
import br.com.msavaliadorcredito.domian.model.cliente.SituacaoCliente;
import br.com.msavaliadorcredito.domian.model.protocolo.ProtocoloSolicitacaoCartao;
import br.com.msavaliadorcredito.infra.clients.CartoesControllerClient;
import br.com.msavaliadorcredito.infra.clients.ClienteControllerClient;
import br.com.msavaliadorcredito.infra.exceptions.DadosClienteNotFoundException;
import br.com.msavaliadorcredito.infra.exceptions.ErroComunicacaoMicroserviceException;
import br.com.msavaliadorcredito.infra.exceptions.ErroSolicitacaoCartaoException;
import br.com.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService implements IAvaliadorCreditoService {
    private final ClienteControllerClient _clienteControllerClient;
    private final CartoesControllerClient _cartoesControllerClient;
    private final SolicitacaoEmissaoCartaoPublisher _solicitacaoEmissaoCartaoPublisher;
    @Autowired
    public AvaliadorCreditoService(ClienteControllerClient clienteControllerClient, CartoesControllerClient cartoesControllerClient, SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher) {
        _clienteControllerClient = clienteControllerClient;
        _cartoesControllerClient = cartoesControllerClient;
        _solicitacaoEmissaoCartaoPublisher = solicitacaoEmissaoCartaoPublisher;
    }

    @Override
    public RetornoAvaliacaoCliente realizarAvalicao(String cpf, Long renda)
            throws ErroComunicacaoMicroserviceException, DadosClienteNotFoundException {
        try {
            DadosCliente dadosCliente = _clienteControllerClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = _cartoesControllerClient.getCartoesRendaAte(renda);
            List<Cartao> cartoes = cartoesResponse.getBody();
            List<CartaoAprovado> listaCartoesAprovados = cartoes.stream().map(cartao -> {

                BigDecimal limiteLiberado = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteLiberado);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        }catch (FeignException.FeignClientException ex){
            int status = ex.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(ex.getMessage(), status);
        }
    }

    @Override
    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try {
            DadosCliente dadosCliente = _clienteControllerClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesCliente = _cartoesControllerClient.getCartoesByCliente(cpf);

            return SituacaoCliente.builder()
                    .cliente(dadosCliente)
                    .cartoes(cartoesCliente.getBody())
                    .build();
        }catch (FeignException.FeignClientException ex){
           int status = ex.status();
           if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
           }
           throw new ErroComunicacaoMicroserviceException(ex.getMessage(), status);
        }
    }

    @Override
    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) throws ErroSolicitacaoCartaoException {
        try {
            _solicitacaoEmissaoCartaoPublisher.solicitarCartao(dados);
            String protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception e){
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}

