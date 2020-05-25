package br.pucrs.gcs.xaea12.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TelaPrincipal {

	private JFrame tela;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TelaPrincipal().montaTela();
	}
	
	private void montaTela() {
		preparaTela();
		mostraTela();
	}

	private void mostraTela() {
		tela.pack();
		Dimension widthHeight = Toolkit.getDefaultToolkit().getScreenSize();
		tela.setSize(widthHeight.width, widthHeight.height);
		tela.setVisible(true);
	}

	private void preparaTela() {
		tela = new JFrame("Gerenciador de Contas - versão: 0.1");
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
