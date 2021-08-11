package mainproject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class ExOne {

	WebDriver driver;
	

	public WebElement locatorCSS(String element) {
		WebElement ele = null;
		try {	
			ele = driver.findElement(By.cssSelector(element));
			if(ele.isEnabled()) {
				System.out.println("Element is visible");
			}else {
				System.out.println("Element is not visible");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	return ele;
	
	}
	@Before
	public void before() {	
		
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();
// 		System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
// 		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String page = "https://demoqa.com/login";
		driver.get(page);
		
	}
	@Test
	//Header
	public void HeaderCheck() {
		
		String Expecteds = "Welcome,";
		String Actuals   = driver.findElement(By.cssSelector("#userForm>div:nth-child(1)>h2")).getText();
		System.out.println(Actuals);
		assertEquals(Expecteds,Actuals);
	}
	
	@Test
	//	paragraph
	public void Paragraph() {
		
		String Expected = "Login in Book Store";
		WebElement test = this.locatorCSS("#userForm>div:nth-child(1)>h5");
		assertEquals(Expected,test.getText());
	}
	
	@Test
	//Input label check
	public void InputLabel() {
		
		WebElement inputLabel = this.locatorCSS("#userName-label");
		String Expected3  = "UserName :";
		assertEquals(Expected3,inputLabel.getText());
	}
	
	@Test
	public void InputCheck() {
		
		WebElement input = this.locatorCSS("#userName");
		input.click();
		input.sendKeys("HariHaran");
		String ExpectedInput = "HariHaran";
		assertEquals(ExpectedInput,input.getAttribute("value"));
	}
	
	@Test
	public void PasswordInput(){
		WebElement password = this.locatorCSS("#password");
		password.click();
		String ExpectedPassword = "1234@abc";
		password.sendKeys("1234@abc");
		assertEquals(ExpectedPassword,password.getAttribute("value"));
		
	}

	@Test
	public void LoginCheck() {
		
		WebElement loginBtn = this.locatorCSS("#userName");
		loginBtn.click();
	}
	@Test
	public void NewUserCheck() {
		
		WebElement newUserBtn = this.locatorCSS("#userName");
		newUserBtn.click();
	}
	
	@After
	public void after(){
		
		driver.close();
	}
}
