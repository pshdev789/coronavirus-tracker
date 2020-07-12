package com.app.coronavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * The Class CoronavirusTrackerApplication.
 */
@EnableCaching		
@SpringBootApplication
@EnableScheduling
//tell spring to create a proxy to call the schedular method within the specified freq
public class CoronavirusTrackerApplication {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CoronavirusTrackerApplication.class, args);

	}

	/**
	 * Gets the rest template.
	 *
	 * @return the rest template
	 */
	@Bean
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}

}
