package br.pucrs.gcs.xaea12.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

public class MovimentosTableModel extends AbstractTableModel {

	private final List<Movimento> movimentos;
	
	public MovimentosTableModel(List<Movimento> movimentos) {
		this.movimentos = movimentos;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Data";

		case 1:
			return "Operador";

		case 2:
			return "NroDoc";

		case 3:
			return "Descrição";

		case 4:
			return "Valor";

		}
		return null;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.movimentos.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Movimento m = this.movimentos.get(arg0);
		switch (arg1) {
		case 0:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(m.getData().getTime());

		case 1:
			return m.getOperador();

		case 2:
			return String.format("%06d", m.getNrdoc());

		case 3:
			return m.getDescricao();

		case 4:
			Locale brasil = new Locale("pt-br", "BR");
			NumberFormat nf = NumberFormat.getCurrencyInstance(brasil);
			return nf.format(m.getValor());

		}
		return null;
	}

}
