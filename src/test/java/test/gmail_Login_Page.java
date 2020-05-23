package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import freemarker.template.TemplateException;
//import net.mindengine.galen.validation.ErrorArea;
import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.api.GalenPageDump;


public class gmail_Login_Page {
	
	static StringTokenizer st1;
	static String msg;
	private static final String START ="====================================================================";
	
	static String readline;
	@Test 
	public static void new1()throws IOException, TemplateException, InterruptedException{
		
		//Launch URL
		String browserDriverBasePath 				= new File("").getAbsoluteFile().toString() + File.separator + "Resources" + File.separator + "BrowserDriver" + File.separator; 
	    String browserDriverBasePathChrome 	= browserDriverBasePath + "chromedriver.exe";
		File chromeDriverExecutable 					= new File(browserDriverBasePathChrome);
	    chromeDriverExecutable.setExecutable(true);
		System.setProperty("webdriver.chrome.driver",browserDriverBasePathChrome);		
        WebDriver driver = new ChromeDriver();
        driver.get("http://gmail.com");
        Thread.sleep(10000);
       //Get IDs of all WebElements
       List<WebElement> allids =driver.findElements(By.xpath(".//*"));        
       
       //Create Spec File with all IDs on Page. This file is used as Input file for Preparing Dumps and store it in Working Directory
       File file1 = new File("C:\\Users\\himani.puri\\Desktop\\GalenFramework\\TestGalen\\Specs\\login.spec");         		
 		
        if (!file1.exists())
        {
 		file1.createNewFile();
 		}
 		FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
 		BufferedWriter bw1 = new BufferedWriter(fw1);
 		String chtxt1=null;
 		bw1.write("====================================================================");
 		bw1.newLine();
 		String delims1 = "\n"; 
        for (WebElement idelement: allids)
        {
            chtxt1=idelement.getAttribute("id").toString();
            System.out.println(chtxt1);
           // these are my delimiters
            st1= new StringTokenizer(chtxt1,delims1);
         	while (st1.hasMoreElements()) 
         	{
			    msg = (String) st1.nextElement();
				bw1.newLine();
				bw1.write(msg+"   id   "+msg);
			}
			 
        }
       bw1.newLine();
       bw1.write("====================================================================");
    // Above is Introduction / Locators Part
       // Append Below text in Above Spec File
       String delims2 = "\n"; 
       for (WebElement idelement: allids)
       {
          chtxt1=idelement.getAttribute("id").toString();
           System.out.println(chtxt1);
          // these are my delimiters
           st1= new StringTokenizer(chtxt1,delims2);
       		while (st1.hasMoreElements())
       		{
			  msg = (String) st1.nextElement();
				bw1.newLine();
				bw1.write(msg);
			}
		}	
       //Close Spec File Here		     
        bw1.close();

        //Create Dump file based on ABove Spec File "Text.spec"
        GalenPageDump dump = new GalenPageDump("http://gmail.com");
        dump.dumpPage(driver,"C:\\Users\\himani.puri\\Desktop\\GalenFramework\\TestGalen\\Specs\\login.spec","C:\\Users\\himani.puri\\Desktop\\GalenFramework\\TestGalen\\dumps\\login");
       // Galen.dumpPage(driver,"http://gmail.com","C:/Users/himani.puri/Desktop/GalenFramework/TestGalen/Specs/input.spec","C:/Users/himani.puri/Desktop/GalenFramework/TestGalen/dumps/login");
       //Enter UserName and Password and Click on Login Button
        WebElement lognid=driver.findElement(By.id("Email"));
        lognid.sendKeys("archanajagdale");
        WebElement nextbtn=driver.findElement(By.id("next"));
        nextbtn.click();
        Thread.sleep(7000);
        WebElement passwd=driver.findElement(By.id("Passwd"));
        passwd.sendKeys("meenaB@5");
        Thread.sleep(7000);
        WebElement loginbtn=driver.findElement(By.id("signIn"));        
        loginbtn.click();
        Thread.sleep(10000);
        driver.manage().window().maximize();
              
		// Now Copy Spec Suggestions created in html fiel to text/Specs file. Which we will use as Actual Input data sheet for test cases       
        //Open HTML page located in local directory 
        driver.get("file://C:/galencode/galen-sample-java-tests-master/dumps/login/page.html");
        //List out all elements shows in left hand side pane
        List<WebElement>allitems=driver.findElements(By.xpath(".//*[@id='object-list']/ul/li")); 
        int elemntsize=allitems.size();
        System.out.println(elemntsize);
        for (WebElement element: allitems)
        {
           System.out.println(element.getText());
            element.click();
        }
        
      
        WebElement specs=driver.findElement(By.xpath(".//*[@id='object-suggestions']/div"));
        List<WebElement> childs =specs.findElements(By.xpath(".//*"));        
        File file = new File("C:/galencode/galen-sample-java-tests-master/specs/output.spec");
        		// if file doesnt exists, then create it
		if (!file.exists()) 
		{
			file.createNewFile();
		}
		
		//write previous file content
		
		List<String> startContent= readLines("C:/galencode/galen-sample-java-tests-master/specs/input.spec");
	
        for (WebElement childelement: childs)
        {

        	
            System.out.println(childelement.getText());
            String chtxt=childelement.getText();
            String delims = "\n"; // these are my delimiters
            StringTokenizer st= new StringTokenizer(chtxt,delims);
            
            while(st.hasMoreElements())
            {
               	String msg =(String)st.nextElement();
            	if(msg.contains("e+3%"))
            	{
            		String msg1=msg.replace("e+3%", "%");
            		msg1="";
               		startContent.add(msg1);
            	}
            	else if(msg.contains("Infinity%"))
            	{
            		String msg2=msg.replace("Infinity%", "10%");
            		msg2="";
            		startContent.add(msg2);
            	}
            	else if(msg.contains("NaN%"))
            	{
            		String msg3=msg.replace("NaN%", "10%");
            		msg3="";
            		startContent.add(msg3);
            	}
            	
            	else
            	{
            		startContent.add(msg);
            	}
            	if(msg.contains("."))
            	{
            	int msg4=msg.indexOf(":");
            	int msg5=msg.indexOf("%");
            	String msg6=msg.substring(msg4,msg5);
            	msg6=msg6.replaceAll(": ", "");
            	float f= Float.parseFloat(msg6);
            	Math.round(f);
            	Math.round(f);
            	String convertTostring=Float.toString(f);
            	msg.replaceAll(msg6, convertTostring);
            	}
            	
            	}               
            }  
             
        FileUtils.writeLines(file, startContent);
        
         //RESULT\
    	//Launch URL
    	String browserDriverBasePath1 				= new File("").getAbsoluteFile().toString() + File.separator + "Resources" + File.separator + "BrowserDriver" + File.separator; 
	    String browserDriverBasePathChrome1 	= browserDriverBasePath1 + "chromedriver.exe";
		File chromeDriverExecutable1 					= new File(browserDriverBasePathChrome1);
		chromeDriverExecutable1.setExecutable(true);
		System.setProperty("webdriver.chrome.driver",browserDriverBasePathChrome1);		
        WebDriver driver1 = new ChromeDriver();
        driver1.get("http:/gmail.com");
        Thread.sleep(7000);
         LayoutReport layoutReport = Galen.checkLayout(driver1, "C:\\Users\\himani.puri\\Desktop\\GalenFramework\\TestGalen\\Specs\\", Arrays.asList("mobile"));       
         LinkedList<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
        // Creating an object that will contain the information about the test
         GalenTestInfo test = GalenTestInfo.fromString("Login page on mobile device test");
         // Adding layout report to the test report
         test.getReport().layout(layoutReport, "check layout on mobile device");
         tests.add(test);
         // Exporting all test reports to html
         new HtmlReportBuilder().build(tests, "target/galen-html-reports");
         driver.quit();
	}

	public static List<String> readLines(String filename) throws IOException   
    {  
        FileReader fileReader = new FileReader(filename);  
          
        BufferedReader bufferedReader = new BufferedReader(fileReader);  
        List<String> lines = new ArrayList<String>();  
        String line = null;  
          boolean isstartindexfound =false;
        while ((line = bufferedReader.readLine()) != null)   
        {  
        	if(line.equalsIgnoreCase(START)){
        		isstartindexfound =!isstartindexfound;
        	}
        	if(isstartindexfound) {
            lines.add(line);  
           
        	}
        }  
        lines.add(START); 
        lines.add("@ ^ | mobile");
        lines.add("--------------------------------------");
        bufferedReader.close();  
          
        return lines;  
    }     
	
}
        
        
        
      