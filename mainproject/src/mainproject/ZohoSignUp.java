package mainproject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class ZohoSignUp{
	
	
		
	public static void main(String[] args) throws InterruptedException {
		
			System.setProperty("webdriver.chrome.driver",  "/home/zoho/Downloads/chromedriver");
			System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
			
			WebDriver driver = new ChromeDriver();
//			WebDriver driverff = new FirefoxDriver();
			String SignUp = "https://www.zoho.com/sign/signup.html";
			
			driver.get(SignUp);

			Thread.sleep(1000);
			
			WebElement email     =  driver.findElement(By.cssSelector("#emailfield"));
			WebElement password  =  driver.findElement(By.cssSelector("#password"));
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement checkBox  = driver.findElement(By.cssSelector("#signup-termservice"));
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement submitbtn = driver.findElement(By.cssSelector("#signupbtn"));

//			Empty check validation
			
//			
		try {
			Thread.sleep(500);
			submitbtn.click();
			
			String ExpectedError = "Please enter a valid email address";
			Thread.sleep(500);
			String ActualError   = driver.findElement(By.cssSelector("#emailfield-error")).getText();	
			
			String ExpectedErrorTwo = "Password cannot be less than 8 characters";
			
			String ActualErrorTwo   = driver.findElement(By.cssSelector("#password-error")).getText();
			
			String ExpectedErrorThree = "Please read and accept the Terms of Service and Privacy Policy";
			
			String ActualErrorThree   = driver.findElement(By.cssSelector("#tos-error")).getText();
			
			Assert.assertEquals(ExpectedError, ActualError);
			
			Assert.assertEquals(ExpectedErrorTwo, ActualErrorTwo);
			
			Assert.assertEquals(ExpectedErrorThree, ActualErrorThree);
			
			System.out.println("Empty check is \"Pass\"");	
			
		} catch(AssertionError e) {
			
			System.out.println("Empty check is \"Fail\"");
			
		}
		    driver.navigate().refresh();
		    
		    
		    //Invalid email validaiton
				
		try {
			email     =  driver.findElement(By.cssSelector("#emailfield"));
				
			email.click();
			Thread.sleep(500);		
			email.clear();
			Thread.sleep(500);
			
			email.sendKeys("hariharan");
			Thread.sleep(500);
			password  =  driver.findElement(By.cssSelector("#password"));
			Thread.sleep(1000);
			password.click();
			password.clear();
			Thread.sleep(500);
			password.sendKeys("12hhhh");
			Thread.sleep(500);
			checkBox  = driver.findElement(By.cssSelector("#signup-termservice"));
			checkBox.click();
			checkBox.click();
			Thread.sleep(500);
			submitbtn = driver.findElement(By.cssSelector("#signupbtn"));
			submitbtn.click();
			
			String ExpectedError = "Please enter a valid email address";
			Thread.sleep(500);
			String ActualError   = driver.findElement(By.cssSelector("#emailfield-error")).getText();	
			Thread.sleep(500);
			String ExpectedErrorTwo = "Password cannot be less than 8 characters";
			String ActualErrorTwo   = driver.findElement(By.cssSelector("#password-error")).getText();
			Assert.assertEquals(ExpectedError, ActualError);
			Assert.assertEquals(ExpectedErrorTwo, ActualErrorTwo);
			
			System.out.println("Invalid Email and password validation is \"Pass\"");
			
		} catch(AssertionError e) {
			
			System.out.println("InValid Email and password validation is \"Fail\"");
			
		}
			driver.navigate().refresh();
			
			//Valid Email and password validation
		
		try {
	
			email     =  driver.findElement(By.cssSelector("#emailfield"));					
			email.click();
			Thread.sleep(500);
			email.clear();
			Thread.sleep(500);			
			email.sendKeys("tirobox772@hyprhost.com");
			Thread.sleep(500);
			password  =  driver.findElement(By.cssSelector("#password"));
			Thread.sleep(1000);
			password.click();
			password.clear();
			Thread.sleep(500);
			password.sendKeys("1234@gghh");
			Thread.sleep(1000);
			checkBox  = driver.findElement(By.cssSelector("#signup-termservice"));
			checkBox.click();
			Thread.sleep(1000);
			submitbtn = driver.findElement(By.cssSelector("#signupbtn"));
			submitbtn.click();
			String ExpectedUrl = "https://sign.zoho.in";
			Thread.sleep(2000);
			String ActualUrl   = driver.getCurrentUrl();
			System.out.println(ActualUrl);
			Assert.assertEquals(ExpectedUrl, ActualUrl);
			
				System.out.println("Valid Email and password validation is \"Pass\"");		
			
		}
		
		catch(AssertionError e) {
			
				System.out.println("Valid Email and password validation is \"Fail\"");
					
			}	
			Thread.sleep(10000);   
//			driver.quit();
				
	}

	
}