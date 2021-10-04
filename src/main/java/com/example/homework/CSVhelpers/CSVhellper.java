package com.example.homework.CSVhelpers;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVhellper implements CSVhellperInterface{

    private final List<QA> questionsAnswerList; // хранит вопросы и ответы
    private final Integer questionsCount; // количество вопросов
    private Integer questionsNumber = 0; // номер вопроса, который возвращается студенты
    private Integer correctAnswerCount = 0; // количество правильн6ых ответов

    public CSVhellper(String path, Integer questionsCount, String localization) {
        this.questionsCount = questionsCount;
        questionsAnswerList = new ArrayList<>();
        init(path, localization); //ради сохранности глаз, лучше не заглядывать!
        //Он просто заполняет поля выше!
    }

    @Override
    public String getNextQuestions() { //Возвращает следующий вопрос
        if (questionsNumber < questionsCount ){
            return questionsAnswerList.get(questionsNumber).getQUESTIONS();
        }
        else return null;
    }

    @Override
    public void setAnswer(String answer) { // проверяет вопрос
        if(questionsAnswerList.get(questionsNumber).getANSWER().equals(answer + " " /* пробел тут обязателен */))  correctAnswerCount++;
        questionsNumber++;
    }

    @Override
    public String getResult() { // возвращает результат
        return "You answered " + correctAnswerCount + " out of " + questionsCount + " questions correctly";
    }

    private void init(String path, String localization){
        //Я предупреждал
        File file;
        if (Objects.equals(localization, "RU")) file = new File(path);
        else{
            StringBuilder builder = new StringBuilder();
            char[] chars = new char[71];
            path.getChars(0, 71, chars, 0);
            builder.append(chars);
            builder.append("_").append(localization);
            chars = new char[4];
            path.getChars(71, path.length(), chars, 0);
            builder.append(chars);
            file = new File(builder.toString());
        }
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

    @Data
    @RequiredArgsConstructor
    private static class QA{ // хранит пару вопрос - ответ
        private final String QUESTIONS;
        private final String ANSWER;
    }
}