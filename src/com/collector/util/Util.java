package com.collector.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public final class Util {

	private Util() {
		
	}
	
	
	public static String getIpAddress(String interfaceName) {
		
		Enumeration<NetworkInterface> nets = null;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (nets != null) {
			for (NetworkInterface netint : Collections.list(nets)) {
				if (interfaceName.equals(netint.getName())) {
					Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
					for (InetAddress inetAddress : Collections.list(inetAddresses)) {
						if (inetAddress instanceof Inet4Address) {
							return inetAddress.getHostAddress();
						}
					}
				}
			}
		}
        
        return null;
    }
}
