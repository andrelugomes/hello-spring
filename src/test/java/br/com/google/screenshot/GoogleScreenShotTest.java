package br.com.google.screenshot;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.google.GooglePage;
import br.com.google.ScreenshotTestRule;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:automatizacao-ie-test.xml")
public class GoogleScreenShotTest {
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
	
	@Rule
	@Autowired
	public ScreenshotTestRule screenshotTestRule;

	
	@Test
	public void deveFazerUmaBuscaNoGoogleTest() {
		google.visita();
		google.busca("Uniara");
		
		assertTrue(google.existe("aaabbbcccddd"));
		
	}
		
}
