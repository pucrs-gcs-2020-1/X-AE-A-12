package br.pucrs.gcs.xaea12.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import br.pucrs.gcs.xaea12.model.MovimentosTableModel;
import br.pucrs.gcs.xaea12.xml.DBXml;

public class TelaPrincipal {

	private JFrame tela;
	private Dimension widthHeight;
	private DBXml db;
	private JMenuBar mnuRodape;
	private JMenuItem mnu1;
	private JMenuItem mnu2;
	private JMenuItem mnu3;
	private JPanel painel;
	private JPanel painelBotoes;
	private JTable tbMovimentos;
	private int idxOperador = 7; // index do operador na lista
	private int idxConta = 5; // index da conta na lista
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TelaPrincipal().montaTela();
	}
	
	private void montaTela() {
		preparaTela();
		carregaBanco();
		selecionaOperador(); // Chamar a tela de seleção do operador
		preparaPainel();
		preparaRodape();
		preparaTabela();
		preparaPainelBotoes();
		preparaBtnNewOperador();
		preparaBtnAltTrocaOperador();
		preparaBtnNewConta();
		preparaBtnAltTrocaConta();
		preparaBtnMovimento();
		preencheTabela();
		mostraTela();
	}
	
	private void mostraTela() {
		tela.pack();
		tela.setMinimumSize(new Dimension(800, 600));
		tela.setSize(widthHeight.width, widthHeight.height);
		tela.setVisible(true);
	}
	
	private void preencheTabela() {
		MovimentosTableModel mtm = new MovimentosTableModel(db.getContas().get(idxConta).getMovimentos());
		tbMovimentos.setModel(mtm);
		// Redimenciona as colunas da tabela para deixar a descrição com todo o restante do espaço
		tbMovimentos.getColumn(mtm.getColumnName(0)).setPreferredWidth((int)(widthHeight.width*0.1));
		tbMovimentos.getColumn(mtm.getColumnName(1)).setPreferredWidth((int)(widthHeight.width*0.1));
		tbMovimentos.getColumn(mtm.getColumnName(2)).setPreferredWidth((int)(widthHeight.width*0.1));
		tbMovimentos.getColumn(mtm.getColumnName(3)).setPreferredWidth((int)(widthHeight.width*0.55));
		tbMovimentos.getColumn(mtm.getColumnName(4)).setPreferredWidth((int)(widthHeight.width*0.15));
	}
	
	private void preparaBtnMovimento() {
		JButton btnMovimento = new JButton("Lançar Movimento");
		btnMovimento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaMovimentacao tmov = new TelaMovimentacao(db.getOperadores().get(idxOperador));
			}
		});
		painelBotoes.add(btnMovimento);
	}
	
	private void preparaBtnAltTrocaConta() {
		JButton btnAltTrocaCo = new JButton("Trocar Conta");
		btnAltTrocaCo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		painelBotoes.add(btnAltTrocaCo);
	}
	
	private void preparaBtnNewConta() {
		JButton btnNewConta = new JButton("Nova Conta");
		btnNewConta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		painelBotoes.add(btnNewConta);
	}
	
	private void preparaBtnAltTrocaOperador() {
		JButton btnAltTrocarOp = new JButton("Trocar Operador");
		btnAltTrocarOp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		painelBotoes.add(btnAltTrocarOp);
	}
	
	private void preparaBtnNewOperador() {
		JButton btnNewOperador = new JButton("Novo Operador");
		btnNewOperador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		painelBotoes.add(btnNewOperador);
	}
	
	private void preparaPainelBotoes() {
		painelBotoes = new JPanel();
		painelBotoes.setLayout(new GridLayout());
		painel.add(painelBotoes, BorderLayout.CENTER);
	}
	
	private void preparaTabela() {
		tbMovimentos = new JTable();
		tbMovimentos.setBorder(new LineBorder(Color.black));
		tbMovimentos.setGridColor(Color.black);
		tbMovimentos.setShowGrid(true);
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tbMovimentos);
		scroll.setPreferredSize(new Dimension(widthHeight.width, (int)(widthHeight.height * 0.68)));
		scroll.setSize(widthHeight.width, widthHeight.height);
		
		painel.add(scroll, BorderLayout.NORTH);
	}
	
	private void preencheDadosTela() {
		mnu1.setText("Iniciais: "+db.getOperadores().get(idxOperador).getIniciais());
		mnu2.setText("Nome: "+db.getOperadores().get(idxOperador).getNome());
		mnu3.setText("Conta nº: "+db.getContas().get(idxConta).getNumero());
	}
	
	private void preparaRodape() {
		mnuRodape = new JMenuBar();
		mnuRodape.setPreferredSize(new Dimension(widthHeight.width, (int)(widthHeight.height*0.03)));
		
		mnu1 = new JMenuItem();
		mnu1.setPreferredSize(new Dimension(90, 0));
		mnuRodape.add(mnu1);
		JSeparator js = new JSeparator(JSeparator.VERTICAL);
		js.setPreferredSize(new Dimension(5, 0));
		mnuRodape.add(js);
		
		mnu2 = new JMenuItem();
		mnu2.setPreferredSize(new Dimension(500, 0));
		mnuRodape.add(mnu2);
		js = new JSeparator(JSeparator.VERTICAL);
		js.setPreferredSize(new Dimension(5, 0));
		mnuRodape.add(js);
		
		mnu3 = new JMenuItem();
		mnu3.setPreferredSize(new Dimension(90, 0));
		mnuRodape.add(mnu3);
		
		painel.add(mnuRodape, BorderLayout.SOUTH);
		preencheDadosTela();
	}
	
	private void preparaPainel() {
		painel = new JPanel();
		painel.setLayout(new BorderLayout());
		tela.add(painel);
	}
	
	private void selecionaOperador() {
		// Chama a tela de escolha do operador ao iniciar o programa
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
