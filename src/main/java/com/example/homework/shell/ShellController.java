package com.example.homework.shell;

import com.example.homework.service.ServiceTest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;
import java.util.Scanner;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {

    private final ServiceTest service;

    @Autowired
    @Lazy
    private MessageSource source;


    @ShellMethod(value = "start test", key = {"start_test", "start", "test"})
    @ShellMethodAvailability(value = "isLocalNotNull")
    public String startTest(){
        service.restart();
        String questions = service.getNextQuestions();
        Scanner scanner = new Scanner(System.in);
        if (questions == null){
            return source.getMessage("passed_test", null, Locale.forLanguageTag(service.getLocal()));
        }

        if (questions.equals("ERROR")) return source.getMessage("error", null, Locale.forLanguageTag(service.getLocal()));
        System.out.println();

        while (questions != null && !questions.equals("ERROR")){
            System.out.print(questions);
            service.setAnswer(scanner.next());
            questions = service.getNextQuestions();
            System.out.println();
        }

        return source.getMessage("finish_test", null, Locale.forLanguageTag(service.getLocal()));
    }

    @ShellMethod(value = "restart test", key = {"restart", "rStart", "restart_test"})
    public String restart(){
        service.restart();
        return startTest();
    }
    @ShellMethodAvailability(value = "isDaoOK")
    @ShellMethod(value = "get result", key = {"getResult", "result", "res"})
    public String getResult(){
        return source.getMessage("questions_сount", null, Locale.forLanguageTag(service.getLocal())) + service.getQuestionsCount() + "\n"
                + source.getMessage("correct_answer_count" , null, Locale.forLanguageTag(service.getLocal())) + service.getCorrectAnswerCount();
    }

    @ShellMethod(value = "set local", key = "set_local")
    public String setLocal(@ShellOption(defaultValue = "RU") String local){
        return service.setLocal(local).equals(local) ? source.getMessage("messages", null, Locale.forLanguageTag(local))
                : source.getMessage("file_not_found", null, Locale.forLanguageTag("en"));
    }

    public Availability isLocalNotNull(){
        return service.getLocal() == null ? Availability.unavailable("Pleas set local : set_local") : Availability.available();
    }

    public Availability isDaoOK(){
        return service.getStatus().equals("OK") ? Availability.available() : Availability.unavailable("Dao error");
    }

}
