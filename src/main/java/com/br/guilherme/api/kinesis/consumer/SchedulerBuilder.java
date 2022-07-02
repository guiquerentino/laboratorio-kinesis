package com.br.guilherme.api.kinesis.consumer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.KinesisClientUtil;
import software.amazon.kinesis.coordinator.Scheduler;

public class SchedulerBuilder {

	@Value("${aws.config.appname}")
	public static String appName= "PDDKinesis";

	@Value("${aws.config.streamname}")
	public static String streamName = "PDD_Mock_Score";
	
	@Value("${aws.config.accessKey}")
	public static String accessKey = "AKIAXFU6OHZF47HGRZYU";

	@Value("${aws.config.secretKey}")
	public static String secretKey = "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";

	public static Scheduler buildScheduler() {

		System.setProperty("aws.accessKeyId", accessKey);
		System.setProperty("aws.secretAccessKey", secretKey);

		String schedulerId = UUID.randomUUID().toString();
		Region region = Region.US_EAST_1;

		SystemPropertyCredentialsProvider credentialsProvider = SystemPropertyCredentialsProvider.create();

		KinesisAsyncClient kinesisClient = KinesisClientUtil
				.createKinesisAsyncClient(KinesisAsyncClient.builder().region(region));

		DynamoDbAsyncClient dynamoClient = DynamoDbAsyncClient.builder().region(region).build();

		CloudWatchAsyncClient cwClient = CloudWatchAsyncClient.builder().region(region).build();

		ScoreRecordProcessorFactory scoreProcessorFactory = new ScoreRecordProcessorFactory();

		ConfigsBuilder configsBuilder = new ConfigsBuilder(streamName, appName, kinesisClient, dynamoClient, cwClient,
				schedulerId, scoreProcessorFactory);

		Scheduler scheduler = new Scheduler(configsBuilder.checkpointConfig(), configsBuilder.coordinatorConfig(),
				configsBuilder.leaseManagementConfig(), configsBuilder.lifecycleConfig(),
				configsBuilder.metricsConfig(), configsBuilder.processorConfig(), configsBuilder.retrievalConfig());

		return scheduler;

	}

}
