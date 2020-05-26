package br.pucrs.gcs.xaea12.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ContaTest {

	private static Conta conta;
	private static List<Movimento> movimentos;
	private static Calendar data;
	private static Operador operadores[];
	
	@BeforeEach
	void setUp() throws Exception {
		operadores = new Operador[5];
		for(int i = 0; i<5; i++) {
			operadores[i] = new Operador(i+1, "Operador"+i, "OP"+i);
		}
		
		data = Calendar.getInstance();
		movimentos = new ArrayList<Movimento>();
		for(int i = 10; i<20; i++) {
			data.set(Calendar.DAY_OF_MONTH, i);
			movimentos.add(new Movimento(data, operadores[0], i*1000, "Movimento 1 dia "+i, (i * 28.94)));
			movimentos.add(new Movimento(data, operadores[1], i*1001, "Movimento 1 dia "+i, (i * -30.94)));
			movimentos.add(new Movimento(data, operadores[2], i*1002, "Movimento 1 dia "+i, (i * 68.94)));
			movimentos.add(new Movimento(data, operadores[3], i*1003, "Movimento 1 dia "+i, (i * -82.4)));
			movimentos.add(new Movimento(data, operadores[4], i*1004, "Movimento 1 dia "+i, (i * -128.45)));
		}
	}

	@Test
	void testA() {
		// testConta
		data.set(Calendar.DAY_OF_MONTH, 2);
		conta = new Conta(1254, data, operadores[4]);
		Assert.assertNotNull(conta);
	}

	@Test
	void testB() {
		// testGetNumero
		Assert.assertEquals(1254, conta.getNumero());
	}

	@Test
	void testC() {
		// testGetCriacao
		Assert.assertEquals(2, conta.getCriacao().get(Calendar.DAY_OF_MONTH));
		
		conta.getCriacao().set(Calendar.DAY_OF_MONTH, 12);
		Assert.assertEquals(2, conta.getCriacao().get(Calendar.DAY_OF_MONTH));
	}

	@Test
	void testD() {
		// testGetOperador
		Assert.assertEquals("OP4", conta.getOperador());
	}

	@Test
	void testE() {
		// testGetSaldo
		Assert.assertEquals(0, conta.getSaldo(), 0.00001);
	}
	
	@Test
	void testF() {
		// testGetMovimentos
		// testSetMovimentos
		Assert.assertTrue(conta.getMovimentos().isEmpty());
		conta.setMovimentos(movimentos);
		Assert.assertFalse(conta.getMovimentos().isEmpty());
		int tamanho = conta.getMovimentos().size();
		conta.getMovimentos().add(new Movimento(data, operadores[2], 998899, "Teste", 800));
		Assert.assertEquals(tamanho, conta.getMovimentos().size());
		conta.getMovimentos().remove(0);
		Assert.assertEquals(tamanho, conta.getMovimentos().size());
	}

	@Test
	void testG() {
		// testAddMovimento
		data.set(Calendar.DAY_OF_MONTH, 21);
		conta.addMovimento(new Movimento(data, operadores[3], 999999, "Ultima movimentação", 1000.89));
		Assert.assertEquals(-19866.06, conta.getSaldo(), 0.001);
	}

}
