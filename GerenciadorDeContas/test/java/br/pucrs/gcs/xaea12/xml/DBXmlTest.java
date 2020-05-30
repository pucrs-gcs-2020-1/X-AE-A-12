package br.pucrs.gcs.xaea12.xml;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import br.pucrs.gcs.xaea12.model.Conta;
import br.pucrs.gcs.xaea12.model.Movimento;
import br.pucrs.gcs.xaea12.model.Operador;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DBXmlTest {

	private static DBXml db;
	private static List<Conta> lstConta;
	private static List<Operador> lstOperador;
	
	@BeforeEach
	void setUp() throws Exception {
		lstConta = new ArrayList<Conta>();
		lstOperador = new ArrayList<Operador>();
		for(int i=1; i<11; i++) {
			lstOperador.add(new Operador(i, "Operador"+i, "OP"+i));
			lstConta.add(new Conta(123*i, Calendar.getInstance(), lstOperador.get(Math.round(i/2))));
			for(int j=i; j>=0; j--) {
				lstConta.get(i-1).addMovimento(new Movimento(Calendar.getInstance(), lstOperador.get(i-1), (i+j)*33, "Movimento "+j+" c: "+i, (i % 2 == 0) ? (238*i) : (0- (129*i))));
			}
		}
	}

	@Test
	void testA() {
		// testDBXml
		Assert.assertNull(db);
		db = new DBXml(lstOperador, lstConta);
		Assert.assertNotNull(db);
	}

	@Test
	void testB() {
		// testGetOperadores
		Operador op = db.getOperadores().get(9);
		Assert.assertEquals("OP10", op.getIniciais());
	}

	@Test
	void testC() {
		// testAddOperadores
		int t = db.getOperadores().size();
		db.addOperadores(new Operador(3456, "OperadorTeste", "OPT"));
		Assert.assertEquals(t+1, db.getOperadores().size());
		Assert.assertEquals("OperadorTeste", db.getOperadores().get(t).getNome());
	}

	@Test
	void testD() {
		// testGetContas
		Conta c = db.getContas().get(7);
		Assert.assertEquals(984, c.getNumero());
	}

	@Test
	void testE() {
		// testAddContas
		int t = db.getContas().size();
		db.addContas(new Conta(9876, Calendar.getInstance(), db.getOperadores().get(7)));
		Assert.assertEquals(t+1, db.getContas().size());
		Assert.assertEquals("OP8", db.getContas().get(t).getOperador());
	}

	@Test
	void testF() {
		// testSalvar
		Assert.assertEquals(1, db.salvar());
	}

	@Test
	void testG() {
		// testInicializar
		DBXml db2 = new DBXml();
		db2.inicializar();
		Assert.assertEquals(db.getContas().size(), db2.getContas().size());
		Assert.assertEquals(db.getOperadores().size(), db2.getOperadores().size());
		Assert.assertEquals(db.getContas().get(3).getNumero(), db2.getContas().get(3).getNumero());
		Assert.assertEquals(db.getOperadores().get(6).getNome(), db2.getOperadores().get(6).getNome());
		Assert.assertEquals(db.getContas().get(10).getOperador(), db2.getOperadores().get(7).getIniciais());
	}

}
