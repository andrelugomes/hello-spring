package br.com.google;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreenshotTestRule extends TestWatcher{

	@Autowired
	private WebDriver driver;
	
	public ScreenshotTestRule(){}
	
	/* (non-Javadoc)
	 * @see org.junit.rules.TestWatcher#failed(java.lang.Throwable, org.junit.runner.Description)
	 */
	
	@Override
    protected void failed(Throwable e, Description description) {
        System.out.println("Falha de teste ("+description.getMethodName()+") - Capiturando tela...");
        
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        //TakesScreenshot takesScreenshot = (TakesScreenshot) remotePhantomWebDriver;
        //File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        
        File destFile = getDestinationFile(description);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        //driver.close();
    }

    @Override
    protected void succeeded(Description description) {
    	//driver.close();
    }

	private File getDestinationFile(Description description) {
    	new File("target/surefire-reports/").mkdirs();
    	String directory = "target/surefire-reports/";
        String fileName = description.getMethodName();
        String absoluteFileName = directory + "/screenshot-" + fileName +".png";

        return new File(absoluteFileName);
    }
}