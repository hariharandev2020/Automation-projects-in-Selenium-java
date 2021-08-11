package mainproject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ExTwoTest {

WebDriver driver;
	
    public	WebElement locatorCSS(String element) {
		WebElement tag = null;
		try {	
			tag = driver.findElement(By.cssSelector(element));
			if(tag.isEnabled()) {
				System.out.println("This" + tag.getTagName() + "is visible");
			}else {
				System.out.println("This" + tag.getTagName() + " is disabled" );
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	return tag;
	
	}
    
    public WebElement labelTest(String label) {
    	WebElement lab = null;
    	try {
    		lab = driver.findElement(By.cssSelector(label));
	    		if(lab.getText() == "Name") {	
	    			System.out.println("Name Input label is matched");
	    		}else if(lab.getText() == "Email"){
	    			System.out.println("Email Input label is matched");
	    		}else if(lab.getText() == "Gender"){
	    			System.out.println("Gender Input label is matched");
	    		}else {
	    			System.out.println(lab.getTagName() + "Input label is not matched");
	    		}
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	return lab;
    }
    
	@Before
	public void before() {	
		
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String page = "https://demoqa.com/automation-practice-form";
		driver.get(page);
		
	}
//							<........     Title Test     ......>
	@Test
	public void TitleTest() {
		
		String Expecteds = "Student Registration Form";
		String Actuals   = driver.findElement(By.cssSelector(".practice-form-wrapper > h5")).getText();
		System.out.println(Actuals);
		assertEquals(Expecteds,Actuals);
	}
//							<........     Input Label Test     ......>
	@Test
	public void InputLabelTest() {
		
		WebElement inputLabel = this.locatorCSS("#userName-label");
		
	}
//							<........     First name InputBox Test     ......>
	@Test
	public void FnameInputCheck() {
		
		WebElement fnameInput = this.locatorCSS("#firstName");
		fnameInput.click();
		fnameInput.sendKeys("Hari");
		String ExpectedInput = "Hari";
		assertEquals(ExpectedInput,fnameInput.getAttribute("value"));
	}
//							<........    Last name InputBox Test     ......>	
	@Test
	public void LnameInputCheck() {
		
		WebElement lnameInput = this.locatorCSS("#lastName");
		lnameInput.click();
		lnameInput.sendKeys("Haran");
		String ExpectedInput = "Haran";
		assertEquals(ExpectedInput,lnameInput.getAttribute("value"));
	}
//							<........   Email Label Test     ......>	
	@Test
	public void EmailLabelTest() {
		
		WebElement emailLabel = this.locatorCSS("#userEmail-label");
		
	}
	//						<........     Email InputBox Test     ......>
	@Test
	public void EmailInputCheck() {
	
		WebElement email = this.locatorCSS("#userEmail");
		email.click();
		email.sendKeys("hariharan7373@gmail.com");
		String ExpectedInput = "hariharan7373@gmail.com";
		assertEquals(ExpectedInput,email.getAttribute("value"));
	}
	//						<........   Gender Label Test     ......>	
	@Test
	public void GenderLabelTest() {
	
		WebElement genderLabel = this.locatorCSS("#genterWrapper > div.col-md-3.col-sm-12");
	}
//							<........  Radio Buton Test     ......>
	@Test
	public void RadioOne() {
		
		WebElement radioOne = this.locatorCSS("#gender-radio-1");
		radioOne.click();
		if(radioOne.isSelected()) {
			
				System.out.println("Male Radio Button is enabled ");
		}else {
		
				System.out.println("Male Radio Button is disabled ");
		}
	}
	

	
	@After
	public void QuitTab() {
		
		driver.quit();
	}

	
	
}
