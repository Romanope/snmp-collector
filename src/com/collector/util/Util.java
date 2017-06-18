package com.collector.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

import com.collector.model.Servidor;

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
	
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.length() == 0;
	}
	
	public static void saveConfig(Servidor servidor) {
	    
		try {
	        Properties props = new Properties();
	        props.setProperty(Constantes.PAR_SERVER_ID, servidor.getId());
	        props.setProperty(Constantes.PAR_SERVER_NOME, servidor.getNome());
	        props.setProperty(Constantes.PAR_SERVER_SO, servidor.getSistemaOperacional());
	        props.setProperty(Constantes.PAR_SERVER_INTERVALO, String.valueOf(servidor.getPeriodicidade()));
	        props.setProperty(Constantes.PAR_SERVER_OIDS, servidor.getOids());
	        props.setProperty(Constantes.PAR_SERVER_USER, servidor.getUser());
	        props.setProperty(Constantes.PAR_SERVER_SENHA_AUTEN, servidor.getSenhaAutenticacao());
	        props.setProperty(Constantes.PAR_SERVER_SENHA_CRIP, servidor.getSenhaCriptografia());
	        props.setProperty(Constantes.PAR_SERVER_ALGORIT_AUTEN, servidor.getAlgoritmoAutenticacao());
	        props.setProperty(Constantes.PAR_SERVER_ALGORIT_CRIP, servidor.getAlgoritmoCriptografia());
	        props.setProperty(Constantes.PAR_SERVER_VERSION_SNMP, String.valueOf(servidor.getVersionSnmp()));
	        props.setProperty(Constantes.PAR_SERVER_SECURITY_LEVEL, String.valueOf(servidor.getSecurityLevel()));
	        props.setProperty(Constantes.PAR_SERVER_TIMEOUT, String.valueOf(servidor.getTimeout()));
	        props.setProperty(Constantes.PAR_SERVER_IP, servidor.getEnderecoIP());
	        File f = new File(System.getProperty("user.home") + "/agente.properties");
	        OutputStream out = new FileOutputStream( f );
	        props.store(out, "id de identificação do servidor");
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	    }
	}
	
	public static Servidor getParametrosServidor() throws Exception {
		
		Properties props = new Properties();
		InputStream is = null;
		try {
			File f = new File(System.getProperty("user.home") + "/agente.properties");
			is = new FileInputStream(f);
		} catch (Exception e) {
			is = null;
		}
		try {
			props.load(is);
		} catch (Exception e) {
			System.out.println("ERRO INESPERADO");
			e.printStackTrace();
			throw e;
		}
		Servidor servidor = new Servidor();
		servidor.setId(props.getProperty(Constantes.PAR_SERVER_ID));
		servidor.setNome(props.getProperty(Constantes.PAR_SERVER_NOME));
		servidor.setOids(props.getProperty(Constantes.PAR_SERVER_OIDS));
		servidor.setPeriodicidade(props.getProperty(Constantes.PAR_SERVER_INTERVALO) != null ? Long.valueOf(props.getProperty(Constantes.PAR_SERVER_INTERVALO)) : 0);
		servidor.setAlgoritmoAutenticacao(props.getProperty(Constantes.PAR_SERVER_ALGORIT_AUTEN));
		servidor.setAlgoritmoCriptografia(props.getProperty(Constantes.PAR_SERVER_ALGORIT_CRIP));
		servidor.setSecurityName(props.getProperty(Constantes.PAR_SERVER_SECURITY_NAME));
		servidor.setSecurityLevel(props.getProperty(Constantes.PAR_SERVER_SECURITY_LEVEL) != null ? Integer.valueOf(props.getProperty(Constantes.PAR_SERVER_SECURITY_LEVEL)) : 0);
		servidor.setSenhaAutenticacao(props.getProperty(Constantes.PAR_SERVER_SENHA_AUTEN));
		servidor.setSenhaCriptografia(props.getProperty(Constantes.PAR_SERVER_SENHA_CRIP));
		servidor.setTimeout(props.getProperty(Constantes.PAR_SERVER_TIMEOUT) != null ? Long.valueOf(props.getProperty(Constantes.PAR_SERVER_TIMEOUT)) : 0);
		servidor.setUser(props.getProperty(Constantes.PAR_SERVER_USER));
		servidor.setVersionSnmp(props.getProperty(Constantes.PAR_SERVER_VERSION_SNMP) != null ? Integer.valueOf(props.getProperty(Constantes.PAR_SERVER_VERSION_SNMP)) : 0);
		return servidor;
	}
}
