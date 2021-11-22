package com.example.homework.events;

import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    @EventListener
    @SneakyThrows
    public void listener(MyEvent event){
        System.out.println(event.getMessege());
    }

}
