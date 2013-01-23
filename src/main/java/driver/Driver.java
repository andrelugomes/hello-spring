package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	public WebDriver start() {
		System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
		return  new ChromeDriver();
	}
}