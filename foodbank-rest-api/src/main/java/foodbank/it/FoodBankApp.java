package foodbank.it;

import foodbank.it.config.FoodBankAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties(FoodBankAppConfig.class)
public class FoodBankApp {



	public static void main(String[] args) {
		SpringApplication.run(FoodBankApp.class, args);
	}
}
