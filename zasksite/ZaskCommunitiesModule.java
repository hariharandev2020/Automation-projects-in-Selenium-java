package zasksite;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ZaskCommunitiesModule {

	public static WebDriver driver; 
	public static JavascriptExecutor js;
	public static Actions action;

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
	public void sendValue(String element,String value) throws InterruptedException {
		if(this.selector(element).isEnabled()) {
			Thread.sleep(500);
			this.selector(element).click();
			this.selector(element).clear();
			this.selector(element).sendKeys(value);
		}else {
			System.out.println("Button is not Clickable");
		}
	}
	public void makeClick(String element) {
		if(this.selector(element).isDisplayed() && this.selector(element).isEnabled()) {
			this.selector(element).click();
		}else {
			System.out.println("Button is not Displayed or Enabled" + this.selector(element).getTagName());
		}	
	}
	public void LogInButton(String element) {
		this.selector(element).click();
	}
	public void LoginPage(String elem,String elemOne,String elemTwo,String valueOne,String valueTwo) throws InterruptedException {
		this.sendValue(elem, valueOne);
		this.makeClick(elemOne);
		Thread.sleep(1000);
		this.sendValue(elemTwo, valueTwo);
		this.makeClick(elemOne);
	}
	public void TitleCheck(String element,String value) {
		assertEquals(value,this.selector(element).getText());
	}
	public Integer Count(String element) {
		Integer count = driver.findElements(By.cssSelector(element)).size();
		//		System.out.println(count);
		return count; 
	}
	public void IconVisiblityTest(String element) {
		this.selector(element).isDisplayed();
	}

	public void SwitchCommunity(String elemOne,String value ) throws InterruptedException {
		this.TitleCheck(elemOne, value);
		this.makeClick(elemOne);
	}
	public void ChangeDefaultCommunity(String elemOne,String elemTwo) throws InterruptedException {
		action.moveToElement(this.selector(elemOne)).perform();
		this.makeClick(elemTwo);
	}
	public void profilePage(String elemOne,String elemTwo) throws InterruptedException {
		this.makeClick(elemOne);
		Thread.sleep(500);
		this.makeClick(elemTwo);
	}


	@BeforeClass
	public static void before() {
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();
		//		System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
		//		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().window().maximize();
		String page = "https://zask.localpali.io/zask/";
		driver.get(page);
		WebElement advanceBtn = driver.findElement(By.cssSelector("#details-button"));
		advanceBtn.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement proceed  = driver.findElement(By.cssSelector("#proceed-link"));
		js.executeScript("window.scrollBy(0,500)", "");
		proceed.click();
	}
	//								Zask Login
	@Test
	public void ZaskLogin() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.LogInButton("//a[text()='Login']");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.LoginPage("#login_id", "#nextbtn", "#password", "", "");
	}
	//							Zask Community DropDown Visiblity & count Check
	@Test
	public void ZaskComDDVisiblityCheck() {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		assertTrue(this.selector("#community-list").isDisplayed());
	}
	//									Zask Community Title Test						
	@Test
	public void ZaskComTitleTest() {
		Integer expect = 7;
		this.TitleCheck("#community-list > div.community-list-header","My Communities");
		assertEquals(expect,this.Count("#community-list > div.community-list-body > a > .communities-list"));		
	}
	//								Zask Community SwitchOne Test				
	@Test 
	public void ZaskComSwitchOneTest() throws InterruptedException {
		Thread.sleep(1000);
		this.SwitchCommunity("//*[@id=\"community-ADAP\"]/div/span[1]","ADAudit Plus");
		Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().contains("ADAP"));
		this.TitleCheck("//*[@id=\"zask-ui-solution-header-container\"]/div/div/span", "ADAudit Plus");
	}
	//								 Switch Default Community One Test	
	@Test 
	public void SwitchDefaultComOneTest() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		Thread.sleep(1000);
		this.ChangeDefaultCommunity("//*[@id=\"community-ADAP\"]/div", "//*[@id=\"community-ADAP\"]/div/span[2]");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Default community has been updated."));
	}
	//					Check the changes in settings pannel for default community change	
	@Test
	public void ProfilePageTestOne() throws InterruptedException {
		this.profilePage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[2]");
		Thread.sleep(2000);
		assertTrue(driver.getCurrentUrl().contains("ADAP/settings"));
	}
	//							DefaultComTabTestOne
	@Test
	public void DefaultComTabTestOne() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "ADAudit Plus";
		this.makeClick("//label[@for='default-community-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("ADAP/settings?tab=defaultcommunity"));
		Thread.sleep(500);
		assertEquals(expect,this.selector("#outlet-zask-content-container > zask-content-container > div > zask-settings-page > div.setting-container > div.default-communities-settings-container.opened > div > div:nth-child(1) > span").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("input[value='ADAP']").isSelected());
	}
	//								PersonalInfoTabTestTwo
	@Test
	public void PersonalInfoTabTestTwo() throws InterruptedException {
		this.makeClick("//label[@for='personal-info-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("settings?tab=profile"));
	}
	//								EmptyCheck
	@Test
	public void EmptyCheck() throws InterruptedException {
		this.selector("#lyteCalendarView").clear();
		this.selector("#aboutMeDescription").clear();
		this.selector("#github").clear();
		this.selector("#stackoverflow").clear();
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid dete"));
	}
	//									DateInputInvalidTestOne	
	@Test
	public void DateInputInvalidTestOne() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "    /  /  ");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//									DateInputInvalidTestTwo		
	@Test
	public void DateInputInvalidTestTwo() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "///");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//									DateInputInvalidTestThree						
	@Test
	public void DateInputInvalidTestThree() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "@@@@/!!/ee");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestFour
	@Test
	public void DateInputInvalidTestFour() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "2000/@@/ee");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestFive	
	@Test
	public void DateInputInvalidTestFive() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "2000/00/12");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Error occurred, please try again later."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestSix
	@Test
	public void DateInputInvalidTestSix() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "0001/01/12");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestSeven	
	@Test
	public void DateInputInvalidTestSeven() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "abcd/ef/gh");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestEight	
	@Test
	public void DateInputInvalidTestEight() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "2000/02/31");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Error occurred, please try again later."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestNine	
	@Test
	public void DateInputInvalidTestNine() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "2001/02/29");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Error occurred, please try again later."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestTen
	@Test
	public void DateInputInvalidTestTen() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "21/02/2000");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//								DateInputInvalidTestEleven	
	@Test
	public void DateInputInvalidTestEleven() throws InterruptedException {
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "02/22/2000");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid date."));
		Thread.sleep(1500);
	}
	//								DateInputValidTest	
	@Test
	public void DateInputValidTest() throws InterruptedException {
		String date = "February 2000";
		this.sendValue("//*[@id=\"lyteCalendarView\"]", "2000/02/29");
		Thread.sleep(1000);
		assertEquals(date,this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[1]/div/span[3]").getText());
	}
	//								DateInputTextTest	
	@Test
	public void DateInputTextTest() throws InterruptedException {
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Profile info has been updated."));
		Thread.sleep(1000);
		assertTrue(this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[2]/div[6]/div[2]").getAttribute("class").contains("lyteCalSel"));
	}
	//								DescriptionInputTest	
	@Test
	public void DescriptionInputTest() throws InterruptedException {
		this.sendValue("#aboutMeDescription", "Zoho Ask is a portal designed for users to build and exchange knowledge with one another. It's a community driven portal, and follows certain guidelines to stay spam free. Zoho Ask helps ensure transparency between its users. The first community created is exclusively for Zoho. You can find articles on existing and emerging software trends, tools, Zoho teams, and products. Communities developed in the future will focus on different areas of interest.");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Profile info has been updated"));
		Thread.sleep(1500);
	}
	//								GithubUrlInputTestOne	
	@Test
	public void GithubUrlInputInvalidTestOne() throws InterruptedException {
		this.sendValue("#github", "https://git-scm.com");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Please enter valid github link"));
		Thread.sleep(1500);
	}
	//								GithubUrlInputTestTwo	
	@Test
	public void GithubUrlInputInvalidTestTwo() throws InterruptedException {
		this.sendValue("#github", "htts://gitorg.openqa.selenium.remote.ProtocolHandshake createSessionorg.openqa.selenium.remote.ProtocolHandshake createSessionorg.openqa.selenium.remote.ProtocolHandshake createSessionorg.openqa.selenium.remote.ProtocolHandshake createSessionorg.openqa.selenium.remote.ProtocolHandshake createSessionorg.openqa.selenium.remote.ProtocolHandshake createSession.com");
		Thread.sleep(800);
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Please enter valid github link."));
		Thread.sleep(1500);
	}
	//								GithubUrlInputTestThree	
	@Test
	public void GithubUrlInputInvalidTestThree() throws InterruptedException {
		this.sendValue("#github", "htts://git12344.com");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Please enter valid github link"));
		Thread.sleep(1500);
	}
	//								GithubUrlInputTestFour	
	@Test
	public void GithubUrlInputInvalidTestFour() throws InterruptedException {
		this.sendValue("#github", "www://git-scm.com");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Please enter valid github link"));
		Thread.sleep(1500);
	}
	//							GithubUrlInputTestFive	
	@Test
	public void GithubUrlInputInvalidTestFive() throws InterruptedException {
		this.sendValue("#github", "aabc://git-scm.com");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Please enter valid github link"));
		Thread.sleep(1500);
	}
	//							GithubUrlInputTestFive	
	@Test
	public void GithubUrlInputValidTestFive() throws InterruptedException {
		this.sendValue("#github", "https://github.com/sank2000");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Profile info has been updated"));
		Thread.sleep(1500);
	}
	//								StackoverflowrlInputTestOne	
	@Test
	public void StackoverflowUrlInputInvalidTestOne() throws InterruptedException {
		this.sendValue("#stackoverflow", "https://stack.com");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Please enter valid stackoverflow link."));
		Thread.sleep(1500);
	}
	//								StackoverflowInputTestTwo	

	@Test
	public void StackoverflowUrlInputInvalidTestTwo() throws InterruptedException {
		this.sendValue("#stackoverflow", "htts://stackoverflow-scm.com");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(600);
		assertTrue(driver.getPageSource().contains("Please enter valid stackoverflow link."));
		Thread.sleep(1500);
	}
	//								StackoverflowrlInputTestThree	
	@Test
	public void StackoverflowUrlInputInvalidTestThree() throws InterruptedException {
		this.sendValue("#stackoverflow", "https://stackoverflow.com/questions/645008/what-are-the-basic-clearcase-concepts-every-developer-should-know-though-you-have-readonly-developer-should-know-though-you-have-readonly-clearcase-concepts-every-developer-should-know-though-you-have-readonly#645771");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Please enter valid stackoverflow link."));
		Thread.sleep(1500);
	}
	//								StackoverflowUrlInputTestFour	
	@Test
	public void StackoverflowUrlInputInvalidTestFour() throws InterruptedException {
		this.sendValue("#stackoverflow", "www://stackoverflow-scm.com");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(700);
		assertTrue(driver.getPageSource().contains("Please enter valid stackoverflow link."));
		Thread.sleep(1500);
	}
	//							StackoverflowUrlInputTestFive	
	@Test
	public void StackoverflowUrlInputInvalidTestFive() throws InterruptedException {
		this.sendValue("#stackoverflow", "https://user/645008know#645771");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Please enter valid stackoverflow link."));
		Thread.sleep(1500);
	}
	//						StackoverflowUrlInputTestFive	
	@Test
	public void StackoverflowUrlInputValidTestFive() throws InterruptedException {
		this.sendValue("#stackoverflow", "https://stackoverflow.com/users/6309/vonc");
		this.makeClick("#save-personal-information-draft");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Profile info has been updated"));
		Thread.sleep(1500);
	}
	//									Profile Click
	@Test
	public void ProfileClick() throws InterruptedException {
		this.makeClick("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div");
		Thread.sleep(500);
		this.makeClick("#outlet > zask-container > zask-ui-header > div:nth-child(1) > div > zask-ui-user-panel > zask-ui-user-profile > div.zask-user-profile > div:nth-child(2) > div:nth-child(3) > a:nth-child(1)");
		assertTrue(driver.getCurrentUrl().contains("https://zask.localpali.io/zask/ADAP/users/57888501"));
	}
	//								Basic Informationn Label check	
	@Test
	public void BasicInformationLabelTest() throws InterruptedException {
		Thread.sleep(1000);
		this.TitleCheck("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[1]/span[1]", "Basic Information");
	}
	//									Basic Informationn DOB Test	
	@Test
	public void BasicInformationDOBTest() throws InterruptedException {
		String expect = "2000-02-29";
		Thread.sleep(1000);
		this.TitleCheck("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[1]/span[1]", "DoB");
		Thread.sleep(1000);
		assertEquals(expect, this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[1]/span[2]").getText());
	}
	//									Basic Informationn Bio Test	
	@Test
	public void BasicInformationDescriptionTest() throws InterruptedException {
		String expect = "Zoho Ask is a portal designed for users to build and exchange knowledge with one another. It's a community driven portal, and follows certain guidelines to stay spam free. Zoho Ask helps ensure transparency between its users. The first community created is exclusively for Zoho. You can find articles on existing and emerging software trends, tools, Zoho teams, and products. Communities developed in the future will focus on different areas of interest.";
		Thread.sleep(1000);
		this.TitleCheck("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[2]/span[1]", "Bio");
		Thread.sleep(1000);
		assertEquals(expect, this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[2]/span[2]").getText());
	}

	//									Basic Informationn Social Links Test	
	@Test
	public void BasicInformationLinksTest() throws InterruptedException {
		String link = "https://github.com/sank2000";
		String link_2 = "https://stackoverflow.com/users/6309/vonc";
		Thread.sleep(1000);
		this.TitleCheck("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[2]/span[1]", "Bio");
		this.TitleCheck("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[3]/div/span[1]", "Github:");
		this.TitleCheck("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[3]/div/span[2]", "StackOverflow:");
		Thread.sleep(1000);
		assertEquals(link, this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[3]/div/a[1]").getText());
		assertEquals(link_2, this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[1]/div[5]/div[2]/div[3]/div/a[2]").getText());
	}
	//										ZaskComSwitchTwoTest
	@Test 
	public void ZaskComSwitchTwoTest() throws InterruptedException {
		this.makeClick("#community-list-select");
		Thread.sleep(1000);
		this.SwitchCommunity("//*[@id=\"community-ADSSP\"]/div/span[1]", "ADSSP");
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().contains("ADSSP"));
		this.TitleCheck("//*[@id=\"zask-ui-solution-header-container\"]/div/div/span", "ADSSP");
	}
	//	 						Switch Default Community One Test	
	@Test 
	public void SwitchDefaultComTwoTest() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		Thread.sleep(600);
		this.ChangeDefaultCommunity("//*[@id='community-ADSSP']", "//*[@id=\"community-ADSSP\"]/div/span[2]");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Default community has been updated."));
	}
	//								Profile Page Test Two	
	@Test
	public void ProfilePageTestTwo() throws InterruptedException {
		this.profilePage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[2]");
		Thread.sleep(1800);
		assertTrue(driver.getCurrentUrl().contains("ADSSP/settings"));
	}
	//									Default Com Tab Test Two	
	@Test
	public void DefaultComTabTestTwo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "ADSSP";
		this.makeClick("//label[@for='default-community-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("ADSSP/settings?tab=defaultcommunity"));
		Thread.sleep(500);
		assertEquals(expect,this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-settings-page/div[4]/div[2]/div/div[2]/span").getText());
		Thread.sleep(500);
		assertTrue(this.selector("input[value='ADSSP']").isSelected());
	}
	//									Zask Com Switch Three	
	@Test 
	public void ZaskComSwitchThreeTest() throws InterruptedException {
		this.makeClick("#community-list-select");
		Thread.sleep(1000);
		this.SwitchCommunity("//*[@id=\"community-EMSDevAsk\"]/div/span[1]", "EMS Dev Ask");
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().contains("EMSDevAsk"));
		this.TitleCheck("//*[@id=\"zask-ui-solution-header-container\"]/div/div/span", "EMS Dev Ask");
	}
	//	 						Switch Default Community  Three	
	@Test 
	public void SwitchDefaultComThreeTest() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		Thread.sleep(600);
		this.ChangeDefaultCommunity("//*[@id='community-EMSDevAsk']", "//*[@id=\"community-EMSDevAsk\"]/div/span[2]");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Default community has been updated."));
	}
	//								Profile Page Test Three		
	@Test
	public void ProfilePageTestThree() throws InterruptedException {
		this.profilePage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[2]");
		Thread.sleep(1800);
		assertTrue(driver.getCurrentUrl().contains("EMSDevAsk/settings"));
	}
	//								Default Com Tab Test Three	
	@Test
	public void DefaultComTabTestThree() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "EMS Dev Ask";
		this.makeClick("//label[@for='default-community-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("EMSDevAsk/settings?tab=defaultcommunity"));
		Thread.sleep(500);
		assertEquals(expect,this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-settings-page/div[4]/div[2]/div/div[3]/span").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("input[value='EMSDevAsk']").isSelected());
	}
	//							Zask Com Switch Four	
	@Test 
	public void ZaskComSwitchFourTest() throws InterruptedException {
		this.makeClick("#community-list-select");
		Thread.sleep(1000);
		this.SwitchCommunity("//*[@id=\"community-LogsAsk\"]/div/span[1]", "Logs Ask");
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().contains("LogsAsk"));
		this.TitleCheck("//*[@id=\"zask-ui-solution-header-container\"]/div/div/span", "Logs Ask");
	}
	//	 						Switch Default Community  Four	
	@Test 
	public void SwitchDefaultComFourTest() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		Thread.sleep(600);
		this.ChangeDefaultCommunity("//*[@id='community-LogsAsk']", "//*[@id=\"community-LogsAsk\"]/div/span[2]");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Default community has been updated."));
	}
	//							Profile Page Test Four		
	@Test
	public void ProfilePageTestFour() throws InterruptedException {
		this.profilePage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[2]");
		Thread.sleep(1800);
		assertTrue(driver.getCurrentUrl().contains("LogsAsk/settings"));
	}
	//							Default Com Tab Test Four	
	@Test
	public void DefaultComTabTestFour() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "Logs Ask";
		this.makeClick("//label[@for='default-community-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("LogsAsk/settings?tab=defaultcommunity"));
		Thread.sleep(500);
		assertEquals(expect,this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-settings-page/div[4]/div[2]/div/div[4]/span").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("input[value='LogsAsk']").isSelected());
	}

	//							Zask Com Switch Five	
	@Test 
	public void ZaskComSwitchFiveTest() throws InterruptedException {
		this.makeClick("#community-list-select");
		Thread.sleep(1000);
		this.SwitchCommunity("//*[@id=\"community-Pali\"]/div/span[1]", "Pali");
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().contains("Pali"));
		this.TitleCheck("//*[@id=\"zask-ui-solution-header-container\"]/div/div/span", "Pali");
	}
	//	 						Switch Default Community  Five	
	@Test 
	public void SwitchDefaultComFiveTest() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		Thread.sleep(600);
		this.ChangeDefaultCommunity("//*[@id='community-Pali']", "//*[@id=\"community-Pali\"]/div/span[2]");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Default community has been updated."));
	}
	//							Profile Page Test Five		
	@Test
	public void ProfilePageTestFive() throws InterruptedException {
		this.profilePage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[2]");
		Thread.sleep(1800);
		assertTrue(driver.getCurrentUrl().contains("Pali/settings"));
	}
	//							Default Com Tab Test Five	
	@Test
	public void DefaultComTabTestFive() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "Pali";
		this.makeClick("//label[@for='default-community-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("Pali/settings?tab=defaultcommunity"));
		Thread.sleep(500);
		assertEquals(expect,this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-settings-page/div[4]/div[2]/div/div[5]/span").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("input[value='Pali']").isSelected());
	}
	//							Zask Com Switch Six	
	@Test 
	public void ZaskComSwitchSixTest() throws InterruptedException {
		this.makeClick("#community-list-select");
		Thread.sleep(1000);
		this.SwitchCommunity("//*[@id=\"community-WorkFromHome\"]/div/span[1]", "Work From Home");
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().contains("WorkFromHome"));
		this.TitleCheck("//*[@id=\"zask-ui-solution-header-container\"]/div/div/span", "Work From Home");
	}
	//	 						Switch Default Community  Six	
	@Test 
	public void SwitchDefaultComSixTest() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		Thread.sleep(600);
		this.ChangeDefaultCommunity("//*[@id='community-WorkFromHome']", "//*[@id=\"community-WorkFromHome\"]/div/span[2]");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Default community has been updated."));
	}
	//							Profile Page Test Six		
	@Test
	public void ProfilePageTestSix() throws InterruptedException {
		this.profilePage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[2]");
		Thread.sleep(1800);
		assertTrue(driver.getCurrentUrl().contains("WorkFromHome/settings"));
	}
	//							Default Com Tab Test Six	
	@Test
	public void DefaultComTabTestSix() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "Work From Home";
		this.makeClick("//label[@for='default-community-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("WorkFromHome/settings?tab=defaultcommunity"));
		Thread.sleep(500);
		assertEquals(expect,this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-settings-page/div[4]/div[2]/div/div[6]/span").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("input[value='WorkFromHome']").isSelected());
	}
	//							Zask Com Switch Seven
	@Test 
	public void ZaskComSwitchSevenTest() throws InterruptedException {
		this.makeClick("#community-list-select");
		Thread.sleep(1000);
		this.SwitchCommunity("//*[@id=\"community-ZAsk\"]/div/span[1]", "ZAsk");
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().contains("ZAsk"));
		this.TitleCheck("//*[@id=\"zask-ui-solution-header-container\"]/div/div/span", "ZAsk");
	}
	//	 						Switch Default Community  Seven	
	@Test 
	public void SwitchDefaultComSevenTest() throws InterruptedException {	
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.makeClick("#community-list-select");
		Thread.sleep(600);
		this.ChangeDefaultCommunity("//*[@id='community-ZAsk']", "//*[@id=\"community-ZAsk\"]/div/span[2]");
		Thread.sleep(800);
		assertTrue(driver.getPageSource().contains("Default community has been updated."));
	}
	//							Profile Page Test Seven		
	@Test
	public void ProfilePageTestSeven() throws InterruptedException {
		this.profilePage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[2]");
		Thread.sleep(1800);
		assertTrue(driver.getCurrentUrl().contains("ZAsk/settings"));
	}
	//							Default Com Tab Test Seven	
	@Test
	public void DefaultComTabTestSeven() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "ZAsk";
		this.makeClick("//label[@for='default-community-setting']");
		Thread.sleep(500);
		assertTrue(driver.getCurrentUrl().contains("ZAsk/settings?tab=defaultcommunity"));
		Thread.sleep(500);
		assertEquals(expect,this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-settings-page/div[4]/div[2]/div/div[7]/span").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("input[value='ZAsk']").isSelected());
	}

	@AfterClass
	public static void Quit() throws InterruptedException {
		WebElement profileBtn = driver.findElement(By.cssSelector("#outlet > zask-container > zask-ui-header > div:nth-child(1) > div > zask-ui-user-panel > div.user-profile-container > div > div.user-profile > div"));
		profileBtn.click();
		Thread.sleep(1000);
		WebElement LogoutBtn   = driver.findElement(By.xpath("//button[text()='Sign Out']"));
		LogoutBtn.click();
		driver.close();	
	}

}
