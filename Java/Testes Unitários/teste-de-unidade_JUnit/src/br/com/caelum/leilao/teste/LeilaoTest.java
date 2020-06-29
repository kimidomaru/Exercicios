package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {

	@Test
	public void receberUmLance() {
		Leilao leilao = new Leilao("Macbook pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.0001);
	}
	
	@Test
	public void receberMultiplosLance() {
		Leilao leilao = new Leilao("Macbook pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.0001);
	}
	
	@Test
	public void impedirLanceSeguido() {
		Leilao leilao = new Leilao("Macbook pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(steveJobs, 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void impedirMaisQueCincoLances() {
		Leilao leilao = new Leilao("Macbook pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		
		leilao.propoe(new Lance(steveJobs, 4000));
		leilao.propoe(new Lance(billGates, 5000));
		
		leilao.propoe(new Lance(steveJobs, 6000));
		leilao.propoe(new Lance(billGates, 7000));
		
		leilao.propoe(new Lance(steveJobs, 8000));
		leilao.propoe(new Lance(billGates, 9000));
		
		leilao.propoe(new Lance(steveJobs, 10000));
		leilao.propoe(new Lance(billGates, 11000));
		
		//deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 12000));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
		
	}
}
