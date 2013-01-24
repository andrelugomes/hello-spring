import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TesteSeleniumGrid2 {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox"); 
		capability.setVersion("17.0.1");
		capability.setPlatform(null);

		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		driver.manage().timeouts();
		driver.get("www.google.com.br");
	}
}
