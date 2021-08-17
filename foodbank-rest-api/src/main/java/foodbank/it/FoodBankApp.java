package foodbank.it;

import foodbank.it.config.FoodBankAppConfig;
import foodbank.it.service.IFilesStorageService;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties(FoodBankAppConfig.class)
public class FoodBankApp implements CommandLineRunner {
	@Resource
	  IFilesStorageService storageService;


	public static void main(String[] args) {
		SpringApplication.run(FoodBankApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 storageService.deleteAll();
		    storageService.init();
		
	}
}
