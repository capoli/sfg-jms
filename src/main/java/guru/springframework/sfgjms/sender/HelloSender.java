package guru.springframework.sfgjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 * @author Olivier Cappelle
 * @version x.x.x
 * @see
 * @since x.x.x 19/12/2020
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class HelloSender {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.info("I'm sending a message");
        var message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello world!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
        log.info("Message sent!");
    }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        var message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello world!")
                .build();

        //creates temp queue to be able to receive response, is synchronous
        Message receivedMessage = requireNonNull(jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RECEIVE_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                try {
                    Message helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", HelloWorldMessage.class.getName());

                    log.info("Sending Hello");
                    return helloMessage;
                } catch (JsonProcessingException e) {
                    throw new JMSException("boom");
                }
            }
        }), "Received message can't be null");
        log.info("Receiving world!! back");
        log.debug(receivedMessage.getBody(String.class));
    }
}
