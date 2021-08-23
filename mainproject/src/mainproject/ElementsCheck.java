package mainproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementsCheck {

	public static WebDriver  driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public	WebElement locatorCSS(String element) {
			WebElement Elem = null;
			try {	
				Elem = driver.findElement(By.cssSelector(element));
				if(Elem.isEnabled()) {
					System.out.println(Elem.getAttribute("id") + " " +  Elem.getTagName()  +" " +"is visible");
					System.out.println(" ");
				}else {
					System.out.println(Elem.getAttribute("id")+ " " + Elem.getTagName()  + " " + " is disabled" );
					System.out.println(" ");			
				}	
			}catch(Exception e) {
				System.out.println(e);
			}
			return Elem;
		 }
	public WebElement locatorXpath(String element) {
			WebElement Elem = null;
			try {	
				Elem = driver.findElement(By.xpath(element));
				if(Elem.isEnabled()) {
					System.out.println(Elem.getAttribute("id") + " " +  Elem.getTagName()  +" " +"is visible");
					System.out.println(" ");
				}else {
					System.out.println(Elem.getAttribute("id")+ " " + Elem.getTagName()  + " " + " is disabled" );
					System.out.println(" ");
				}	
			}catch(Exception e) {
				System.out.println(e);
			}
			return Elem;
		  }
	
	@BeforeClass
	public static void test() {
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();
//			System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
//			driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String page = "https://demoqa.com/elements";
		driver.get(page);
	}
	@Test
	public void OpenLink() throws InterruptedException {
		WebElement link = this.locatorCSS("#item-5");
		js.executeScript("arguments[0].scrollIntoView(true);",link);
		link.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		assertEquals("Links not matched","https://demoqa.com/links",driver.getCurrentUrl());
	}
	@Test
	public void HomeLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement home = this.locatorCSS("#simpleLink");
		home.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		assertEquals("Links not matched", "https://demoqa.com/",driver.getCurrentUrl());
		Thread.sleep(500);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}
	@Test
	public void DynamicLinkTest() throws InterruptedException {
		WebElement dynamic = this.locatorCSS("#dynamicLink");
		Thread.sleep(3000);
		dynamic.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		assertEquals("Links not matched", "https://demoqa.com/",driver.getCurrentUrl());
		Thread.sleep(500);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}
	@Test
	public void CreatedLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement createdLink = this.locatorCSS("#created");
		Thread.sleep(1000);
		createdLink.click();
		Thread.sleep(500);
		js.executeScript("javascript:window.scrollBy(0,350)");
		Thread.sleep(500);
		assertEquals("Link has responded with staus 201 and status text Created",this.locatorCSS("#linkResponse").getText().toString());
	}
	@Test
	public void NoContentLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement noContent = this.locatorCSS("#no-content");
		Thread.sleep(1000);
		noContent.click();
		Thread.sleep(1000);
		assertEquals("Link has responded with staus 204 and status text No Content",this.locatorCSS("#linkResponse").getText().toString());
	}
	@Test
	public void MovedLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement movedLink = this.locatorCSS("#moved");
		Thread.sleep(1000);
		movedLink.click();
		Thread.sleep(500);
		assertEquals("Link has responded with staus 301 and status text Moved Permanently",this.locatorCSS("#linkResponse").getText().toString());
	}
	@Test
	public void BadRequestLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement badRequest = this.locatorCSS("#bad-request");
		Thread.sleep(1000);
		badRequest.click();
		Thread.sleep(500);
		assertEquals("Link has responded with staus 400 and status text Bad Request",this.locatorCSS("#linkResponse").getText().toString());
	}
	@Test
	public void UnauthorisedLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement unAuthorised = this.locatorCSS("#unauthorized");
		Thread.sleep(1000);
		unAuthorised.click();
		Thread.sleep(500);
		assertEquals("Link has responded with staus 401 and status text Unauthorized",this.locatorCSS("#linkResponse").getText().toString());
	}
	@Test
	public void ForbiddenLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement forbiddenLink = this.locatorCSS("#forbidden");
		Thread.sleep(1000);
		forbiddenLink.click();
		Thread.sleep(500);
		assertEquals("Link has responded with staus 403 and status text Forbidden",this.locatorCSS("#linkResponse").getText().toString());
	}
	@Test
	public void NotFoundLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement notFoundLink = this.locatorCSS("#invalid-url");
		Thread.sleep(1000);
		notFoundLink.click();
		Thread.sleep(500);
		assertEquals("Link has responded with staus 404 and status text Not Found",this.locatorCSS("#linkResponse").getText().toString());
	}
	@Test 
	public void ChangeLink() throws InterruptedException{
		WebElement brokenPage = this.locatorCSS("#item-6");
		brokenPage.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		assertEquals("Link not matched","https://demoqa.com/broken",driver.getCurrentUrl().toString());
		Thread.sleep(5000);
 	}
	@Test
	public void ValidImageTextTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement text = this.locatorCSS(".row > div.col-12.mt-4.col-md-6 > div:first-of-type > p:first-of-type");
		assertEquals("Text not matched","Valid image",text.getText().toString());
		Thread.sleep(3000);
	}
	@Test
	public void ValidImageTest() {
		WebElement image = this.locatorCSS(".row > div:nth-child(2) > div:nth-child(1) > img:nth-child(2)");
		System.out.println(image.getAttribute("naturalWidth"));
		String ActualWidth = image.getAttribute("naturalWidth"); 
		assertNotSame(0,ActualWidth);
	}
	@Test
	public void BrokenImageTextTest() throws InterruptedException {
		WebElement text = this.locatorCSS(".row > div.col-12.mt-4.col-md-6 > div:first-of-type > p:nth-child(5)");
		assertEquals("Text not matched","Broken image",text.getText().toString());
		Thread.sleep(3000);
	}
	@Test
	public void InvalidImageTest() {
		WebElement image = this.locatorCSS(".row > div:nth-child(2) > div:nth-child(1) > img:nth-child(6)");
		System.out.println(image.getAttribute("naturalWidth"));
		String ActualWidth = image.getAttribute("naturalWidth"); 
		assertEquals(ActualWidth,"0");
	}
	@Test
	public void ValidLinkTextTest() throws InterruptedException {
		Thread.sleep(1000);
		WebElement text = this.locatorCSS(".row > div.col-12.mt-4.col-md-6 > div:nth-child(1) > p:nth-child(9)");
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollIntoView(true);",text);
		assertEquals("Text not matched","Valid Link",text.getText().toString());
	}
	@Test
	public void ValidLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement validLink = this.locatorCSS(".row > div.col-12.mt-4.col-md-6 > div:nth-child(1) > a:nth-child(10)");
		Thread.sleep(100);
		validLink.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		assertEquals("https://demoqa.com/",driver.getCurrentUrl().toString());
		Thread.sleep(2000);
		driver.navigate().back();
	}
	@Test
	public void InvalidLinkTextTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement text2 = this.locatorCSS(".row > div.col-12.mt-4.col-md-6 > div:nth-child(1) > p:nth-child(13)");
		Thread.sleep(500);
		assertEquals("Text not matched","Broken Link",text2.getText().toString());
	}
	@Test
	public void InvalidLinkTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement validLink = this.locatorCSS("#app > div > div > div.row > div.col-12.mt-4.col-md-6 > div:nth-child(1) > a:nth-child(14)");
		Thread.sleep(500);
		validLink.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("Broken link Expect result Fail","https://demoqa.com/",driver.getCurrentUrl().toString());
		Thread.sleep(1000);
		driver.navigate().back();
	}
	
	
	@AfterClass
	public static void QuitBrowser() {
		driver.quit();
	}

}