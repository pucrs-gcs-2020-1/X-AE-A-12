package br.pucrs.gcs.xaea12.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.pucrs.gcs.xaea12.model.Operador;
import br.pucrs.gcs.xaea12.xml.DBXml;
import javafx.stage.WindowEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaSelecaoOperador extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JDialog frame;
	private final JLabel titulo = new JLabel("Selecione um operador");
	private final JComboBox<Operador> selecionarOperador = new JComboBox<Operador>();
	private final JSeparator separator = new JSeparator();
	private final JLabel labelCodOperador = new JLabel("C\u00F3digo do operador");
	private final JTextField txtOperador = new JTextField();
	private final JTextField txtNome = new JTextField();
	private final JLabel labelNome = new JLabel("Nome");
	private final JTextField txtIniciais = new JTextField();
	private final JLabel labelIniciais = new JLabel("Iniciais");
	private final JButton selecionar = new JButton("Selecionar");
	private final JLabel labelSelecioneOOperador = new JLabel("Selecione o operador");
	private final JSeparator separator_1 = new JSeparator();
	private final JButton voltar = new JButton("Voltar");

	private List<Operador> operadores;
	private Operador operadorEscolhido;
	private int idxOperador;
	private TelaPrincipal telaprin;

	public TelaSelecaoOperador(JFrame frame, DBXml dbData, TelaPrincipal tela) {
		this.operadores = new ArrayList<>();
		this.operadores = dbData.getOperadores();
		this.telaprin = tela;
		this.frame = new JDialog(frame);
		for(Operador op : operadores){
			selecionarOperador.addItem(op);
		}
	}

	public void iniciar() {
		this.txtIniciais.setBounds(214, 187, 181, 20);
		this.txtIniciais.setColumns(10);
		this.txtNome.setBounds(214, 156, 181, 20);
		this.txtNome.setColumns(10);
		this.txtOperador.setBounds(214, 125, 181, 20);
		this.txtOperador.setColumns(10);
		frame.setBounds(100, 100, 483, 349);
		frame.setModal(true);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.titulo.setBounds(163, 32, 191, 14);
		frame.setTitle("Selecione o operador");

		frame.getContentPane().add(this.titulo);
		this.selecionarOperador.setBounds(214, 77, 124, 20);

		frame.getContentPane().add(this.selecionarOperador);
		this.separator.setBounds(32, 64, 407, 2);

		frame.getContentPane().add(this.separator);
		this.labelCodOperador.setBounds(56, 128, 140, 14);

		frame.getContentPane().add(this.labelCodOperador);

		frame.getContentPane().add(this.txtOperador);

		frame.getContentPane().add(this.txtNome);
		this.labelNome.setBounds(56, 158, 46, 14);

		frame.getContentPane().add(this.labelNome);

		frame.getContentPane().add(this.txtIniciais);
		this.labelIniciais.setBounds(56, 190, 46, 14);

		frame.getContentPane().add(this.labelIniciais);

		this.selecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idxOperador = operadores.indexOf(operadorEscolhido);
				telaprin.setIdxOperador(idxOperador);
				frame.dispose();
			}
		});
		this.selecionar.setBounds(268, 248, 95, 23);

		frame.getContentPane().add(this.selecionar);
		this.labelSelecioneOOperador.setBounds(58, 77, 150, 14);

		frame.getContentPane().add(this.labelSelecioneOOperador);
		this.separator_1.setBounds(32, 224, 407, 2);

		frame.getContentPane().add(this.separator_1);
		this.voltar.setBounds(82, 248, 95, 23);

		frame.getContentPane().add(this.voltar);

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}

	public void mostraTela(DBXml dbData){
		selecionarOperador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operadorEscolhido = (Operador) selecionarOperador.getSelectedItem();
				txtIniciais.setText(operadorEscolhido.getIniciais());
				txtNome.setText(operadorEscolhido.getNome());
				txtOperador.setText(String.valueOf(operadorEscolhido.getCodigo()));
			}
		});
		//System.out.println(newOperadores.size());
		frame.revalidate();
		frame.setVisible(true);
	}

	public int getIdxOperador(){
		return idxOperador;
	}

	public void addOperador(Operador operador){
		operadores.add(operador);
		selecionarOperador.addItem(operador);
	}

}
