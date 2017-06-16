package com.collector.resources;

import java.util.HashMap;
import java.util.Map;

import com.collector.model.Servidor;
import com.collector.model.DadosUsoHardware;
import com.collector.util.Constantes;
import com.google.gson.Gson;

public class ResourceOffline implements IResource {

	private Map<String, Servidor> configuracaoes;
	
	public ResourceOffline() {
		start();
	}
	
	@Override
	public Servidor obterConfiguracao(String identificadorServidor) {
		
		return configuracaoes.get(identificadorServidor);
	}
	
	private void start() {
		
		configuracaoes = new HashMap<String, Servidor>();
		
		Servidor dados = new Servidor();
		dados.setId("234sfdsfgsdfg");
		dados.setNome("Servidor lojas americanas 1");
		dados.setOids(Constantes.OID_IF_IN_OCTETS +"#"+Constantes.OID_IF_OUT_OCTETS);
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
		
		
		
		configuracaoes.put(dados.getId(), dados);
	}

	@Override
	public void enviarDadosColetados(DadosUsoHardware dados) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Servidor dados = new Servidor();
		
		dados.setNome("Servidor lojas americanas 2");
		dados.setOids(Constantes.OID_IF_IN_OCTETS +"#"+Constantes.OID_IF_OUT_OCTETS);
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
		System.out.println(new Gson().toJson(dados));
	}

	@Override
	public String cadastrarServidor(Servidor servidor) {
		// TODO Auto-generated method stub
		return null;
	}
}
