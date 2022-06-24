package com.br.guilherme.api.kinesis.service;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.br.guilherme.api.dtos.RequestSolicitacaoScorePOST;
import com.br.guilherme.api.dtos.SolicitacaoScoreDTO;
import com.br.guilherme.api.kinesis.config.AwsKinesisClientConfig;

@Service
public class KinesisProducerService {

	public void enviaRecord(RequestSolicitacaoScorePOST mensagem) {

		AmazonKinesis kinesisClient = AwsKinesisClientConfig.getKinesisClient();

		for (SolicitacaoScoreDTO req : mensagem.getSolicitacaoScore()) {

			JSONObject json = new JSONObject();
			json.put("idSolicitacao", req.getIdSolicitacao());
			json.put("idNota", req.getIdNota());
			json.put("conteudoNota", req.getConteudoNota());
			json.put("conteudoDevedor", req.getConteudoDevedor());
			json.put("conteudoCredor", req.getConteudoCredor());

			PutRecordRequest putRecord = new PutRecordRequest();
			putRecord.setStreamName("kinesis_guilherme");
			putRecord.setPartitionKey(UUID.randomUUID().toString());
			putRecord.setData(ByteBuffer.wrap(json.toString().getBytes()));

			try {

				kinesisClient.putRecord(putRecord);
				System.out.println("Mensagem enviada com sucesso.");
				PutRecordResult result = kinesisClient.putRecord(putRecord);
				System.out.println(result);

			} catch (AmazonClientException ex) {
				System.out.println("Deu ruim: " + ex);
			}

		}

	}

}
