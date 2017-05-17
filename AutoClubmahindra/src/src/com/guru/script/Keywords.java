package src.com.guru.script;
import static src.com.guru.script.DriverScript.APP_LOGS;
import static src.com.guru.script.DriverScript.CONFIG;
import static src.com.guru.script.DriverScript.OR;















import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Keywords {
	
	static String EmpDynamicID;
	static String DynamicEmail;
	
	public WebElement element=null;
	public WebDriver driver;
	
	
	
	public String closeBrowser(String object,String data){		
		APP_LOGS.debug("Closing Browser");
		try{
			
		if(data.equals("Chrome")){
			driver.close();
		}
		long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		return Constants.KEYWORD_PASS;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- unable to close the browser"+e.getMessage();
		}
	}
	
	
	public String openBrowser(String object,String data){		
		APP_LOGS.debug("Opening browser");
		if(data.equals("Mozilla")){
			driver=new FirefoxDriver();
			
			driver.manage().window().maximize();
		}
		else if(data.equals("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
	  }
		else if(data.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        	driver=new ChromeDriver();
        	driver.manage().window().maximize();
        	}
        	
		
		long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		return Constants.KEYWORD_PASS;

	}
	
	public String navigate(String object,String data){		
		APP_LOGS.debug("Navigating to URL");
		try{
			
		//	driver.navigate().to(OR.getProperty(object));
		driver.navigate().to(data);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- Not able to navigate";
		}
		return Constants.KEYWORD_PASS;
	}
	
	public  String clickButton(String object,String data)
	{
        APP_LOGS.debug("Clicking on Button");
        try{
        
        	WebDriverWait Exwait = new WebDriverWait(driver, 20);
	        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
	        //SkipB.click();
        	        	
        //	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        	
        	//driver.findElement(By.xpath(OR.getProperty(object))).click();

            driver.findElement(By.xpath(OR.getProperty(object))).click();
            Thread.sleep(20000);
           
            }catch(Exception e){
    			return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
            }
        
        
        
		return Constants.KEYWORD_PASS;
	}
	
	
    //To select a Radio Button with some pattern of string values,store the pattern value in test data and put a "|" in place of pattern in Xpath.
	
	

	public  String selectRadio(String object, String data){
        APP_LOGS.debug("Selecting a radio button");
        try{
            String temp[]=object.split(Constants.DATA_SPLIT);
            driver.findElement(By.xpath(OR.getProperty(temp[0]))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +"- Not able to find radio button";

        }

        return Constants.KEYWORD_PASS;

    }
	
	public  String AlertMessage(String object, String data){
        APP_LOGS.debug("Pop Up Message Verify");
        try{
            
        	System.out.println("Enter in Alert ");
        Thread.sleep(8000);
        	Alert alert = driver.switchTo().alert();
        	//String msg = alert.getText();
        	System.out.println(alert.getText());
        	//Assert.assertEquals(msg,"Skip");
        	alert.dismiss();
        	System.out.println("Clicking in Skip button ");
        	
        	
        }catch(Exception e){
        	
            return Constants.KEYWORD_FAIL +"- Verification message does not match.";

        }

        return Constants.KEYWORD_PASS;

    }
	
	
	
	
	public  String selectList(String object, String data){
		APP_LOGS.debug("Selecting from list");
		try{
			if(!data.equals(Constants.RANDOM_VALUE)){
			  driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			 
			}else{
				// logic to find a random value in list
				WebElement droplist= driver.findElement(By.xpath(OR.getProperty(object))); 
				List<WebElement> droplist_cotents = droplist.findElements(By.tagName("option"));
				Random num = new Random();
				int index=num.nextInt(droplist_cotents.size());
				String selectedVal=droplist_cotents.get(index).getText();
				
			  driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(selectedVal);
			}
		}catch(Exception e){
			return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();	

		}
		
		return Constants.KEYWORD_PASS;	
	}
	public String datePicker(String object, String data){
		APP_LOGS.debug("Select the date from datepicker");
		try{
			WebElement fromDate = driver.findElement(By.xpath(OR.getProperty(object)));
			WebElement table = driver.findElement(By.xpath(".//*[@class='ui-datepicker-calendar']/td"));
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			java.util.Iterator<WebElement> i = rows.iterator();
			while(i.hasNext()) {
			    WebElement row = i.next();
			    System.out.println(row.getText());
			}
			
			//driver.findElement(By.xpath(OR.getProperty(object))).selectByVisibleText(data);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}
	
	public String selectDropDownList(String object, String data){
		
		try{
			List<WebElement> listOptions=driver.findElements(By.xpath(OR.getProperty(object)));
			for(WebElement we : listOptions)
			{
				String text=we.getText();
				System.out.println("ENter in loop");
				System.out.println(text);
				if(text.equalsIgnoreCase(data))
				{
					System.out.println("Member Selected");
				
					
					
				}else{
					APP_LOGS.debug("Unable to select the element");
				}
			}
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}
	
	public String dropDownList(String object, String data){
		APP_LOGS.debug("Select the dropdown list");
		try{
			//driver.switchTo().frame("titlebar");
			//WebElement dropDown = driver.findElement(By.xpath(OR.getProperty(object)));
			Select value = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
			List<WebElement> size = value.getOptions();
			System.out.println("Size of City  is  "+size.size());
			String Text;
			for(WebElement we : size)
			{
				
			Text = we.getText();
			if(Text.equalsIgnoreCase(data))
			{
				System.out.println("Enter in Dropdown ");
				we.click();
			}
			
			}		
			
		
			/*int count=0;
			for(WebElement we : size)
			{
				String text=we.getText();
				count++;
				System.out.print(" "+count);
			 			   
		System.out.print(" "+text);
				
			}
			*///value.selectByVisibleText(data);	
			
			//driver.findElement(By.xpath(OR.getProperty(object))).selectByVisibleText(data);
		}catch(Exception e){
			System.out.println(e);
			return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	
	public String datepicker (String object, String data) throws InterruptedException{
		  Thread.sleep(3000);
		  try{
				//int  integerData = Integer.parseInt(data);
				
			  int i = Double.valueOf(data).intValue();
			  System.out.println("element is---->" +i);

			  
				//System.out.println("element is---->" +integetData);

			  //driver.switchTo().frame("titlebar");
			  WebElement table = driver.findElement(By.xpath(OR.getProperty(object)));
			  List<WebElement> tableData1 = (List<WebElement>) table.findElements(By.tagName("td"));
			  //String i = tableData1.getText();
			  for(WebElement element:tableData1)
			  {
				  //System.out.println("element is---->" +tableData1);

				  
				 if(element.getText().equals(""+i))
				 {
					  System.out.println("element is---->" +i);

					 
					 element.findElement(By.linkText(""+i)).click();
					 break;
				 }
					 
				  //System.out.println("list elements are"+valuesInList);
			  }
			  //System.out.println(tableData1);
			  //if(valuesInList.contains(data))
			  //{
				  //System.out.println("inside if "+valuesInList);

//				  int index=valuesInList.indexOf(tableData1);
//				  select.selectByIndex(index);
//				  System.out.println("Selected value is "+index);

			  //}
		  	}catch(Exception e){
	            return Constants.KEYWORD_FAIL +"- Unable to find the element";

		  		}
			return Constants.KEYWORD_PASS;
		}
	public String dropDownByIndex (String object, String data) throws InterruptedException{
		  Thread.sleep(3000);
		  try{
			  //driver.switchTo().frame("titlebar");
			  WebElement dropDown = driver.findElement(By.xpath(OR.getProperty(object)));
			  Select select = new Select(dropDown);
			  int i =select.getOptions().size();
			  ArrayList<String>  valuesInList = new ArrayList<String>();
			  for(WebElement element:select.getOptions())
			  {
				  valuesInList.add(element.getText());
				  
				  System.out.println("list elements are"+valuesInList);
			  }
			  System.out.println(data);
			  //if(valuesInList.contains(data))
			  //{
				  //System.out.println("inside if "+valuesInList);

				  int index=valuesInList.indexOf(data);
				  select.selectByIndex(index);
				  System.out.println("Selected value is "+index);

			  //}
		  	}catch(Exception e){
	            return Constants.KEYWORD_FAIL +"- Not able to find radio button";

		  		}
  			return Constants.KEYWORD_PASS;
		}
	
		
	public String verifyText(String object, String data){
		APP_LOGS.debug("Verifying the text");
		try{
			WebDriverWait waitObj=new WebDriverWait(driver, 10);
			
			waitObj.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(object)))));
			String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
	    	String expected=data;
	    	//System.out.println(expected);
	    	//String confirmation=driver.findElement(By.xpath(OR.getProperty("confirmation"))).getText();
	    	if(actual.contains(expected))
	    	{
			    System.out.println("verified"+expected);

	    	}
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
		return Constants.KEYWORD_PASS;
		
	}
	
	public  String writeInInput(String object,String data){
		APP_LOGS.debug("Writing in text box");
		
		try{
			System.out.println("Enter in Write In InPut = "+data);
			WebDriverWait Exwait = new WebDriverWait(driver, 25);
	        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
	        //SkipB.click();
	        
			int dataLength=data.length();
			for(int i=0; i<=dataLength-1;i++){
				char singleCharacter=data.charAt(i);
				String singleChar=Character.toString(singleCharacter);
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(singleChar);
				
			}
				
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

		}
		return Constants.KEYWORD_PASS;
		
	}
	
	public  String writeInput(String object,String data)
	{
		APP_LOGS.debug("Writing in text box");
		
		try{
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

		}
		return Constants.KEYWORD_PASS;
		
	}
	
	
	
	
	public  String writeIntInput(String object,String data){
		APP_LOGS.debug("Writing in integer value in text box");
		System.out.println("Exceuting writeIntInput Keyword............");
		
		int integetData = Integer.parseInt(data); 
				
		try{
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+integetData);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

		}
		return Constants.KEYWORD_PASS;
		
	}
	//For mouse over to Element
	public String mouseOverToElementAction(String object, String data){
		try{
			Actions actionObj = new Actions(driver);
			WebElement getElement =driver.findElement(By.xpath(OR.getProperty(object)));
			//actionObj.moveToElement(getElement).build().perform();
			actionObj.moveToElement(getElement, 0, 20).build().perform();
			//screenshotCaptue();
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- unable to mouse over the element"+e.getMessage();
		}
		 
		return Constants.KEYWORD_PASS;
	}
	
	
	
	public String mouseOverToElementPoint(String object, String data){
		try{
			int xPosition =0;
			Actions actionObj = new Actions(driver);
			WebElement getElement =driver.findElement(By.xpath(OR.getProperty(object)));
			Point elementLoc =getElement.getLocation();
			int MyData =Integer.parseInt(data);
			xPosition =50+MyData;
			int yPosition =50;
			System.out.println("x point is"+xPosition+" y point is = "+yPosition);
			actionObj.moveToElement(getElement, xPosition, yPosition);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- unable to mouse over the element"+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}

	
	public String screenshotCaptue(String object, String data){
		try{
			
			Thread.sleep(5000);
			int count =1;
			File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") +"//screenshots//"+count+".jpg"));
			count++;
			System.out.println(count+"st screenshot of the page is captured");
			
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- unable to take the screenshot"+e.getMessage();
		}
		
		
		return Constants.KEYWORD_PASS;
	}
	
	//window scroll to the point
	public String scrollToTheElement(String object,String data){
		try{
			WebElement scrollElement = driver.findElement(By.xpath(OR.getProperty(object)));
			Point objLocation =scrollElement.getLocation();
			int xposition =objLocation.x;
			System.out.println(xposition);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scroll(0,250);",scrollElement);
			//jse.executeScript("arguments[0].scrollIntoView();",scrollElement);


				
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- unable to scroll down to the element"+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}
	
	
	public  String verifyLinkText(String object,String data){
        APP_LOGS.debug("Verifying link Text");
        try{
        	String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
        	String expected=data;
        	String confirmation=driver.findElement(By.xpath(OR.getProperty("confirmation"))).getText();
        	if(actual.equals(expected))
        		return Constants.KEYWORD_PASS+"--"+confirmation;
        	else
        		return Constants.KEYWORD_FAIL+" -- Link text not verified";
        	
        }catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- Link text not verified"+e.getMessage();

        }
        
	}
	
	public  String click(String object,String data){
	       APP_LOGS.debug("Clicking on any element");
	       try{
	    	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	    	   
	    	   driver.findElement(By.xpath(OR.getProperty(object))).click();
			   }catch(Exception e){
					return Constants.KEYWORD_FAIL+" Not able to click";
			  }
			return Constants.KEYWORD_PASS;
	}
		
		public String clickLink(String object,String data){
	        APP_LOGS.debug("Clicking on link ");
	        try{
	        	
	        driver.findElement(By.xpath(OR.getProperty(object))).click();
	        }catch(Exception e){
				return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
	        }
	     
			return Constants.KEYWORD_PASS;
		}
	
		//**************************************************************************
		
		public String privacySetting(String object, String data){
			APP_LOGS.debug("Selecting the project privacy setting");
			
			try {
			driver.findElement(By.xpath(OR.getProperty("privacy_setting_icon"))).click();
			Thread.sleep(1000);
							
			if(data.equals(Constants.Public)){
			
		 		driver.findElement(By.xpath(OR.getProperty("public_radio"))).click();
		 						
				}
				
				else if(data.equals(Constants.Subscribers)){
				//	driver.findElement(By.xpath(OR.getProperty("privacy_setting_icon"))).click();
			 	Thread.sleep(1000);
					driver.findElement(By.xpath(OR.getProperty("subscribers_radio"))).click();
				//	driver.findElement(By.xpath(OR.getProperty("save_button"))).click();
				}
				else if(data.equals(Constants.Private)){
				//	 driver.findElement(By.xpath(OR.getProperty("privacy_setting_icon"))).click();
				 	Thread.sleep(1000);
					 driver.findElement(By.xpath(OR.getProperty("private_radio"))).click();
				//	driver.findElement(By.xpath(OR.getProperty("save_button"))).click();
				
				}
			driver.findElement(By.xpath(OR.getProperty("save_button"))).click();
				
			}catch(Exception e){
				return Constants.KEYWORD_FAIL +" - could not select the project privacy setting"+ e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}	
		
		
		//Applicaiton specific Keyword
		
				public String addBudget(String object, String data){
					  APP_LOGS.debug("Performing the Budget Operation");
					  try{
					   
					   String[] actualData = data.split(", ");
					    
					   System.out.println("\n"+actualData[0]+"\n"+actualData[1]+"\n"+actualData[2]+"\n"+actualData[3]+"\n"+actualData[4]+"\n"+actualData[5]);
					   
					   if(actualData[0].equalsIgnoreCase("Fixed Price")){
					     
						   driver.findElement(By.xpath(OR.getProperty("budget"))).sendKeys(actualData[1]);
					    // Fixed Price select dropdown xpath
						  //  Select select1 = new Select(driver.findElement(By.xpath(OR.getProperty("fixedPrice_select_Dropdown")))); 
					   	    //select1.selectByVisibleText(actualData[1]);  
					   }
					   
					   else if(actualData[0].equalsIgnoreCase("Hourly")){
					    
					    // Hourly radio button xpath
					    driver.findElement(By.xpath(OR.getProperty("hourly_radioButton"))).click(); 
					    
					    //Project duration selcect dropdown 
					    Select select2 = new Select(driver.findElement(By.xpath(OR.getProperty("hourly_projectDuration_Dropdown"))));
					    select2.selectByVisibleText(actualData[2]);
					       
					    // Hours per week select dropdown
					    Select select3 = new Select(driver.findElement(By.xpath(OR.getProperty("hourly_hrsPerWeek_Dropdown"))));
					    select3.selectByVisibleText(actualData[3]);
					        
					    // Min. rate/Hour inputbox  
					    driver.findElement(By.xpath(OR.getProperty("hourly_minRatePerHour"))).sendKeys(actualData[4]);
					        
					    // Max. rate/Hour inputbox
					    driver.findElement(By.xpath(OR.getProperty("hourly_maxRatePerHour"))).sendKeys(actualData[5]);    
					   }
					   
					   else {
					    return Constants.KEYWORD_FAIL +" - Nothing found in the budget Type cell ";
					   }
					  
					   
					  }catch(Exception e){
					   return Constants.KEYWORD_FAIL +" - could not select the Project Budget Type"+ e.getMessage();
					   
					  }
					  return Constants.KEYWORD_PASS;
					 }
		
				
				
				public String addSkills(String object, String data){
							  
				  				  
				  try{
					  if(!data.equals("")){
				     driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.ENTER);    
		    
					  }
				  
				  } catch(Exception e){
				  	return Constants.KEYWORD_FAIL;
				  }
				  return Constants.KEYWORD_PASS;
				 }
				 
				
				public String locationPreference(String object, String data){
					
					  try{
						   String[] actualData = data.split(", ");
					     
					     
					     	if(actualData[0].equalsIgnoreCase("Country")){
					       driver.findElement(By.xpath(OR.getProperty("location_icon"))).click();
						   driver.findElement(By.xpath(OR.getProperty("country_text_box"))).sendKeys(actualData[1]);
						   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
						   driver.findElement(By.xpath(OR.getProperty("location_auto_suggest"))).click();
						   driver.findElement(By.xpath(OR.getProperty("location_save_button"))).click();
					   }
					   
					   else if(actualData[0].equalsIgnoreCase("City")){
						   driver.findElement(By.xpath(OR.getProperty("location_icon"))).click();
						   driver.findElement(By.xpath(OR.getProperty("city_radio_button"))).click();
						   driver.findElement(By.xpath(OR.getProperty("city_text_box"))).sendKeys(actualData[1]);
						   driver.findElement(By.xpath(OR.getProperty("location_auto_suggest"))).click();
						   driver.findElement(By.xpath(OR.getProperty("city_text_box"))).sendKeys(actualData[2]);
						   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
						   driver.findElement(By.xpath(OR.getProperty("location_auto_suggest"))).click();
						   driver.findElement(By.xpath(OR.getProperty("location_save_button"))).click();
					   }
					   else if(actualData[0].equalsIgnoreCase("Zip Code")){
						   driver.findElement(By.xpath(OR.getProperty("location_icon"))).click();
						   driver.findElement(By.xpath(OR.getProperty("zip_radio_button"))).click();
						   driver.findElement(By.xpath(OR.getProperty("zip_dropdown"))).sendKeys(actualData[1]);
						   driver.findElement(By.xpath(OR.getProperty("zip_text_box"))).sendKeys(actualData[2]);
						   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
						   driver.findElement(By.xpath(OR.getProperty("location_save_button"))).click();
					   }
					   
					      	      
					      
					  }catch(Exception e){
					   return Constants.KEYWORD_FAIL +" - Not able to select the location"+ e.getMessage();
					   
					  }
					  return Constants.KEYWORD_PASS;
					 }
				
				
	
	// For screenshot
		public void captureScreenshot(String filename, String keyword_execution_result) throws IOException{
		// take screen shots
		if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
			// capturescreen
			
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
			
		}else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") ){
		// capture screenshot
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
		}
	}
	
	
		public  String categoryList(String object,String data){
		       APP_LOGS.debug("Clicking on any element");
		       try{
		    	   Thread.sleep(1000);
		    	   Select selectBox = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
		    	   selectBox.selectByVisibleText(data);
				   }catch(Exception e){
						return Constants.KEYWORD_FAIL+" Not able to select";
				  }
				return Constants.KEYWORD_PASS;
		}
		
		public String uploadPostProject(String obejct, String data){
			
			try{
				driver.findElement(By.xpath(OR.getProperty("attachment_icon"))).click();
				
				// switch focus of WebDriver to the next found window handle (that's your newly opened window)
				 for (String winHandle : driver.getWindowHandles()) {
				       Thread.sleep(1000);
					   driver.switchTo().window(winHandle); 
					   }
				 driver.findElement(By.xpath(OR.getProperty("browse_button"))).click();
					 Process process = new ProcessBuilder("C:\\Users\\sanjay.kaushik\\Desktop\\Check3.exe",
				                "C:\\Users\\sanjay.kaushik\\Desktop\\Apple-image.jpg", "Open").start();
				
				driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);  
				
				driver.findElement(By.xpath(OR.getProperty("upload_button"))).click();
				
				driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);  
				driver.findElement(By.xpath(OR.getProperty("done_button"))).click();
			}catch (Exception e){
				return Constants.KEYWORD_FAIL;
			}
		
			return Constants.KEYWORD_PASS;
		}
		
		public String uploadFile(String object, String data){
			try{
				WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
				element.sendKeys(data);
				return Constants.KEYWORD_PASS;
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+e.getMessage();
			}
		}
		
		public String projectAward(String obejct, String data){
			try{
				driver.findElement(By.xpath(OR.getProperty("emp_projects_Award"))).click();
				
				// switch focus of WebDriver to the next found window handle (that's your newly opened window)
				for (String winHandle : driver.getWindowHandles()) {
				       Thread.sleep(1000);
					   driver.switchTo().window(winHandle); 
					   }
				
				driver.findElement(By.xpath(OR.getProperty("emp_projects_confirmAward"))).click();
				
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
	
		public String TeslinkDropdown(String object, String data)
		{
			
			try{
			
              /*     System.out.println("Hello");
                   
				WebDriverWait waitObj=new WebDriverWait(driver, 20);
				
				Select select1= new Select(dropDown);
				System.out.println("Click");
					WebElement dropDown = driver.findElement(By.id("CSRFToken"));
				dropDown.click();
				
				WebElement element=waitObj.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[name='testproject']"))));
				
				System.out.println("Selecting Dropdown");
				
				//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(object))));
				element.click();
				
			
				Thread.sleep(8000);
				System.out.println("Selecting Dropdown");
				
				Select select1= new Select(driver.findElement(By.id("CSRFToken")));
				
				System.out.println("Selecting Dropdown");
			
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				select1.selectByValue("261481");
					
				
				Thread.sleep(15000);
				
				System.out.println("Selecting Dropdown");
				
				driver.switchTo().frame("titlebar");
				
			    Select select3=new Select(driver.findElement(By.name(OR.getProperty(object))));


				select3.selectByVisibleText(kOR.getProperty("ProjectName"));
			  //WebElement dropDown = driver.findElement(By.xpath(OR.getProperty(object)));
				Select value = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
				List<WebElement> size = value.getOptions();
				System.out.println("size is"+size);
				*/
				
				driver.switchTo().frame("titlebar");
				   //WebElement dropDown = driver.findElement(By.xpath(OR.getProperty(object)));
				   Select value = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
				   List<WebElement> size = value.getOptions();
				   System.out.println("size is"+size);
				   
				   value.selectByVisibleText(data);
				
				
			System.out.println("Finished");
				
					
			
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
				
			}
			return Constants.KEYWORD_PASS;
		}
		
		
		
		
		public String Resultclick(String object, String data)
		{
			
		try{
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        	
	        	driver.findElement(By.xpath(OR.getProperty(object))).click();
				
				
					
			
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Image click not Found "+e.getMessage();
				
			}
			return Constants.KEYWORD_PASS;
		}
		
		 //Taking Full Screenshot of Report module in Testlink
			public void FullScreenshot(String filename, String keyword_execution_result) throws IOException, InterruptedException
			{
			
				
				Thread.sleep(3000);

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        	
				
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    //FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
			    
			    
			 //   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  
	            FileUtils.copyFile(scrFile, new File("D:\\screenShot8.png"));
	            
	            System.out.println("CApture Screenshot");
			}
			
			
			//Enter Destination & Click on DEstination
			
			public String EnteDestination(String object, String data)
			{
				APP_LOGS.debug("Select the date from datepicker");
				try{
					int dataLength=data.length();
				
				for(int i=0; i<=dataLength-1;i++)
				{
					char singleCharacter=data.charAt(i);
					String singleChar=Character.toString(singleCharacter);
					driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(singleChar);
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				}
				
				
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					 
						//driver.findElement(By.xpath(OR.getProperty(object))).sendKeys("Clubmahindra");
						
					    List<WebElement> autoSuggest = driver.findElements(By.xpath("//div[@id='autocomplete']/ul/li"));
					    
					    // verify the size of the list
					    System.out.println("Size of the AutoSuggets is = " + autoSuggest.size());
					    // print the auto suggest
					    for (WebElement a : autoSuggest)
					    
					      //  System.out.println("Values are = " + a.getText());
					    
					    
					    autoSuggest.get(0).click();
					    System.out.println("Done");
					    
					    
					//driver.findElement(By.xpath(OR.getProperty(object))).selectByVisibleText(data);
				}
				catch(Exception e){
					return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
				}
				return Constants.KEYWORD_PASS;
			}
		
		public String DatePicCalender(String object, String data)
		{
			APP_LOGS.debug("Select the date from datepicker");
			try{
				
				WebElement fromDate = driver.findElement(By.xpath(OR.getProperty(object)));
				WebElement table = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]"));
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				java.util.Iterator<WebElement> i = rows.iterator();
				while(i.hasNext()) {
				    WebElement row = i.next();
				    System.out.println(row.getText());
				}
				
				//driver.findElement(By.xpath(OR.getProperty(object))).selectByVisibleText(data);
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
		//Method used to click on Calender & Enter date in Calender
		
		public String MemberCalender(String object, String data)
		{
			
			String Day;
			//int Diff;*/
			APP_LOGS.debug("Select the date from datepicker");
			try{
			System.out.println("Clicking on Calender");
		     //Explicit Wait
		/*	WebDriverWait waitObj=new WebDriverWait(driver, 10);
				
			     WebElement CalenderClick=waitObj.until(ExpectedConditions.elementToBeClickable((By.xpath(OR.getProperty(object)))));
		         	CalenderClick.click();
				//driver.findElement(By.xpath(OR.getProperty(object))).click();
		*/
		       	Thread.sleep(5000);
		      	//Read Excel check In Date 
		       	
		       	System.out.println("Date from Excel is "+data);
		       	String[] dateSplit = data.split("/");
		       	Day = dateSplit[0];
		       	
		       //Clicking on Calender			       	
		       	WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			    Actions actions = new Actions(driver);
      			actions.moveToElement(element).click().perform();
      			
      			//EMonth=Integer.parseInt(dateSplit[0]);
		        
		       /*	//Find Current Date
		        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date Currentdate = new Date();
				String CDdate=dateFormat.format(Currentdate);
				String[] dateSplit1 = CDdate.split("/");
		       	CMonth=Integer.parseInt(dateSplit[0]);
				     
		 		WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			    Actions actions = new Actions(driver);
      			actions.moveToElement(element).click().perform();
			
   	            //Click on Next to change Month
      			if(EMonth>12||EMonth<CMonth||EMonth>(CMonth+3)||EMonth==0)	
      			{
      						
      				System.out.println("Wrong Date enter in Excel");
      				
      				System.out.println("Curent Month = "+CMonth+" Excel Month ="+EMonth);
      			
      			}
      			else if(EMonth<(CMonth+4))
      			{
      				System.out.println("Curent Month = "+CMonth+" Excel Month ="+EMonth);
      				Diff=EMonth-CMonth;
      				WebElement nexlink=driver.findElement(By.xpath(OR.getProperty("CalenderNext")));
      				
      				for(int i=0;i<=Diff-1;i++)
      				{
      					
      					Thread.sleep(2000);
      					nexlink.click();
      				}
      				
      		    }      			*/
      			
				WebElement nexlink=driver.findElement(By.xpath(OR.getProperty("CalenderNext")));
				Thread.sleep(2000);
				nexlink.click();
		       //To Select the date 
				WebElement table = driver.findElement(By.xpath(".//table[@class='ui-datepicker-calendar']"));
				List<WebElement> rows = table.findElements(By.tagName("td"));
				java.util.Iterator<WebElement> i = rows.iterator();
				//while(i.hasNext()) {
				  //  WebElement row = i.next();
				for(WebElement row:rows) 
				{	
				//System.out.println(row.getText());
				    String date=row.getText();
				    if(date.equalsIgnoreCase(Day))
				    {
				    	row.click();
				    }
						   
				}
				
		}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
	
		//Select Person from Person travelling dropdown
		
		public String Memberdropdown(String object, String data){
			APP_LOGS.debug("Select the dropdown list");
			try{
				//System.out.println("Enter in Person Travlling dropdown");
				
				Select value = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
				value.selectByVisibleText(data);
				
				//System.out.println("Exit ");
				/*List<WebElement> size = value.getOptions();
				System.out.println("size is"+size);
				String Text;
				for(WebElement we : size)
				{
					Text=we.getText();
					System.out.println(Text);
				}
				*/
				//value.selectByVisibleText(data);	
				//driver.findElement(By.xpath(OR.getProperty(object))).selectByVisibleText(data);
			}
			catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
   // Method used to Click on Skip button present on Pop up
		
		public  String MemberAlert(String object, String data){
	        APP_LOGS.debug("Pop Up Message Verify");
	        try{
	            //By Using Explicit wait 
	        	
	        	/*WebDriverWait Exwait = new WebDriverWait(driver, 35);
	        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Skip")));
	        SkipB.click();*/
	        	//By using Sleep Method
	        
	        	Thread.sleep(25000);
               driver.findElement(By.linkText("Skip")).click();
      	      }
	        catch(Exception e){
	        	
	        	System.out.println(e);
	        	
	    return Constants.KEYWORD_FAIL +"- Verification message does not match.";
	    }
              return Constants.KEYWORD_PASS;
	    }
	
  // Increase the Pax & Kids & Infrant count
		
		public  String IncreasePax(String object,String data) throws InterruptedException
		{
			
	        APP_LOGS.debug("Clicking on Button");
	        try{
	          	String[] sr = data.split("\\.");
	           	int pax=Integer.parseInt(sr[0]);
	           	System.out.println("Pax count is  "+pax);
	        	Thread.sleep(2000);
	        
	        	if(pax==2)
	        	{        		
	        		Thread.sleep(1000);
	        	}
	        	else if(pax==1)
	        	{
	        		Thread.sleep(1000);
		        	driver.findElement(By.xpath(OR.getProperty("Decpax"))).click();	
	        	}
	        	else
	        		for(int i = 1;i <=pax-2;i++)
	        	{
	        		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		        	Thread.sleep(1000);
		        	driver.findElement(By.xpath(OR.getProperty(object))).click();
	        	}
	     /*      
	         int Pax=Integer.parseInt(CONFIG.getProperty("Pax"));
	        
	        for(int i=0;i<=Pax;i++)
	        {
	        	System.out.println("Enter in Loop");
	        	//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        	Thread.sleep(1000);
	        	driver.findElement(By.xpath(OR.getProperty(object))).click();
	        }
	     */
	          
	        	//driver.findElement(By.xpath(OR.getProperty(object))).click();
	           
	            }catch(NumberFormatException e) {
	            	
	            		return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
	            }
	        
	        return Constants.KEYWORD_PASS;
		}
		public String InsertGuest(String object, String data){
			APP_LOGS.debug("Select the dropdown list");
			try{
							
				Select value = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
				value.selectByVisibleText(data);
				
				//System.out.println("Exit ");
				/*List<WebElement> size = value.getOptions();
				System.out.println("size is"+size);
				String Text;
				for(WebElement we : size)
				{
					Text=we.getText();
					System.out.println(Text);
				}
				*/
				//value.selectByVisibleText(data);	
				//driver.findElement(By.xpath(OR.getProperty(object))).selectByVisibleText(data);
			}
			catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
		public String RedirectHome(String object, String data)
		{ APP_LOGS.debug("Redirect on Home page");
			try{
				
				
				WebDriverWait Exwait = new WebDriverWait(driver, 20);
		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
		        SkipB.click();
				
				
				
			}
			catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
		
		public String EnterNumeric(String object, String data)
		{ APP_LOGS.debug("Redirect on Home page");
			try{
				System.out.println("Enter In Numeric value is ="+data);
				WebDriverWait Exwait = new WebDriverWait(driver, 20);
		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		        //SkipB.click();
  
	          	String[] sr = data.split("\\.");
	          // 	int MemberId=Integer.parseInt(sr[0]);
	          	
	        	driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+sr[0]);	        	
	        
				
			}
			catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
		
		public  String MemberFeedback(String object, String data){
	        APP_LOGS.debug("Pop Up Message Verify");
	        try{
	            //By Using Explicit wait 
	        	
	        	/*WebDriverWait Exwait = new WebDriverWait(driver, 35);
	        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Skip")));
	        SkipB.click();*/
	        	//By using Sleep Method
	        
	        	Thread.sleep(25000);
               driver.findElement(By.linkText("No Thanks")).click();
      	      }
	        catch(Exception e){
	        	
	        	System.out.println(e);
	        	
	    return Constants.KEYWORD_FAIL +"- Verification message does not match.";
	    }
              return Constants.KEYWORD_PASS;
	    }
	
		
		public  String ClickPopButton(String object,String data)
		{
	        APP_LOGS.debug("Clicking on Button");
	        try{
	        
	        	Thread.sleep(15000);
	        	System.out.println("Click on Pop Up Button ");
	        	        	
	        	//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        	
	        	driver.findElement(By.xpath(OR.getProperty(object))).click();

	            //driver.findElement(By.xpath(OR.getProperty(object))).click();
	           
	            }catch(Exception e){
	    			return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
	            }
        
			return Constants.KEYWORD_PASS;
		}
		
		
		// Method to click on terms & Condition check Box
		
		public String CheckBox(String object, String data)
		{
			try{
		//	System.out.println("Enter In check Box");
			
			Thread.sleep(8000);			
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		
		//driver.findElement(By.className("control-indicator")).click();	
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL +" Check box Not Selected "+e.getMessage();
			}
		
		return Constants.KEYWORD_PASS;
		}
		
	//Fundays Role dropdown 	
		
		public String Select_Dropdown(String object, String data){
			
			try{
				WebDriverWait Exwait = new WebDriverWait(driver, 25);
		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		        //SkipB.click();
		        //Thread.sleep(2000);
		        Select value = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
				List<WebElement> size = value.getOptions();
				//System.out.println("Size of City  is  "+size.size());
				String Text;
				for(WebElement we : size)
				{
				Text = we.getText();
				if(Text.equalsIgnoreCase(data))
				{
					
					Thread.sleep(2000);
					we.click();
				}else if(Text.contains(".com"))
				{
					//String[] arr1 = data.split("@");
					DynamicEmail =data;
					//System.out.println("Domain is ="+DynamicEmail);
					
				}
				
				}
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
		//Dynamic Domain 
		
	public String DynamicEmailId(String object, String data){
			
			try{
				WebDriverWait Exwait = new WebDriverWait(driver, 20);
		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		        //SkipB.click();
		  String Email=data+EmpDynamicID+"@"+DynamicEmail;  
		  System.out.println(Email);
		       driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Email);
	      
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
		//Select if Dropdown Contains Numeric Value
               public String NumericDropdown(String object, String data){
			
			try{
				WebDriverWait Exwait = new WebDriverWait(driver, 20);
		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		        //SkipB.click();
		     	String[] sr = data.split("\\.");
	           	data=sr[0];
	           	
		        Select value = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
				value.selectByValue(data);
		        
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
           
               //To generate Dynamic EMployee ID
               public String DynamicID(String object, String data){
       			
       			try{
       				WebDriverWait Exwait = new WebDriverWait(driver, 20);
       		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
       		        //SkipB.click();
     		    
       				//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       				String[] sr = data.split("\\.");
       	           	//data=sr[0];
       	         Random rand = new Random();
 	    		int  n = rand.nextInt(50) + 1;
 	    		EmpDynamicID=sr[0]+n;
 	    		System.out.println("Dynamic Employee Id ="+EmpDynamicID);
 	    		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+sr[0]+n);	
       		       
       			}catch(Exception e){
       				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
       			}
       			return Constants.KEYWORD_PASS;
       		}
               
               
             //Extract all the Link
           	public String ExtractTableLink(String object,String data){
    	        APP_LOGS.debug("Clicking on link ");
    	        try{
    	        	WebDriverWait Exwait = new WebDriverWait(driver, 20);
    		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
    		        SkipB.click();
    		        
    		      /*  WebElement table = driver.findElement(By.xpath(".//table[@class='ui-datepicker-calendar']"));
    				List<WebElement> rows = table.findElements(By.tagName("td"));
    				java.util.Iterator<WebElement> i = rows.iterator();*/
    		          
    		        WebElement Table=driver.findElement(By.xpath(OR.getProperty(object)));
    		        List<WebElement> Link = Table.findElements(By.tagName("a"));
    		        for(WebElement we :Link)
    		        {
    		        	String LinkText=we.getText();
    		        	we.click();
    		        } 
    		        
    	        //driver.findElement(By.xpath(OR.getProperty(object))).click();
    	        }catch(Exception e){
    				return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
    	        }
    	     
    			return Constants.KEYWORD_PASS;
    		}
           	
           	public String FundaysCalender(String object, String data)
    		{ APP_LOGS.debug("Fundays Calender");
    		String Day;
    			try{
    				System.out.println("Enter In Fundays Calender is ="+data);
    				WebDriverWait Exwait = new WebDriverWait(driver, 20);
    		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
    		        //SkipB.click();
    		        
    		        String[] str= data.split("/");
    		        
    		        Day=str[0];
    		        
      		        WebElement table = driver.findElement(By.xpath(".//table[@class='ui-datepicker-calendar']"));
    				List<WebElement> rows = table.findElements(By.tagName("td"));
    				java.util.Iterator<WebElement> i = rows.iterator();
    				//while(i.hasNext()) {
    				  //  WebElement row = i.next();
    				for(WebElement row:rows) 
    				{	
    				//System.out.println(row.getText());
    				    String date=row.getText();
    				    if(date.equalsIgnoreCase(Day))
    				    {
    				    	row.click();
    				    }
    						   
    		        // 	int MemberId=Integer.parseInt(sr[0]);
    		        driver.findElement(By.xpath(OR.getProperty(object))).click();
    	        	driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);	        	
    	        
    				
    			}
    			}
    			catch(Exception e){
    				return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
    			}
    			return Constants.KEYWORD_PASS;
    		}
               
		public String ClickLink(String object,String data){
	        APP_LOGS.debug("Clicking on link ");
	        try{
	        	WebDriverWait Exwait = new WebDriverWait(driver, 20);
		        WebElement SkipB = Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
		        SkipB.click();
	        //test
		        //driver.findElement(By.xpath(OR.getProperty(object))).click();
	        }catch(Exception e){
				return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
	        }
	     
			return Constants.KEYWORD_PASS;
		}
		public String Switchframe(String object, String data){
			   APP_LOGS.debug("Select the dropdown list");
			   try{
			    driver.switchTo().frame(data);
			    
			   }catch(Exception e){
			    System.out.println(e);
			    return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
			   }
			   return Constants.KEYWORD_PASS;
			  }
		
		
		
		
}



