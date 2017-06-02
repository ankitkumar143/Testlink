package testing;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class test12 {

	public static void main(String[] args) throws FindFailed, IOException {
		// TODO Auto-generated method stub
		
		 
		{
		   
		        System.setProperty("webdriver.gecko.driver","D:\\Selenium\\geckodriver.exe");
		        WebDriver driver = new FirefoxDriver();
		        driver.manage().window().maximize();
		 
		        driver.get("http://demo.automationtesting.in/Register.html");
		        driver.findElement(By.id("imagesrc")).click();
		        //.getRuntime().exec("D:\\auto\\Upload File.exe");
		        try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
		        
	        Pattern fileNameInput = new Pattern("D:\\Selenium\\FileOpenInput.PNG");
		        Pattern openButton = new Pattern("D:\\Selenium\\OpenButton.PNG");
		 
		        Screen screen = new Screen();
		        screen.type(fileNameInput, "D:\\Selenium\\OpenButton.PNG");
		        screen.click(openButton);
		    }
		}
	}


