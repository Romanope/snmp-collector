package com.collector.model;

import java.io.Serializable;

public class Servidor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String nome;
	
	private String sistemaOperacional;
	
	private long periodicidade;
	
	private String oids;

	private String securityName;
	
	private String user;
	
	private String senhaAutenticacao;
	
	private String senhaCriptografia;
	
	private String algoritmoAutenticacao;
	
	private String algoritmoCriptografia;
	
	private int versionSnmp;
	
	private int securityLevel;
	
	private long timeout;
	
	private String enderecoIP;
	
	public Servidor() {
		
	}

	public long getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(long periodicidade) {
		this.periodicidade = periodicidade;
	}

	public String getOids() {
		return oids;
	}

	public void setOids(String oids) {
		this.oids = oids;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenhaAutenticacao() {
		return senhaAutenticacao;
	}

	public void setSenhaAutenticacao(String senhaAutenticacao) {
		this.senhaAutenticacao = senhaAutenticacao;
	}

	public String getSenhaCriptografia() {
		return senhaCriptografia;
	}

	public void setSenhaCriptografia(String senhaCriptografia) {
		this.senhaCriptografia = senhaCriptografia;
	}

	public String getAlgoritmoAutenticacao() {
		return algoritmoAutenticacao;
	}

	public void setAlgoritmoAutenticacao(String algoritmoAutenticacao) {
		this.algoritmoAutenticacao = algoritmoAutenticacao;
	}

	public String getAlgoritmoCriptografia() {
		return algoritmoCriptografia;
	}

	public void setAlgoritmoCriptografia(String algoritmoCriptografia) {
		this.algoritmoCriptografia = algoritmoCriptografia;
	}

	public int getVersionSnmp() {
		return versionSnmp;
	}

	public void setVersionSnmp(int versionSnmp) {
		this.versionSnmp = versionSnmp;
	}

	public int getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getEnderecoIP() {
		return enderecoIP;
	}

	public void setEnderecoIP(String enderecoIP) {
		this.enderecoIP = enderecoIP;
	}
}
