package com.example.homework.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    @Getter
    private final String messege;

    public MyEvent(Object source, String messege) {
        super(source);
        this.messege = messege;
    }
}
