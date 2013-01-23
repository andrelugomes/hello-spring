package google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage {
	private WebDriver driver;

	public GooglePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get("http://www.google.com.br");
	}

	public void busca(String string) {
		WebElement  campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys(string);
		WebElement botaoPesquisar = driver.findElement(By.name("btnG"));
		botaoPesquisar.click();
	}

	 public boolean existe(String string) {
	         
	        return driver.getPageSource().contains(string);
	    }
}
