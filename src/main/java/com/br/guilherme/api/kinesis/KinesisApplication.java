package com.br.guilherme.api.kinesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.br.guilherme.api.kinesis.consumer.SchedulerBuilder;
import software.amazon.kinesis.coordinator.Scheduler;

@SpringBootApplication
public class KinesisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KinesisApplication.class, args);

		Scheduler scheduler = SchedulerBuilder.buildScheduler();

		try {
			scheduler.run();
		} catch (Exception e) {
			System.out.println("KCL Scheduler app  encountered error: " + e);
		}
	}

}
