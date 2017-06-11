package com.collector;

import com.collector.model.DadosConfiguracao;
import com.collector.util.Constantes;

public class Collector implements Runnable {

	private SNMPCollector snmpCollector;
	
	private long interval;

	private long lastRateIn;
	
	private long lastRateOut;
	
	private long ratePacketsIn;
	
	private long ratePacketsOut;
	
	public Collector(DadosConfiguracao dadosConfig, String ipAddress) {
		
		this.interval = dadosConfig.getPeriodicidade();
		snmpCollector = new SNMPCollector(dadosConfig, ipAddress);
		ratePacketsIn = 0;
		ratePacketsOut = 0;
		lastRateIn = 0;
		lastRateOut = 0;
	}
	
	@Override
	public void run() {
		
		String oidOut = "";
		String oidIn = "";
		
		while (true) {
			String response = snmpCollector.getBulk();
			if (response == null) {
				//adicionar no log
			}
			
			response = response.substring(response.indexOf("VBS"), response.length());
			response = response.substring(response.indexOf("[")+1, response.indexOf("]"));
			String[] values = response.split(";");
			for (String value: values) {
				String[] v = value.trim().split("=");
				long currentValue = Long.valueOf(v[1].trim());
				if (v[0].contains("1.3.6.1.2.1.2.2.1.10")) {
					if (lastRateIn == 0) {
						lastRateIn = currentValue;
					}
					ratePacketsIn = currentValue - lastRateIn;
					lastRateIn = currentValue;
				} else if (v[0].contains(Constantes.OID_IF_OUT_OCTETS) || v[0].contains("1.3.6.1.2.1.2.2.1.17")) {
					if (lastRateOut == 0) {
						lastRateOut = currentValue;
					}
					ratePacketsOut = currentValue - lastRateOut;
					lastRateOut = currentValue;
				}
			}
			System.out.println("RATE IN " + ratePacketsIn + " RATE OUT " + ratePacketsOut);
			try {
				Thread.currentThread().sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
