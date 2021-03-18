package com.solms.PharmaPartnersOpdracht;
/*

        Author: Johan Solms - johan.solms@gmail.com

 */
import com.solms.PharmaPartnersOpdracht.Controllers.CurrencyRepository;
import com.solms.PharmaPartnersOpdracht.Models.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;



@SpringBootApplication
@EnableSwagger2
public class PharmaPartnersOpdrachtApplication implements CommandLineRunner {
	public static final Logger logger = LoggerFactory.getLogger(PharmaPartnersOpdrachtApplication.class);

	@Autowired
	private CurrencyRepository repository;
	public static void main(String[] args) {

		SpringApplication.run(PharmaPartnersOpdrachtApplication.class, args);

	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.solms.PharmaPartnersOpdracht")).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/posts.*"), regex("/api/currencies.*"));
	}



	@Override
	public void run(String... args){
		repository.save(new Currency("BTC", "Bitcoin", 16_770_000, 189_580_000_000L));
		repository.save(new Currency("ETH", "Ethereum", 96_710_000, 69_280_000_000L));
		repository.save(new Currency("XRP", "Ripple", 38_590_000, 64_750_000_000L));
		repository.save(new Currency("BCH", "BitcoinCash", 16_670_000, 69_020_000_000L));
		logger.info("Currencies loaded");
	}

}
