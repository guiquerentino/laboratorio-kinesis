package com.br.guilherme.api.kinesis.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

public class AwsKinesisClientConfig {

	public static final String AWS_ACCESS_KEY = "aws.accessKeyId";
	public static final String AWS_SECRET_KEY = "aws.secretKey";

	static {
		System.setProperty(AWS_ACCESS_KEY, "AKIA4LIUBQTS2OISJUCV");
		System.setProperty(AWS_SECRET_KEY, "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");
	}

	public static AmazonKinesis getKinesisClient() {
		return AmazonKinesisClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.build();
	}
	
}
