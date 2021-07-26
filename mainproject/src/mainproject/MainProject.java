package mainproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainProject {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver",  "/home/zoho/Downloads/chromedriver");
		System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
		

       WebDriver driver = new ChromeDriver();
	   WebDriver driverf = new FirefoxDriver();

       String Hari = "https://cliq.zoho.in/";
				 
	   String Hariharan = "https://cliq.zoho.in/";	 
       
	   driver.get(Hari);
       driverf.get(Hariharan);
       
	}

	
} 
