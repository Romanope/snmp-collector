package com.collector.resources;

import com.collector.model.DadosConfiguracao;

public interface IResource {

	public DadosConfiguracao obterConfiguracao(long identificadorServidor);
	
}
