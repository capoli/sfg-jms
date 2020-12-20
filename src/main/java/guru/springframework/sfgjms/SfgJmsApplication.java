package guru.springframework.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;

@SpringBootApplication
@ImportAutoConfiguration(ArtemisAutoConfiguration.class)
public class SfgJmsApplication {

    public static void main(String[] args) throws Exception {
        //embedded jms broker configuration and start the server
        //not needed because we're connecting to a local activemq
//        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//                .setPersistenceEnabled(false)
//                .setJournalDirectory("target/data/journal")
//                .setSecurityEnabled(false)
//                .addAcceptorConfiguration("invm", "vm://0"));
//        server.start();

        SpringApplication.run(SfgJmsApplication.class, args);
    }

}
