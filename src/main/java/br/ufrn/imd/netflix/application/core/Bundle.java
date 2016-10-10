package br.ufrn.imd.netflix.application.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por encapsular os parâmetros da solicitação de uma nova janela
 * @author Roberto Dantas
 *
 */
public class Bundle {
	
	private Map<String,Object> params;
	
	public Bundle() {
		params = new HashMap<>();
	}
	
	public Object get(String name){
		return params.get(name);
	}
	
	public void putExtra(String name, Object value){
		params.put(name, value);
	}

}
