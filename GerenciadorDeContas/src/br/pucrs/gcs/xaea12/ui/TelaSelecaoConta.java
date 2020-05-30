package br.pucrs.gcs.xaea12.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.pucrs.gcs.xaea12.model.Conta;
import br.pucrs.gcs.xaea12.xml.DBXml;

public class TelaSelecaoConta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialog frame;
	private final JLabel lblTitulo = new JLabel("Selecione uma Conta");
	private final JLabel lblContas = new JLabel("Selecione:");
	private final JComboBox<Conta> cmbConta = new JComboBox<Conta>();
	private JSeparator js = new JSeparator();
	private final JLabel lblNumero = new JLabel("Conta nº:");
	private final JTextField txfNumero = new JTextField();
	private final JLabel lblCriador = new JLabel("Criado por:");
	private final JTextField txfCriador = new JTextField();
	private final JLabel lblData = new JLabel("Data de criação:");
	private final JTextField txfData = new JTextField();
	private final JButton btnVoltar = new JButton("Voltar");
	private final JButton btnSelecionar = new JButton("Selecionar");
	private final TelaPrincipal tela;

	public TelaSelecaoConta(JFrame fr, DBXml db, TelaPrincipal tp) {
		this.frame = new JDialog(fr);
		this.tela = tp;
		for(Conta c : db.getContas()) {
			cmbConta.addItem(c);
		}
		montaDialogo();
	}
	
	private void montaDialogo() {
		this.frame.setBounds(((int)(Toolkit.getDefaultToolkit().getScreenSize().width/2))-200, ((int)(Toolkit.getDefaultToolkit().getScreenSize().height/2))-150, 400, 300);
		this.frame.setModal(true);
		this.frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.frame.setTitle("Selecionar Conta");
		this.frame.setResizable(false);
		JPanel jp = new JPanel();
		this.frame.add(jp);
		
		JPanel jp1 = new JPanel();
		jp1.setPreferredSize(new Dimension(300, 30));
		this.lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jp1.add(this.lblTitulo);
		jp.add(jp1);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		js.setPreferredSize(new Dimension(300, 5));
		jp.add(js);
		
		JPanel jp2 = new JPanel(new GridLayout(1,2,0,0));
		jp2.setPreferredSize(new Dimension(300, 25));
		jp2.add(this.lblContas);
		this.cmbConta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Conta c = (Conta) cmbConta.getSelectedItem();
				txfCriador.setText(c.getOperador());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				txfData.setText(sdf.format(c.getCriacao().getTime()));
				txfNumero.setText(String.valueOf(c.getNumero()));
			}
		});
		jp2.add(this.cmbConta);
		jp.add(jp2);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		js.setPreferredSize(new Dimension(300, 5));
		jp.add(js);
		
		JPanel jp3 = new JPanel(new GridLayout(3, 2, 0, 10));
		jp3.setPreferredSize(new Dimension(300, 90));
		jp3.add(this.lblNumero);
		jp3.add(this.txfNumero);
		jp3.add(this.lblCriador);
		jp3.add(this.txfCriador);
		jp3.add(this.lblData);
		jp3.add(this.txfData);
		jp.add(jp3);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		js.setPreferredSize(new Dimension(300, 5));
		jp.add(js);
		
		JPanel jp4 = new JPanel(new GridLayout(1, 2, 0, 10));
		jp4.setPreferredSize(new Dimension(300, 50));
		this.btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		jp4.add(this.btnVoltar);
		this.btnSelecionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tela.setIdxConta(cmbConta.getSelectedIndex());
				tela.preencheDadosTela();
				tela.preencheTabela();
				frame.dispose();
			}
		});
		jp4.add(this.btnSelecionar);
		jp.add(jp4);
		
		this.frame.setVisible(true);
	}
}
