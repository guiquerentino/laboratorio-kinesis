package com.br.guilherme.api.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class SolicitacaoScoreDTO {

	private String idSolicitacao;

	private String idNota;
	
	private String conteudoNota;
	
	private String conteudoDevedor;
	
	private String conteudoCredor;
 
	
}
