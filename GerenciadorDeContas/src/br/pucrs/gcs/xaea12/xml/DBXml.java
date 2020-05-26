package br.pucrs.gcs.xaea12.xml;

import java.io.Serializable;
import java.util.List;

import br.pucrs.gcs.xaea12.model.Conta;
import br.pucrs.gcs.xaea12.model.Operador;

public class DBXml implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Operador> operadores;
	private List<Conta> contas;
	
	public DBXml() { }
	
	public DBXml(List<Operador> operadores, List<Conta> contas) {
		this.operadores = operadores;
		this.contas = contas;
	}

	public List<Operador> getOperadores() {
		return operadores;
	}

	public void addOperadores(Operador operadores) {
		this.operadores.add(operadores);
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void addContas(Conta contas) {
		this.contas.add(contas);
	}
	
	public void inicializar() {
		DBXml db = PersisteXML.carregaXML();
		this.contas = db.getContas();
		this.operadores = db.getOperadores();
	}
	
	public int salvar() {
		try {
			PersisteXML.salvaXML(new DBXml(this.operadores, this.contas));
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
