package com.br.guilherme.api.kinesis.service;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.br.guilherme.api.dtos.RequestSolicitacaoScorePOST;
import com.br.guilherme.api.dtos.SolicitacaoScoreDTO;
import com.br.guilherme.api.kinesis.config.AwsKinesisClientConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KinesisProducerService {
	
	@Autowired
	private AwsKinesisClientConfig config;
	
	public void enviaRecord(RequestSolicitacaoScorePOST mensagem) throws UnsupportedEncodingException {

		KinesisProducer kinesis = new KinesisProducer(config.getKinesisClientConfig());

		for (SolicitacaoScoreDTO req : mensagem.getSolicitacaoScore()) {

			JSONObject json = new JSONObject();
			json.put("idSolicitacao", req.getIdSolicitacao());
			json.put("idNota", req.getIdNota());
			json.put("conteudoNota", req.getConteudoNota());
			json.put("conteudoDevedor", req.getConteudoDevedor());
			json.put("conteudoCredor", req.getConteudoCredor());

			ByteBuffer data = ByteBuffer.wrap(json.toString().getBytes("UTF-8"));
			System.out.println("Adicionando mensagem -> " + json.toString());
			kinesis.addUserRecord("PDD_Mock_Solicitacao", "123", data);

		}

	}
}
