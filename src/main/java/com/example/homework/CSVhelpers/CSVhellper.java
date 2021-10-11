package com.example.homework.CSVhelpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Repository
@PropertySource("classpath:application.yml")
public class CSVhellper implements CSVhellperInterface{

    private final List<QA> questionsAnswerList; // хранит вопросы и ответы
    private final Integer questionsCount; // количество вопросов
    private Integer questionsNumber = 0; // номер вопроса, который возвращается студенты
    private Integer correctAnswerCount = 0; // количество правильн6ых ответов

    public CSVhellper(@Value("${questions.path}") String path,
                      @Value("${questions.count}") Integer questionsCount) {
        this.questionsCount = questionsCount;
        questionsAnswerList = new ArrayList<>();

        init(path); //ради сохранности глаз, лучше не заглядывать!
        //Он просто заполняет поля выше!
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
        if(questionsAnswerList.get(questionsNumber).ANSWER().equals(answer + " " /* пробел тут обязателен */))  correctAnswerCount++;
        questionsNumber++;
    }

    @Override
    public String getResult() { // возвращает результат
        return "You answered " + correctAnswerCount + " out of " + questionsCount + " questions correctly";
    }

    @Override
    public void restart() {
        correctAnswerCount = 0;
        questionsNumber = 0;
    }

    private void init(String path) {

        //Я предупреждал

        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ignored) {}

        if (scanner != null){
            StringBuilder q = new StringBuilder();
            StringBuilder a = new StringBuilder();
            String temp;
            for (int i = 0; i < questionsCount; i++) {
                temp = scanner.next();
                while (!temp.equals(",")){
                    q.append(temp).append(" ");
                    temp = scanner.next();
                }
                temp = scanner.next();
                while (!temp.equals(";")){
                    a.append(temp).append(" ");
                    temp = scanner.next();
                }
                questionsAnswerList.add(new QA(q.toString(), a.toString()));
                q = new StringBuilder();
                a = new StringBuilder();

            }
            scanner.close();
        } else {
            throw new NullPointerException("file not found");
        }
    }

    private record QA(String QUESTIONS, String ANSWER) { /* хранит пару вопрос - ответ */}
}

