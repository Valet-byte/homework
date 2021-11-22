package com.example.homework.config;

import org.springframework.context.annotation.*;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

}
