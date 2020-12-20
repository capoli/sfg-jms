package guru.springframework.sfgjms.config;

import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author Olivier Cappelle
 * @version x.x.x
 * @see
 * @since x.x.x 19/12/2020
 **/
//@EnableJms
@Configuration
@ConfigurationProperties(prefix = "spring.activemq")
public class JmsConfig {
    public static final String MY_QUEUE = "my-hello-world";
    public static final String MY_SEND_RECEIVE_QUEUE = "replybacktome";

    private String user;
    private String password;
    private String brokerUrl;

    //used for receiving and sending messages from/to jms from spring app, convert to jms message and from jms message
    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT); //other option BYTE
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        ArtemisAutoConfiguration
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//        connectionFactory.setUserName(user);
//        connectionFactory.setPassword(password);
//        connectionFactory.setBrokerURL(brokerUrl);
//        return connectionFactory;
//    }
}
