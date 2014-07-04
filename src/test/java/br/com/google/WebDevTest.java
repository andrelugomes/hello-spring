package br.com.google;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:automatizacao-test.xml")
public class WebDevTest {
	
	@Autowired
	private WebDriver driver;	
	
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
	public void fazerLoginNoMeuPortalDoSescTest() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.get("http://www.webdev.sescsp.org.br/sesc/meuportal/index.cfm");		
		
		if(wait.until(ExpectedConditions.alertIsPresent())!=null){
			driver.switchTo().alert().accept();
		}
		if(driver.findElements(By.name("principal")).size() != 0){
			driver.switchTo().frame(driver.findElement(By.name("principal")));
		}
		
		driver.findElement(By.id("Login")).sendKeys("d_adm");
		driver.findElement(By.id("Password")).sendKeys("dclick12#*");
		driver.findElement(By.className("inputsubmit")).click();
		
		WE
		
		
	}
	
}
