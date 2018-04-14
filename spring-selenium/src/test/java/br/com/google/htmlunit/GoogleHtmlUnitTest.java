package br.com.google.htmlunit;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.google.GooglePage;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:automatizacao-html-unit-test.xml")
public class GoogleHtmlUnitTest {
	@Autowired
	private WebDriver driver;
 
	@Autowired
	private GooglePage google;
	
	@Before
	public void inicializa() throws MalformedURLException{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveFazerUmaBuscaNoGoogleTest() {
		google.visita();
		google.busca("Uniara");
		
		assertTrue(google.existe("Uniara"));
		
	}
		
}
