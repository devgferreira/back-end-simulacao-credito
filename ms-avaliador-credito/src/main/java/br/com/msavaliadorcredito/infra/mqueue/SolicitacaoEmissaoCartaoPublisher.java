package br.com.msavaliadorcredito.infra.mqueue;

import br.com.msavaliadorcredito.domian.model.dados.DadosSolicitacaoEmissaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

    private final RabbitTemplate _rabbitTemplate;
    private final Queue _queueEmissaoCartoes;

    @Autowired
    public SolicitacaoEmissaoCartaoPublisher(RabbitTemplate rabbitTemplate, Queue queueEmissaoCartoes) {
        _rabbitTemplate = rabbitTemplate;
        _queueEmissaoCartoes = queueEmissaoCartoes;
    }

    public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        String json = convertIntoJson(dados);
        _rabbitTemplate.convertAndSend(_queueEmissaoCartoes.getActualName(), json);
    }

    private String convertIntoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);
    }
}
