package com.example.homework.CSVhelpers;

import com.example.homework.exception.FailedToFindFileWithTheRequiredLocalizationException;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

@Repository
@PropertySource("classpath:application.yml")
public class CSVhellper implements CSVhellperInterface{

    private final List<QA> questionsAnswerList; // хранит вопросы и ответы
    @Getter
    private int questionsCount = 0; // количество вопросов
    private int questionsNumber = 0; // номер вопроса, который возвращается студенту
    @Getter
    private int correctAnswerCount = 0; // количество правильн6ых ответов
    private final Environment environment;

    public CSVhellper(@Autowired Environment environment) {
        this.environment = environment;
        questionsAnswerList = new ArrayList<>();
    }

    @Override
    public String getNextQuestions() { //Возвращает следующий вопрос
        if (questionsNumber < questionsCount ){
            return questionsAnswerList.get(questionsNumber).QUESTIONS();
        }
        else return null;
    }

    @Override
    public void setAnswer(String answer) { // проверяет вопрос
        if(questionsAnswerList.get(questionsNumber).ANSWER().equals(answer))  correctAnswerCount++;
        questionsNumber++;
    }

    @Override
    public void restart() {
        correctAnswerCount = 0;
        questionsNumber = 0;
    }

    @SneakyThrows
    private void init(String local){
        try {
            questionsAnswerList.clear();
            restart();
            File file = ResourceUtils.getFile(Objects.requireNonNull(environment.getProperty("questions.path." + local)));
            FileInputStream stream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String csvLine;
            while ((csvLine = reader.readLine()) != null){
                String[] employ = csvLine.split(",");
                questionsAnswerList.add(new QA(employ[0], employ[1]));
                questionsCount++;
            }
        } catch (Exception e){
            throw new FailedToFindFileWithTheRequiredLocalizationException();
        }

    }

    public void setLocal(String local) throws FailedToFindFileWithTheRequiredLocalizationException {
        init(local);
    }

    private record QA(String QUESTIONS, String ANSWER){} /* хранит пару вопрос - ответ */
}

