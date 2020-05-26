package br.pucrs.gcs.xaea12.xml;

import java.io.FileReader;
import java.io.FileWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public final class PersisteXML {

	private static final String ARQXML = "db/base.xml";
	
	public static DBXml carregaXML() {
		XStream stream = new XStream(new DomDriver());
		stream.alias("dbxml", DBXml.class);
		try {
			FileReader file = new FileReader(ARQXML);
			DBXml db = (DBXml) stream.fromXML(file);
			file.close();
			return db;
		} catch (Exception e) {
			System.out.println("Não carregou o arquivo\n"+e.getMessage());
		}
		return null;
	}
	
	public static void salvaXML(DBXml db) throws Exception {
		XStream stream = new XStream(new DomDriver());
		stream.alias("dbxml", DBXml.class);
		try {
			FileWriter file = new FileWriter(ARQXML);
			file.write(stream.toXML(db));
			file.close();
		} catch (Exception e) {
			throw new Exception("Falha ao salvar\n"+e.getMessage());
		}
	}
}
