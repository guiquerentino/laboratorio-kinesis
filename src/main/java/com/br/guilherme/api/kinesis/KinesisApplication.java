package com.br.guilherme.api.kinesis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.br.guilherme.api.kinesis.consumer.SchedulerBuilder;

import software.amazon.kinesis.coordinator.Scheduler;

@SpringBootApplication
public class KinesisApplication {

	private static String accessKey;

	private static String secretKey;

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(KinesisApplication.class, args);

		SchedulerBuilder schedulerBuilder = new SchedulerBuilder();
		
		Scheduler scheduler = schedulerBuilder.buildScheduler(accessKey, secretKey);

		try {
			scheduler.run();
		} catch (Exception e) {
			System.out.println("KCL Scheduler app  encountered error: " + e);
		}
	}
		

	@PostConstruct
	public void setaCredenciais() {

		accessKey = env.getProperty("aws.config.accessKey");
		secretKey = env.getProperty("aws.config.secretKey");

	}
}
