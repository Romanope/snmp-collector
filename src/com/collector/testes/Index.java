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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import com.collector.Collector;
import com.collector.controller.ControllerCollector;
import com.collector.controller.ControllerServidor;
import com.collector.model.Servidor;
import com.collector.util.Constantes;
import com.collector.util.Logger;
import com.collector.util.Util;

public class Index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private JPanel panelConfig;
	
	private JButton btnStart;
	
	private JTextArea log;
	
	private Thread collectorThread;
	
	private JTextField txtIdServidor;
	
	private JTabbedPane tabs;
	
	private JTextField txtId;
	
	private JTextField txtNome;
	
	private JTextField txtSistemaOper;
	
	private JTextField txtIp;
	
	private JTextField txtIntervalo;
	
	private JTextField txtOids;
	
	private JTextField txtUser;
	
	private JTextField txtPassAuten;
	
	private JTextField txtPassCrip;
	
	private JTextField txtAlgorAuten;
	
	private JTextField txtAlgoCrip;
	
	private JTextField txtVersaoSNMP;
	
	private JTextField txtSecurityLevel;
	
	private JTextField timeoutSNMP;
	
	private JButton    btnCadastrar;
	
	private JButton    btnAtualizar;
	
	private JButton btnConsultar;
	
	private Index() {
		this.setTitle("Coletor");
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		panel = new JPanel(null);
		panelConfig = new JPanel(null);
		tabs = new JTabbedPane();
		tabs.add("Serviço", panel);
		tabs.add("Configurações", panelConfig);
		getContentPane().add(tabs, null);
		start();
	}
	
	private void start() {
		
		montarLayoutAbaServico();
		montarLayoutAbaConfiguracoes();
	}
	
	private void montarLayoutAbaConfiguracoes() {
		
		String id = "";
		
		try {
			id = Util.getParametrosServidor().getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtIdServidor.setText(id);
		panelConfig.add(createLabel("ID SERVER:", 5, 5, 80, 22));
		txtId = createTextField(80, 5, 370, 22);
		txtId.setEnabled(false);
		txtId.setText(id);
		panelConfig.add(txtId);
		
		btnConsultar = new JButton("...");
		btnConsultar.setToolTipText("Consultar dados do agente no serviço");
		btnConsultar.setBounds(460, 5, 30, 22);
		addEventBtnConsultar(btnConsultar);
		panelConfig.add(btnConsultar);
		
		panelConfig.add(createLabel("Nome:", 5, 30, 80, 22));
		txtNome = createTextField(80, 30, 190, 22);
		txtNome.setText("cadastrado pelo agente");
		panelConfig.add(txtNome);
		
		panelConfig.add(createLabel("SO:", 273, 30, 30, 22));
		txtSistemaOper = createTextField(300, 30, 190, 22);
		txtSistemaOper.setText("LINUX");
		panelConfig.add(txtSistemaOper);
		
		panelConfig.add(createLabel("Intervalo:", 5, 60, 80, 22));
		txtIntervalo = createTextField(80, 60, 190, 22);
		txtIntervalo.setText("5000");
		panelConfig.add(txtIntervalo);
		
		panelConfig.add(createLabel("IP:", 273, 60, 30, 22));
		txtIp = createTextField(300, 60, 190, 22);
		txtIp.setText(Util.getIpAddress(Constantes.INTERFACE_NAME));
		txtIp.setEnabled(false);
		panelConfig.add(txtIp);
		
		panelConfig.add(createLabel("Usuário:", 5, 90, 80, 22));
		txtUser = createTextField(80, 90, 190, 22);
		txtUser.setText("romano");
		panelConfig.add(txtUser);
		
		panelConfig.add(createLabel("SA:", 273, 90, 30, 22));
		txtPassAuten = createTextField(300, 90, 190, 22);
		txtPassAuten.setToolTipText("Senha de autenticação");
		txtPassAuten.setText("1234567890");
		panelConfig.add(txtPassAuten);
		
		panelConfig.add(createLabel("A. Auten.:", 5, 120, 80, 22));
		txtAlgorAuten = createTextField(80, 120, 190, 22);
		txtAlgorAuten.setToolTipText("Algoritmo de autenticação");
		txtAlgorAuten.setText("1.3.6.1.6.3.10.1.1.3");
		txtAlgorAuten.setEnabled(false);
		panelConfig.add(txtAlgorAuten);
		
		panelConfig.add(createLabel("SC:", 273, 120, 30, 22));
		txtPassCrip = createTextField(300, 120, 190, 22);
		txtPassCrip.setToolTipText("Senha de Criptografia");
		txtPassCrip.setText("0987654321");
		panelConfig.add(txtPassCrip);
		
		panelConfig.add(createLabel("A. Cript.:", 5, 150, 80, 22));
		txtAlgoCrip = createTextField(80, 150, 190, 22);
		txtAlgoCrip.setToolTipText("Algoritmo de Criptografia");
		txtAlgoCrip.setText("1.3.6.1.6.3.10.1.2.4");
		txtAlgoCrip.setEnabled(false);
		panelConfig.add(txtAlgoCrip);
		
		panelConfig.add(createLabel("NV:", 273, 150, 30, 22));
		txtSecurityLevel = createTextField(300, 150, 190, 22);
		txtSecurityLevel.setToolTipText("Nível de Segurança");
		txtSecurityLevel.setText("3");
		txtSecurityLevel.setEnabled(false);
		panelConfig.add(txtSecurityLevel);
		
		panelConfig.add(createLabel("Versão:", 5, 180, 80, 22));
		txtVersaoSNMP = createTextField(80, 180, 190, 22);
		txtVersaoSNMP.setToolTipText("Versão SNMP:");
		txtVersaoSNMP.setText("3");
		txtVersaoSNMP.setEnabled(false);
		panelConfig.add(txtVersaoSNMP);
		
		panelConfig.add(createLabel("Timeout:", 273, 180, 70, 22));
		timeoutSNMP = createTextField(340, 180, 150, 22);
		timeoutSNMP.setToolTipText("Timeout SNMP");
		timeoutSNMP.setText("10000");
		timeoutSNMP.setEnabled(false);
		panelConfig.add(timeoutSNMP);
		
		panelConfig.add(createLabel("OIDS:", 5, 210, 80, 22));
		txtOids = createTextField(80, 210, 410, 22);
		txtOids.setToolTipText("Separar OIDS com #:");
		txtOids.setText("1.3.6.1.2.1.2.2.1.10.1#1.3.6.1.2.1.2.2.1.16.2");
		panelConfig.add(txtOids);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(5, 350, 130, 22);
		addEventCadastrar(btnCadastrar);
		panelConfig.add(btnCadastrar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(360, 350, 130, 22);
		panelConfig.add(btnAtualizar);
		
	}
	
	private void montarLayoutAbaServico() {
		
		JLabel lblIdServidor = new JLabel("Servidor:");
		lblIdServidor.setBounds(5, 5, 100, 22);
		panel.add(lblIdServidor);
		
		txtIdServidor = new JTextField();
		txtIdServidor.setBounds(80, 5, 410, 22);
		txtIdServidor.setEnabled(false);
		panel.add(txtIdServidor);
		
		log = createJtextArea(log, false, 5, 60, 490, 250, panel);
		Logger.setFieldLog(log);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(5, 350, 80, 22);
		addEventStart(btnStart);
		panel.add(btnStart);
	}
	
	private JTextField createTextField(int x, int y, int width, int height) {
		JTextField text = new JTextField();
		text.setBounds(x, y, width, height);
		return text;
	}
	
	private void addEventBtnConsultar(JButton btnConsultar) {
		
		btnConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						String id = txtId.getText();
						Servidor servidor = ControllerCollector.getInstance().obterDadosConfiguracao(id);
						popularCampos(servidor);						
					}
				}).start();
			}
		});
	}
	
	private JLabel createLabel(String label, int x, int y, int width, int height) {
		
		JLabel lbl = new JLabel(label);
		lbl.setBounds(x, y, width, height);
		return lbl;
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
			Collector collector = new Collector(Util.getIpAddress(Constantes.INTERFACE_NAME), identificador.trim());
			collectorThread = new Thread(collector);
			collectorThread.start();
		} else {
			collectorThread.notify();
		}
	}
	
	private void addEventCadastrar(JButton btnCadastrar) {
		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nome = txtNome.getText();
				String so = txtSistemaOper.getText();
				String ip = txtIp.getText();
				String intervalo = txtIntervalo.getText();
				String oids = txtOids.getText();
				String user = txtUser.getText();
				String passAutenticacao = txtPassAuten.getText();
				String passCriptografia = txtPassCrip.getText();
				String algoAutenticacao = txtAlgorAuten.getText();
				String algoCrip         = txtAlgoCrip.getText();
				String versionSNMP    	= txtVersaoSNMP.getText();
				String securityLevel	= txtSecurityLevel.getText();
				String timeout			= timeoutSNMP.getText();
			
				Servidor servidor = ControllerServidor.getInstance().createInstanceServidor(nome, so, ip, intervalo, oids, user, 
						passAutenticacao, passCriptografia, algoAutenticacao, algoCrip, versionSNMP, securityLevel, timeout);
				
				String id = ControllerServidor.getInstance().cadastrarServidor(servidor);
				servidor.setId(id);
				txtId.setText(id);
				Util.saveConfig(servidor);
			}
		});
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
	
	private void popularCampos(Servidor servidor) {
		
		txtNome.setText(servidor.getNome());
		txtSistemaOper.setText(servidor.getSistemaOperacional());
		txtIntervalo.setText(String.valueOf(servidor.getPeriodicidade()));
		txtIp.setText(servidor.getEnderecoIP());
		txtUser.setText(servidor.getUser());
		txtPassAuten.setText(servidor.getSenhaAutenticacao());
		txtPassCrip.setText(servidor.getSenhaCriptografia());
		txtAlgoCrip.setText(servidor.getAlgoritmoCriptografia());
		txtAlgorAuten.setText(servidor.getAlgoritmoAutenticacao());
		txtSecurityLevel.setText(String.valueOf(servidor.getSecurityLevel()));
		txtVersaoSNMP.setText(String.valueOf(servidor.getVersionSnmp()));
		timeoutSNMP.setText(String.valueOf(servidor.getTimeout()));
		txtOids.setText(servidor.getOids());
		
	}
}
