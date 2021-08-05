package mainproject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import junit.framework.Assert;	

;

public class SampleTestPage {



	public static void main (String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/home/zoho/Downloads/geckodriver");
		
		
		
		WebDriver driver = new ChromeDriver();
//		WebDriver driverff = new FirefoxDriver();
		
		Actions action = new Actions(driver);
		
		String pageLink = "https://www.testandquiz.com/selenium/testing.html";
		
		driver.get(pageLink);
		driver.manage().window().fullscreen();
		
//		driverff.get(link);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		
		WebElement link = driver.findElement(By.cssSelector(".col-md-12>p>a"));
		WebElement nameInput = driver.findElement(By.name("firstName"));
		WebElement submit    = driver.findElement(By.cssSelector("#IdOfButton"));
		WebElement male      = driver.findElement(By.cssSelector("#male"));
		WebElement female    = driver.findElement(By.cssSelector("#female"));
		WebElement CkBoxOne  = driver.findElement(By.cssSelector(".performance"));
		WebElement CkBoxTwo  = driver.findElement(By.cssSelector(".automation"));
		WebElement DbBtn     = driver.findElement(By.xpath("//*[@id='dblClkBtn']"));
		WebElement btn		 = driver.findElement(By.cssSelector("div.container >div:nth-child(20) > div > p:nth-child(1) > button"));
		WebElement altBtn	 = driver.findElement(By.cssSelector("div.container > div:nth-child(22) > div > p:nth-child(1) > button"));
		WebElement alttxt    = driver.findElement(By.cssSelector("#demo"));
		WebElement dropdown  = driver.findElement(By.cssSelector("#testingDropdown"));
//		Select select  = new Select(dropdown);
		
		
		driver.manage().window().maximize();
		
		//Link test....................................

		try {
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
		
//		Textbox check.................................................................................
		
		try {
			
			driver.navigate().back();
			Thread.sleep(300);
			nameInput = driver.findElement(By.name("firstName"));
			submit    = driver.findElement(By.cssSelector("#IdOfButton"));
			Thread.sleep(500);
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
			
			System.out.println(ActualInputValue);
			Assert.assertEquals(ExpectedInputValue, ActualInputValue);
			System.out.println("Input field is Enabled and the test is passed");
			System.out.println("");
			
		} 
		catch(AssertionError e) {
		
			System.out.println("Input field test is failed");
		
		}
		
		
//		//Button test.............................................
		
		try {
			driver.navigate().refresh();	
			Thread.sleep(200);
			submit    = driver.findElement(By.cssSelector("#IdOfButton"));
			
			if(submit.isEnabled()) {
				
				System.out.println("Submit button is Enabled");
				submit.click();
				String ExpectedColor = "background: green;";
				String ActualColor = submit.getAttribute("style");
				System.out.println(ActualColor);
				Assert.assertEquals(ExpectedColor, ActualColor);
				System.out.println("Submit button is clicked");
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
		
		
//		//Radio button test..............................................
		
		driver.navigate().refresh();
		male      	= driver.findElement(By.cssSelector("#male"));
		female      = driver.findElement(By.cssSelector("#female"));
		Thread.sleep(1000);
		male.click();
		try {
			
			if(male.isEnabled() && female.isEnabled()) {
			
				String MaleType     = male.getAttribute("type");
				String FemaleType   = female.getAttribute("type");
				String ExpectedType = "radio";
			
					if((MaleType.equals(ExpectedType))&&(FemaleType.equals(ExpectedType))) {
		
						Thread.sleep(500);	
						male.click();
						Thread.sleep(500);
				 
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
					
					System.out.println("Male Radio button test passed");
					System.out.println("");
					
//		//...............................
		
		driver.navigate().refresh();
			
		male      	= driver.findElement(By.cssSelector("#male"));
		female      = driver.findElement(By.cssSelector("#female"));
		Thread.sleep(200);
		
		
		if(male.isEnabled() && female.isEnabled()) {
			
			String MaleType     = male.getAttribute("type");
			String FemaleType   = female.getAttribute("type");
			String ExpectedType = "radio";
		
			if((MaleType.equals(ExpectedType))&&(FemaleType.equals(ExpectedType))) {
		
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				female.click();
				Thread.sleep(500);
		 
			 		if((male.isSelected() == false) && (female.isSelected() == true)) {
			
			 			Thread.sleep(500);
			 			System.out.println("Female radio button is selected");
			 			
			 			}	
			 			else {
			
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
			
			System.out.println("Female radio button test passed");
			System.out.println("");
		
		}catch(AssertionError e) {
			
			
			System.out.println("Radio button test failed");
			System.out.println("");
		}
		
		
//		//Checkbox test...............................................
		
		driver.navigate().refresh();
		
		CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
		CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
		
		Thread.sleep(500);
		CkBoxOne.click();
		
		try {
			
				if((CkBoxOne.isEnabled() == true) && (CkBoxOne.isEnabled() == true) || (CkBoxOne.isEnabled() == true) && (CkBoxOne.isEnabled() == false) ) {
			
					if((CkBoxOne.isSelected()) == true && (CkBoxTwo.isSelected() == false)) {
					
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
						System.out.println("Automation  checkbox test is passed ");
						System.out.println("");
						
//				//...............................................................................
						
							Thread.sleep(200);
							driver.navigate().refresh();
							CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
							CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
							Thread.sleep(500);
							
				
					if((CkBoxOne.isEnabled() == true) && (CkBoxOne.isEnabled() == true) ) {		
						
						CkBoxTwo.click();
					
				 			if((CkBoxOne.isSelected()) == false && (CkBoxTwo.isSelected() == true) || (CkBoxOne.isEnabled() == false) && (CkBoxOne.isEnabled() == true) ) {
					
				 					System.out.println("Performance checkbox is checked and Automation checkbox is not checked");
				 					
				 					
								}else {
					
									System.out.println("Performance checkbox is not checked");
									System.out.println("");
									
								}
						}else {
						
							System.out.println("Perfomance checkbox is not checked");
							System.out.println("");
							
						}
						
							Assert.assertTrue(CkBoxTwo.isSelected());
							System.out.println("Performance checkbox test is passed");
							System.out.println("");
					
//				//......................................
					
							Thread.sleep(200);
							driver.navigate().refresh();
							CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
							CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
							Thread.sleep(500);
							
							
							
					if((CkBoxOne.isEnabled() == true) && (CkBoxOne.isEnabled() == true) ) {	
							
							if((CkBoxOne.isSelected()) == false && (CkBoxTwo.isSelected() == false)) {
	
								System.out.println("Both Automation and Performance checkbox is not checked");
								System.out.println("");
							
							}else {
					
								System.out.println("both checkbox is not checked");
								System.out.println("");					
							}
							
					}else { 
									
							System.out.println("Checkbox is Disabled");
							System.out.println("");
					}			
					
							
							Assert.assertFalse(CkBoxTwo.isSelected() && CkBoxTwo.isSelected());
							System.out.println("Both Automation and Performance checkbox is not checked test pass");
							System.out.println("");
							
//		//........................................................................................			

							Thread.sleep(200);
							driver.navigate().refresh();
							CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
							CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
							Thread.sleep(500);
							
							
							
					if((CkBoxOne.isEnabled() == true) && (CkBoxOne.isEnabled() == true) ) {	
						
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
					
							
							Assert.assertFalse(CkBoxTwo.isSelected() && CkBoxTwo.isSelected());
							System.out.println("Automation checkbox and  Performance checkbox test is passed");
							System.out.println("");
							
							
							
							
							}catch(AssertionError e) {
					
								System.out.println("Input type is not a checkbox");
								System.out.println("");
			
							}
		
//		//Dropdown    ..............................................................................		
//
			driver.navigate().refresh();
			dropdown  = driver.findElement(By.cssSelector("#testingDropdown"));
			WebElement dropdown1  = driver.findElement(By.cssSelector("#testingDropdown>#automation"));
			WebElement dropdown2  = driver.findElement(By.cssSelector("#testingDropdown>#performance"));
			WebElement dropdown3  = driver.findElement(By.cssSelector("#testingDropdown>#manual"));
			WebElement dropdown4  = driver.findElement(By.cssSelector("#testingDropdown>#database"));
			
			System.out.println(dropdown1.getText());
			
			Thread.sleep(400);
			if(dropdown.isEnabled()) {
			
				
				if(dropdown.isSelected())	
					
					dropdown.click();
					dropdown1.click();
					Thread.sleep(5000);
					String Expectedd1 = "Automation Testing";
					String Actualdd1 = dropdown1.getText();
					Thread.sleep(1000);
					Assert.assertEquals(Expectedd1, Actualdd1);
					System.out.println("dropdown1 is pass");
			
			}
//		
		
//	//Ddouble click button test..............................................
		
		try{
			
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
				DbBtn     = driver.findElement(By.cssSelector("#dblClkBtn"));
		
			if (DbBtn.isEnabled()) {
				
		 		action.doubleClick(DbBtn).perform();
		 		String ExpectedTxt1 = "hi, JavaTpoint Testing";
		 		String ActualTxt   = driver.switchTo().alert().getText();
		 		driver.switchTo().alert().accept();
		 		
		 		Assert.assertEquals(ExpectedTxt1, ActualTxt);
		 		System.out.println("Double click is pass and the Alert message is verified");
			}else {
				
				System.out.println("Double click Button is disabled");
			
				}
				
			}catch(AssertionError e) {
			
			System.out.println("Double click is failed");
			System.out.println("");
				
			}
		
		//Generate alertBox...............................................................................................
		
		
		driver.navigate().refresh();

		try {
				
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			btn		 = driver.findElement(By.cssSelector("div.container > div:nth-child(20) > div > p:nth-child(1) > button"));
				
				if (btn.isEnabled()) {
					
					btn.click();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					String actualalt   = driver.switchTo().alert().getText();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					String expectedalt = "hi, JavaTpoint Testing";
					Assert.assertEquals(expectedalt, actualalt);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					System.out.println("button is clicked");
					Thread.sleep(500);
					driver.switchTo().alert().accept();
					
				}else {
			
							System.out.println("button is disabled");
					  }
					System.out.println("Alert verified");
			
				}catch(AssertionError e) {
			
							System.out.println("button test is failed");
	
				}
		
//		//....................>>>>>>>>>>>>>>>>>>>.................>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>............
//		
		driver.navigate().refresh();

		try {
				
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			altBtn	 = driver.findElement(By.cssSelector("div.container > div:nth-child(22) > div > p:nth-child(1) > button"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			alttxt    = driver.findElement(By.cssSelector("#demo"));
			
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
						System.out.println("Alert accepted");
					
				}else {
			
							System.out.println("button is disabled");
					  }
						
					
					
					
			//..........................................................................................
					
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
						System.out.println("Alert dissmised");
					
				}else {
			
							System.out.println("button is disabled");
					  }
						
					
				
				}catch(AssertionError e) {
			
							System.out.println("Alert test is failed");
	
				}
		
			
		
		//.....................................................................................................
		driver.close();
	
	}
			
		
		
}
