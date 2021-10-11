package com.example.homework.config;

import org.springframework.context.annotation.*;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    /*@Bean  старый варик
    CSVhellperInterface getCSVhellper(@Value("${questions.path}") String path,
                                      @Value("${questions.count}") Integer count,
                                      @Value("${localization}") String localization) {
        return new CSVhellper(path, count, localization);
    }*/

}
