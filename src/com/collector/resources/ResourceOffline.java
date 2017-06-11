package com.collector.resources;

import java.util.HashMap;
import java.util.Map;

import com.collector.model.DadosConfiguracao;
import com.collector.util.Constantes;

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
		dados.setOids(new String[]{Constantes.OID_IF_IN_OCTETS, Constantes.OID_IF_OUT_OCTETS});
		dados.setPeriodicidade(5000);
		dados.setAlgoritmoAutenticacao("1.3.6.1.6.3.10.1.1.3");
		dados.setAlgoritmoCriptografia("1.3.6.1.6.3.10.1.2.4");
		dados.setSecurityName("romano");
		dados.setSecurityLevel(3);
		dados.setSenhaAutenticacao("1234567890");
		dados.setSenhaCriptografia("0987654321");
		dados.setTimeout(10000);
		dados.setUser("romano");
		dados.setVersionSnmp(3);
		
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
