package com.example.homework.CSVhelpers;
import com.example.homework.HomeworkApplication;
import org.jline.reader.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.Input;
import org.springframework.shell.Shell;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.shell.result.DefaultResultHandler;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.shell.jline.ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT;

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