package com.collector.resources;

import com.collector.model.DadosConfiguracao;
import com.collector.model.DadosUsoHardware;

public interface IResource {

	public DadosConfiguracao obterConfiguracao(Long identificadorServidor);
	
	public void enviarDadosColetados(DadosUsoHardware dados);
	
}
