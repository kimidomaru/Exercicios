package br.ce.luiks.servicos;

import static br.ce.luiks.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.luiks.entidades.Filme;
import br.ce.luiks.entidades.Locacao;
import br.ce.luiks.entidades.Usuario;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws Exception{
		if(filme.getEstoque() == 0) {
			throw new Exception("Filme esgotado!");
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar m�todo para salvar
		
		return locacao;
	}

}