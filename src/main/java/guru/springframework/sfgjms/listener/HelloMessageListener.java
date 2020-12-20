package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * @author Olivier Cappelle
 * @version x.x.x
 * @see
 * @since x.x.x 19/12/2020
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class HelloMessageListener {
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {
        log.info("I got a message!!!");
        log.debug(helloWorldMessage.toString());
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RECEIVE_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                               @Headers MessageHeaders headers,
                               Message jmsMessage,
                               org.springframework.messaging.Message springMessage) throws JMSException {
        var payloadMsg = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("world!!")
                .build();

        log.info("Receiving hello");
        //same functionality using spring instead of jakarta library
//        jmsTemplate.convertAndSend((Destination) springMessage.getHeaders().get("jms_replyTo"), "got it!");
        jmsTemplate.convertAndSend(jmsMessage.getJMSReplyTo(), payloadMsg);
    }
}
