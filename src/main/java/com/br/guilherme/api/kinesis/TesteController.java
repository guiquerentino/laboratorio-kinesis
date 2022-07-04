package com.br.guilherme.api.kinesis;

import java.io.UnsupportedEncodingException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.guilherme.api.dtos.RequestSolicitacaoScorePOST;
import com.br.guilherme.api.kinesis.service.KinesisProducerService;

@RestController
@RequestMapping
public class TesteController {

	@Autowired
	private KinesisProducerService service;

	@PostMapping(path = "/envio")
	public ResponseEntity<Object> enviaDadosStream(@RequestBody RequestSolicitacaoScorePOST mensagem) throws InterruptedException, UnsupportedEncodingException {
		
		service.enviaRecord(mensagem);
		
		return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
}
