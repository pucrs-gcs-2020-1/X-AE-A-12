package br.pucrs.gcs.xaea12.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.pucrs.gcs.xaea12.model.MovimentosTableModel;
import br.pucrs.gcs.xaea12.model.Operador;
import br.pucrs.gcs.xaea12.pdf.GerarPDF;
import br.pucrs.gcs.xaea12.xml.DBXml;
//import javafx.stage.WindowEvent;

public class TelaPrincipal extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static TelaPrincipal TP;
	private JFrame tela;
	private Dimension widthHeight;
	private DBXml db;
	private JFormattedTextField dataIn;
	private JFormattedTextField dataOut;
	private JComboBox<String> filtOpera;
	private JComboBox<String> filtReceita;
	private JLabel saldoParcial;
	private JMenuBar mnuRodape;
	private JMenuItem mnu1;
	private JMenuItem mnu2;
	private JMenuItem mnu3;
	private JMenuItem mnu4;
	private JPanel painel;
	private JPanel painelBotoes;
	private JPanel painelTabela;
	private JPanel painelFiltros;
	private JTable tbMovimentos;
	private MovimentosTableModel mtm;
	private MaskFormatter mascara;
	private int idxOperador = 0; // index do operador na lista
	private int idxConta = 0; // index da conta na lista
	private TelaMovimentacao tmov;
	private TelaSelecaoOperador tSelOp;
	private TelaOperador tOp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TP = new TelaPrincipal();
		TP.splash();
		//new TelaPrincipal().montaTela();
	}	
	public void setIdxOperador(int i){
		this.idxOperador = i;
	}
	
	public void setIdxConta(int c) {
		this.idxConta = c;
	}
	
	private void splash() {
		carregaBanco();
		preparaTela();
		selecionaOperador();
	}

	public void retSplash() {
		TP.montaTela();
	}
	
	private void montaTela() {
		preparaPainel();
		preparaRodape();
		preparaTabela();
		preparaPainelBotoes();
		preparaBtnNewOperador();
		preparaBtnAltTrocaOperador();
		preparaBtnNewConta();
		preparaBtnAltTrocaConta();
		preparaBtnMovimento();
		preparaBtnFiltrar();
		preencheTabela();
		mostraTela();
	}

	private void mostraTela() {
		tela.pack();
		tela.setMinimumSize(new Dimension(800, 600));
		tela.setSize(widthHeight.width, widthHeight.height);
		tela.setVisible(true);
	}

	public void preencheTabela() {
		mtm = new MovimentosTableModel(db.getContas().get(idxConta).getMovimentos());
		tbMovimentos.setModel(mtm);
		ajustaTamanhoTabela();
	}
	
	private void preencheTabelaFiltrada() {
		Calendar d1 = Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			d1.setTimeInMillis(df.parse(dataIn.getText()).getTime());
			d2.setTimeInMillis(df.parse(dataOut.getText()).getTime());
			d2.add(Calendar.DAY_OF_MONTH, 1);
			d2.add(Calendar.MILLISECOND, -1);
		} catch (Exception e) {
			d1 = null;
			d2 = null;
		}
		String op = (filtOpera.getSelectedIndex() == 0) ? null : filtOpera.getSelectedItem().toString();
		int tipo = filtReceita.getSelectedIndex();
		mtm = new MovimentosTableModel(db.getContas().get(idxConta).getFilterMovimentos(d1, d2, op, tipo));
		tbMovimentos.setModel(mtm);
		ajustaTamanhoTabela();
	}
	
	private void ajustaTamanhoTabela() {
		// Redimenciona as colunas da tabela para deixar a descrição com todo o restante do espaço
		tbMovimentos.getColumn(mtm.getColumnName(0)).setPreferredWidth((int)(widthHeight.width*0.1));
		tbMovimentos.getColumn(mtm.getColumnName(1)).setPreferredWidth((int)(widthHeight.width*0.1));
		tbMovimentos.getColumn(mtm.getColumnName(2)).setPreferredWidth((int)(widthHeight.width*0.1));
		tbMovimentos.getColumn(mtm.getColumnName(3)).setPreferredWidth((int)(widthHeight.width*0.55));
		tbMovimentos.getColumn(mtm.getColumnName(4)).setPreferredWidth((int)(widthHeight.width*0.15));
		tbMovimentos.setAutoCreateRowSorter(true);
		saldoParcial.setText("Saldo parcial: "+mtm.getTotal());
		mnu4.setText("Saldo: "+NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(db.getContas().get(idxConta).getSaldo()));
		backup();
	}
	
	private void preparaBtnFiltrar() {
		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			dataIn = new JFormattedTextField(mascara);
			dataOut = new JFormattedTextField(mascara);
			painelFiltros.add(new JLabel("Data Inicial:"));
			painelFiltros.add(dataIn);
			painelFiltros.add(new JLabel("Data Final:"));
			painelFiltros.add(dataOut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		painelFiltros.add(new JLabel("Operador:"));
		filtOpera = new JComboBox<String>();
		filtOpera.addItem("Todos");
		for (Operador op : db.getOperadores()) {
			filtOpera.addItem(op.toString());
		}
		painelFiltros.add(filtOpera);
		
		painelFiltros.add(new JLabel("Tipo"));
		filtReceita = new JComboBox<String>();
		filtReceita.addItem("Todos");
		filtReceita.addItem("Receitas");
		filtReceita.addItem("Despesas");
		painelFiltros.add(filtReceita);
		
		JButton btnFiltar = new JButton("Filtrar");
		btnFiltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				preencheTabelaFiltrada();
			}
		});
		painelFiltros.add(btnFiltar);
		painelTabela.add(painelFiltros, BorderLayout.NORTH);
	}

	private void preparaBtnMovimento() {
		JButton btnMovimento = new JButton("Lançar Movimento");
		btnMovimento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tmov.setOperador(db.getOperadores().get(idxOperador));
				tmov.setConta(db.getContas().get(idxConta));
				tmov.addMovimentacao();
			}
		});
		painelBotoes.add(btnMovimento);
	}

	private void preparaBtnAltTrocaConta() {
		JButton btnAltTrocaCo = new JButton("Trocar Conta");
		btnAltTrocaCo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new TelaSelecaoConta(tela, db, TP);
			}
		});
		painelBotoes.add(btnAltTrocaCo);
	}

	private void preparaBtnNewConta() {
		JButton btnNewConta = new JButton("Nova Conta");
		btnNewConta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new TelaConta(tela, db.getOperadores().get(idxOperador), db.getContas(), TP);
			}
		});
		painelBotoes.add(btnNewConta);
	}

	private void preparaBtnAltTrocaOperador() {
		JButton btnAltTrocarOp = new JButton("Trocar Operador");
		btnAltTrocarOp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tSelOp.ativo();
				tSelOp.mostraTela(db);
				tSelOp.setLocationRelativeTo(tela);
			}
		});
		painelBotoes.add(btnAltTrocarOp);
	}

	private void preparaBtnNewOperador() {
		JButton btnNewOperador = new JButton("Novo Operador");
		btnNewOperador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tOp.mostraTela();
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

		painelTabela.add(scroll, BorderLayout.CENTER);
		
		JPanel painelImpremir = new JPanel(new GridLayout(1, 4));
		painelImpremir.setPreferredSize(new Dimension(widthHeight.width, 30));
		JButton btnRelGeral = new JButton("Gerar relatório geral");
		btnRelGeral.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GerarPDF.relatorioGeral(idxOperador, db);
			}
		});
		painelImpremir.add(btnRelGeral);
		JButton btnRelParcial = new JButton("Gerar relatório parcial");
		btnRelParcial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GerarPDF.relatorioParcial(db.getOperadores().get(idxOperador), db.getContas().get(idxConta), mtm);
			}
		});
		painelImpremir.add(btnRelParcial);
		painelImpremir.add(new JLabel());
		saldoParcial = new JLabel();
		saldoParcial.setHorizontalAlignment(SwingConstants.RIGHT);
		saldoParcial.setFont(new Font(saldoParcial.getFont().getName(), saldoParcial.getFont().getStyle(), saldoParcial.getFont().getSize()+10));
		painelImpremir.add(saldoParcial);
		
		painelTabela.add(painelImpremir, BorderLayout.SOUTH);
		painel.add(painelTabela, BorderLayout.NORTH);
	}

	public void preencheDadosTela() {
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
		js = new JSeparator(JSeparator.VERTICAL);
		js.setPreferredSize(new Dimension(5, 0));
		mnuRodape.add(js);

		mnu4 = new JMenuItem();
		mnu4.setPreferredSize(new Dimension(90, 0));
		mnuRodape.add(mnu4);

		painel.add(mnuRodape, BorderLayout.SOUTH);
		preencheDadosTela();
	}

	private void preparaPainel() {
		painelFiltros = new JPanel();
		painelFiltros.setLayout(new GridLayout(0, 10));
		painelTabela = new JPanel();
		painelTabela.setLayout(new BorderLayout());
		painel = new JPanel();
		painel.setLayout(new BorderLayout());
		tela.add(painel);
		
	}

	private void selecionaOperador() {
		tSelOp.mostraTela(db);
		tSelOp.setLocationRelativeTo(tela);
		//preencheDadosTela();
		//tela.revalidate();
	}

	private void carregaBanco() {
		db = new DBXml();
		db.inicializar();
	}

	private void preparaTela() {
		tela = new JFrame("Gerenciador de Contas - versão: 0.1");
		tmov = new TelaMovimentacao(this);
		tSelOp = new TelaSelecaoOperador(tela, db, this);
		tOp = new TelaOperador(tela, db, tSelOp);
		tOp.iniciar();
		tSelOp.iniciar();
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		widthHeight = Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	private void backup() {
		db.salvar();
	}
}
