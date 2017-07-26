package testing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class opengoverneye {

	public static void main(String[] args) throws FindFailed {
		// TODO Auto-generated method stub
    WebDriver driver =  new FirefoxDriver();
    driver.get("http://qa1947.governeye.com/");
    try {
    	Thread.sleep(5000);
    WebElement aks = driver.findElement(By.xpath("//*[@class='text']"));
       aks.sendKeys("");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("baicode");;
        
        driver.findElement(By.xpath("//*[@id='submit']")).click();
    	Thread.sleep(5000);
    	/*List<WebElement> listOptions=driver.findElements(By.xpath("//*[@class='buttonlink menu_user_home']"));
    	//List<WebElement> listOptions=driver.findElements(By.xpath("//*[@class='menu_user_home']"));
		for(WebElement we : listOptions)
		{
			we.click();
			System.out.println("ENter in loop");
			//driver.findElement(By.xpath("//*[@class='post-reject']")).click();
			driver.findElement(By.xpath("//*[@class='remove-comment']")).click();
			Thread.sleep(5000);
		}	
	*/
        /*driver.findElement(By.xpath("//*[@class='compose-content']")).sendKeys("");
        WebElement uploadbutton = driver.findElement(By.xpath("//*[@id='compose-music-activator']"));
		uploadbutton.click();
		driver.findElement(By.xpath("//input[@id='compose-music-form-input']")).sendKeys("C:\\Users\\Public\\Music\\Sample Music.mp3");
		Thread.sleep(5000);*/
		
		/*Pattern img1 = new Pattern("C:\\Users\\ankit.kumar\\workspace\\Governeye\\screen\\filename.png");
        Pattern img2 = new Pattern("C:\\Users\\ankit.kumar\\workspace\\Governeye\\screen\\open.png");
		//Pattern img1 = new Pattern(System.getProperty("user.dir")+"\\Governey\\screen\\filename.png");
		  
		 // Pattern img2 = new Pattern(System.getProperty("user.dir")+"\\Governey\\screen\\open.png");

		  Screen screen = new Screen();
		  
		  screen.type(img1,"C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3");
		  
		  screen.click(img2);	
*/
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	}

}
