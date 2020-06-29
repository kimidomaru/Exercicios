package br.ce.luiks.servicos;

import static br.ce.luiks.utils.DataUtils.isMesmaData;
import static br.ce.luiks.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.luiks.entidades.Filme;
import br.ce.luiks.entidades.Locacao;
import br.ce.luiks.entidades.Usuario;

public class LocacaoServiceTest {
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException excep = ExpectedException.none();
	
	@Test
	public void teste() throws Exception {
		
		//cenario
		Filme joker = new Filme("Joker", 10, 25.50);
		Usuario user = new Usuario("Joao");
		LocacaoService teste = new LocacaoService();
		
		//a��o
		Locacao loc = teste.alugarFilme(user, joker);
			
		//verifica��o
			
		//TESTES
		error.checkThat(loc.getValor(), is(equalTo(25.5)));
		error.checkThat(isMesmaData(loc.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(loc.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
		
		
		//verifica��o
		/*System.out.println("Usu�rio: "+ loc.getUsuario().getNome() +
				"\nFilme: "+ loc.getFilme().getNome() + 
				"\nValor: " + loc.getValor() +
				"\nData de loca��o: " + loc.getDataLocacao() + 
				"\nData de devolu��o: " + loc.getDataRetorno() );
		
		System.out.println();*/
		
		/*
		assertThat(loc.getValor(), is(equalTo(25.5)));
		assertThat(loc.getValor(), is(not(7.0)));
		assertThat(isMesmaData(loc.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(loc.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
		*/
		
		/*
		Assert.assertEquals(25.50, loc.getValor(), 0.01);
		Assert.assertTrue(DataUtils.isMesmaData(loc.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(loc.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		*/
		
		/*System.out.println(loc.getValor() == 25.50f);
		System.out.println(DataUtils.isMesmaData(loc.getDataLocacao(), new Date()));
		System.out.println(DataUtils.isMesmaData(loc.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		*/
	}
	
	//Teste esperando excecao, forma elegante
	@Test(expected = Exception.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		//cenario
		Filme joker = new Filme("Joker", 0, 25.50);
		Usuario user = new Usuario("Joao");
		LocacaoService teste = new LocacaoService();
				
		//a��o
		Locacao loc = teste.alugarFilme(user, joker);
					
	}
	
	//Teste esperando excecao, forma robusta
	@Test
	public void testeLocacao_filmeSemEstoque2() throws Exception {
		//cenario
		Filme joker = new Filme("Joker", 0, 25.50);
		Usuario user = new Usuario("Joao");
		LocacaoService teste = new LocacaoService();
				
		//a��o
		try {
			teste.alugarFilme(user, joker);
			Assert.fail("Deveria ter lan�ado uma exception");
		} catch (Exception e) {
			// TODO: handle exception
			Assert.assertThat(e.getMessage(), is("Filme esgotado!"));
		}
		
	}
	
	//Teste esperando excecao, forma nova
	@Test
	public void testeLocacao_filmeSemEstoque3() throws Exception {
		//cenario
		Filme joker = new Filme("Joker", 0, 25.50);
		Usuario user = new Usuario("Joao");
		LocacaoService teste = new LocacaoService();
			
		excep.expect(Exception.class);
		excep.expectMessage("Filme esgotado!");
		//a��o
		teste.alugarFilme(user, joker);
		
	}
}
