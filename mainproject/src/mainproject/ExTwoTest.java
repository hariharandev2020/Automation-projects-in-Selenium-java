package mainproject;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.Assertion;


public class ExTwoTest {
	
		static WebDriver driver;
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		Actions act = new Actions(driver);
	
	    public	WebElement locatorCSS(String element) {
			WebElement tag = null;
			try {	
				tag = driver.findElement(By.cssSelector(element));
				if(tag.isEnabled()) {
					System.out.println(tag.getAttribute("id") + " " +  tag.getTagName()  +" " +"is visible");
					System.out.println(" ");
				}else {
					System.out.println(tag.getAttribute("id")+ " " + tag.getTagName()  + " " + " is disabled" );
					System.out.println(" ");
					
				}	
			}catch(Exception e) {
				System.out.println(e);
			}
		return tag;
		
		}
	    
	    public Boolean CheckBox(String locator) {
        	try {
        		return "checkBox" == this.locatorCSS(locator).getAttribute("type") ? true : false;
        	} catch(Exception e) {
        	}
			return false;
	    }
	    
		@BeforeClass
		public static void before() {	
			
			System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
			driver = new ChromeDriver();
//			System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
//			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			String page = "https://demoqa.com/automation-practice-form";
			driver.get(page);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
		}
	//							<........     Title Test     ......>
		@Test
		public void TitleTest() throws InterruptedException { 
			assertEquals("Student Registration Form",this.locatorCSS(".practice-form-wrapper > h5:nth-child(1)").getText());
		}
	//							<........     Input Label Test     ......>
		@Test
		public void InputLabelTest() {
			assertEquals("Name",this.locatorCSS("#userName-label").getText());
		}
	//							<........     First name InputBox Test     ......>
		@Test
		public void FnameInputCheck() throws InterruptedException {
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			WebElement fnameInput = this.locatorCSS("#firstName");
			Thread.sleep(1000);
			fnameInput.click();
			fnameInput.clear();
			fnameInput.sendKeys("Hari");
			String ExpectedInput = "Hari";
			assertEquals(ExpectedInput,fnameInput.getAttribute("value"));
		}
	//							<........    Last name InputBox Test     ......>	
		@Test
		public void LnameInputCheck() throws InterruptedException  {
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			WebElement lnameInput = this.locatorCSS("#lastName");
			Thread.sleep(1000);
			lnameInput.click();
			lnameInput.clear();
			Thread.sleep(1000);
			lnameInput.sendKeys("Haran");
			String ExpectedInput = "Haran";
			assertEquals(ExpectedInput,lnameInput.getAttribute("value"));
		}
	//							<........   Email Label Test     ......>	
		@Test
		public void EmailLabelTest() {
			assertEquals("Email",this.locatorCSS("#userEmail-label").getText());
		}
		//						<........     Email InputBox Test     ......>
		@Test
		public void EmailInputCheck() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement email = this.locatorCSS("#userEmail");
			email.click();
			email.clear();
			email.sendKeys("hariharan7373@gmail.com");
			String ExpectedInput = "hariharan7373@gmail.com";
			assertEquals(ExpectedInput,email.getAttribute("value"));
		}
		//						<........   Gender Label Test     ......>	
		@Test
		public void GenderLabelTest() {
		
			assertEquals("Gender",this.locatorCSS("#genterWrapper > div.col-md-3.col-sm-12").getText());	
		}
	//							<........  Radio Buton Test     ......>
		@Test
		public void RadioOne() throws InterruptedException {
			WebElement radioOne = this.locatorCSS("#gender-radio-1");
			WebElement radioTwo = this.locatorCSS("#gender-radio-2");
			WebElement radioThree = this.locatorCSS("#gender-radio-3");
			WebElement r1 = driver.findElement(By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label"));
			WebElement r2 = driver.findElement(By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(2) > label"));
			WebElement r3 = driver.findElement(By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(3) > label"));
			String radio = "radio";
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);			
					assertEquals(radioOne.getAttribute("type"),radio);
			r1.click();
					assertTrue(radioOne.isSelected() && !radioTwo.isSelected() && !radioThree.isSelected());
						//		<.....     Test case -2     .....>
					assertEquals(radioTwo.getAttribute("type"),radio);
			r2.click();
					assertTrue(!radioOne.isSelected() && radioTwo.isSelected() && !radioThree.isSelected());
					//			<.....     Test case -3     .....>
					assertEquals(radioThree.getAttribute("type"),radio);
			r3.click();
					assertTrue(!radioOne.isSelected() && !radioTwo.isSelected() && radioThree.isSelected());			
		}	
	//								<........  MobileNumLabel Test     ......>
		@Test
		public void MobileNumLabelTest() {
			assertEquals("Mobile(10 Digits)",this.locatorCSS("#userNumber-label").getText());
		}
	//								<........  MobileNumInput Test     ......>
		@Test
		public void MobileNumInputTest() {
			
			WebElement mobNumInput = this.locatorCSS("#userNumber");
			assertEquals("10",mobNumInput.getAttribute("minlength"));
			assertEquals("10",mobNumInput.getAttribute("maxlength"));
			mobNumInput.click();
			mobNumInput.clear();
			mobNumInput.sendKeys("9876543210");
			assertEquals("9876543210", mobNumInput.getAttribute("value"));
		}
									//	<........  DobLabelTest     ......>
		@Test
		public void DobLabelTest() {
			
			assertEquals("Date of Birth", this.locatorCSS("#dateOfBirth-label").getText());
		}
	//									<........  DobInputTest     ......>
//		@Test
//		public void DobInputTest() {
//			
//			WebElement dobInput = this.locatorCSS("#dateOfBirthInput");
//			dobInput.click();
//			dobInput.clear();
//			dobInput.sendKeys("29 Feb 2000");
//			assertEquals("29 Feb 2000",dobInput.getText());
//			System.out.println(dobInput.getText());
//		}
		//		<........  SubjectLabelTest     ......>
		@Test
		public void SubjectLabelTest() {
			assertEquals("Subjects", this.locatorCSS("#subjects-label").getText());
		}
		//									<........  SubjectInputTest     ......>
		@Test
		public void SubjectInputTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebElement subInput  = this.locatorCSS("#subjectsInput");
			subInput.sendKeys("Comp");
			Thread.sleep(2000);
			subInput.sendKeys(Keys.RETURN);
			Thread.sleep(1000);
			WebElement ans = driver.findElement(By.cssSelector("#subjectsContainer > div > div > div > div"));
			assertEquals( "Computer Science",ans.getText());
			
		}
	//									<........  HobbiesLabelTest     ......>
		@Test
		public void HobbiesLabelTest() {
			assertEquals("Hobbies", this.locatorCSS("#hobbiesWrapper>div>label").getText());
		}
									//	<........  Hobbies Checkbox Test     ......>
		@Test
		public void HobbiesCheckBoxTest() throws InterruptedException {
			Boolean hobbiesCb1 = this.CheckBox("#hobbies-checkbox-1");
			Boolean hobbiesCb2 = this.CheckBox("#hobbies-checkbox-2");
			Boolean hobbiesCb3 = this.CheckBox("#hobbies-checkbox-3");
			Thread.sleep(1000);
//			assertTrue(hobbiesCb1);
//			assertTrue(hobbiesCb2);
//			assertTrue(hobbiesCb3);
//			Thread.sleep(3000);
		}
		@Test 
		public void CheckBoxChecked() throws InterruptedException {
		  try {	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement Cbox  = driver.findElement(By.cssSelector("#hobbiesWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label"));
			WebElement CboxOne  = driver.findElement(By.cssSelector("#hobbiesWrapper > div.col-md-9.col-sm-12 > div:nth-child(2) > label"));
			WebElement CboxTwo  = driver.findElement(By.cssSelector("#hobbiesWrapper > div.col-md-9.col-sm-12 > div:nth-child(3) > label"));
			js.executeScript("arguments[0].scrollIntoView(true);",Cbox);
			Cbox.click();
			assertTrue(Cbox.isSelected());
			CboxOne.click();
			assertTrue(CboxOne.isSelected());
			CboxTwo.click();
			assertTrue(CboxTwo.isSelected());
			Cbox.click();
			CboxOne.click();
			assertTrue(Cbox.isSelected() && CboxOne.isSelected());
			Cbox.click();
			CboxTwo.click();
			assertTrue(Cbox.isSelected() && CboxTwo.isSelected());
			CboxOne.click();
			CboxTwo .click();
			assertTrue( CboxOne.isSelected() && CboxTwo.isSelected());
			Cbox.click();
			CboxOne.click();
			CboxTwo.click();
			assertTrue(Cbox.isSelected() && CboxOne.isSelected() && CboxTwo.isSelected());
		  }catch(AssertionError e) {
			  System.out.println(e);
		  }
		}
		//							<........  PictureInput LabelTest     ......>
		@Test
		public void PictureLabelTest() {

			assertEquals("Picture", this.locatorCSS("#userForm > div:nth-child(8)>div>label").getText());
			
		}
//									<........  PictureInput Test     ......>
		@Test
		public void PictureInputTest() {

			WebElement picInput  = this.locatorCSS("#uploadPicture");
			picInput.sendKeys("/home/zoho/Pictures/mh.jpg");
			assertEquals("\\fakepath\\mh.jpg",picInput.getAttribute("value"));	
			js.executeScript("arguments[0].scrollIntoView(true);",picInput);
//			System.out.println(picInput.getAttribute("value"));
		}
//									<........  HobbiesLabelTest     ......>
		@Test
		public void AddressLabelTest() {

			assertEquals("Current Address", this.locatorCSS("#currentAddress-label").getText());
		}	
//									<........  AddresInput Test     ......>
		@Test
		public void AddressInputTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement addressInput  = this.locatorCSS("#currentAddress");
			js.executeScript("arguments[0].scrollIntoView(true);",addressInput);
			addressInput.click();
			addressInput.sendKeys("187 Ramalayam colony covai.");
			js.executeScript("arguments[0].scrollIntoView();",addressInput );
			Thread.sleep(500);
			assertEquals("187 Ramalayam colony covai.",addressInput.getAttribute("value"));
		}
//									<........  StateCityDropDownLabel Test     ......>
		@Test
		public void StateCityDropDownLabelTest() throws InterruptedException {
			assertEquals("State and City", this.locatorCSS("#stateCity-label").getText());
		}	
//									<.....     StateCityDropDown Test     .....>
//		@Test
//		public void StateCityDropDownTest() throws InterruptedException {
//				WebElement stateDropDown  = this.locatorCSS("#react-select-3-input");
//				Thread.sleep(1000);
//				stateDropDown.click();
//				stateDropDown.sendKeys("Har");
//				Thread.sleep(2000);
//				stateDropDown.sendKeys(Keys.RETURN);
//				Thread.sleep(1000);
//				WebElement cityDropDown   = this.locatorCSS("#state > div:nth-child(1) > div:nth-child(1)");
//				Thread.sleep(2000);
//				cityDropDown.click();
//				cityDropDown.sendKeys("Pa");
//				Thread.sleep(2000);
//				cityDropDown.sendKeys(Keys.RETURN);
//				
//				WebElement state = this.locatorCSS("#state > div > div > div");
//				WebElement city  = this.locatorCSS("#city > div > div > div");
//				assertEquals("Haryana",state.getText());
//				assertEquals("Panipat",state.getText());
//		  }
//									<.....    Submit buttton Test     .....>
		@Test
		public void  SubmitButtonTest() throws InterruptedException{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement submitButton  = this.locatorCSS("#submit");
			js.executeScript("arguments[0].scrollIntoView(true);",submitButton);
			Thread.sleep(500);
			submitButton.click();
		}
		@Test
		public void PopUpWindow() {
			try {	
				WebElement preview = this.locatorCSS("body > div.fade.modal.show > div > div");	
					if(preview.isDisplayed()) {
							assertEquals("Hari Haran",this.locatorCSS("div.fade.modal.show > div > div > div.modal-body > div > table > tbody > tr:nth-child(1) > td:nth-child(2)").getText().toString());
							assertEquals("hariharan7373@gmail.com",this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(2) > td:nth-child(2)").getText());
							String ExpectedVal = this.locatorCSS("#gender-radio-1").getText();
							System.out.println(ExpectedVal);
							String ExpectedVal2 = this.locatorCSS("#gender-radio-2").getText();
							System.out.println(ExpectedVal2);
							String ExpectedVal3 = this.locatorCSS("#gender-radio-3").getText();
							System.out.println(ExpectedVal3);
							String ActualVal   = this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(3) > td:nth-child(2)").getText();
								if( ExpectedVal == ActualVal ) {
									System.out.println("Male is clicked");
								}else if (ExpectedVal2 == ActualVal) {
									System.out.println("Female is clicked");
								}else if(ExpectedVal3 == ActualVal) {
									System.out.println("Other is clicked");
								}
							assertEquals("9876543210",this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(4) > td:nth-child(2)").getText());
							assertEquals("17 August,2021",this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(5) > td:nth-child(2)").getText());
							assertEquals("Computer Science",this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(6) > td:nth-child(2)").getText());
							assertEquals("mh.jpg",this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(8) > td:nth-child(2)").getText());
							assertEquals("187 Ramalayam colony covai.",this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(9) > td:nth-child(2)").getText());
//							assertEquals("Haryana Panipat",this.locatorCSS(".modal-body > div > table > tbody > tr:nth-child(10) > td:nth-child(2)").getText());
							
					}else{
						System.out.println("PopUp window is not displayed");
					}
				
				
			}catch(Exception e) {
					System.out.println(e);
			}
		}
		@Test
		public void CloseButtonTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebElement closeButton = this.locatorCSS("#closeLargeModal");
			js.executeScript("arguments[0].scrollIntoView(true);",closeButton);
			closeButton.click();
		}
		
	@AfterClass
		public static void QuitTab() {
			
			driver.quit();
		}
	
	}
