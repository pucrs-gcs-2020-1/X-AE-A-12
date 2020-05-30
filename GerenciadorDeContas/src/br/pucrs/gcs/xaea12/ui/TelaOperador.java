package br.pucrs.gcs.xaea12.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.pucrs.gcs.xaea12.model.Operador;
import br.pucrs.gcs.xaea12.xml.DBXml;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaOperador {

	private JDialog frame;
	private final JLabel titulo = new JLabel("Cadastre um operador ou escolha um existente");
	private final JSeparator separator = new JSeparator();
	private final JLabel labelCodOperador = new JLabel("C\u00F3digo do operador");
	private final JTextField txtOperador = new JTextField();
	private final JTextField txtNome = new JTextField();
	private final JLabel labelNome = new JLabel("Nome");
	private final JTextField txtIniciais = new JTextField();
	private final JLabel labelIniciais = new JLabel("Iniciais");
	private final JButton cadastrar = new JButton("Cadastrar");
	private final JSeparator separator_1 = new JSeparator();
	private final JButton voltar = new JButton("Voltar");

	private DBXml dbData;
	private TelaSelecaoOperador telaSelOp;

	public TelaOperador(JFrame frame, DBXml dbData, TelaSelecaoOperador telaSelOp) {
		this.dbData = dbData;
		this.telaSelOp = telaSelOp;
	}

	void iniciar() {
		this.txtIniciais.setBounds(214, 155, 181, 20);
		this.txtIniciais.setColumns(10);
		this.txtNome.setBounds(214, 127, 181, 20);
		this.txtNome.setColumns(10);
		this.txtOperador.setBounds(214, 96, 181, 20);
		this.txtOperador.setColumns(10);
		frame = new JDialog();
		frame.setModal(true);
		frame.setBounds(100, 100, 483, 349);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.titulo.setBounds(82, 30, 326, 14);
		frame.setTitle("Cadastro de operador");
		
		frame.getContentPane().add(this.titulo);

		this.separator.setBounds(32, 64, 407, 2);
		
		frame.getContentPane().add(this.separator);
		this.labelCodOperador.setBounds(56, 99, 120, 14);
		
		frame.getContentPane().add(this.labelCodOperador);
		
		frame.getContentPane().add(this.txtOperador);
		
		frame.getContentPane().add(this.txtNome);
		this.labelNome.setBounds(56, 130, 46, 14);
		
		frame.getContentPane().add(this.labelNome);
		
		frame.getContentPane().add(this.txtIniciais);
		this.labelIniciais.setBounds(56, 158, 46, 14);
		
		frame.getContentPane().add(this.labelIniciais);
		this.cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codigoInformado = txtOperador.getText();
				String nomeInformado = txtNome.getText();
				String iniciaisInformadas = txtIniciais.getText();
				String regexCodigo = "[0-9]+";
				String regexNome = "^[a-zA-Z]+$";
				String regexIniciais = "[A-Z]+";
				int codigo = 0;
				if(!codigoInformado.matches(regexCodigo) || !nomeInformado.matches(regexNome) || !iniciaisInformadas.matches(regexIniciais)) {
					JOptionPane.showMessageDialog(null, "Por favor preencha todos campos. Campo codigo aceita somente numeros e as iniciais devem estar em letra maiuscula.", "Erro ao cadastrar.", JOptionPane.ERROR_MESSAGE);
				}
				else {
					codigo = Integer.parseInt(codigoInformado);
					Operador operador = new Operador(codigo, nomeInformado, iniciaisInformadas);
					dbData.addOperadores(operador);
					telaSelOp.addOperador(operador);
					txtOperador.setText(null);
					txtNome.setText(null);
					txtIniciais.setText(null);
					frame.revalidate();
				    frame.repaint();
				}

			}
		});
		
		this.cadastrar.setBounds(214, 186, 95, 23);
		frame.getContentPane().add(this.cadastrar);
		
		this.separator_1.setBounds(32, 250, 407, 2);
		
		frame.getContentPane().add(this.separator_1);
		this.voltar.setBounds(350, 186, 95, 23);
		
		frame.getContentPane().add(this.voltar);
		
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}

	public void mostraTela(){
		frame.setVisible(true);
	}
}
