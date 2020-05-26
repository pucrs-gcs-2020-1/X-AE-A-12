package br.pucrs.gcs.xaea12.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.pucrs.gcs.xaea12.model.Movimento;
import br.pucrs.gcs.xaea12.model.Operador;

public class TelaMovimentacao {

	private JFrame movimentacao;
	private Operador operador;
	
	public TelaMovimentacao(Operador operador) {
		this.operador = operador;
		addMovimentacao();
	}

	private void addMovimentacao() {
		movimentacao = new JFrame();
		JLabel label_data = new JLabel("Data");
		JTextField data = new JTextField();
		JLabel titulo = new JLabel("Adicione os dados da movimenta\u00E7\u00E3o");
		JLabel label_nroDoc = new JLabel("N\u00FAmero do documento");
		JTextField nroDoc = new JTextField();
		JLabel label_valor = new JLabel("Valor");
		JTextField valor = new JTextField();
		JLabel label_descricao = new JLabel("Descri\u00E7\u00E3o");
		JTextField descricao = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		JButton limpar = new JButton("Limpar");
		JButton voltar = new JButton("Voltar");
		JSeparator separator = new JSeparator();
		movimentacao.setBounds(100, 100, 483, 349);
		movimentacao.setTitle("Adicionar Movimenta\u00E7\u00E3o");
		movimentacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		movimentacao.getContentPane().setLayout(null);
		valor.setBounds(173, 138, 86, 20);
		valor.setColumns(10);
		nroDoc.setBounds(173, 106, 86, 20);
		nroDoc.setColumns(10);
		data.setBounds(173, 74, 86, 20);
		data.setColumns(10);
		movimentacao.setVisible(true);
		movimentacao.setBounds(100, 100, 483, 349);
		movimentacao.setTitle("Adicionar Movimenta\u00E7\u00E3o");
		movimentacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		movimentacao.getContentPane().setLayout(null);
		
		label_data.setBounds(36, 80, 46, 14);	
		movimentacao.getContentPane().add(label_data);
		movimentacao.getContentPane().add(data);
		
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(127, 23, 228, 20);
		movimentacao.getContentPane().add(titulo);
		
		label_nroDoc.setBounds(36, 106, 130, 14);
		
		movimentacao.getContentPane().add(label_nroDoc);
		movimentacao.getContentPane().add(nroDoc);
		label_valor.setBounds(36, 138, 46, 14);
		
		movimentacao.getContentPane().add(label_valor);
		movimentacao.getContentPane().add(valor);
		
		label_descricao.setBounds(36, 170, 60, 14);
		movimentacao.getContentPane().add(label_descricao);
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataInformada = data.getText();
				String nroDocInformado = nroDoc.getText();
				String descricaoInformada = descricao.getText();
				String valorInformado = valor.getText();
				String regexData = "[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]";
				String regexNroDoc = "[0-9]+";
				String regexValor = "[0-9]+.\\d{2}";
				Calendar dataFinal = null;
				if(!dataInformada.matches(regexData)){
					JOptionPane.showMessageDialog(null, "Por favor preencha a data no padr\u00E3o ano/mes/ano e com uma data válida.", "Erro no formato da data.", JOptionPane.ERROR_MESSAGE);
				}
				if(!nroDocInformado.matches(regexNroDoc)){
					JOptionPane.showMessageDialog(null, "Por favor preencha o campo somente com numeros.", "Erro no formato do numero do documento.", JOptionPane.ERROR_MESSAGE);
				}
				if(!valorInformado.matches(regexValor)){
					JOptionPane.showMessageDialog(null, "Por favor preencha o campo no seguinte formato 11.25.", "Erro no formato do valor.", JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(descricaoInformada.equals("")) {
						JOptionPane.showMessageDialog(null, "Por favor preencha todos campos.", "Erro de confirmacao.", JOptionPane.ERROR_MESSAGE);
					}
					else{
						try {
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							dataFinal  = Calendar.getInstance();
							dataFinal.setTime(df.parse(dataInformada));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						int nroDocFinal = Integer.parseInt(nroDocInformado);  
						double valorfinal = Double.parseDouble(valorInformado);  
						Movimento movimentacao = new Movimento(dataFinal, operador, nroDocFinal, descricaoInformada, valorfinal);
					}
				}
			}
		});
		
		confirmar.setBounds(348, 76, 95, 23);
		movimentacao.getContentPane().add(confirmar);
		
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.setText(null);
				nroDoc.setText(null);
				valor.setText(null);
				descricao.setText(null);
			}
		});
		limpar.setBounds(348, 105, 95, 23);
		
		movimentacao.getContentPane().add(limpar);
		voltar.setBounds(348, 166, 95, 23);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movimentacao.setVisible(false);
			}
		});
		
		movimentacao.getContentPane().add(voltar);
		separator.setBounds(36, 52, 402, 11);
		
		movimentacao.getContentPane().add(separator);
		descricao.setBounds(173, 170, 155, 101);
		
		movimentacao.getContentPane().add(descricao);
	}
}
