package br.com.mscartoes.infra.mqueue;

import br.com.mscartoes.domain.interfaces.ICartaoRepository;
import br.com.mscartoes.domain.interfaces.IClienteCartaoRepository;
import br.com.mscartoes.domain.model.Cartao;
import br.com.mscartoes.domain.model.ClienteCartao;
import br.com.mscartoes.domain.model.DadosSolicitacaoEmissaoCartao;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmissaoCartaoSubscriber {
    private final ICartaoRepository _cartaoRepository;
    private final IClienteCartaoRepository _clienteCartaoRepository;

    @Autowired
    public EmissaoCartaoSubscriber(ICartaoRepository cartaoRepository, IClienteCartaoRepository clienteCartaoRepository) {
        _cartaoRepository = cartaoRepository;
        _clienteCartaoRepository = clienteCartaoRepository;
    }

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = _cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            _clienteCartaoRepository.save(clienteCartao);

        } catch (Exception e) {
            log.error("Erro ao receber solicitacao de emissao de cartao: {}", e.getMessage());
        }
    }

}
