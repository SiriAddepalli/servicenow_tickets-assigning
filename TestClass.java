package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {
	
	public static String url="https://ibmgbsindia1prod.service-now.com/nav_to.do?uri=%2Fincident_list.do%3Fsysparm_first_row%3D1%26sysparm_query%3Du_franchise%3DCAI%5EORu_franchise%3DCommercial%5EORu_franchise%3DA1_Telekom%5EORu_franchise%3DPMI%5EORu_franchise%3DABC%5EORu_clientSTARTSWITHKANTAR%5Estate!%3D6%5Eopened_atBETWEENjavascript:gs.beginningOfLastWeek()@javascript:gs.endOfToday()%5Eassignment_group%3Dfcae63e4dbe5eb00b2dd253948961906%5EORassignment_group%3D3d95d5e7db0fd0501dd1d8c2f396194d%5Estate!%3D8%5Estate!%3D7%26sysparm_view%3D";

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\SiriAB\\Documents\\Automation\\chromedriver_win321\\chromedriver_win32 (4)\\chromedriver.exe");  
		 System.setProperty("webdriver.chrome.silentOutput", "true");
         WebDriver driver=new ChromeDriver();  
         driver.manage().window().maximize(); 
         driver.navigate().to("https://ibmgbsindia1prod.service-now.com/navpage.do");         
         driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
         driver.switchTo().frame("gsft_main");
         driver.findElement(By.id("user_name")).sendKeys("siriab");
         driver.findElement(By.id("user_password")).sendKeys("Siri@1234");
         driver.findElement(By.id("sysverb_login")).click();
         
        
         //Siri URL
        
         driver.navigate().to(url);
         Thread.sleep(5000);
         
         driver.switchTo().defaultContent();
         driver.switchTo().frame("gsft_main");
         
         for (int i = 0; i < 1000000000; i++) 
         {
        	 
        	 
        	 List<WebElement> RowElements=driver.findElements(By.xpath("//tr[@record_class='incident']"));
        	 System.out.println("No of WebElements for "+(i+1)+" time(s) is : "+ RowElements.toString());
             int noOfRows=RowElements.size();   
             System.out.println("No of records available for the "+(i+1)+" time(s) is : "+ noOfRows);
             for (int j = 1; j <=noOfRows; j++) 
             {
            	 driver.switchTo().defaultContent();
                 driver.switchTo().frame("gsft_main");
            	 //(//tbody[@class='list2_body']/tr/td[4])[1] - status xpath
            	 System.out.println("Checking the "+j+"th Record assigned to : "+driver.findElement(By.xpath("(//tr[@record_class='incident']["+j+"]/td)[9]")).getText());
            	 if( driver.findElement(By.xpath("(//tr[@record_class='incident']["+j+"]/td)[4]")).getText().equalsIgnoreCase("(empty)")) 
            	 {
            		try {
            			System.out.println("Clicking on - "+driver.findElement(By.xpath("(//tr[@record_class='incident']["+j+"]/td)[3]")).getText());
                		driver.findElement(By.xpath("(//tr[@record_class='incident']["+j+"]/td)[3]")).click();
                		Thread.sleep(1000);
                		driver.switchTo().defaultContent();
                	    driver.switchTo().frame("gsft_main");
                		driver.findElement(By.id("sys_display.incident.assigned_to")).click();
                		driver.findElement(By.id("sys_display.incident.assigned_to")).clear();
                		driver.findElement(By.id("sys_display.incident.assigned_to")).sendKeys("Siri AB");
                		Thread.sleep(2000);
                		driver.findElement(By.id("sys_display.incident.assigned_to")).sendKeys(Keys.ENTER);
                		Thread.sleep(1000);
                		driver.findElement(By.id("sysverb_update")).click();
                		Thread.sleep(5000);
                		System.out.println("Assigned successfully to Siri AB");
                		j=1;
                		driver.navigate().to(driver.getCurrentUrl());
                		noOfRows=RowElements.size();
                        Thread.sleep(5000);						
					    }catch (Exception e) {
							driver.navigate().to(driver.getCurrentUrl());
	                        Thread.sleep(5000);
	                        continue;
					    }
            		
            	}
            	
				
			 }
             
             System.out.println("Completed looking up the empty assignee records for the "+(i+1)+" time(s)");            
             driver.navigate().to(url);;          
         	 Thread.sleep(5000); 
         	noOfRows=RowElements.size();
             driver.switchTo().defaultContent();
             driver.switchTo().frame("gsft_main");
             
			
		}
         
	}

}
