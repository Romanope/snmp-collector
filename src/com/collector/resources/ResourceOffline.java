package com.collector.resources;

import java.util.HashMap;
import java.util.Map;

import com.collector.model.DadosConfiguracao;

public class ResourceOffline implements IResource {

	private Map<Long, DadosConfiguracao> configuracaoes;
	
	public ResourceOffline() {
		start();
	}
	
	@Override
	public DadosConfiguracao obterConfiguracao(long identificadorServidor) {
		
		return configuracaoes.get(identificadorServidor);
	}
	
	private void start() {
		
		configuracaoes = new HashMap<Long, DadosConfiguracao>();
		
		DadosConfiguracao dados = new DadosConfiguracao();
		dados.setIdenficadorServidor(10);
		dados.setNomeServidor("Servidor lojas americanas 1");
		dados.setOids(new String[]{"1.3.6.1.2.1.2.2.1.10.1"});
		dados.setPeriodicidade(5000);
		
		configuracaoes.put(dados.getIdenficadorServidor(), dados);
		
		dados = new DadosConfiguracao();
		dados.setIdenficadorServidor(20);
		dados.setNomeServidor("Servidor lojas americanas 2");
		dados.setOids(new String[]{"1.3.6.1.2.1.2.2.1.10.1"});
		dados.setPeriodicidade(5000);
		
		configuracaoes.put(dados.getIdenficadorServidor(), dados);
		
		dados = new DadosConfiguracao();
		dados.setIdenficadorServidor(30);
		dados.setNomeServidor("Servidor lojas americanas 3");
		dados.setOids(new String[]{"1.3.6.1.2.1.2.2.1.10.1"});
		dados.setPeriodicidade(5000);
		
		configuracaoes.put(dados.getIdenficadorServidor(), dados);
	}
}
