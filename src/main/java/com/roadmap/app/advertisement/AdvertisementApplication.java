package com.roadmap.app.advertisement;

import com.roadmap.app.advertisement.entity.Advertisement;
import com.roadmap.app.advertisement.repository.AdvertisementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@EnableTransactionManagement
public class AdvertisementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisementApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AdvertisementRepository repository) {
		return (args) -> {
			for (int i = 0; i < 5; i++) {
				repository.save(demoData(i));
			}
		};
	}

	private Advertisement demoData(int i) {
		Advertisement advertisement = new Advertisement();
		advertisement.setTitle("Title_" + i);
		advertisement.setDescription("Description_" + i);
		advertisement.setPrice(BigDecimal.valueOf(50.00));
		advertisement.setContactPhoneNumber1("+111 555777" + i);
		advertisement.setStartDateOfAd(LocalDate.now());
		advertisement.setEndDateOfAd(LocalDate.now().plusMonths(1));
		advertisement.setAddress("Address_" + i);
		advertisement.setContactPhoneNumber2("+222 43322" + i);
		advertisement.setCity("City_" + i);
		advertisement.setContactEmailAddress("ad_" + i + "@demo.com");
		return advertisement;
	}
}
