package br.pucrs.gcs.xaea12.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.pucrs.gcs.xaea12.model.Conta;
import br.pucrs.gcs.xaea12.model.Operador;
import br.pucrs.gcs.xaea12.xml.DBXml;

public class TelaConta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialog frame;
	private final JLabel lblTitulo = new JLabel("Criando uma conta");
	private JSeparator js = new JSeparator();
	private final JLabel lblNumero = new JLabel("Conta nº:");
	private JFormattedTextField txfNumero;
	private final JLabel lblCriador = new JLabel("Criador por:");
	private final JTextField txfCriador = new JTextField();
	private final JLabel lblData = new JLabel("Data de criação:");
	private final JTextField txfData = new JTextField();
	private final JButton btnVoltar = new JButton("Voltar");
	private final JButton btnCriar = new JButton("Criar Conta");
	private List<Conta> contas;
	private final Operador op;
	private final Calendar data = Calendar.getInstance();
	private TelaPrincipal tela;
	
	public TelaConta(JFrame fr, Operador op, List<Conta> lc, TelaPrincipal tp) {
		this.frame = new JDialog(fr);
		this.op = op;
		this.contas = lc;
		this.tela = tp;
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
		
		JPanel jp2 = new JPanel(new GridLayout(3, 2, 0, 0));
		jp2.setPreferredSize(new Dimension(300, 90));
		jp2.add(this.lblNumero);
		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter("######");
			mf.setPlaceholderCharacter('0');
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.txfNumero = new JFormattedTextField(mf);
		jp2.add(this.txfNumero);
		jp2.add(this.lblCriador);
		this.txfCriador.setEnabled(false);
		this.txfCriador.setText(this.op.toString());
		jp2.add(this.txfCriador);
		jp2.add(this.lblData);
		this.txfData.setEnabled(false);
		this.txfData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.data.getTime()));
		jp2.add(this.txfData);
		jp.add(jp2);
		
		js = new JSeparator(JSeparator.HORIZONTAL);
		js.setPreferredSize(new Dimension(300, 5));
		jp.add(js);
		
		JPanel jp3 = new JPanel(new GridLayout(1, 2, 0, 0));
		jp3.setPreferredSize(new Dimension(300, 50));
		this.btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		jp3.add(this.btnVoltar);
		this.btnCriar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					NumberFormat nf = NumberFormat.getCurrencyInstance();
					int num = Integer.parseInt(txfNumero.getText());
					for(Conta c : contas) {
						if(num == c.getNumero())
							throw new Exception("Conta já existente!");
					}
					
					contas.add(new Conta(Integer.parseInt(txfNumero.getText()), data, op));
					tela.setIdxConta(contas.size()-1);
					tela.preencheDadosTela();
					tela.preencheTabela();
					frame.dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Tente outra conta", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jp3.add(this.btnCriar);
		jp.add(jp3);
		
		this.frame.setVisible(true);
	}

}
