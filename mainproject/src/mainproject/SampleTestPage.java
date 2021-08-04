package mainproject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;

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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		
		WebElement link = driver.findElement(By.cssSelector(".col-md-12>p>a"));
		WebElement nameInput = driver.findElement(By.name("firstName"));
		WebElement submit    = driver.findElement(By.cssSelector("#IdOfButton"));
		WebElement male      = driver.findElement(By.cssSelector("#male"));
		WebElement female    = driver.findElement(By.cssSelector("#female"));
		WebElement CkBoxOne  = driver.findElement(By.cssSelector(".performance"));
		WebElement CkBoxTwo  = driver.findElement(By.cssSelector(".automation"));
		WebElement DbButton  = driver.findElement(By.cssSelector("#dblClkBtn")); 
		
		driver.manage().window().maximize();
		
		//Link test....................................

		try {
		Thread.sleep(500);
		
		link.click();
		Thread.sleep(1000);
		
		String ExpectedUrl = "https://www.javatpoint.com/";
		String ActualUrl   = driver.getCurrentUrl();
		Assert.assertEquals(ExpectedUrl, ActualUrl);
		
		System.out.println("Link testcase is pass");
		}
		catch(AssertionError e){
			
		System.out.println("Link testcase is failed");
			
		}
		
		driver.navigate().back();
		Thread.sleep(100);
		
//		Textbox check...............................
		
		try {
			
			nameInput = driver.findElement(By.name("firstName"));
			submit    = driver.findElement(By.cssSelector("#IdOfButton"));
			Thread.sleep(1000);
			if(nameInput.isEnabled()) {
	
				nameInput.click();
				nameInput.clear();
				nameInput.sendKeys("ascnfhhgrtryeuwi1234567890@!?#$%^&*_=+");
				Thread.sleep(1000);

			} else {
				
				System.out.println("Input field is disabled");
			}
		
			String ExpectedInputValue = "ascnfhhgrtryeuwi1234567890@!?#$%^&*_=+";
			String ActualInputValue   = nameInput.getAttribute("value");
			
//			System.out.println(ActualInputValue);
			Assert.assertEquals(ExpectedInputValue, ActualInputValue);
			System.out.println("Input field is Enabled and the test is passed");
		} 
		catch(AssertionError e) {
		
			System.out.println("Input field test is failed");
		
		}
		
		
		//Button test.............................................
		
		try {
			driver.navigate().refresh();	
			Thread.sleep(20000);
			submit    = driver.findElement(By.cssSelector("#IdOfButton"));
			
			if(submit.isEnabled()) {
				
				System.out.println("Submit button is Enabled");
				submit.click();
				String ExpectedColor = "background: green;";
				String ActualColor = submit.getAttribute("style");
				System.out.println(ActualColor);
				Assert.assertEquals(ExpectedColor, ActualColor);
				System.out.println("Submit button is clicked");	
			}else {
		
				
				System.out.println("Submit button is disabled");
			}
			
		}
		catch(Exception e) {
			
//			System.out.println(e);
			System.out.println("No submit button is there");
			
		}
		
		
		//Radio button test..............................................
		
		driver.navigate().refresh();
		male      = driver.findElement(By.cssSelector("#male"));
		female      = driver.findElement(By.cssSelector("#female"));
		Thread.sleep(200);
//		male.click();
		Thread.sleep(300);
		female.click();
	
		if((male.isSelected() == true) && (female.isSelected() == false)) {
		
			Thread.sleep(300);
			System.out.println("Male is selected");
		}
		else if ((female.isSelected() == true) && (male.isSelected()==false)) {
			
			Thread.sleep(300);
			System.out.println("Female is selected");
			
		}else {
			
			System.out.println("Radio button test failed");
			
		}
		
		//Checkbox test...............................................
		
		driver.navigate().refresh();
		
		CkBoxOne   = driver.findElement(By.cssSelector(".automation"));
		CkBoxTwo   = driver.findElement(By.cssSelector(".performance"));
		
		Thread.sleep(200);
//		CkBoxOne.click();
//		CkBoxTwo.click();
		
		if((CkBoxOne.isSelected()) == true && (CkBoxTwo.isSelected() == true)) {
			
			System.out.println("Both Automation and Performance checkbox is checked");	
			
		}else if  ((CkBoxOne.isSelected()) == true && (CkBoxTwo.isSelected() == false)) {
			System.out.println("Automation checkbox is checked");	
		}else if ((CkBoxOne.isSelected()) == false && (CkBoxTwo.isSelected() == true)) {
			
			System.out.println("Performance checkbox is checked");
			
		}else {
			
			System.out.println("No checkbox is checked");
		}
	
		
//		Ddouble click button test..............................................
		
		try{
			
		driver.navigate().refresh();
		DbButton  = driver.findElement(By.cssSelector("#dblClkBtn"));
 		action.doubleClick(DbButton).perform();
 		String ExpectedTxt = "hi, JavaTpoint Testing";
 		String ActualTxt   = driver.switchTo().alert().getText();
 		driver.switchTo().alert().accept();
 		Assert.assertEquals(ExpectedTxt, ActualTxt);
 		System.out.println("Double click is passed");
 		
		}catch(AssertionError e) {
			
		System.out.println("Double click is failed");
			
		}
		
	
	}
			
		
		
}