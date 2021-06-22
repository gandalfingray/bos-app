package com.mintit.bos.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	CommandLineRunner sampleCommandLineRunner() {
//		return args -> {
//			City city = new City();
//			city.setName("San Francisco");
//			city.setState("CA");
//			city.setCountry("US");
//			cityMapper.insert(city);
//			System.out.println(this.cityMapper.findById(city.getId()));
//		};
//	}

}
