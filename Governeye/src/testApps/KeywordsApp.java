package testApps;
//import static src.com.guru.script.DriverScript.APP_LOGS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xalan.xsltc.compiler.sym;
import org.openqa.selenium.By;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import datatable.Xlfile_Reader;


//import src.com.guru.script.Constants;
import testReports.TestReports;
import util.DbManager;
import util.ErrorCollectors;
import util.TestConfig;
import util.TestUtil;
import util.monitoringMail;





public class KeywordsApp extends DriverApp {
	
	   public static Random randomGenerator = new Random();
	   public static Calendar cal = new GregorianCalendar();  //used for adding current date in variable and then used in paths
	   public static int date = cal.get(Calendar.DATE);  //used for adding current date in variable and then used in paths
	   public static int month = cal.get(Calendar.MONTH);  //used for adding current date in variable and then used in paths
	   public static int year = cal.get(Calendar.YEAR);  //used for adding current date in variable and then used in paths
	   public static int sec =cal.get(Calendar.SECOND);  //used for adding current date in variable and then used in paths
	   public static int day =cal.get(Calendar.HOUR_OF_DAY);  //used for adding current date in variable and then used in paths
	   public static int hour=cal.get(Calendar.HOUR);  //used for adding current date in variable and then used in paths
	   public static int min=cal.get(Calendar.MINUTE);  //used for adding current date in variable and then used in paths
	   public static String sMin = new Integer(randomGenerator.nextInt(60)).toString(); //Converted Integer value to String and then used in paths
	   public static String sSec=new Integer(randomGenerator.nextInt(60)).toString(); //Converted Integer value to String and then used in paths
	   public static String sHour=new Integer(randomGenerator.nextInt(24)).toString();  //Converted Integer value to String and then used in paths
	   public static String sDate=new Integer(date).toString();  //Converted Integer value to String and then used in paths
	   /*public static String sMin = new Integer(min).toString(); //Converted Integer value to String and then used in paths
	   public static String sSec=new Integer(sec).toString(); //Converted Integer value to String and then used in paths
	   public static String sHour=new Integer(hour).toString();  //Converted Integer value to String and then used in paths
	   */
	   
	   public static String call_id ; //Used in GetText() and DBQuerycheck() to store the call id to be used for Eval UI
	   public static String sUser=null;
	   public static String sUser_Name;
	   public static Xlfile_Reader datareader=null;
	   public static Xlfile_Reader datawriter=null;
	   public static float round;
	   public static float round1;
       public static String currentactivity=null;
	   //datepicker code 
	   static WebElement datePicker;
	   static List<WebElement> noOfColumns;
	   static List<String> monthList = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
	   // Expected Date, Month and Year
	   public static int expMonth;
	   public static int expYear;
	   public static String expDate = null;
	   // Calendar Month and Year
	   static String calMonth = null;
	   static String calYear = null;
	   static boolean dateNotFound;
	   
	   public static String script_error=null;
	   public static int globalwait;
	   public static int invalidImageCount;
	//Navigate to the current URL
	   
	public static String navigate() throws Throwable{
		APPLICATION_LOGS.debug("Executing Navigate");
		try{
			driver.get(CONFIG.getProperty(object));
		
		    
		}catch(Throwable t){
			
			// Report error in Application logs
			APPLICATION_LOGS.debug("Error while navigating -"+ object + t.getMessage());
					   
		}
		return "Pass";
		
	}
	
	//Clicking on a link or an Object
	
	public static String clickLink() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing clickLink");
		try{
		//	selenium.isElementPresent(Objects.getProperty(object));
		//	selenium.click(Objects.getProperty(object));
		driver.findElement(By.xpath(Objects.getProperty(object))).click();
		}catch(Throwable t){
			
			// Report error in Application logs
			APPLICATION_LOGS.debug("Error while clicking on an Object -"+ object + t.getMessage());
			script_error = "Timed out after "+globalwait+" miliseconds";
			
		   return "Fail - Link Not Found";
		}
		
		return "Pass";
	}
	

	//delete a row in excel

	
	//Input data Keyword
	
		public static String input() throws Exception{
			
			APPLICATION_LOGS.debug("Executing input Keyword");
			// extract the test data
			String message = "pass";
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			try{
					System.out.println("Input keyword data :"+data);
			   driver.findElement(By.xpath(Objects.getProperty(object))).sendKeys(data);
			   
				}catch(Exception t){
					// report error
				APPLICATION_LOGS.debug("Error while wrinting into input -"+ object + t.getMessage());
					
				script_error = "Timed out after "+globalwait+" miliseconds";
				
				return "Fail - "+t.getMessage();
				//return "Fail - "+t.getMessage();
					
				}
				
			
				return "Pass";
				
				
		}
		
		
		
		
	public static String softAssertTrue() throws Exception{
			
			APPLICATION_LOGS.debug("Executing input Keyword");
			// extract the test data
			String message = "pass";
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			try{
					System.out.println("Assert keyword data :"+data);
					System.out.println(driver.findElement(By.xpath(Objects.getProperty(object))).getText());
					ErrorCollectors.verifyEquals(driver.findElement(By.xpath(Objects.getProperty(object))).getText(), data);
					System.out.println("Data matches expected was : "+driver.findElement(By.xpath(Objects.getProperty(object))).getText());
				}catch(Exception t){
					// report error
					System.out.println("Inside catch");
					APPLICATION_LOGS.debug("Error while wrinting into input -"+ object + t.getMessage());
					
					script_error = "Timed out after "+globalwait+" miliseconds";
					
					return "Fail - "+t.getMessage();
								
				}
				
			
				return "Pass";
				
				
		}
		
	
		
		
		
		//Verifying text presence 
		
		
	           
		  public static String verifyText(){
		         APPLICATION_LOGS.debug("Executing verifyText");
		         String expected=APPTEXT.getProperty(object);
		         String actual=driver.findElement(By.xpath(Objects.getProperty(object))).getText();
		         APPLICATION_LOGS.debug(expected);
		         APPLICATION_LOGS.debug(actual);
		         try{
		             Assert.assertEquals(actual.trim() , expected.trim());
		         }catch(Throwable t){
		             // error
		             APPLICATION_LOGS.debug("Error in text - "+object);
		             APPLICATION_LOGS.debug("Actual - "+actual);
		             APPLICATION_LOGS.debug("Expected -"+ expected);
		             return "Fail -"+ t.getMessage();
		            
		         }
		        
		         return "Pass";
		        
		     
	       
	        
	        
	        }
		
		//verifyTextOnThePage
		public void verifyTextOnThePage (String expected) throws InterruptedException{
			final WebDriverException exception =new WebDriverException();
			try{
			if(driver.findElement(By.xpath(Objects.getProperty(object))).getText().contains(expected)){
			System.out.println(expected +" text is on this page");
			}
			else{
			System.out.println(expected +" text is NOT on this page");
			throw new WebDriverException(exception.getMessage());
			}
			
			}
			catch (WebDriverException e) {
			throw new WebDriverException(e.getMessage());
			}
			
		} 
	
		
		//verify video play
	public void verifyvideo()	{
		final WebDriverException exception =new WebDriverException();
  try{
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			   
			 //play video
			 js.executeScript("document.getElementsByClassName('fp-ui')[0].click();");
	}
  catch (WebDriverException e) {
		throw new WebDriverException(exception.getMessage());
		}
			
		
	}	
		
		
		
		
		//verifyTextOnThePage
				public void verifyTextOnpage() {
					final WebDriverException exception =new WebDriverException();
					String expected =testData.getCellData(currentTest, data_column_name , testRepeat);
                   try{
						List<WebElement> listOptions=driver.findElements(By.xpath(Objects.getProperty(object)));
						for(WebElement we : listOptions)
						{
							String text=we.getText();
							System.out.println("ENter in loop");
							System.out.println(text);
							if(text.equalsIgnoreCase(expected))
							{
								System.out.println(expected +" post is on this page");
							
								
							}else{
								APPLICATION_LOGS.debug("post is NOT on this page");
								throw new WebDriverException(exception.getMessage());

							}
						}	
					}
					catch (WebDriverException e) {
					throw new WebDriverException(e.getMessage());
					}
					
				} 

				
				public static void activityitem() {
					final WebDriverException exception =new WebDriverException();
					String expected =testData.getCellData(currentTest, data_column_name , testRepeat);
                   try{
                	   	
					   List<WebElement> allactivity = driver.findElements(By.className("activity-item"));
					   
				for(WebElement we : allactivity)
						{   
							String activity1=we.getAttribute("data-activity-feed-item");
							String activitytext = driver.findElement(By.xpath(".//*[@id="+activity1+"]/div[1]/span[1]/span[2]")).getText();
							
							if(activitytext.equalsIgnoreCase(expected)){
							System.out.println(activitytext);
							currentactivity = activity1;
						}
							else{
								APPLICATION_LOGS.debug("post is NOT on this page");
								throw new WebDriverException(exception.getMessage());

							}
						}	
					}
					catch (WebDriverException e) {
					throw new WebDriverException(e.getMessage());
					}
					
				} 				
				
public static String validateInvalidImages() {
					try {
						invalidImageCount = 0;
						List<WebElement> imagesList = driver.findElements(By.tagName("img"));
						System.out.println("Total no. of images are " + imagesList.size());
						for (WebElement imgElement : imagesList) {
							if (imgElement != null) {
								verifyimageActive(imgElement);
							}
						}
						System.out.println("Total no. of invalid images are "	+ invalidImageCount);
					} catch (Exception e) {
						e.printStackTrace();
						return "Fail - "+e.getMessage();
					}
					 return "Pass";
				}		
		
public static void verifyimageActive(WebElement imgElement) {
	try {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(imgElement.getAttribute("src"));
		HttpResponse response = client.execute(request);
		// verifying response code he HttpStatus should be 200 if not,
		// increment as invalid images count
		if (response.getStatusLine().getStatusCode() != 200)
			invalidImageCount++;
	} catch (Exception e) {
		e.printStackTrace();
	}
}


public void validateInvalidlinks() {
	try {
		 //Find total No of links on page and print In console.
		  List<WebElement> total_links = driver.findElements(By.tagName("a"));
		  System.out.println("Total Number of links found on page = " + total_links.size());
		  
		  //for loop to open all links one by one to check response code.
		  boolean isValid = false;
		  for (int i = 0; i < total_links.size(); i++) {
		   String url = total_links.get(i).getAttribute("href");

		   if (url != null) {
		    
		    //Call getResponseCode function for each URL to check response code.
		    isValid = getResponseCode(url);
		    
		    //Print message based on value of isValid which Is returned by getResponseCode function.
		    if (isValid) {
		     System.out.println("Valid Link:" + url);
		     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
		     System.out.println();
		    } else {
		     System.out.println("Broken Link ------> " + url);
		     System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
		     System.out.println();
		    }}
		    else {    
		    //If <a> tag do not contain href attribute and value then print this message
		    System.out.println("String null");
		    System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
		    System.out.println();
		    continue;
		   }
		  }
			System.out.println("Total no. of invalid images are "	+ invalidImageCount);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
}		

public static boolean getResponseCode(String chkurl) {
	  boolean validResponse = false;
	  try {   
	   //Get response code of URL
	   HttpResponse urlresp = new DefaultHttpClient().execute(new HttpGet(chkurl));
	   int resp_Code = urlresp.getStatusLine().getStatusCode();
	   System.out.println("Response Code Is : "+resp_Code);
	   if ((resp_Code == 404) || (resp_Code == 505)) {
	    validResponse = false;
	   } else {
	    validResponse = true;
	   }
	  } catch (Exception e) {

	  }
	  return validResponse;
	 }
	

		public static String clickButton(){
			APPLICATION_LOGS.debug("Executing clickButton Keyword");
			
			
			try{
				driver.findElement(By.xpath(Objects.getProperty(object))).click();
				}catch(Throwable t){
					// report error
					APPLICATION_LOGS.debug("Error while clicking on Button -"+ object + t.getMessage());
					return "Fail - "+t.getMessage();
				}
				
				return "Pass";
		}
		
		
		public static String select(){
			APPLICATION_LOGS.debug("Executing select Keyword");
			// extract the test data
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			
			try{
				driver.findElement(By.xpath(Objects.getProperty(object))).sendKeys(data);
				}catch(Throwable t){
					// report error
					APPLICATION_LOGS.debug("Error while Selecting from droplist -"+ object + t.getMessage());
					return "Fail - "+t.getMessage();
				}
				
				return "Pass";
		}
		
		
		
		public static String waitfor() throws NumberFormatException, InterruptedException, AddressException, MessagingException{
		     APPLICATION_LOGS.debug("Executing wait Keyword");
		  // extract the test data
				String data =testData.getCellData(currentTest, data_column_name , testRepeat);
				try{
					 
					float test = (Float.parseFloat(data));
					int test1 = (int) test;
					Thread.sleep(test1);
					globalwait = test1/1000;
				}catch(Throwable t){
					APPLICATION_LOGS.debug("Error while waiting -"+ object + t.getMessage());
				    return "Fail - "+t.getMessage();
								}
		     return "Pass";
		}
		
		
		public String Switchframe(String object, String data){
			   APPLICATION_LOGS.debug("Select the dropdown list");
			   try{
			    driver.switchTo().frame(data);
			    
			   }catch(Exception e){
			    System.out.println(e);
			    return "Fail"+" Object not found "+e.getMessage();
			   }
			   return "Pass";
			  }

public static String changewindow() throws Exception{
			
			APPLICATION_LOGS.debug("Executing input Keyword");
			// extract the test data
			String message = "pass";
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			try{
					System.out.println("Input keyword data :"+data);
					
					
			   driver.findElement(By.xpath(Objects.getProperty(object))).sendKeys(data);
			   
				}catch(Exception t){
					// report error
				APPLICATION_LOGS.debug("Error while wrinting into input -"+ object + t.getMessage());
					
				script_error = "Timed out after "+globalwait+" miliseconds";
				
				return "Fail - "+t.getMessage();
				//return "Fail - "+t.getMessage();
					
				}
				
			
				return "Pass";
				
				
		}

    public static String select_dropdown(){
    	APPLICATION_LOGS.debug("Selecting a dropdown");
    	String data = testData.getCellData(currentTest, data_column_name, testRepeat);
    	
    	try{
    		System.out.println("Selected data is :"+data);
    		WebElement dropdown = driver.findElement(By.xpath(Objects.getProperty(object)));
    		Select value = new Select(dropdown);
			List<WebElement> size = value.getOptions();
			System.out.println("Size of dropdown  is  "+size.size());
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

    }catch(Exception t){
		// report error
	APPLICATION_LOGS.debug("Error while selecting the dropdown -"+ object + t.getMessage());
		
	script_error = "Timed out after "+globalwait+" miliseconds";
	
	return "Fail - "+t.getMessage();
	//return "Fail - "+t.getMessage();
		
	}
	

	return "Pass";
	
	
    }
    
    
    
    public static String verifycolour() throws NumberFormatException, InterruptedException, AddressException, MessagingException
    {
    	APPLICATION_LOGS.debug("Executing wait Keyword");
  	  // extract the test data
  			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
  			try{
  				
  				WebElement iselementpresent = driver.findElement(By.xpath(Objects.getProperty(object)));
  				if(iselementpresent!= null){
  					APPLICATION_LOGS.debug("Element found");
  					String color = iselementpresent.getCssValue("color");
  					String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

  					int hexValue1=Integer.parseInt(hexValue[0]);
  					hexValue[1] = hexValue[1].trim();
  					int hexValue2=Integer.parseInt(hexValue[1]);
  					hexValue[2] = hexValue[2].trim();
  					int hexValue3=Integer.parseInt(hexValue[2]);

  					String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
  					Assert.assertEquals(data, actualColor);
  				}
  				
  			}catch(Throwable t){
  				APPLICATION_LOGS.debug("Element not found-"+ object + t.getMessage());
  			    return "Fail - "+t.getMessage();
  							}
  	     return "Pass";
  	}    

   public static String pickExpDate() throws InterruptedException, ParseException{
	   APPLICATION_LOGS.debug("Selecting a dropdown");
	   String data = testData.getCellData(currentTest, data_column_name, testRepeat);
 	try{ 
   	//Click on date text box to open date picker popup.
   	driver.findElement(By.xpath(Objects.getProperty(object))).click();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		  //Click on next so that we will be in next month
		 // driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		  
		  /*DatePicker is a table.So navigate to each cell 
		   * If a particular cell matches value 13 then select it
		   */
		  WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
		  List<WebElement> rows=dateWidget.findElements(By.tagName("tr"));
		  List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		  
		  for (WebElement cell: columns){
		   //Select 13th Date 
		   if (cell.getText().equals("13")){
		   cell.findElement(By.linkText("13")).click();
		   break;
		   }
		  }
/*    	APPLICATION_LOGS.debug("Picking a date");
    	String data = testData.getCellData(currentTest, data_column_name, testRepeat);
    	  
    	//Click on date text box to open date picker popup.
    	driver.findElement(By.xpath(Objects.getProperty(object))).click();
    	  dateNotFound = true;
    	  
    	  //Set your expected date, month and year.  
    	DateFormat inputDF  = new SimpleDateFormat("MM/dd/yyyy");
  		Date date1 = inputDF.parse(data);

  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date1);
  	  int expDate = 3;
  	  int expMonth= 7;
  	  int expYear = 2004;
  		int expMonth = cal.get(Calendar.MONTH);
  		int expDate = cal.get(Calendar.DAY_OF_MONTH);
  		int expYear = cal.get(Calendar.YEAR);
  		
  		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
  	    String expMonth  = monthNames[month];  
    	  //This loop will be executed continuously till dateNotFound Is true.
    	  while(dateNotFound)
    	  { 
    	   //Retrieve current selected month name from date picker popup.
    	   calMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
    	   //Retrieve current selected year name from date picker popup.
    	   calYear = driver.findElement(By.className("ui-datepicker-year")).getText();
    	   
    	   //If current selected month and year are same as expected month and year then go Inside this condition.
    	   if(monthList.indexOf(calMonth)+1 == expMonth && (expYear == Integer.parseInt(calYear)))
    	   {
    	    //Call selectDate function with date to select and set dateNotFound flag to false.
    		String strI = Integer.toString(expDate);
    	    selectDate(strI);
    	    dateNotFound = false;
    	   }
    	   //If current selected month and year are less than expected month and year then go Inside this condition.
    	   else if(monthList.indexOf(calMonth)+1 < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear))
    	   {
    	    //Click on next button of date picker.
    	    driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
    	   }
    	   //If current selected month and year are greater than expected month and year then go Inside this condition.
    	   else if(monthList.indexOf(calMonth)+1 > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear))
    	   {
    	    //Click on previous button of date picker.
    	    driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
    	   }
    	  }
    	  Thread.sleep(3000);
    	 } 

    	 public static void selectDate(String date)
    	 
    	 {
    	  datePicker = driver.findElement(By.id("ui-datepicker-div")); 
    	  noOfColumns=datePicker.findElements(By.tagName("td"));

    	  //Loop will rotate till expected date not found.
    	  for (WebElement cell: noOfColumns){
    	   //Select the date from date picker when condition match.
    	   if (cell.getText().equals(date)){
    	    cell.findElement(By.linkText(date)).click();
    	    break;
    	   }*/
		  }catch(Exception t){
				// report error
			APPLICATION_LOGS.debug("Error while selecting the dropdown -"+ object + t.getMessage());
				
			script_error = "Timed out after "+globalwait+" miliseconds";
			
			return "Fail - "+t.getMessage();
			//return "Fail - "+t.getMessage();
				
			}
			

			return "Pass";
			
			
		    } 
    
    
    public static String upload(){
    	APPLICATION_LOGS.debug("Uploading a file");
    	String data = testData.getCellData(currentTest, data_column_name, testRepeat);
    	
    	try{
    		System.out.println("Selected data is :"+data);
    		WebElement uploadbutton = driver.findElement(By.xpath(Objects.getProperty(object)));
    		uploadbutton.click();
    		Pattern img1 = new Pattern(System.getProperty("user.dir")+"\\Governey\\screen\\window.png");
    		  
    		  Pattern img2 = new Pattern(System.getProperty("user.dir")+"\\Governey\\screen\\open.png");

    		  Screen screen = new Screen();
    		  
    		  screen.type(data);
    		  
    		  screen.click(img2);	

    }catch(Exception t){
		// report error
	APPLICATION_LOGS.debug("Error while selecting the dropdown -"+ object + t.getMessage());
		
	script_error = "Timed out after "+globalwait+" miliseconds";
	
	return "Fail - "+t.getMessage();
	//return "Fail - "+t.getMessage();
		
	}
	

	return "Pass";
	
	
    }
    
    
    public static String waitinvisible() throws NumberFormatException, InterruptedException, AddressException, MessagingException{
	     APPLICATION_LOGS.debug("Executing wait Keyword");
	  // extract the test data
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			try{
				float test = (Float.parseFloat(data));
				int test1 = (int) test;
				//long l = Long.parseLong(data); 
				WebDriverWait wait = new WebDriverWait(driver,test1);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Objects.getProperty(object))));
				
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while waiting -"+ object + t.getMessage());
			    return "Fail - "+t.getMessage();
							}
	     return "Pass";
	}
  
    
    public static String waitvisible() throws NumberFormatException, InterruptedException, AddressException, MessagingException{
	     APPLICATION_LOGS.debug("Executing wait Keyword");
	  // extract the test data
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			try{
				float test = (Float.parseFloat(data));
				int test1 = (int) test;
				//long l = Long.parseLong(data); 
				WebDriverWait wait = new WebDriverWait(driver,test1);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Objects.getProperty(object))));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Objects.getProperty(object))));
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while waiting -"+ object + t.getMessage());
			    return "Fail - "+t.getMessage();
							}
	     return "Pass";
	}
    
    
 
//Selecting numeric elements from Drop down list
/*
		public static String select_number(){
			APPLICATION_LOGS.debug("Executing select Keyword");
			
			// extract the test data
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
					
			try{
				
				
				selenium.isElementPresent(Objects.getProperty(object));
				selenium.select(Objects.getProperty(object),"label="+data.replaceAll("\\.0*$", ""));
				System.out.println("data printed as :"+data.replaceAll("\\.0*$", ""));
			}catch(Throwable t){
				
					// report error
					APPLICATION_LOGS.debug("Error while Selecting from droplist -"+ object + t.getMessage());
				
					return "Fail - "+t.getMessage();
				}
				
				return "Pass";
		}*/
		
		
	
	/*
	
	//Clicking on a link or an Object which contains Pop up in same window
	    
    public static String clickLink_popUp() throws AddressException, MessagingException{
        
    	APPLICATION_LOGS.debug("Executing clickLink");
          try{
         if(selenium.isElementPresent("//div[@class='gwt-PopupPanel']")){
                selenium.click("//a[@class='gwt-Anchor alertLink']");
                selenium.isElementPresent(Objects.getProperty(object));
                Thread.sleep(5000);
                selenium.click(Objects.getProperty(object));
          }else{
                selenium.isElementPresent(Objects.getProperty(object));
                selenium.click(Objects.getProperty(object));
          }
          }catch(Throwable t){
        	  
                // report error
                APPLICATION_LOGS.debug("Error while clicking on an Object -"+ object + t.getMessage());
                script_error = "Timed out after "+globalwait+" miliseconds";
                return "Fail - Link Not Found";
          }
          
          return "Pass";
    }

	
	
	//Input data Keyword
	
	public static String input() throws Exception{
		
		APPLICATION_LOGS.debug("Executing input Keyword");
		// extract the test data
		String message = "pass";
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
		
		try{
			selenium.isElementPresent(Objects.getProperty(object));
			selenium.type(Objects.getProperty(object),data);
			System.out.println("Input keyword data :"+data);
		   // driver.findElement(By.xpath(Objects.getProperty(object))).sendKeys(data);
			}catch(Exception t){
				// report error
			APPLICATION_LOGS.debug("Error while wrinting into input -"+ object + t.getMessage());
				
			script_error = "Timed out after "+globalwait+" miliseconds";
			
			throw t;
			//return "Fail - "+t.getMessage();
				
			}
			
		
			return "Pass";
			
			
	}
	
	
	
	//Input data
	
		public static String input_number() throws Exception{
			
			APPLICATION_LOGS.debug("Executing input Keyword");
			// extract the test data
			String message = "pass";
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			
			
			try{
				selenium.isElementPresent(Objects.getProperty(object));
				selenium.type(Objects.getProperty(object),(data.replace(".", "")).replace("E9", ""));
			   
				}catch(Exception t){
					// report error
				APPLICATION_LOGS.debug("Error while wrinting into input -"+ object + t.getMessage());
					
				script_error = "Timed out after "+globalwait+" miliseconds";
				
				throw t;
				//return "Fail - "+t.getMessage();
					
				}
				
			
				return "Pass";
				
				
		}
		
	
	
	
	//Implement Wait
	 
	 
	
	public static String waitfor() throws NumberFormatException, InterruptedException, AddressException, MessagingException{
	     APPLICATION_LOGS.debug("Executing wait Keyword");
	  // extract the test data
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			try{
				 
				float test = (Float.parseFloat(data));
				int test1 = (int) test;
				Thread.sleep(test1);
				globalwait = test1/1000;
			}catch(Throwable t){
				APPLICATION_LOGS.debug("Error while waiting -"+ object + t.getMessage());
			    return "Fail - "+t.getMessage();
							}
	     return "Pass";
	}
	
	
    //Clicking on a Button
	
	public static String clickButton() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing clickButton Keyword");
		
		
		try{
			
			selenium.isElementPresent(Objects.getProperty(object));
			selenium.click(Objects.getProperty(object));
		
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while clicking on Button -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	//Selecting text elements from Drop down list
	
	public static String select(){
		APPLICATION_LOGS.debug("Executing select Keyword");
		
		// extract the test data
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
				
		try{
			
			selenium.isElementPresent(Objects.getProperty(object));
			selenium.select(Objects.getProperty(object),"label="+data);
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while Selecting from droplist -"+ object + t.getMessage());
			
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	
	
	
	
	
	public static String Refresh(){
		APPLICATION_LOGS.debug("Executing select Keyword");
		// extract the test data
		
				
		try{
			
			selenium.refresh();
			Thread.sleep(10000);
			
		}catch(Throwable t){
			
				// report error
				APPLICATION_LOGS.debug("Error while refreshing -"+ object + t.getMessage());
			
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	
	
	
	//Executing type keystrokes
	
	public static String typekeys() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing typekeys() Keyword");
		
		// extract the test data
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
				
		try{
		
		
			selenium.typeKeys(Objects.getProperty(object),data);
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while typing data -"+ object + t.getMessage());
		
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	//Executing type keystrokes with current hour
	
	public static String typekeys_hrs() throws AddressException, MessagingException{
		
		APPLICATION_LOGS.debug("Executing typekeys_hrs() Keyword");
		
		// extract the test data
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
				
		try{
				
			selenium.typeKeys(Objects.getProperty(object),sHour);
			
		}catch(Throwable t){
		
			// report error
				APPLICATION_LOGS.debug("Error while typing data -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
				
			}
			
			return "Pass";
	}
	
	
	//Executing type keystrokes with current minute
	
	public static String typekeys_min() throws AddressException, MessagingException{
		
		APPLICATION_LOGS.debug("Executing typekeys_min() Keyword");
		
				
		try{
				
			selenium.typeKeys(Objects.getProperty(object),sMin);
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while typing data -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	//Executing type keystrokes with current second
	
	public static String typekeys_sec() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing typekeys_sec() Keyword");
				
		try{
			
			selenium.typeKeys(Objects.getProperty(object),sSec);
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while typing data -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	//Getting text from an object and executing it based on the object
	
	public static String GetText() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing GetText Keyword");
				
		try{
			
			selenium.getText(Objects.getProperty(object));
			APPLICATION_LOGS.debug("Got the text for:  "+object+"----"+ selenium.getText(Objects.getProperty(object)));
			
			if(object.equals("callid")){
				call_id=selenium.getText(Objects.getProperty(object));
				
				//setting the test data file to null
				DriverApp.testData=null; 
				datareader = new Xlfile_Reader("C:\\Selenium2.0\\app\\test\\Framework\\AutomationBvt_Hybrid\\src\\config\\TestData.xlsx");						
				
				//Adding generated call id in Evaluation and Audit
				
				datareader.setCellData("EvaluateaCall", "generated_call_id", 2, call_id);
				datareader.setCellData("Audit_call", "generated_call_id", 2, call_id);
				datareader.setCellData("Calibrations", "generated_call_id", 2, call_id);
				//Loading the test data file again
				DriverApp.testData  =  new Xlfile_Reader(System.getProperty("user.dir")+"\\src\\config\\TestData.xlsx");				 
				
								 
			   }else if(object.equals("evalscore")){
				String app_score=(selenium.getText(Objects.getProperty(object))).replaceAll("\\(.+\\)", "");
				System.out.println("App score is:"+app_score);
				float score= Float.parseFloat(app_score);
				round = TestUtil.Round(score,2);
				System.out.println("First round: "+round);
				
				datawriter = new Xlfile_Reader("C:\\Selenium2.0\\app\\test\\Framework\\AutomationBvt_Hybrid\\src\\config\\db_data.xlsx");						
				datawriter.setFloatCellData("Evaluation", "QAScore_Application", 2, round);
			 
				
		     		
				
				
				
                
          }

			
			
		}catch(Throwable t){
			
				// report error
				APPLICATION_LOGS.debug("Error while fetching text -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	

	//Verifying text presence 
	
	public static String verifytext() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing verifytext() Keyword");
		
		try{
			
			//selenium.isTextPresent(Objects.getProperty(object));
			 
			
			if(object.equals("callid")){
			selenium.isTextPresent((call_id));
			System.out.println("Verifed Text as  :"+call_id);	
			}
			
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while Verifying text presence -"+ object + t.getMessage());
			
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	

	// clicking on an Object that contains certain text
	
	public static String containsText_click(){
		APPLICATION_LOGS.debug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
			selenium.click("//div[contains(text(),'"+data+"')]");
			
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while searching and clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	

	// Forcefully clicking on an Object
	
	public static String clickat() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		//	selenium.click("//div[contains(text(),'"+data+"')]");
			selenium.clickAt(Objects.getProperty(object), data);
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while searching and clicking -"+ object + t.getMessage());
				
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}

	
	
	
	
	
	public static String doubleclickat() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.doubleClickAt(Objects.getProperty(object), data);
		
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while searching and clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	// Clicking on a object by fireevent keyword
	
	public static String fireevent() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.fireEvent(Objects.getProperty(object),"click");
			
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	// Clicking on a object by checkBox keyword
	
	public static String checkBox() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.check(Objects.getProperty(object));
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while checking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	
	
	// Clicking on a object by fireevent keyword by getting the data from the excel file
	
	public static String checkelementpresence() throws AddressException, MessagingException{
		APPLICATION_LOGS.debug("Executing Dynamic element present Keyword");
		
		try{
					
			String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
			selenium.fireEvent("//table/tbody/tr/td[2]/a[contains(text(),'"+data+"')]", "click");
			
		}catch(Throwable t){
				// report error
				APPLICATION_LOGS.debug("Error while clicking -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	// 	
	

	
*/

	
	
}
