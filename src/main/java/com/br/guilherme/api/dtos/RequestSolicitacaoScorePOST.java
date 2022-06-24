package com.br.guilherme.api.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestSolicitacaoScorePOST {

	@JsonProperty(value = "data")
	private List<SolicitacaoScoreDTO> solicitacaoScore;
	
}
