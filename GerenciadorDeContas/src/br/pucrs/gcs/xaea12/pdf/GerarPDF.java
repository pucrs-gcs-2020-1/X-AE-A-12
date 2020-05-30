package br.pucrs.gcs.xaea12.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.pucrs.gcs.xaea12.model.Conta;
import br.pucrs.gcs.xaea12.model.Movimento;
import br.pucrs.gcs.xaea12.model.MovimentosTableModel;
import br.pucrs.gcs.xaea12.model.Operador;
import br.pucrs.gcs.xaea12.xml.DBXml;

public final class GerarPDF {

	public static void relatorioGeral(int idxOp, DBXml db) {
		String arq = "pdf/RelatorioGeral.pdf";
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arq));
			doc.open();
			
			doc.addSubject("Gerando PDF Gerenciador de Contas - versão: 0.1");
			doc.addKeywords("http://www.pucrs.br/politecnica/");
			doc.addCreator("X-AE-A-12");
			doc.addAuthor("Operador - "+db.getOperadores().get(idxOp).getNome());
			
			Paragraph titulo = new Paragraph("Relatório Geral de Contas");
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.setFont(new Font(FontFamily.COURIER, 20, Font.BOLD));
			doc.add(titulo);
			
			Locale loc = new Locale("pt", "BR");
			NumberFormat nrf = NumberFormat.getCurrencyInstance(loc);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for(Conta c : db.getContas()) {
				doc.add(new Paragraph(" "));
				doc.add(new Paragraph("Conta "+String.valueOf(c.getNumero())+", criada por XXXX ("+c.getOperador()+") em "+sdf.format(c.getCriacao().getTime())));
				
				PdfPTable tb = new PdfPTable(new float[] { 0.14f, 0.15f, 0.1f, 0.36f, 0.25f });
				tb.setWidthPercentage(100.0f);
				tb.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				PdfPCell cellData = new PdfPCell(new Phrase("Data"));
				cellData.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cellOperador = new PdfPCell(new Phrase("Operador"));
				cellOperador.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cellDoc = new PdfPCell(new Phrase("NroDoc"));
				cellDoc.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cellDescri = new PdfPCell(new Phrase("Descrição"));
				cellDescri.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell cellValor = new PdfPCell(new Phrase("Valor"));
				cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				tb.addCell(cellData);
				tb.addCell(cellOperador);
				tb.addCell(cellDoc);
				tb.addCell(cellDescri);
				tb.addCell(cellValor);
				
				if(c.getMovimentos().size() > 0) {
					PdfPCell pc = new PdfPCell();
					for(Movimento m : c.getMovimentos()) {
						pc = new PdfPCell(new Phrase(sdf.format(m.getData().getTime())));
						pc.setHorizontalAlignment(Element.ALIGN_CENTER);
						tb.addCell(pc);
						
						pc = new PdfPCell(new Phrase(m.getOperador()));
						pc.setHorizontalAlignment(Element.ALIGN_CENTER);
						tb.addCell(pc);
						
						pc = new PdfPCell(new Phrase(String.valueOf(m.getNrdoc())));
						pc.setHorizontalAlignment(Element.ALIGN_CENTER);
						tb.addCell(pc);
						
						pc = new PdfPCell(new Phrase(m.getDescricao()));
						pc.setHorizontalAlignment(Element.ALIGN_LEFT);
						tb.addCell(pc);
						
						pc = new PdfPCell(new Phrase(nrf.format(m.getValor())));
						pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
						tb.addCell(pc);
					}
					
				}
				
				doc.add(tb);
				Paragraph saldo = new Paragraph("SALDO ATUAL: "+nrf.format(c.getSaldo()));
				saldo.setAlignment(Element.ALIGN_RIGHT);
				doc.add(saldo);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		doc.close();
		
		try {
			Runtime.getRuntime().exec("google-chrome-stable ./"+arq);
		} catch (IOException e) {
			// fuga
		}
	}
	
	public static void relatorioParcial(Operador op, Conta ct, MovimentosTableModel mtm) {
		String arq = "pdf/RelatorioParcial.pdf";
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arq));
			doc.open();
			
			doc.addSubject("Gerando PDF Gerenciador de Contas - versão: 0.1");
			doc.addKeywords("http://www.pucrs.br/politecnica/");
			doc.addCreator("X-AE-A-12");
			doc.addAuthor("Operador - "+op.getNome());
			
			Paragraph titulo = new Paragraph("Relatório Parcial de Conta ("+String.valueOf(ct.getNumero())+")");
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.setFont(new Font(FontFamily.COURIER, 20, Font.BOLD));
			doc.add(titulo);
			
			doc.add(new Paragraph(" "));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			doc.add(new Paragraph("Conta "+String.valueOf(ct.getNumero())+", criada por XXXX ("+ct.getOperador()+") em "+sdf.format(ct.getCriacao().getTime())));
			
			PdfPTable tb = new PdfPTable(new float[] { 0.14f, 0.15f, 0.1f, 0.36f, 0.25f });
			tb.setWidthPercentage(100.0f);
			tb.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			PdfPCell cellData = new PdfPCell(new Phrase("Data"));
			cellData.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellOperador = new PdfPCell(new Phrase("Operador"));
			cellOperador.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellDoc = new PdfPCell(new Phrase("NroDoc"));
			cellDoc.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellDescri = new PdfPCell(new Phrase("Descrição"));
			cellDescri.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellValor = new PdfPCell(new Phrase("Valor"));
			cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			tb.addCell(cellData);
			tb.addCell(cellOperador);
			tb.addCell(cellDoc);
			tb.addCell(cellDescri);
			tb.addCell(cellValor);
			
			if(mtm.getRowCount() > 0) {
				PdfPCell pc = new PdfPCell();
				for(int i=0; i<mtm.getRowCount(); i++) {
					pc = new PdfPCell(new Phrase(String.valueOf(mtm.getValueAt(i, 0))));
					pc.setHorizontalAlignment(Element.ALIGN_CENTER);
					tb.addCell(pc);
					
					pc = new PdfPCell(new Phrase(String.valueOf(mtm.getValueAt(i, 1))));
					pc.setHorizontalAlignment(Element.ALIGN_CENTER);
					tb.addCell(pc);
					
					pc = new PdfPCell(new Phrase(String.valueOf(mtm.getValueAt(i, 2))));
					pc.setHorizontalAlignment(Element.ALIGN_CENTER);
					tb.addCell(pc);
					
					pc = new PdfPCell(new Phrase(String.valueOf(mtm.getValueAt(i, 3))));
					pc.setHorizontalAlignment(Element.ALIGN_LEFT);
					tb.addCell(pc);
					
					pc = new PdfPCell(new Phrase(String.valueOf(mtm.getValueAt(i, 4))));
					pc.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tb.addCell(pc);
				}
			}
			
			doc.add(tb);
			Paragraph saldo = new Paragraph("SALDO ATUAL: "+String.valueOf(mtm.getTotal()));
			saldo.setAlignment(Element.ALIGN_RIGHT);
			doc.add(saldo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		doc.close();
		
		try {
			Runtime.getRuntime().exec("google-chrome-stable ./"+arq);
		} catch (IOException e) {
			// fuga
		}
	}
}
