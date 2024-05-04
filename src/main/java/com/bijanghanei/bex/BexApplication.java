package com.bijanghanei.bex;

import com.bijanghanei.bex.entity.Price;
import com.bijanghanei.bex.service.BexServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.TelegramBotsApi;


@SpringBootApplication
@EnableFeignClients
public class BexApplication {

	public static void main(String[] args) {
		SpringApplication.run(BexApplication.class, args);
	}

}
