package com.jinbro.common.scraper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import com.jinbro.common.scraper.runner.ScrapRunner;

@SpringBootApplication
public class CommonScarperApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommonScarperApplication.class, args);
	}

	@Profile("runner")
	@Bean
	public CommandLineRunner runner() {
		return new ScrapRunner();
	}
}