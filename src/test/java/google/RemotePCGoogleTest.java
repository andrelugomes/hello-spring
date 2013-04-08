package google;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemotePCGoogleTest {
	private GooglePage google;
	
	@Test
	public void testeRemote() throws MalformedURLException{
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setJavascriptEnabled(true);
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		WebDriver driver = new RemoteWebDriver(
                                new URL("http://192.168.8.117:4444/wd/hub"), 
                                ieCapabilities);
        this.google = new GooglePage(driver);	
        
        google.visita();
		google.busca("Bruno : 'Eu sou gay!'");
		
        assertFalse(google.existe("DClick Software"));
        
        
        // RemoteWebDriver does not implement the TakesScreenshot class
        // if the driver does have the Capabilities to take a screenshot
        // then Augmenter will add the TakesScreenshot methods to the instance
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot)augmentedDriver).
                            getScreenshotAs(OutputType.FILE);
        
        //driver.close();
    }
	
}
