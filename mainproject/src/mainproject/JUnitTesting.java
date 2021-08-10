package mainproject;

import static org.junit.Assert.assertEquals;

import java.sql.Driver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;


public class JUnitTesting {
	
	WebDriver driver;
	JUnitTesting mainIns = new JUnitTesting();
	
	public WebElement visible(WebElement element) {
		
	try {	
		if(element.isEnabled()) {
			
			return element;
			
		}else {
		
			System.out.println("Element is not visible");
			return element;
		}
		
	}catch(Exception e) {
		
		System.out.println(e);
		return element;
	}
	
	}
	@Before
	public void before() {	
		
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String page = "https://demoqa.com/login";
		driver.get(page);
		
	}
	
	
	@Test
	//Header
	public void headerCheck() {
		
		String Expecteds = "Welcome,";
		String Actuals   = driver.findElement(By.cssSelector("#userForm>div:nth-child(1)>h2")).getText();
		System.out.println(Actuals);
		assertEquals(Expecteds,Actuals);
	}
	
	@Test
//	paragraph
	public void paragraph() {
		
		String Expected = "Login in Book Store";
		String Actual   = driver.findElement(By.cssSelector("#userForm>div:nth-child(1)>h5")).getText();
		assertEquals(Expected,Actual);
	}
	
	@Test
	//Input label check
	public void inputLabel() {
		
		String Expected3  = "UserName :";
		String Actual3    = driver.findElement(By.cssSelector("#userName-label")).getText();
		assertEquals(Expected3,Actual3);
	}
	
	public void inputCheck() {
		
		WebElement input = driver.findElement(By.cssSelector("#userName"));
		mainIns.visible(input);
	}
	
	@After
	public void after(){
		
		driver.close();
	}
}
