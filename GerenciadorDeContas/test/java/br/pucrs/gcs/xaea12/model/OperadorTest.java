package br.pucrs.gcs.xaea12.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperadorTest {

	private Operador operador1;
	private Operador operador2;
	private Operador operador3;
	private Operador operador4;
	
	@BeforeEach
	void setUp() throws Exception {
		operador1 = new Operador(1, "Operador1", "OP1");
		operador2 = new Operador(2, "Operador2", "OP2");
		operador3 = new Operador(3, "Operador3", "OP3");
		operador4 = new Operador(4, "Operador4", "OP4");
	}
	
	@Test
	void testCreate() {
		Assert.assertNotNull(operador1);
		Assert.assertEquals(2, operador2.getCodigo());
		Assert.assertEquals("Operador3", operador3.getNome());
		Assert.assertEquals("OP4", operador4.getIniciais());
		Assert.assertEquals(true, operador1.isOperador("OP1"));
		Assert.assertEquals(false, operador2.isOperador("OP5"));
	}

}
