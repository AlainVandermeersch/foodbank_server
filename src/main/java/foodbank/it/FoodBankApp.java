package foodbank.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@SpringBootApplication
@EnableConfigurationProperties(FoodBankAppConfig.class)
public class FoodBankApp {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// registry.addMapping("/**").allowedOrigins(System.getenv("CORS_ALLOWED_ORIGINS"));
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodBankApp.class, args);
	}
}
