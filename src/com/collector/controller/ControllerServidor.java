package com.collector.controller;

import com.collector.model.Servidor;
import com.collector.resources.IResource;
import com.collector.resources.ResourceManager;

public class ControllerServidor {

	private static ControllerServidor controllerServidor;
	
	private IResource resources;
	
	private ControllerServidor() {
		resources = ResourceManager.getInstance(true);
	}
	
	public static ControllerServidor getInstance() {
		
		if (controllerServidor == null) {
			controllerServidor = new ControllerServidor();
		}
		
		return controllerServidor;
	}
	
	public Servidor createInstanceServidor(String nome, String so, String ip, String intervalo, String oids, String user, String passAutenticacao,
			String passCriptografia, String algoAutenticacao, String algoCrip, String versionSNMP, String securityLevel, String timeout) {
		
		Servidor servidor = new Servidor();
		
		servidor.setNome(nome);
		servidor.setSistemaOperacional(so);
		servidor.setEnderecoIP(ip);
		servidor.setPeriodicidade(Long.valueOf(intervalo.trim()));
		servidor.setOids(oids);
		servidor.setUser(user);
		servidor.setSenhaAutenticacao(passAutenticacao);
		servidor.setSenhaCriptografia(passCriptografia);
		servidor.setAlgoritmoAutenticacao(algoAutenticacao);
		servidor.setAlgoritmoCriptografia(algoCrip);
		servidor.setVersionSnmp(Integer.valueOf(versionSNMP));
		servidor.setSecurityLevel(Integer.valueOf(securityLevel));
		servidor.setTimeout(Long.valueOf(timeout));
		
		return servidor;
	}
	
	public String cadastrarServidor(Servidor servidor) {
		return resources.cadastrarServidor(servidor);
	}
}
