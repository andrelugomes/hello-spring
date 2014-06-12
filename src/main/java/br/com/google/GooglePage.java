package br.com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GooglePage {
	@Autowired
	private WebDriver driver;

	public GooglePage() {
	}

	public void visita() {
		driver.get("http://www.google.com.br");
	}

	public void busca(String string) {
		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys(string);
		WebElement botaoPesquisar = driver.findElement(By.name("btnG"));
		botaoPesquisar.click();
	}

	public boolean existe(String string) {

		return driver.getPageSource().contains(string);
	}
}
