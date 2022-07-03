package com.br.guilherme.api.kinesis.service;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.br.guilherme.api.dtos.RequestSolicitacaoScorePOST;
import com.br.guilherme.api.dtos.SolicitacaoScoreDTO;
import com.br.guilherme.api.kinesis.config.AwsKinesisClientConfig;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.GetRecordsRequest;
import software.amazon.awssdk.services.kinesis.model.GetRecordsResponse;
import software.amazon.awssdk.services.kinesis.model.GetShardIteratorRequest;
import software.amazon.awssdk.services.kinesis.model.GetShardIteratorResponse;
import software.amazon.awssdk.services.kinesis.model.Record;
import software.amazon.awssdk.services.kinesis.model.ShardIteratorType;

@Service
@Slf4j
public class KinesisProducerService {

	public void enviaRecord(RequestSolicitacaoScorePOST mensagem) {

		AmazonKinesis kinesisClient = AwsKinesisClientConfig.getKinesisClient();

		PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
		putRecordsRequest.setStreamName("PDD_Mock_Solicitacao");
		List<PutRecordsRequestEntry> putRecordsRequestEntryList = new ArrayList<>();

		for (SolicitacaoScoreDTO req : mensagem.getSolicitacaoScore()) {

			JSONObject json = new JSONObject();
			json.put("idSolicitacao", req.getIdSolicitacao());
			json.put("idNota", req.getIdNota());
			json.put("conteudoNota", req.getConteudoNota());
			json.put("conteudoDevedor", req.getConteudoDevedor());
			json.put("conteudoCredor", req.getConteudoCredor());

			PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
			putRecordsRequestEntry.setPartitionKey("Shard1");
			putRecordsRequestEntry.setData(ByteBuffer.wrap(json.toString().getBytes()));
			putRecordsRequestEntryList.add(putRecordsRequestEntry);

		}

		try {
			putRecordsRequest.setRecords(putRecordsRequestEntryList);
			PutRecordsResult putRecordsResult = kinesisClient.putRecords(putRecordsRequest);
			System.out.println("Records enviados: " + putRecordsResult);
		} catch (AmazonClientException ex) {
			System.out.println("Deu ruim: " + ex);
		}

	}

}
