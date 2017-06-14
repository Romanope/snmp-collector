package com.collector;

import java.io.IOException;

import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.AuthSHA;
import org.snmp4j.security.PrivAES128;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.collector.model.Servidor;

public class SNMPCollector {

	private Snmp snmp;
	
	private Servidor dadosConfig;
	
	private String ipAddress;
	
	private UserTarget target;
	
	private PDU pdu;

	public SNMPCollector(Servidor dadosConfig, String ipAddress) {
		this.dadosConfig = dadosConfig;
		this.ipAddress = ipAddress;
		try {
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void start() throws IOException {
		
		snmp = getSnmp();
		
		target = getTarget();
		
		UsmUser usmUser = getUsmUser();
		
		byte[] auth = snmp.discoverAuthoritativeEngineID(target.getAddress(), 5000);
		
		snmp.getUSM().addUser(new OctetString(dadosConfig.getSecurityName()), new OctetString(auth), usmUser);
		
		pdu = getPDU();
	}
	
	private Snmp getSnmp() throws IOException {
		
		TransportMapping transport = new DefaultUdpTransportMapping();
		Snmp snmp = new Snmp(transport);

		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		snmp.listen();
		
		return snmp;
	}
	
	private UserTarget getTarget() {
		
		UserTarget userTarget = new UserTarget();
		userTarget.setAddress(GenericAddress.parse("udp:".concat(this.ipAddress).concat("/161")));
		userTarget.setSecurityName(new OctetString(dadosConfig.getSecurityName()));
		userTarget.setVersion(dadosConfig.getVersionSnmp());
		userTarget.setSecurityLevel(dadosConfig.getSecurityLevel());
		userTarget.setTimeout(dadosConfig.getTimeout());
		userTarget.setRetries(0);
		
		return userTarget;
	}
	
	private UsmUser getUsmUser() {
		
		UsmUser user2 = new UsmUser(new OctetString(dadosConfig.getUser()), AuthSHA.ID, new OctetString(dadosConfig.getSenhaAutenticacao()),
				PrivAES128.ID, new OctetString(dadosConfig.getSenhaCriptografia()));
		
		return user2;
	}
	
	private PDU getPDU() {
		
		PDU pdu = new ScopedPDU();
		String[] oids = dadosConfig.getOids().split("#");
		pdu.setNonRepeaters(oids.length); 
		pdu.setType(PDU.GETBULK);

		for (String oid: oids) {
			OID oid1 = new OID(oid);
			pdu.add(new VariableBinding(oid1));
		}
		
		return pdu;
	}
	
	public String getBulk() {
		
		ResponseEvent event = null;
		try {
			event = snmp.getBulk(pdu, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (event != null && event.getResponse() != null) {
				return event.getResponse().toString();
		} else {
			return null;
		}
	}
}
