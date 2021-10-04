package com.example.homework.CSVhelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка CSVhellper' а")
class CSVhellperTest {

    CSVhellperInterface csVhellper;

    @BeforeEach
    void getBean(){ // обновляет объект для тестов
        csVhellper = new CSVhellper(
                "C:\\Users\\User\\Desktop\\homework\\src\\main\\resources\\questions_answer.csv",
                5, "RU");
    }


    @DisplayName("Провера наличия вопросов")
    @Test
    void test1(){
        assertNotNull(csVhellper.getNextQuestions());
    }

    @DisplayName("Проверка занесения ответов")
    @Test
    void test2(){
        String q1 = csVhellper.getNextQuestions();
        csVhellper.setAnswer("");
        String q2 = csVhellper.getNextQuestions();
        assertNotEquals(q1, q2);
    }
}