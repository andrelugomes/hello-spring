package google;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteGoogleTest {
	private GooglePage google;
	
	@Test
	public void testeRemote() throws MalformedURLException{
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setJavascriptEnabled(true);
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
		WebDriver driver = new RemoteWebDriver(
                                new URL("http://127.0.0.1:4444/wd/hub"), 
                                ieCapabilities);
        this.google = new GooglePage(driver);	
        
        google.visita();
		google.busca("DClick");
		
        assertTrue(google.existe("DClick"));
        
         
        driver.quit();
    }
	
}
