package google;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.Driver;

public class GoogleTest {
	private WebDriver driver; 
	
	@Before
	public void inicializa(){
		this.driver = new Driver().chromeStart();
	}	
	
	@Test
	public void deveFazerUmaBuscaNoGoogleTest() {
		driver.get("http://www.google.com.br");
		WebElement  campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("DClick");
		WebElement botaoPesquisar = driver.findElement(By.name("btnGddddd"));
		botaoPesquisar.click();
		
		assertTrue(driver.getPageSource().contains("Mathias cachorro"));
		
	}
	@After
	public void finaliza(){
		driver.quit();
	}
	
}
