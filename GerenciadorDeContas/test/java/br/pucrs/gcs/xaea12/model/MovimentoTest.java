package br.pucrs.gcs.xaea12.model;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovimentoTest {

	private static Operador operador1;
	private static Operador operador2;
	
	@BeforeEach
	void setUp() throws Exception {
		operador1 = new Operador(1, "Operador1", "OP1");
		operador2 = new Operador(2, "Operador2", "OP2");
	}

	@Test
	void testCreate() {
		Calendar hoje = Calendar.getInstance();
		Movimento movimento1 = new Movimento(hoje, operador1, 1212, "Movimento de teste", 18.05);
		Movimento movimento2 = new Movimento(hoje, operador2, 1213, "Movimento de teste2", 14.53);
		
		Assert.assertEquals(1212, movimento1.getNrdoc());
		Assert.assertEquals("OP2", movimento2.getOperador());
		Assert.assertEquals(hoje, movimento1.getData());
		Assert.assertEquals(14.53, movimento2.getValor(), 0.00001);
	}
	
	@Test
	void testAlterar() {
		Calendar hoje = Calendar.getInstance();
		hoje.set(Calendar.DAY_OF_MONTH, 10);
		Movimento movimento = new Movimento(hoje, operador1, 1234, "Move", 12.98);
		
		movimento.getData().set(Calendar.DAY_OF_MONTH, 15);
		Assert.assertEquals(10, movimento.getData().get(Calendar.DAY_OF_MONTH));
	}

}
