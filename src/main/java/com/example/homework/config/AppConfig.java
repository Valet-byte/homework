package com.example.homework.config;

import com.example.homework.CSVhelpers.CSVhellper;
import com.example.homework.CSVhelpers.CSVhellperInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    @Bean
    CSVhellperInterface getCSVhellper(@Value("${questions.path}") String path,
                                      @Value("${questions.count}") Integer count,
                                      @Value("${localization}") String localization) {
        return new CSVhellper(path, count, localization);
    }

}
