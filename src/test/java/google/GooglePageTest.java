package google;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import driver.Driver;



public class GooglePageTest {
	
	private WebDriver driver; 
	private GooglePage google;
	
	/**
	 * inicializa os testes criando o Driver e a página a ser testeda
	 */
	@Before
	public void inicializa(){
		this.driver = new Driver().chromeStart();
		this.google = new GooglePage(driver);
		
		
	}	
	
	/**
	 * 
	 */
	@Test
	public void deveFazerUmaBuscaNoGoogleTest() {
		google.visita();
		google.busca("DClick");
		
        assertTrue(google.existe("DClick"));
	}
	
	/**
	 * 
	 */
	@Test
	public void deveFazerUmaBuscaNoGoogleSemResultadoTest() {
		google.visita();
		google.busca("DClickKKK");
		
        assertFalse(google.existe("DClick Software"));
	}	
	/**
	 * Finaliza o Driver e fecha o browser
	 */
	@After
	public void finaliza() {
		//driver.close(); close da erro no log	
		driver.quit();
	}
}
