package com.collector.model;

import java.io.Serializable;

public class DadosUsoHardware implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long packetsIn;
	
	private long packetsOut;
	
	private String percentualUsoCPU;
	
	private String totalMemoria;
	
	private String totalMemoriaEmUso;
	
	private String tamanhoDisco;
	
	private String totalDiscoEmUso;

	public long getPacketsIn() {
		return packetsIn;
	}

	public void setPacketsIn(long packetsIn) {
		this.packetsIn = packetsIn;
	}

	public long getPacketsOut() {
		return packetsOut;
	}

	public void setPacketsOut(long packetsOut) {
		this.packetsOut = packetsOut;
	}

	public String getPercentualUsoCPU() {
		return percentualUsoCPU;
	}

	public void setPercentualUsoCPU(String percentualUsoCPU) {
		this.percentualUsoCPU = percentualUsoCPU;
	}

	public String getTotalMemoria() {
		return totalMemoria;
	}

	public void setTotalMemoria(String totalMemoria) {
		this.totalMemoria = totalMemoria;
	}

	public String getTotalMemoriaEmUso() {
		return totalMemoriaEmUso;
	}

	public void setTotalMemoriaEmUso(String totalMemoriaEmUso) {
		this.totalMemoriaEmUso = totalMemoriaEmUso;
	}

	public String getTamanhoDisco() {
		return tamanhoDisco;
	}

	public void setTamanhoDisco(String tamanhoDisco) {
		this.tamanhoDisco = tamanhoDisco;
	}

	public String getTotalDiscoEmUso() {
		return totalDiscoEmUso;
	}

	public void setTotalDiscoEmUso(String totalDiscoEmUso) {
		this.totalDiscoEmUso = totalDiscoEmUso;
	}
}
