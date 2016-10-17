package br.ufrn.imd.netflix.application.core;

/**
 * Classe responsável por demonstrar uma intenção de abrir uma nova janela.
 * @author Roberto Dantas
 *
 */
public class Intent {
	
	private String fxml;
	private Bundle bundle;
	private Controller conroller;
	private boolean loadingDialog;
	private String loadingText;
	private String title;
	
	public Intent() {
		this.fxml = "";
		this.bundle = new Bundle();
		this.title = "";
	}
	
	public Intent fxml(String fxml){
		this.fxml = fxml;
		return this;
	}
	
	public Intent controller(Controller controller){
		this.conroller = controller;
		return this;
	}
	
	public Intent putExtra(String param, Object object){
		this.bundle.putExtra(param, object);
		return this;
	}
	
	public Intent loadingDialog(boolean loadingDialog){
		this.loadingDialog = loadingDialog;
		return this;
	}
	
	public Intent loadingText(String loadingText){
		this.loadingText = loadingText;
		return this;
	}
	
	public Intent title(String title){
		this.title = title;
		return this;
	}

	public String getFXML(){
		return this.fxml;
	}
	
	public Bundle getExtras(){
		return this.bundle;
	}
	
	public Controller getController(){
		return this.conroller;
	}

	public boolean isLoadingDialog() {
		return loadingDialog;
	}

	public String getLoadingText() {
		return loadingText;
	}
	
	public String getTitle(){
		return title;
	}
	
	
	
}
