package com.example.homework.CSVhelpers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка CSVhellper' а")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false", //отключение shell
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class CSVhellperTest {

    @Autowired
    CSVhellper csVhellper;

    @DisplayName("Провера наличия вопросов")
    @Test
    void test1() {
        assertNotNull(csVhellper.getNextQuestions());
    }

    @DisplayName("Проверка занесения ответов")
    @Test
    void test2() {
        String q1 = csVhellper.getNextQuestions();
        csVhellper.setAnswer("");
        String q2 = csVhellper.getNextQuestions();
        assertNotEquals(q1, q2);
    }
}