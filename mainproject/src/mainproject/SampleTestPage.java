package mainproject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import junit.framework.Assert;	

public class SampleTestPage {

	public static void main (String[] args) throws InterruptedException {
			
	try {	
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/home/zoho/Downloads/geckodriver");
	
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver();
	
		driver.manage().window().maximize();
		Actions action = new Actions(driver);
		String pageLink = "https://www.testandquiz.com/selenium/testing.html";
		driver.get(pageLink);
//		driverff.get(link);
//						<<<<<<<<<<...................           Link test          ..............>>>>>>>>>>
		try {
			
				WebElement link = driver.findElement(By.cssSelector("div:nth-child(6) > div > p > a"));
				Thread.sleep(200);
				link.click();
				String ExpectedUrl = "https://www.javatpoint.com/";
				String ActualUrl   = driver.getCurrentUrl();
				Assert.assertEquals(ExpectedUrl, ActualUrl);	
				System.out.println("Link testcase is pass");
				System.out.println("");
		}
		catch(AssertionError e){
			
				System.out.println("Link testcase is failed");
				System.out.println("");	
		}		
//							<<<<<<<<<<<...........            Input Textbox check                 .............>>>>>>>>>>>
		try {
			
				driver.navigate().back();
				Thread.sleep(1000);
				WebElement nameInput = driver.findElement(By.name("firstName"));
				WebElement submit    = driver.findElement(By.cssSelector("#IdOfButton"));
				
					if(nameInput.isEnabled()) {
						nameInput.click();
						nameInput.clear();
						nameInput.sendKeys("ascnfhhgrtryeuwi1234567890@!?#$%^&*_=+");
						Thread.sleep(100);
					} else {
				
						System.out.println("Input field is disabled");	
					}
						String ExpectedInputValue = "ascnfhhgrtryeuwi1234567890@!?#$%^&*_=+";
						String ActualInputValue   = nameInput.getAttribute("value");	
//						System.out.println(ActualInputValue);
						Assert.assertEquals(ExpectedInputValue, ActualInputValue);
						System.out.println("Input field is Enabled and test pass");
						System.out.println("");
			} 
			catch(Exception e) {
		
						System.out.println("Input field test is failed");
			}
//									<<<<<<<<<<...........        Button test         ..........>>>>>>>>>>
		try {
				driver.navigate().refresh();	
				Thread.sleep(500);
				WebElement submit    = driver.findElement(By.cssSelector("#IdOfButton"));
				Thread.sleep(200);
					if(submit.isEnabled()) {
				
						System.out.println("Submit button is Enabled");
						submit.click();
						String ExpectedColor = "background: green none repeat scroll 0% 0%;";
						String ActualColor = submit.getAttribute("style");
//						System.out.println(ActualColor);
						Assert.assertEquals(ExpectedColor, ActualColor);
						System.out.println("Submit button test is pass");
						System.out.println("");
					
					}else {
						System.out.println("Submit button is disabled");
						System.out.println("");
						
						}
				}
		catch(Exception e) {
			
						System.out.println(e);
						System.out.println("No submit button is there");
				}
//								<<<<<<<<<<..........         Radio button test          ..........>>>>>>>>>>
		
		try {
				driver.navigate().refresh();
				Thread.sleep(300);
				WebElement male      = driver.findElement(By.cssSelector("#male"));
				WebElement female    = driver.findElement(By.cssSelector("#female"));
				Thread.sleep(300);
				male.click();
						
					if(male.isEnabled() && female.isEnabled()) {
							String MaleType     = male.getAttribute("type");
							String FemaleType   = female.getAttribute("type");
							String ExpectedType = "radio";
						
						if((MaleType.equals(ExpectedType))&&(FemaleType.equals(ExpectedType))) {	
							male.click();
				 
								if((male.isSelected() == true) && (female.isSelected() == false)) {
						 			Thread.sleep(500);
						 			System.out.println("Male radio button is selected");
						 		}	
					 			else {
			
					 				System.out.println("Input type is not a Radio");
					 			}
						}else {
					
								System.out.println("Input type is not a Radio");
						}
				    }else {
				
								System.out.println("Radio button is diabled");
				
						}
								Assert.assertTrue(male.isSelected());
								
								System.out.println("Male Radio button test pass");
								System.out.println("");
					
//							<<<<<<<<<<...............           TestCase-2          ................>>>>>>>>>>
				driver.navigate().refresh();
				Thread.sleep(300);
				male      	= driver.findElement(By.cssSelector("#male"));
				female      = driver.findElement(By.cssSelector("#female"));
		
					if(male.isEnabled() && female.isEnabled()) {
							String MaleType     = male.getAttribute("type");
							String FemaleType   = female.getAttribute("type");
							String ExpectedType = "radio";
		
						if((MaleType.equals(ExpectedType))&&(FemaleType.equals(ExpectedType))) {
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
							female.click();
		 
								if((male.isSelected() == false) && (female.isSelected() == true)) {
							 			Thread.sleep(500);
							 			System.out.println("Female radio button is selected");
			 			
									}else {
			
										System.out.println("Input type is not a Radio");
										System.out.println("");
									}
							}else {
					
										System.out.println("Input type is not a Radio");
										System.out.println("");
									}
					}else {
				
										System.out.println("Radio button is diabled");
										System.out.println("");
					
							}
			
								Assert.assertTrue(female.isSelected());
								System.out.println("Female radio button test pass");
								System.out.println("");
				}catch(Exception e) {
			
								System.out.println("Radio button test failed");
								System.out.println("");
				}
		
//								<<<<<<<<<<..........           Checkbox test           .................>>>>>>>>>>
		try {
				driver.navigate().refresh();
				
				WebElement CkBoxOne  = driver.findElement(By.cssSelector(".automation"));
				WebElement CkBoxTwo  = driver.findElement(By.cssSelector(".performance"));
				
					Thread.sleep(500);		
			
				if(CkBoxOne.isEnabled()) {	
						CkBoxOne.click();
					
						if((CkBoxOne.isSelected() == true) && (CkBoxTwo.isSelected() == false)) {
								System.out.println("Automation checkbox is checked and Performance checkbox is not checked");
				
						}else {
					
								System.out.println("Automation checkbox is not checked");
								System.out.println("");			
						}
				}else {	
								System.out.println("Automation checkbox checkbox is disabled");
								System.out.println("");
						}
						Assert.assertTrue(CkBoxOne.isSelected());
						System.out.println("Automation  checkbox test is pass");
						System.out.println("");

//									<<<<<<<<<<............          TestCase-2          .............>>>>>>>>>>>						
					Thread.sleep(200);
					driver.navigate().refresh();
					CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
					CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
					Thread.sleep(500);
							
						if(CkBoxTwo.isEnabled()) {		
						    CkBoxTwo.click();
					
				 			if((CkBoxOne.isSelected()) == false && (CkBoxTwo.isSelected() == true)) {
				 				System.out.println("Performance checkbox is checked and Automation checkbox is not checked");
				 						
								}else {
									
									System.out.println("Performance checkbox is not checked");
									System.out.println("");
								}
						}else {
						
								System.out.println("Perfomance checkbox is disabled");
								System.out.println("");
						}
							Assert.assertTrue(CkBoxTwo.isSelected());
							System.out.println("Performance checkbox test is pass");
							System.out.println("");
					
//								<<<<<<<<<<...........          TestCase-3          ............>>>>>>>>>>
					
					Thread.sleep(200);
					driver.navigate().refresh();
					CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
					CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
					Thread.sleep(500);
									
						if((CkBoxOne.isEnabled()) && (CkBoxTwo.isEnabled()) ) {	
							
								if((CkBoxOne.isSelected()) == false && (CkBoxTwo.isSelected() == false)) {
										System.out.println("Both Automation and Performance checkbox is not checked");
										System.out.println("");
							
								}else {
					
										System.out.println("both checkbox is checked");
										System.out.println("");					
								}
						}else { 
									
									System.out.println("Checkbox is Disabled");
									System.out.println("");
						}			
							Assert.assertFalse(CkBoxTwo.isSelected() && CkBoxTwo.isSelected());
							System.out.println("Both Automation and Performance checkbox is not checked test case is pass");
							System.out.println("");
							
//									<<<<<<<<<<............          TeseCase-3          ............>>>>>>>>>>			
						
						Thread.sleep(200);
						driver.navigate().refresh();
						CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
						CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
						Thread.sleep(500);
								
							if((CkBoxOne.isEnabled()) && (CkBoxTwo.isEnabled())) {	
									CkBoxOne.click();
									CkBoxTwo.click();
							
									if((CkBoxOne.isSelected()) == true && (CkBoxTwo.isSelected() == true)) {
											System.out.println("Both Automation and Performance checkbox is checked");
											System.out.println("");
									}else {
					
											System.out.println("both checkbox is not checked");
											System.out.println("");					
									}
								}else { 
									
										System.out.println("Checkbox is Disabled");
										System.out.println("");
								}			
							
					}catch(Exception e) {
					
							System.out.println("Input type is not a checkbox");
							System.out.println("");
					}
//							<<<<<<<<<<............          DropDown          ............>>>>>>>>>>		
			try{
			driver.navigate().refresh();
			Thread.sleep(500);
			WebElement dropdown  = driver.findElement(By.cssSelector("#testingDropdown"));
			WebElement dropdown1  = driver.findElement(By.cssSelector("#testingDropdown>#automation"));
			Thread.sleep(200);
			if(dropdown.isEnabled()) {
				dropdown.click();
				dropdown1.click();
				
					if(dropdown1.isSelected()) {				
						String Expectedd1 = "Automation Testing";
						String Actualdd1 = dropdown1.getText();
						dropdown1.click();
							if(Expectedd1.equals(Actualdd1)) {
									Thread.sleep(200);
									System.out.println("dropdown is Enabled and Automation Test case is pass");
									System.out.println("");	
							}else {
								
								System.out.println("dropdown content is not matched");
							}	System.out.println("");		
						}else {
						System.out.println("dropdown is not selected");
						System.out.println("");
						}
			}else {
				
				System.out.println("dropdown is disabled");
				System.out.println("");
			}
	//					<<<<<<<<<<...........     TestCase -2          ..........>>>>>>>>>>		
			
			driver.navigate().refresh();
			Thread.sleep(500);
			dropdown  = driver.findElement(By.cssSelector("#testingDropdown"));
			Thread.sleep(500);
			WebElement dropdown2  = driver.findElement(By.cssSelector("#testingDropdown>#performance"));
			Thread.sleep(200);
			if(dropdown.isEnabled()) {
				dropdown.click();
				dropdown2.click();
				
					if(dropdown2.isSelected()) {		
						String Expectedd2 = "Performance Testing";
						String Actualdd2 = dropdown2.getText();
						dropdown2.click();	
							if(Expectedd2.equals(Actualdd2)) {
									System.out.println("dropdown is Enabled and Performance Test case is pass");
									System.out.println("");	
							}else {
								
								System.out.println("dropdown content is not matched");
							}	System.out.println("");		
						}else {
						System.out.println("dropdown is not selected");
						System.out.println("");
						}
			}else {
				
				System.out.println("dropdown is disabled");
				System.out.println("");
			}
			
			
			
	//			         <<<<<<<<<<...........     TestCase -3          ..........>>>>>>>>>>		
	
			driver.navigate().refresh();
			Thread.sleep(500);
			dropdown  = driver.findElement(By.cssSelector("#testingDropdown"));
			Thread.sleep(500);
			WebElement dropdown3  = driver.findElement(By.cssSelector("#testingDropdown>#manual"));
			Thread.sleep(200);
			if(dropdown.isEnabled()) {
				dropdown.click();
				dropdown3.click();
				
					if(dropdown3.isSelected()) {		
						String Expectedd3 = "Manual Testing";
						String Actualdd3 = dropdown3.getText();
						dropdown.click();	
							if(Expectedd3.equals(Actualdd3)) {
									System.out.println("dropdown is Enabled and Manual Test case is pass");
									System.out.println("");	
							}else {
								
								System.out.println("dropdown content is not matched");
							}	System.out.println("");		
						}else {
						System.out.println("dropdown is not selected");
						System.out.println("");
						}
			}else {
				
				System.out.println("dropdown is disabled");
				System.out.println("");
			}		
	//					<<<<<<<<<<..........        TestCase-4          ..........>>>>>>>>>>		
			driver.navigate().refresh();
			Thread.sleep(500);
			dropdown  = driver.findElement(By.cssSelector("#testingDropdown"));
			Thread.sleep(500);
			WebElement dropdown4  = driver.findElement(By.cssSelector("#testingDropdown>#database"));
			Thread.sleep(200);
			if(dropdown.isEnabled()) {
				dropdown.click();
				dropdown4.click();
				
					if(dropdown4.isSelected()) {		
						String Expectedd4 = "Database Testing";
						String Actualdd4 = dropdown4.getText();
						dropdown4.click();	
							if(Expectedd4.equals(Actualdd4)) {
									System.out.println("dropdown is Enabled and Database Test case is pass");
									System.out.println("");	
							}else {
								
								System.out.println("dropdown content is not matched");
							}	System.out.println("");		
						}else {
						System.out.println("dropdown is not selected");
						System.out.println("");
						}
			}else {
				
				System.out.println("dropdown is disabled");
				System.out.println("");
			}
			
			
			
			}catch(Exception e) {
				
				System.out.println(e);
				System.out.println("Dropdown failed");
			}
//								<<<<<<<<<<............          DoubleClick          ............>>>>>>>>>>
		try{		
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
				WebElement DbBtn     = driver.findElement(By.cssSelector("#dblClkBtn"));
				Thread.sleep(500);
				
					if (DbBtn.isEnabled()) {
					 		action.doubleClick(DbBtn).perform();
					 		Thread.sleep(500);
					 		String ExpectedTxt1 = "hi, JavaTpoint Testing";
					 		String ActualTxt   = driver.switchTo().alert().getText();
					 		driver.switchTo().alert().accept();
					 		Thread.sleep(500);
					 		Assert.assertEquals(ExpectedTxt1, ActualTxt);
					 		System.out.println("Double click is pass and the Alert message is verified");
					 		System.out.println("");
					}else {
				
							System.out.println("Double click Button is disabled");
							System.out.println("");
					}
				}catch(Exception e) {
			
						System.out.println("Double click is failed");
						System.out.println("");
				}
		
//								<<<<<<<<<<............          Generate AlertBox          ............>>>>>>>>>>
			driver.navigate().refresh();

			try {
					 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					 WebElement  btn  = driver.findElement(By.cssSelector("div.container > div:nth-child(20) > div > p:nth-child(1) > button"));
				
						if (btn.isEnabled()) {	
								btn.click();
								driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
								String actualalt   = driver.switchTo().alert().getText();
								driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
								String expectedalt = "hi, JavaTpoint Testing";
								Assert.assertEquals(expectedalt, actualalt);
								driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
								System.out.println("Alert generate button is clicked");
								Thread.sleep(500);
								driver.switchTo().alert().accept();
						}else {
			
								System.out.println("button is disabled");
								System.out.println("");
						}
								System.out.println("Alert is verified");
								System.out.println("");
			
				}catch(Exception e) {
			
							System.out.println("button test is failed");
							System.out.println("");
				}
		
//							<<<<<<<<<<............          Confirm AlertBox          ............>>>>>>>>>>
	
			driver.navigate().refresh();

				try {
				
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					WebElement altBtn	 = driver.findElement(By.cssSelector("div.container > div:nth-child(22) > div > p:nth-child(1) > button"));
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					WebElement alttxt    = driver.findElement(By.cssSelector("#demo"));
			
				Thread.sleep(200);
			
						if (altBtn.isEnabled()) {
								altBtn.click();
								driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								String actualAlt   = driver.switchTo().alert().getText();
								driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								String expectedAlt = "Press a button!";
								Assert.assertEquals(expectedAlt,actualAlt);
								driver.switchTo().alert().accept();
								System.out.println("Alert message verifed");
								String txtOne = "You pressed OK!";
								String alttxt1 = alttxt.getText();
								Assert.assertEquals(txtOne, alttxt1);
								Thread.sleep(500);
								System.out.println("Confirm Alert message has been accepted");
								System.out.println("");
							
						}else {
			
								System.out.println("button is disabled");
								System.out.println("");
						}
//								<<<<<<<<<<............          TeseCase-2          ............>>>>>>>>>>
					driver.navigate().refresh();	
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					altBtn	 = driver.findElement(By.cssSelector("div.container > div:nth-child(22) > div > p:nth-child(1) > button"));
					alttxt    = driver.findElement(By.cssSelector("#demo"));
					Thread.sleep(200);
					
							if (altBtn.isEnabled()) {
									altBtn.click();
									driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
									String actualAlt   = driver.switchTo().alert().getText();
									driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);									
									String expectedAlt = "Press a button!";
									Assert.assertEquals(expectedAlt,actualAlt);
									System.out.println("Alert message verifed");
									driver.switchTo().alert().dismiss();;
									driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
									String alttxt2 = alttxt.getText();
									String txtTwo = "You pressed Cancel!";
									Assert.assertEquals(txtTwo, alttxt2);
									Thread.sleep(500);
									System.out.println("Cancel Alert message has been dismissed");
									System.out.println("");
								
							}else {
									System.out.println("button is disabled");
									System.out.println("");
								}
						
				}catch(AssertionError e) {
			
							System.out.println("Alert test is failed");
				}			
//							<<<<<<<<<<............          THE-END          ............>>>>>>>>>>
		driver.close();
				
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Tool error");		
		}	
		
	}		
}
