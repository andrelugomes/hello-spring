package driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
	protected static DesiredCapabilities dCaps;

	public WebDriver chromeStart() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		}
		if (os.contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		}
		System.out.println(os);
		return new ChromeDriver();
	}

	public WebDriver fireFoxStart() {
		return new FirefoxDriver();
	}

	public WebDriver phantomJs() throws MalformedURLException {
		dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", true);

		return new RemoteWebDriver( new URL("http://127.0.0.1:8910"), dCaps);
	}
}