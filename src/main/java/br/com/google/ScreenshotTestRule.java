package br.com.google;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreenshotTestRule extends TestWatcher{

	@Autowired
	private WebDriver driver;
	
	public ScreenshotTestRule(){
	}
	
	@Override
    protected void failed(Throwable e, Description description) {
        System.out.println("Falha no teste : "+description.getMethodName()+" Classe: "+description.getClassName());
        System.out.println("Capiturando tela...");
        //driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = getDestinationFile(description);
        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Capiturado");
        } catch (IOException ioe) {
        	System.out.println("Erro ao capturar a tela e salvar o arquivo no destino");
            throw new RuntimeException(ioe);
        }
        
    }

    @Override
    protected void succeeded(Description description) {
    	//driver.close();
    }

	private File getDestinationFile(Description description) {
    	new File("target/screenshots/").mkdirs();
    	String directory = "target/screenshot/";
        String fileName = description.getDisplayName();
        String absoluteFileName = directory + fileName +".png";

        return new File(absoluteFileName);
    }
}