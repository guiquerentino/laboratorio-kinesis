package com.br.guilherme.api.kinesis.consumer;

import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.processor.ShardRecordProcessorFactory;

public class ScoreRecordProcessorFactory implements ShardRecordProcessorFactory {

	@Override
	public ShardRecordProcessor shardRecordProcessor() {

		return new ScoreRecordProcessor();
	}

}
