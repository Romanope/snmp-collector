package com.collector.testes;

import com.collector.Collector;
import com.collector.model.DadosConfiguracao;
import com.collector.resources.IResource;
import com.collector.resources.ResourceManager;
import com.collector.util.Constantes;
import com.collector.util.Util;

public class Main {

	public static void main(String[] args) {
		
		IResource resources = ResourceManager.getInstance(false);
		DadosConfiguracao dadosConfig = resources.obterConfiguracao(10);
		
		Collector collector = new Collector(dadosConfig, Util.getIpAddress(Constantes.INTERFACE_NAME));
		
		Thread thread = new Thread(collector);
		thread.start();
	}
}
