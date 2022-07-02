package com.br.guilherme.api.kinesis;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.util.CredentialUtils;
import com.br.guilherme.api.kinesis.consumer.SchedulerBuilder;
import com.br.guilherme.api.kinesis.consumer.ScoreRecordProcessorFactory;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.KinesisClientUtil;
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
