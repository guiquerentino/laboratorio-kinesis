package com.br.guilherme.api.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ResponseScoreDTO {

	private String idTransacao;
	
	private String numeroNfe;
	
	private List<ConstatacaoDTO> constatacao;

	
}
