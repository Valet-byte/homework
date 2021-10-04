package com.example.homework;

import com.example.homework.CSVhelpers.CSVhellper;
import com.example.homework.CSVhelpers.CSVhellperInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);
		System.out.println(context.getClass());

		CSVhellperInterface csVhellper = context.getBean(CSVhellper.class);

		String questions = csVhellper.getNextQuestions();
		Scanner scanner = new Scanner(System.in);

		System.out.println();

		while (questions != null){
			System.out.print(questions);
			csVhellper.setAnswer(scanner.next());
			questions = csVhellper.getNextQuestions();
			System.out.println();
		}

		System.out.println(csVhellper.getResult());
	}

}