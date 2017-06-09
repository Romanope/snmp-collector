package com.collector.testes;

public class Testes {

	public static void main(String[] args) {
		
		String response = "RESPONSE[{contextEngineID=80:00:1f:88:80:9c:b6:a9:5d:63:62:33:59:00:00:00:00, contextName=}, requestID=1415402555, errorStatus=0, errorIndex=0, VBS[1.3.6.1.2.1.2.2.1.10.2 = 369506419]]";
		
		int index = response.indexOf("1.3.6.1.2.1.2.2.1.10.2");
		
		String response2 = response.substring(index, response.length());
		String response22[] = response2.substring(0, response2.indexOf("]")).trim().split("=");
		System.out.println(response22[0] + "  " + response22[1]);
	}
}
