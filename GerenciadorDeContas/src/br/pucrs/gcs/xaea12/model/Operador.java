package br.pucrs.gcs.xaea12.model;

public final class Operador {
	private final int codigo;
	private final String nome;
	private final String iniciais;
	
	public Operador(int codigo, String nome, String iniciais) {
		this.codigo = codigo;
		this.nome = nome;
		this.iniciais = iniciais;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getIniciais() {
		return iniciais;
	}

	public boolean isOperador(String inicial) {
		return this.iniciais.equals(inicial);
	}
}
