package br.pucrs.gcs.xaea12.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class Conta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private Calendar criacao;
	private Operador operador;
	private double saldo;
	private List<Movimento> movimentos;
	
	public Conta(int numero, Calendar criacao, Operador operador) {
		this.numero = numero;
		this.criacao = criacao;
		this.operador = operador;
		this.saldo = 0;
		this.movimentos = new ArrayList<Movimento>();
	}
	
	public int getNumero() {
		return numero;
	}

	public Calendar getCriacao() {
		return (Calendar) criacao.clone();
	}

	public String getOperador() {
		return operador.getIniciais();
	}

	public double getSaldo() {
		return saldo;
	}

	public List<Movimento> getMovimentos() {
		List<Movimento> seguranca = new ArrayList<Movimento>();
		seguranca.addAll(movimentos);
		return seguranca;
	}

	public void setMovimentos(List<Movimento> movimentos) {
		this.movimentos = movimentos;
		getAtualizaSaldo();
	}
	
	public void addMovimento(Movimento movimento) {
		this.movimentos.add(movimento);
		DecimalFormat df = new DecimalFormat("#.##");
		this.saldo = Double.valueOf(df.format(this.saldo + movimento.getValor()));
	}
	
	private final void getAtualizaSaldo() {
		this.saldo = 0;
		DecimalFormat df = new DecimalFormat("#.##");
		for(Movimento mov : this.movimentos) {
			this.saldo = Double.valueOf(df.format(this.saldo + mov.getValor()));
		}
	}
}
