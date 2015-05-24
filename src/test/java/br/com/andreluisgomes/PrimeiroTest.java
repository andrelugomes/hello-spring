package br.com.andreluisgomes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:automatizacao-chrome-test.xml")
public class PrimeiroTest {
	
	@Autowired
	private WebDriver driver;
	
	@Test
	public void deveAcessarOSite() {
		driver.get("http://www.andreluisgomes.com.br");
		
		
	}
		
}
