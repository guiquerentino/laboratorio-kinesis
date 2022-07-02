package com.br.guilherme.api.kinesis.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;

public class AwsKinesisClientConfig {

	public static final String AWS_ACCESS_KEY = "aws.accessKeyId";
	public static final String AWS_SECRET_KEY = "aws.secretKey";

	static {
		System.setProperty(AWS_ACCESS_KEY, "AKIAXFU6OHZF47HGRZYU");
		System.setProperty(AWS_SECRET_KEY, "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");
	}

	public static AmazonKinesis getKinesisClient() {
		return AmazonKinesisClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.build();
	}
	
}
