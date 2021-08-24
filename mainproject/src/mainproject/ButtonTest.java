package mainproject;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ButtonTest {

	public static WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions act = new Actions(driver);
	
	public WebElement locatorCSS(String element) {
		WebElement ele = null;
		try {	
			ele = driver.findElement(By.cssSelector(element));
			if(ele.isEnabled()) {
				System.out.println(ele.getTagName() + " is visible");
			}else {
				System.out.println(ele.getTagName() +"Element is not visible");
				return null;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	return ele;
	
	}
	public WebElement locatorXPATH(String element) {
		WebElement ele = null;
		try {	
			ele = driver.findElement(By.xpath(element));
			if(ele.isEnabled()) {
				System.out.println("Element is visible");
			}else {
				System.out.println("Element is not visible");
				return null;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	return ele;
	
	}
	@BeforeClass
	public static void before() {	
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();
//		System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
//		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String page = "https://demoqa.com";
		driver.get(page);	
	}
	
	@Test
	public void OpenLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		js.executeScript("javascript:window.scrollBy(0,250)");
		Thread.sleep(500);
		WebElement linkBtn = this.locatorCSS("#app > div > div > div.home-body > div > div:nth-child(1) > div > div.avatar.mx-auto.white");
		linkBtn.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		assertEquals(driver.getCurrentUrl(),"https://demoqa.com/elements");
	}
	@Test
	public void OpenPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		js.executeScript("javascript:window.scrollBy(0,250)");
		WebElement pageBtn = this.locatorCSS("#item-4");
		Thread.sleep(500);
		pageBtn.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		assertEquals(driver.getCurrentUrl(),"https://demoqa.com/buttons");
	}
	@Test
	public void DoubleClickBuutonTest() throws InterruptedException {
		WebElement dbcBtn = this.locatorCSS("#doubleClickBtn");
		Thread.sleep(500);
		act.doubleClick(dbcBtn).perform();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		assertEquals("You have done a double click",this.locatorCSS("#doubleClickMessage").getText().toString());
	}
	@Test
	public void RightClickBuutonTest() throws InterruptedException {
		WebElement rClick = this.locatorCSS("#rightClickBtn");
		Thread.sleep(500);
		act.contextClick(rClick).build().perform();;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		assertEquals("You have done a right click",this.locatorCSS("#rightClickMessage").getText());	
	}
	@Test
	public void dynamicClickBuutonTest() throws InterruptedException {
		WebElement dynamicClick = this.locatorXPATH("//button[text() = 'Click Me']");
		Thread.sleep(500);
		dynamicClick.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		assertEquals("You have done a dynamic click",this.locatorCSS("#dynamicClickMessage").getText());
		}
	@Test
	public void OpenDynamicProperties() throws InterruptedException {
		js.executeScript("javascript:window.scrollBy(0,350)");	
		WebElement linkButton = this.locatorCSS("#item-8");
		Thread.sleep(500);
		linkButton.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		assertEquals("https://demoqa.com/dynamic-properties",driver.getCurrentUrl());		
	}
//	public void 
	
	@AfterClass
	public static void Quit() {
		driver.quit();
	}
}
