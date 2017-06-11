package com.collector.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextArea;

public class Logger {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSSS");

	private static JTextArea areaLog;
	
	private Logger() {
		
	}
	
	/**
	 * M�todo respons�vel por imprimir logs. Imprime os logs apenas no modo debug.
	 * @param text
	 */
	public static void log(String tag, String text) {
		if (!Util.isNullOrEmpty(text)) {
			System.out.println("[ " + FORMATTER.format(Calendar.getInstance().getTime()) + " ] - " + "[ " + tag + " ] " + text);
		}
	}
	
	public static void setFieldLog(JTextArea textAreaLog) {
		areaLog = textAreaLog;
	}
	
	public static void logInScreen(String tag, String text) {
		if (areaLog != null) {
			areaLog.append("[ " + FORMATTER.format(Calendar.getInstance().getTime()) + " ] - " + "[ " + tag + " ] " + text + "\n");
		}
	}
}
