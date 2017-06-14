package com.collector.controller;

import com.collector.model.Servidor;
import com.collector.resources.IResource;
import com.collector.resources.ResourceManager;

public class ControllerCollector {

	private static ControllerCollector controllerCollector;
	
	private IResource resources;
	
	private ControllerCollector() {
		this.resources = ResourceManager.getInstance(false);
	}
	
	public synchronized static ControllerCollector getInstance() {
		if (controllerCollector == null) {
			controllerCollector = new ControllerCollector();
		}
		
		return controllerCollector;
	}
	
	public Servidor obterDadosConfiguracao(Long identificadorServidor) {
		
		return resources.obterConfiguracao(identificadorServidor);
	}
}
