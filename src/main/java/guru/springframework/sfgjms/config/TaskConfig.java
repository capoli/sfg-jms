package guru.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Olivier Cappelle
 * @version x.x.x
 * @see
 * @since x.x.x 19/12/2020
 **/
@EnableScheduling
@EnableAsync
@Configuration
public class TaskConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
