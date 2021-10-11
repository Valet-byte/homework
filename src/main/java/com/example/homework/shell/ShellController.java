package com.example.homework.shell;

import com.example.homework.CSVhelpers.CSVhellper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Scanner;

@ShellComponent
public class ShellController {

    CSVhellper csVhellper;

    public ShellController(CSVhellper csVhellper) {
        this.csVhellper = csVhellper;
    }

    @ShellMethod(value = "start test", key = {"start_test", "start", "test"})
    public String startTest(){
        String questions = csVhellper.getNextQuestions();
        Scanner scanner = new Scanner(System.in);
        if (questions == null){
            return "You have already passed test";
        }
        System.out.println();

        while (questions != null){
            System.out.print(questions);
            csVhellper.setAnswer(scanner.next());
            questions = csVhellper.getNextQuestions();
            System.out.println();
        }

        return "test finish!";
    }

    @ShellMethod(value = "restart test", key = {"restart", "rStart", "restart_test"})
    public String restart(){
        csVhellper.restart();
        return startTest();
    }

    @ShellMethod(value = "get result", key = {"getResult", "result", "res"})
    public String getResult(){
        return csVhellper.getResult();
    }

}
