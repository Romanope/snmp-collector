package com.collector;

import com.collector.model.DadosConfiguracao;

public class Collector implements Runnable {

	private SNMPCollector snmpCollector;
	
	private long interval;
	
	public Collector(DadosConfiguracao dadosConfig, String ipAddress) {
		
		snmpCollector = new SNMPCollector(dadosConfig, ipAddress);
	}
	
	@Override
	public void run() {
		
		String response = snmpCollector.getBulk();
		if (response == null) {
			//adicionar no log
		}
		
	}
}
