package com.ti2cc;

public class Game {
	
	private int id;
	private String nome;
	private String dataLancamento;
	private String genero;
	
	public Game() {
		this.id = -1;
		this.nome = "";
		this.dataLancamento = "";
		this.genero = "";
	}
	
	public Game(int id, String nome, String dataLancamento, String genero) {
		this.id = id;
		this.nome = nome;
		this.dataLancamento = dataLancamento;
		this.genero = genero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", nome=" + nome + ", data de lançamento=" + dataLancamento + ", gênero=" + genero + "]";
	}	

}
