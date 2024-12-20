import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteFramesEJanelas {
	
	private WebDriver driver;
	private Manager manager;

	@Before
	public void inicializa(){
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		manager = new Manager(driver);
	}
	
	@After
	public void finaliza(){
		//driver.quit();
	}

	@Test
	public void deveInteragirComFrames(){
		manager.entrarFrame("frame1");
		manager.clicarBotao("frameButton");
		String msg = manager.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);

		manager.sairFrame();
		manager.escrever("elementosForm:nome", msg);
	}
	
	@Test
	public void deveInteragirComJanelas(){
		manager.clicarBotao("buttonPopUpEasy");
		manager.trocarJanela("Popup");
		manager.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close();
		manager.trocarJanela("");
		manager.escrever(By.tagName("textarea"), "e agora?");
	}
		
	@Test
	public void deveInteragirComJanelasSemTitulo(){
		manager.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		manager.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		manager.escrever(By.tagName("textarea"), "Deu certo?");
		manager.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		manager.escrever(By.tagName("textarea"), "e agora?");
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = driver.findElement(By.id("frame2"));
		manager.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		
		manager.entrarFrame("frame2");
		
		manager.clicarBotao("frameButton");
		String msg = manager.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
}
