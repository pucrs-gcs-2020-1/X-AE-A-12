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

import br.pucrs.gcs.xaea12.model.Conta;
import br.pucrs.gcs.xaea12.model.Movimento;
import br.pucrs.gcs.xaea12.model.Operador;
import br.pucrs.gcs.xaea12.xml.DBXml;

public class TelaTransferencia {

	private JFrame transferencia;
	private Conta conta;
	private Operador operador;
	private DBXml db;
	
	public TelaTransferencia(Operador operador, Conta conta) {
		this.conta = conta;
		this.operador = operador;
		addTransferencia();
	}

	private void addTransferencia() {
		transferencia = new JFrame();
		JLabel label_data = new JLabel("Data");
		JTextField data = new JTextField();
		JLabel titulo = new JLabel("Adicione os dados para transferência");
		JLabel label_nroDocOrigem = new JLabel("Numero do documento da conta origem");
		JTextField nroDocOrigem = new JTextField();
		JLabel label_nroDocDestino = new JLabel("Numero do documento da conta destino");
		JTextField nroDocDestino = new JTextField();
		JLabel label_valor = new JLabel("Valor");
		JTextField valor = new JTextField();
		JLabel label_mensagem = new JLabel("Descricao");
		JTextField mensagem = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		JButton limpar = new JButton("Limpar");
		JButton voltar = new JButton("Voltar");
		JSeparator separator = new JSeparator();
		transferencia.setBounds(100, 100, 483, 349);
		transferencia.setTitle("Realizar transferencia");
		transferencia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		transferencia.getContentPane().setLayout(null);
		valor.setBounds(173, 170, 86, 20);
		valor.setColumns(10);
		nroDocOrigem.setBounds(173, 106, 86, 20);
		nroDocOrigem.setColumns(10);
		nroDocDestino.setBounds(173, 138, 86, 20);
		nroDocDestino.setColumns(10);
		data.setBounds(173, 74, 86, 20);
		data.setColumns(10);
		transferencia.setVisible(true);
		transferencia.setBounds(100, 100, 483, 349);
		transferencia.setTitle("Realizar transferencia");
		transferencia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		transferencia.getContentPane().setLayout(null);
		
		label_data.setBounds(36, 80, 46, 14);	
		transferencia.getContentPane().add(label_data);
		transferencia.getContentPane().add(data);
		
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(127, 23, 228, 20);
		transferencia.getContentPane().add(titulo);
		
		label_nroDocOrigem.setBounds(36, 106, 130, 14);
		label_nroDocDestino.setBounds(36, 138, 130, 14);
		
		transferencia.getContentPane().add(label_nroDocOrigem);
		transferencia.getContentPane().add(nroDocOrigem);
		transferencia.getContentPane().add(label_nroDocDestino);
		transferencia.getContentPane().add(nroDocDestino);
		label_valor.setBounds(36, 170, 46, 14);
		
		transferencia.getContentPane().add(label_valor);
		transferencia.getContentPane().add(valor);
		
		label_mensagem.setBounds(36, 202 , 60, 14);
		mensagem.setBounds(173, 202 , 180, 28);
		transferencia.getContentPane().add(label_mensagem);
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataInformada = data.getText();
				String nroDocO = nroDocOrigem.getText();
				String nroDocD = nroDocDestino.getText();
				String descricaoInformada = mensagem.getText();
				String valorInformado = valor.getText();
				String regexData = "[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]";
				String regexNroDoc = "[0-9]+";
				String regexValor = "[0-9]+.\\d{2}";
				Calendar dataFinal = null;
				if(!dataInformada.matches(regexData)){
					JOptionPane.showMessageDialog(null, "Por favor preencha a data no padr\u00E3o ano/mes/ano e com uma data vÃ¡lida.", "Erro no formato da data.", JOptionPane.ERROR_MESSAGE);
				}
				if(!nroDocO.matches(regexNroDoc)){
					JOptionPane.showMessageDialog(null, "Por favor preencha o campo somente com numeros.", "Erro no formato do numero do documento de origem.", JOptionPane.ERROR_MESSAGE);
				}
				if(!nroDocD.matches(regexNroDoc)){
					JOptionPane.showMessageDialog(null, "Por favor preencha o campo somente com numeros.", "Erro no formato do numero do documento de destino.", JOptionPane.ERROR_MESSAGE);
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
						int nroDocOri = Integer.parseInt(nroDocO);  
						int nroDocDest = Integer.parseInt(nroDocD); 
						double valorfinal = Double.parseDouble(valorInformado);  
						Conta alvo = null;
						Operador opAlvo = null;
						for(Conta conta: db.getContas()) {
							if(conta.getNumero() == nroDocDest) {
								alvo = conta;
							}
						}
						
						for(Operador operador: db.getOperadores()) {
							if(operador.getIniciais() == conta.getOperador()) {
								opAlvo = operador;
							}
						}
						
						Movimento movimentacao = new Movimento(dataFinal, operador, nroDocOri, descricaoInformada, valorfinal);
						Movimento movimentacao2 = new Movimento(dataFinal, opAlvo, alvo.getNumero(), descricaoInformada, valorfinal);
						conta.addMovimento(movimentacao);
						alvo.addMovimento(movimentacao2);
					}
				}
			}
		});
		
		confirmar.setBounds(348, 76, 95, 23);
		transferencia.getContentPane().add(confirmar);
		
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.setText(null);
				nroDocOrigem.setText(null);
				nroDocDestino.setText(null);
				valor.setText(null);
				mensagem.setText(null);
			}
		});
		limpar.setBounds(348, 105, 95, 23);
		
		transferencia.getContentPane().add(limpar);
		voltar.setBounds(348, 166, 95, 23);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferencia.setVisible(false);
			}
		});
		
		transferencia.getContentPane().add(voltar);
		separator.setBounds(36, 52, 402, 11);
		
		transferencia.getContentPane().add(separator);
		transferencia.setBounds(173, 170, 155, 101);
		
		transferencia.getContentPane().add(mensagem);
	}
}
