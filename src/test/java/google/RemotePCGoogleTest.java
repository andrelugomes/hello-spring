package google;

import static org.junit.Assert.assertFalse;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemotePCGoogleTest {
	private GooglePage google;
	
	@Test
	public void testeRemote() throws MalformedURLException{
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setJavascriptEnabled(true);
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
		//IEDriver.exe esta na pasta c:/Systen32/IEDriver.exe no server 192.168.8.117
		WebDriver driver = new RemoteWebDriver(
                                new URL("http://192.168.8.117:4444/wd/hub"), 
                                ieCapabilities);
        this.google = new GooglePage(driver);	
        
        google.visita();
		google.busca("Bruno : 'Eu sou gay!'");
		
        assertFalse(google.existe("DClick Software"));
        
        
        
        driver.close();
    }
	
}
