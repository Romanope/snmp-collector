package com.collector;

import com.collector.controller.ControllerCollector;
import com.collector.controller.ControllerDadosUsoHardware;
import com.collector.model.Servidor;
import com.collector.model.DadosUsoHardware;
import com.collector.util.Constantes;
import com.collector.util.Logger;
import com.collector.util.Util;

public class Collector implements Runnable {

	private SNMPCollector snmpCollector;
	
	private String servidorID;
	
	private long interval;

	private long lastRateIn;
	
	private long lastRateOut;
	
	private long ratePacketsIn;
	
	private long ratePacketsOut;
	
	private int  t;
	
	public Collector(String ipAddress, String identificadorServidor) {
		
		Servidor dadosConfig = ControllerCollector.getInstance().obterDadosConfiguracao(identificadorServidor);

		if (dadosConfig != null) {
			servidorID = identificadorServidor;
			this.interval = dadosConfig.getPeriodicidade();
			snmpCollector = new SNMPCollector(dadosConfig, ipAddress);
			ratePacketsIn = 0;
			ratePacketsOut = 0;
			lastRateIn = 0;
			lastRateOut = 0;
		}
	}
	
	@Override
	public void run() {

		while (true) {
			String response = snmpCollector.getBulk();
			if (response == null) {
				// adicionar no log
			}

			response = response.substring(response.indexOf("VBS"), response.length());
			response = response.substring(response.indexOf("[") + 1, response.indexOf("]"));
			String[] values = response.split(";");
			for (String value : values) {
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

			if (t > 0) {
				DadosUsoHardware dados = ControllerDadosUsoHardware.getInstace().createDadosUsoHardware(servidorID, ratePacketsIn,
						ratePacketsOut, "", "", "", "", "");
				ControllerDadosUsoHardware.getInstace().enviarDadosUsoHardware(dados);
			}

			t++;
			Logger.logInScreen("COLLECTOR", "t = " + t + ": RATE IN " + ratePacketsIn + " RATE OUT " + ratePacketsOut);
			try {
				Thread.currentThread().sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
