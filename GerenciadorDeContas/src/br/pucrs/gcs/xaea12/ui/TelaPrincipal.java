package br.pucrs.gcs.xaea12.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import br.pucrs.gcs.xaea12.model.MovimentosTableModel;
import br.pucrs.gcs.xaea12.xml.DBXml;

public class TelaPrincipal {

	private JFrame tela;
	private Dimension widthHeight;
	private DBXml db;
	private JPanel painel;
	private JTable tbMovimentos;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TelaPrincipal().montaTela();
	}
	
	private void montaTela() {
		preparaTela();
		carregaBanco();
		preparaPainel();
		preparaTabela();
		preencheTabela(9);
		mostraTela();
	}
	
	private void mostraTela() {
		tela.pack();
		tela.setSize(widthHeight.width, widthHeight.height);
		tela.setVisible(true);
	}
	
	private void preencheTabela(int idxConta) {
		MovimentosTableModel mtm = new MovimentosTableModel(db.getContas().get(idxConta).getMovimentos());
		tbMovimentos.setModel(mtm);
	}
	
	private void preparaTabela() {
		tbMovimentos = new JTable();
		tbMovimentos.setBorder(new LineBorder(Color.black));
		tbMovimentos.setGridColor(Color.black);
		tbMovimentos.setShowGrid(true);
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tbMovimentos);
		
		painel.add(scroll, BorderLayout.SOUTH);
	}
	
	private void preparaPainel() {
		painel = new JPanel();
		painel.setLayout(new BorderLayout());
		tela.add(painel);
	}
	
	private void carregaBanco() {
		db = new DBXml();
		db.inicializar();
	}

	private void preparaTela() {
		tela = new JFrame("Gerenciador de Contas - versão: 0.1");
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		widthHeight = Toolkit.getDefaultToolkit().getScreenSize();
	}

}
