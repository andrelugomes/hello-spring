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
import br.com.google.HeadlessScreenshotTestRule;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:automatizacao-phantomjs-test.xml")
public class GoogleScreenShotPhantomJSTest {
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
	public HeadlessScreenshotTestRule screenshotTestRule;

	
	@Test
	public void deveFazerUmaBuscaNoGoogleTest() {
		google.visita();
		google.busca("Uniara");
		//phantomjs --webdriver=8910 
		assertTrue(google.existe("aaabbbcccddd"));
		
	}
		
}
