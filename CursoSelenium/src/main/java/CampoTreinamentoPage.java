import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private Manager manager;
	
	public CampoTreinamentoPage(WebDriver driver) {
		manager = new Manager(driver);
	}

	public void setNome(String nome) {
		manager.escrever("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		manager.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino(){
		manager.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino(){
		manager.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		manager.clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaPizza(){
		manager.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano(){
		manager.clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor) {
		manager.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String... valores) {
		for(String valor: valores)
			manager.selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar(){
		manager.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro(){
		return manager.obterTexto("resultado");
	}
	
	public String obterNomeCadastro(){
		return manager.obterTexto("descNome");
	}
	
	public String obterSobrenomeCadastro(){
		return manager.obterTexto("descSobrenome");
	}
	
	public String obterSexoCadastro(){
		return manager.obterTexto("descSexo");
	}
	
	public String obterComidaCadastro(){
		return manager.obterTexto("descComida");
	}
	
	public String obterEscolaridadeCadastro(){
		return manager.obterTexto("descEscolaridade");
	}
	
	public String obterEsportesCadastro(){
		return manager.obterTexto("descEsportes");
	}
}
