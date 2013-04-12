package google;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BuscaNoGoogleTest {

	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
	}

	@Test
	public void testBuscaSelenium() {

		driver.get("www.google.com.br");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		driver.findElement(By.name("btnG")).click();

	}

	@After
	public void finaliza() {
		driver.quit();
	}
}
