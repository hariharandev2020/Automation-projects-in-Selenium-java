package zaskasite;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZaskMethods {

	public static WebDriver driver;
	
	public void defineUrl() {
		System.setProperty("webdriver.chrome.driver",  "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.get("https://zask.localpali.io/zask/");
	}
	public WebElement selector(String element) {
		WebElement ele = null;
		try {
			if(element.contains("//")) {
				ele = this.driver.findElement(By.xpath(element));
			} else {
				ele = this.driver.findElement(By.cssSelector(element));
			}
		}
		catch(AssertionError e){
			System.out.println(e);
		}
		return ele;
	}
	public void Click_evt(String clkelement) {
		
		if((this.selector(clkelement).isDisplayed())&&(this.selector(clkelement).isEnabled())) {
			
  this.selector(clkelement).click();
		}else {
			
			System.out.println("Button is not Clickable");
		}
	}
	public void Clear_evt(String clrelement) {
		
  this.selector(clrelement).clear();
	}
}
