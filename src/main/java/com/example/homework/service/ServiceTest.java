package com.example.homework.service;

import com.example.homework.CSVhelpers.CSVhellper;
import com.example.homework.exception.FailedToFindFileWithTheRequiredLocalizationException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {

    private final CSVhellper helper;
    @Getter
    private String local;

    @Autowired
    @Lazy
    public ServiceTest(CSVhellper helper) {
        this.helper = helper;
    }

    public String setLocal(String local){
        try {
            helper.setLocal(local);
            this.local = local;
            return local;
        } catch (FailedToFindFileWithTheRequiredLocalizationException e){
            return "ERROR";
        }
    }

    public String getNextQuestions() {
        try {
            return helper.getNextQuestions();
        } catch (Exception ignore){
            return "ERROR";
        }

    }

    public void setAnswer(String next) {
        helper.setAnswer(next);
    }

    public void restart() {
        helper.restart();
    }

    public Integer getQuestionsCount() {
        return helper.getQuestionsCount();
    }

    public Integer getCorrectAnswerCount() {
        return helper.getCorrectAnswerCount();
    }

    public String getStatus() {
        if (helper.getCorrectAnswerCount() == 0 ||
            helper.getQuestionsCount() == 0 ||
            local == null) return "Error";
        else return "OK";
    }
}
