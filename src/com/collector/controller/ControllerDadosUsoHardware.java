package com.collector.controller;

import com.collector.model.DadosUsoHardware;
import com.collector.resources.IResource;
import com.collector.resources.ResourceManager;

public class ControllerDadosUsoHardware {

	private static ControllerDadosUsoHardware controller;
	
	private IResource resources;
	
	private ControllerDadosUsoHardware() {
		resources = ResourceManager.getInstance(true);
	}
	
	public synchronized static ControllerDadosUsoHardware getInstace() {
		
		if (controller == null) {
			controller = new ControllerDadosUsoHardware();
		}
		
		return controller;
	}
	
	public DadosUsoHardware createDadosUsohardware(long packetsIn, long packetsOut, String percentualUsoCPU, String totalMemoria, String totalMemoriaEmUso, String totalDisco, String totalDiscoEmUso) {
		
		DadosUsoHardware dados = new DadosUsoHardware();
		
		dados.setPacketsIn(packetsIn);
		dados.setPacketsOut(packetsOut);
		dados.setPercentualUsoCPU(percentualUsoCPU);
		dados.setTotalMemoria(totalMemoria);
		dados.setTotalMemoriaEmUso(totalMemoriaEmUso);
		dados.setTamanhoDisco(totalDisco);
		dados.setTotalDiscoEmUso(totalDiscoEmUso);
		
		return dados;
	}
	
	public void enviarDadosUsoHardware(DadosUsoHardware dados) {
		
		resources.enviarDadosColetados(dados);
	}
}
