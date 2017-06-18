package com.collector.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.collector.model.Servidor;
import com.collector.model.DadosUsoHardware;
import com.collector.util.Constantes;
import com.google.gson.Gson;

public class ResourceOnline implements IResource {

	@Override
	public Servidor obterConfiguracao(String identificadorServidor) {
		
		String response = enviarRequisicao(Constantes.URL_SERVICO_AGENTE, identificadorServidor, Constantes.CONTENT_TYPE_TEXT_PLAIN, Constantes.GET);

		Servidor dados = new Gson().fromJson(response, Servidor.class);
		
		return dados;
	}
	
	private String enviarRequisicao(String url, String conteudo, String applicationType, String methodType) {
		
		HttpURLConnection conn = null;
		StringBuilder response = new StringBuilder();
		try {
			
			if (Constantes.GET.equals(methodType)) {
				url = url + "/" + conteudo;
			}
			URL url1 = new URL(url);
			conn = (HttpURLConnection) url1.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(methodType);
			conn.setRequestProperty("Content-Type", applicationType+";charset=UTF-8");

			if (Constantes.POST.equals(methodType)) {
				OutputStream os = conn.getOutputStream();
				os.write(conteudo.getBytes());
				os.flush();
				os.close();
			}
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response.toString();
	}

	@Override
	public void enviarDadosColetados(DadosUsoHardware dados) {
		
		String jsonDadosUso = new Gson().toJson(dados);
		
		enviarRequisicao(Constantes.URL_SERVICO_GET_POST_DADOS_COLETADOS, jsonDadosUso, Constantes.CONTENT_TYPE_JSON, Constantes.POST);
	}

	@Override
	public String cadastrarServidor(Servidor servidor) {
		
		String servidorJson = new Gson().toJson(servidor);
		String response = enviarRequisicao(Constantes.URL_SERVICO_AGENTE, servidorJson, Constantes.CONTENT_TYPE_JSON, Constantes.POST);
		servidor = new Gson().fromJson(response, Servidor.class);
		return servidor.getId();
	}
}
