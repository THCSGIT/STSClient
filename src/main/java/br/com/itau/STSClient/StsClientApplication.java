package br.com.itau.STSClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StsClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(StsClientApplication.class, args);
		STSClient stsClient = applicationContext.getBean(STSClient.class);
		System.out.println("TOKEN: " + stsClient.fetchToken());
		System.exit(0);
	}

}
