package br.pucrs.gcs.xaea12.model;

import java.io.Serializable;

//import br.pucrs.gcs.xaea12.xml.Xmlcripto;

public final class Operador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private String iniciais;
	
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
	
	@Override
	public String toString() {
		return iniciais;
	}
}
