package com.dogan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.dogan.dao.LoginAccountRepository;
import com.dogan.model.LoginAccount;

@SpringBootApplication
public class TaskApplication {
	
	private AppConfProperties appConfProperties;
	
	@Autowired
	public void setAppConfProperties(AppConfProperties appConfProperties) {
		this.appConfProperties = appConfProperties;
	}

	public static void main(String[] args) {
		// System.setProperty("spring.devtools.restart.enabled", "false");
		// new
		// SpringApplicationBuilder(TaskApplication.class).web(true).run(args);
		// SpringApplication.run(TaskApplication.class, args);
		SpringApplication app = new SpringApplication(TaskApplication.class);
		//app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Bean
	@Order(value=Ordered.LOWEST_PRECEDENCE)
	public CommandLineRunner init(final LoginAccountRepository loginAccountRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				System.out.println("First CommandLineRunner");
				loginAccountRepository.save(new LoginAccount("dogan", "123456"));
			}
		};
	}

	@Bean
	@Order(value=5)
	public CommandLineRunner commandLineRunnera(ApplicationContext ctx) {
		return args -> {
			System.out.println("Application Name: " + appConfProperties.getAppName());
		};
	}
}
