package com.br.guilherme.api.kinesis.config;

import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;

public class AwsKinesisClientConfig {

	public static KinesisProducerConfiguration getKinesisClientConfig() {
		
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAXFU6OHZF47HGRZYU",
				"834Llzu/MczGrJlkE79wsAOiSWQ0MfDm7SFo8Fdv");

		KinesisProducerConfiguration producerConfig = new KinesisProducerConfiguration()
				.setCredentialsProvider(new AWSStaticCredentialsProvider(awsCredentials))
				.setRegion(Regions.US_EAST_1.getName());
		
		return producerConfig;
	}
	
}
