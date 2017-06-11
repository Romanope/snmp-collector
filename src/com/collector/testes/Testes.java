package com.collector.testes;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class Testes {

	public static void main(String[] args) throws SocketException {
		
//		String response = "RESPONSE[{contextEngineID=80:00:1f:88:80:9c:b6:a9:5d:63:62:33:59:00:00:00:00, contextName=}, requestID=1415402555, errorStatus=0, errorIndex=0, VBS[1.3.6.1.2.1.2.2.1.10.2 = 369506419]]";
//		
//		int index = response.indexOf("1.3.6.1.2.1.2.2.1.10.2");
//		
//		String response2 = response.substring(index, response.length());
//		String response22[] = response2.substring(0, response2.indexOf("]")).trim().split("=");
//		System.out.println(response22[0] + "  " + response22[1]);
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        
        if ("enp0s3".equals(netint.getName())) {
        	Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        	for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        		if (inetAddress instanceof Inet4Address) {
        			System.out.printf("InetAddress: %s\n", inetAddress.getHostAddress());
        			break;
        		}
        	}
        }
        System.out.printf("\n");
     }

}