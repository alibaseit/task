package com.dogan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (String option : args) {
			sb.append(" ").append(option);
		}
		System.out.println(sb.toString());
	}

}
