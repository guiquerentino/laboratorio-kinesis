package com.br.guilherme.api.kinesis.consumer;

import software.amazon.kinesis.lifecycle.events.InitializationInput;
import software.amazon.kinesis.lifecycle.events.LeaseLostInput;
import software.amazon.kinesis.lifecycle.events.ProcessRecordsInput;
import software.amazon.kinesis.lifecycle.events.ShardEndedInput;
import software.amazon.kinesis.lifecycle.events.ShutdownRequestedInput;
import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.retrieval.KinesisClientRecord;

public class ScoreRecordProcessor implements ShardRecordProcessor {

	 private String shardId;
	
	@Override
	public void initialize(InitializationInput initializationInput) {
		
		shardId = initializationInput.shardId();
        System.out.println(String.format("Initialized shard %s @ sequence %s",
                                shardId, initializationInput.extendedSequenceNumber().toString()));
		
	}

	@Override
	public void processRecords(ProcessRecordsInput processRecordsInput) {
		
		for (KinesisClientRecord record : processRecordsInput.records()) {
			
			byte[] byteArr = new byte[record.data().remaining()];
            record.data().get(byteArr);
            System.out.println(new String(byteArr));
			
		}
		
	}

	@Override
	public void leaseLost(LeaseLostInput leaseLostInput) {
		
	}

	@Override
	public void shardEnded(ShardEndedInput shardEndedInput) {
		
	}

	@Override
	public void shutdownRequested(ShutdownRequestedInput shutdownRequestedInput) {
		
	}

}
