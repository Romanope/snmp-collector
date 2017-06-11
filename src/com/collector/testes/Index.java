package com.collector.testes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import com.collector.Collector;
import com.collector.util.Constantes;
import com.collector.util.Logger;
import com.collector.util.Util;

public class Index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private JButton btnStart;
	
	private JTextArea log;
	
	private Thread collectorThread;
	
	private JTextField txtIdServidor;
	
	private Index() {
		this.setTitle("Coletor");
		this.setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel = new JPanel(null);
		start();
	}
	
	private void start() {
		
		JLabel lblIdServidor = new JLabel("Servidor:");
		lblIdServidor.setBounds(5, 5, 100, 22);
		panel.add(lblIdServidor);
		
		txtIdServidor = new JTextField();
		txtIdServidor.setBounds(80, 5, 200, 22);
		panel.add(txtIdServidor);
		
		log = createJtextArea(log, false, 5, 60, 300, 250, panel);
		Logger.setFieldLog(log);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(5, 350, 80, 22);
		addEventStart(btnStart);
		panel.add(btnStart);
		
		this.add(panel);
	}
	
	private void addEventStart(JButton btnStart) {
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String identificador = txtIdServidor.getText();
				if (Util.isNullOrEmpty(identificador)) {
					showMessage("Campo servidor deve ser preenchido");
					return;
				}
				startOrRestart(identificador);
			}
		});
	}
	
	private synchronized void startOrRestart(String identificador) {
		if (collectorThread == null) {
			Collector collector = new Collector(Util.getIpAddress(Constantes.INTERFACE_NAME), Long.valueOf(identificador.trim()));
			collectorThread = new Thread(collector);
			collectorThread.start();
		} else {
			collectorThread.notify();
		}
	}
	
	private JTextArea createJtextArea(JTextArea jArea, boolean editable, int x, int y, int width, int height, JPanel panel) {
		jArea = new JTextArea("");
		jArea.setLineWrap(true);
		jArea.setEditable(editable);
		jArea.setBackground(Color.BLACK);
		jArea.setForeground(Color.WHITE);
		DefaultCaret caret = (DefaultCaret) jArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scroll = new JScrollPane(jArea);
		scroll.setBounds(x, y, width, height);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		return jArea;
	}
	
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static void main(String[] args) {
		new Index().setVisible(true);
	}
}
