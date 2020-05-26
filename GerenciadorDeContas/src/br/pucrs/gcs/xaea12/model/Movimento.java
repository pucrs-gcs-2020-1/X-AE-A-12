package br.pucrs.gcs.xaea12.model;

import java.text.DecimalFormat;
import java.util.Calendar;

public final class Movimento {
	private final Calendar data;
	private final Operador operador;
	private final int nrdoc;
	private final String descricao;
	private final double valor;
	
	public Movimento(Calendar data, Operador operador, int nrdoc, String descricao, double valor) {
		this.data = data;
		this.operador = operador;
		this.nrdoc = nrdoc;
		this.descricao = descricao;
		DecimalFormat df = new DecimalFormat("#.##");
		this.valor = Double.valueOf(df.format(valor));
		//this.valor.setScale(2);
	}
	
	public Calendar getData() {
		return (Calendar) data.clone();
	}
	
	public String getOperador() {
		return operador.getIniciais();
	}
	
	public int getNrdoc() {
		return nrdoc;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public double getValor() {
		return valor;
	}
}
