package br.com.techtaste.microsservice.producer;

import br.com.techtaste.microsservice.dto.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("fila.mensagem.usuario")
    private String queue;

    public void  enviarEmail(EmailDto emailDto) {
        rabbitTemplate.convertAndSend(queue, emailDto);
        System.out.println("Email enviado com sucesso! -> " + emailDto );
    }
}
