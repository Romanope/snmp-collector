package com.collector.resources;

import com.collector.model.Servidor;
import com.collector.model.DadosUsoHardware;

public interface IResource {

	public Servidor obterConfiguracao(Long identificadorServidor);
	
	public void enviarDadosColetados(DadosUsoHardware dados);
	
	public String cadastrarServidor(Servidor servidor);
}
