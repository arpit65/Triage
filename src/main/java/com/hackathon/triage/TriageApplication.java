package com.hackathon.triage;

import com.hackathon.triage.executor.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TriageApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TriageApplication.class, args);
		Executor executor = applicationContext.getBean(Executor.class);
		executor.execute();
	}

}
