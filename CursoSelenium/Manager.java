import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//*** Uma DSL é uma classe "manager" onde você coloca todas as interações nativas do Selenium com os objetos que vc vai ter na sua automação.
public class Manager {
	
	private WebDriver driver;
	//*** Declara uma variável privada driver do tipo WebDriver para controlar o navegador.	
	
	public Manager(WebDriver driver) {
		this.driver = driver;
	}
	//*** Construtor da classe que inicializa a variável driver com a instância passada como parâmetro.
	
	/********* TextField e TextArea ************/
	
	public void escrever(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto){
	    escrever(By.id(id_campo), texto);
	}
	//*** Overload do método anterior, permitindo localizar o elemento apenas pelo atributo id.

	public String obterValorCampo(String id_campo) {
	    return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	//*** Retorna o valor contido em um campo de texto (como <input>), localizado pelo atributo id.

	
	//*** Métodos para manipulação de radio buttons e checkboxes
	public void clicarRadio(String id) {
	    driver.findElement(By.id(id)).click();
	}
	//*** Localiza e clica em um botão de rádio pelo id.

	public boolean isRadioMarcado(String id){
	    return driver.findElement(By.id(id)).isSelected();
	}
	//*** Verifica se o botão de rádio está marcado.

	public void clicarCheck(String id) {
	    driver.findElement(By.id(id)).click();
	}
	//*** Localiza e clica em um checkbox pelo id.

	public boolean isCheckMarcado(String id){
	    return driver.findElement(By.id(id)).isSelected();
	}
	//*** Verifica se um checkbox está marcado.
	
	
	//*** Métodos para manipulação de combos (dropdowns)
	public void selecionarCombo(String id, String valor) {
	    WebElement element = driver.findElement(By.id(id));
	    Select combo = new Select(element);
	    combo.selectByVisibleText(valor);
	}
	//*** Seleciona uma opção em um dropdown (<select>) pelo texto visível.

	public void deselecionarCombo(String id, String valor) {
	    WebElement element = driver.findElement(By.id(id));
	    Select combo = new Select(element);
	    combo.deselectByVisibleText(valor);
	}
	//*** Deseleciona uma opção de um dropdown multisseleção pelo texto visível.

	public String obterValorCombo(String id) {
	    WebElement element = driver.findElement(By.id(id));
	    Select combo = new Select(element);
	    return combo.getFirstSelectedOption().getText();
	}
	//*** Retorna o texto da primeira opção selecionada em um dropdown.

	public List<String> obterValoresCombo(String id) {
	    WebElement element = driver.findElement(By.id("elementosForm:esportes"));
	    Select combo = new Select(element);
	    List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
	    List<String> valores = new ArrayList<String>();
	    for(WebElement opcao: allSelectedOptions) {
	        valores.add(opcao.getText());
	    }
	    return valores;
	}
	//*** Retorna uma lista de textos de todas as opções selecionadas em um dropdown.

	public int obterQuantidadeOpcoesCombo(String id){
	    WebElement element = driver.findElement(By.id(id));
	    Select combo = new Select(element);
	    List<WebElement> options = combo.getOptions();
	    return options.size();
	}
	//*** Retorna o número de opções disponíveis em um dropdown.

	public boolean verificarOpcaoCombo(String id, String opcao){
	    WebElement element = driver.findElement(By.id(id));
	    Select combo = new Select(element);
	    List<WebElement> options = combo.getOptions();
	    for(WebElement option: options) {
	        if(option.getText().equals(opcao)){
	            return true;
	        }
	    }
	    return false;
	}
	//*** Verifica se uma opção específica existe em um dropdown.

	
	//*** Métodos para botões
	public void clicarBotao(String id) {
	    driver.findElement(By.id(id)).click();
	}
	//*** Localiza e clica em um botão pelo id.

	public String obterValueElemento(String id) {
	    return driver.findElement(By.id(id)).getAttribute("value");
	}
	//*** Retorna o atributo value de um elemento identificado pelo id.
	
	
	//*** Métodos para links
	public void clicarLink(String link) {
	    driver.findElement(By.linkText(link)).click();
	}
	//*** Clica em um link pelo texto visível.
	
	
	//*** Métodos para textos
	public String obterTexto(By by) {
	    return driver.findElement(by).getText();
	}
	//*** Retorna o texto de um elemento localizado pelo seletor by.

	public String obterTexto(String id) {
	    return obterTexto(By.id(id));
	}
	//*** overload do método anterior, localizando pelo id.

	//*** Métodos para alerts
	public String alertaObterTexto(){
	    Alert alert = driver.switchTo().alert();
	    return alert.getText();
	}
	//*** Obtém o texto de um alerta exibido no navegador.

	public String alertaObterTextoEAceita(){
	    Alert alert = driver.switchTo().alert();
	    String valor = alert.getText();
	    alert.accept();
	    return valor;
	}
	//*** Obtém o texto de um alerta e o aceita.

	public String alertaObterTextoENega(){
	    Alert alert = driver.switchTo().alert();
	    String valor = alert.getText();
	    alert.dismiss();
	    return valor;
	}
	//*** Obtém o texto de um alerta e o rejeita.

	public void alertaEscrever(String valor) {
	    Alert alert = driver.switchTo().alert();
	    alert.sendKeys(valor);
	    alert.accept();
	}
	//*** Insere um valor em um alerta e o aceita.

	//*** Métodos para frames e janelas
	public void entrarFrame(String id) {
	    driver.switchTo().frame(id);
	}
	//*** Altera o contexto para um iframe identificado pelo id.

	public void sairFrame(){
	    driver.switchTo().defaultContent();
	}
	//*** Retorna ao contexto principal (fora do frame).

	public void trocarJanela(String id) {
	    driver.switchTo().window(id);
	}
	//*** Troca o foco para uma janela específica identificada pelo id.
	
	
	//*** Interagir via JS
	
	 public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	 }
	 
	 //*** Métodos de tabela
	 public void clicarBotaoTabela(String coluna, String valor, String colunaBusca) {
		 //procurar coluna
		WebElement tabela = driver.findElement(By.xpath("*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		 
		 
		 
		 
		 //procurar a linha do registro
		 //procurar coluna do botão
		 //clicar no botão da célula encontrada
	 }
	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna; 
	}
	 
}