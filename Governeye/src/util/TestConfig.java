package util;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;

import testApps.DriverApp;


public class TestConfig{


	public static String server="smtp.gmail.com";
	public static String from = "akumar543213@gmail.com";
	public static String password = "nikiankit12345";
	public static String[] to ={"ankit.kumar2@kelltontech.com","ankitkumar8976543@gmail.com"};
	public static String subject = "Test Report";
	public static String attachmentName = "Selenium2.0 Database Validation report";
	/*public static String messageBody ="Level 2 monitoring script has been failed on production. Please check the logs report and attached screenshot for analyzing the root cause." +
			" <br><br><b>Link for this validation</b>"+"<br>"+"C:\\tomcat\\webapps\\ROOT\\"+DriverApp.strDate+".html"+"<p> <b>To search for previous validation results</b><br>http://"+TestUtil.Handeler()+":8282"+"/ReportView.jsp </p>"+"<p> <b>Link to the CPV Guide</b><br></p>"+"<br>"+
	"<b>Thanks,</b><br>QA Automation";*/
	
	public static String messageBody ="TestMessage";
	//public static String attachmentPath="C:\\screenshot\\error.jpg";
	//public static String attachmentName="Error.jpeg";
	
	/*public static String testSiteURL = "http://www.moneycontrol.com";
	public static String testBrowser = "*firefox";
	public static String server="172.207.185.112";
	//public static String server="smtp.gmail.com";
	public static String from = "support@abcd.com";
	//public static String from = "aaa@gmail.com";
	//public static String password = "rauu";
	public static String[] to ={"lkjk@gmail.com"};
	//public static String[] to ={"raman@abcd.com","kjhjk@abc.com"};
	public static String subject = "Test Report";
	public static String port="465";
	public static String username="bafd@gmail.com";
	public static String subjectattachment = "Selenium2.0 Database Validation report";
	public static String messageBody ="Level 2 monitoring script has been failed on production. Please check the logs report and attached screenshot for analyzing the root cause." +
			" <br><br><b>Link for this validation</b>"+"<br>"+"http://"+TestUtil.Handeler()+":8282"+"/htmlpages/index"+DriverApp.strDate+".html"+"<p> <b>To search for previous validation results</b><br>http://"+TestUtil.Handeler()+":8282"+"/ReportView.jsp </p>"+"<p> <b>Link to the CPV Guide</b><br></p>"+"<br>"+
	"<b>Thanks,</b><br>QA Automation";*/
	
		
	public static String driver="sun.jdbc.odbc.JdbcOdbcDriver"; 
	public static String dbConnectionUrl="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + "C:\\Database1.accdb"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$qler!!1"; 
	
	public static String mysqldriver="com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "pasrd";
	public static String mysqlurl = "jdbc:mysql://172.16.150.111/monitor_dm";
	//public static String attachmentPath="C:/Selenium3.0/app/test/Framework/AutomationBvt_Hybrid/Reports.zip";
	//file:\\"+System.getProperty("user.dir")+"\\screenshot\\
	public static String attachmentPath=System.getProperty("user.dir")+"\\Reports\\htmlpages0"+(DriverApp.month+1)+"-"+(DriverApp.date)+"-"+(DriverApp.year)+"_"+DriverApp.day+"-"+DriverApp.min+"-"+DriverApp.sec+".html";                                                                             

	//blic static String attachmentPath=System.getProperty("user.dir")+"\\Reports\\htmlpages"+(DriverApp.month+1)+"-" riverApp.date+"_"+(DriverApp.year)+"_"+DriverApp.day+"_"+DriverApp.min+"_" +DriverApp.sec+".html";
	//public static String attachmentPath="C:/Selenium3.0/app/test/Framework/testweb/WebContent/webpages/index"+DriverScript.year+"_"+DriverScript.date+"_"+(DriverScript.month+1)+"_"+DriverScript.day+"_"+DriverScript.min+"_" +DriverScript.sec+".html";
	//public static String attachmentPath="C:/CMAutomation/tomcat-6.0/webapps/ROOT/screenshots/"+DriverScript.mailscreenshotpath+".jpeg";
	//public static String attachmentPath=DriverApp.mailscreenshotpath+".jpeg";
	//public static String attachmentName="Error.jpeg";
	//public static String filepath="C:/Selenium3.0/app/test/Framework/testweb/WebContent/webpages";
	
	
	
	
	
	
	
	
}
