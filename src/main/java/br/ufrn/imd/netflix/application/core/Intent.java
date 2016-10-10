package br.ufrn.imd.netflix.application.core;

/**
 * Classe responsável por demonstrar uma intenção de abrir uma nova janela.
 * @author Roberto Dantas
 *
 */
public class Intent {
	
	private String fxml;
	private Bundle bundle;
	
	public Intent() {
		this.fxml = "";
		this.bundle = new Bundle();
	}
	
	public Intent fxml(String fxml){
		this.fxml = fxml;
		return this;
	}
	
	public Intent putExtra(String param, Object object){
		this.bundle.putExtra(param, object);
		return this;
	}
	
	public String getFXML(){
		return this.fxml;
	}
	
	public Bundle getExtras(){
		return this.bundle;
	}
	
}
