package br.senai.sp.jandira.model;

public enum Periodo {
	
	MANHA("Manhã"), 
	TARDE("Tarde"), 
	NOITE("Noite"), 
	JORGE("JorginhoOMaisBrabo");
	
	private Periodo(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		
		return descricao;
	}

}
