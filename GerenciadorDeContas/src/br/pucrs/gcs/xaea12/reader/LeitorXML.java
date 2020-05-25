package br.pucrs.gcs.xaea12.reader;

import java.io.Reader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.pucrs.gcs.xaea12.model.Operador;

public class LeitorXML {

	public List<Operador> carregaOperador(Reader xml) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("operador", Operador.class);
		return (List<Operador>) stream.fromXML(xml);
	}
}
