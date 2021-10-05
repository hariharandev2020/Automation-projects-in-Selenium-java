package zaskasite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ZaskQuestionPageFilter {

	public static WebDriver driver; 
	public static JavascriptExecutor js;

	public void WebdriverAndUrl() {
//		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
//		driver = new ChromeDriver();
		 driver = new FirefoxDriver();
		
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
		if(this.selector(element).isEnabled()) {
			this.selector(element).click();
		}else {
			System.out.println("Button is not Clickable");
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
	public void OpenFilters(String elemOne, String elemTwo) throws InterruptedException {
		this.makeClick(elemOne);
		Thread.sleep(2000);
		this.makeClick(elemTwo);
	}
	public Integer Count(String element) {
		Integer count = driver.findElements(By.cssSelector(element)).size();
		//		System.out.println(count);
		return count; 
	}
	public void ClearFilter(String elementOne,String elementTwo) throws InterruptedException {
		this.makeClick(elementOne);
		this.makeClick(elementTwo);		
	}
	public void getBtnDotMark(String elem) throws InterruptedException {
		assertTrue(this.selector(elem).isDisplayed());
	}
	public void TextTest(String elem,String val) {
		assertEquals(val,this.selector(elem).getText());
	}
	public void getFilterDotMark(String btn,String element,String btnTwo) throws InterruptedException {
		this.selector(btn).click();
		Thread.sleep(1000);
		assertTrue(this.selector(element).isDisplayed());
		Thread.sleep(500);
		this.selector(btnTwo).click();
	}
	public void GoBack(String btn,String elemOne,String elemTwo) throws InterruptedException {
		this.makeClick(btn);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		this.makeClick(elemOne);
		Thread.sleep(1000);
		this.makeClick(elemTwo);
		
	}
	public void InputClick(String element,String elementOne,String elementTwo,String elementThree,String elementFour,String elementFive,String elementSix) throws InterruptedException {
		if(this.selector(elementOne).isEnabled() ) {
			this.makeClick(element);
			if(this.selector(elementOne).isSelected() && this.selector(elementTwo).isSelected()==false && this.selector(elementThree).isSelected()== false && this.selector(elementFour).isSelected()== false && this.selector(elementFive).isSelected()== false && this.selector(elementSix).isSelected() == false) {
				Thread.sleep(2000);
				assertTrue(this.selector("//button[text()='Apply']").isEnabled());
				this.makeClick("//button[text()='Apply']");
			}else {
				System.out.println("Element is not selected");
			}
		}
		else {
			System.out.println("Element is not in visible");
		}
	}


	@BeforeClass
	public static void before() {
		System.setProperty("webdriver.chrome.driver", "/home/zoho/Downloads/chromedriver");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		//		System.setProperty("webdriver.gecko.driver",  "/home/zoho/Downloads/geckodriver");
		//		WebDriver driver = new FirefoxDriver();
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
		this.LoginPage("#login_id", "#nextbtn", "#password", "esakkivash.n+testt25@zohocorp.com", "Tester@07");
	}
//								Question Page Url Test
	
	@Test
	public void QuestionPageUrlTest() throws InterruptedException {
		Thread.sleep(3000);
		String expectUrl = "https://zask.localpali.io/zask/questions?sort=New&filter=All";
		assertEquals(expectUrl,driver.getCurrentUrl());
	}
//								Question Button Text Test
	
	@Test
	public void QuestionButtonTextTest() throws InterruptedException {
		String btnText = "Questions";
		Thread.sleep(1500);
		assertEquals(btnText,this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[2]/zask-questions/div[1]/div[1]").getText());
		assertTrue(this.selector("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/div/ul/li[1]/link-to/a").isEnabled());
	}
//									Question Button color Test
	
	@Test
	public void QuestionButtoncolorTest() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String color = "Questions";
		assertEquals(color,this.selector("a.active").getText());	
	}
//									Filter Button Icon Test
	
	@Test
	public void FilterButtonIconTest() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		assertTrue(this.selector("//*[@id=\"question-filter\"]/span[1]").isDisplayed());
	}
//									Filter Button Text Test
	
	@Test
	public void FilterButtonTextTest() {
		String btnText = "Filters";
		assertEquals(btnText,this.selector("//*[@id=\"question-filter\"]/span[2]").getText());
	}
//									Filter Button Hover Test
	
	@Test
	public void FilterButtonHoverTest() throws InterruptedException {
		String btnHoverColor = "rgba(254, 254, 254, 1)";
		Actions actions = new Actions(driver);
		actions.moveToElement(this.selector("#question-filter")).perform();
		assertEquals(btnHoverColor,this.selector("#question-filter").getCssValue("background-color"));
		Thread.sleep(500);
	}
//									Filter Button Click Test
	
	@Test
	public void FilterButtonClickTest() throws InterruptedException {
		this.makeClick("#question-filter");
		Thread.sleep(500);
		assertTrue(this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-questions-filters/div[2]").isDisplayed());
		this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-questions-filters/div[2]");
		Integer expect = 10;
		Integer count =  this.Count("#filter > div > lyte-accordion");
		assertEquals(expect,count);
	}
//											Filter Text
	
	@Test
	public void FilterText() throws InterruptedException {
		String text =  "Filters";
		Thread.sleep(2000);
		WebElement label = this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-questions-filters/div[2]/div[1]/h2");
		assertEquals(text,label.getText());
	}
//										Clear All Test	
	@Ignore
	@Test
	public void ClearAllTest() throws InterruptedException {
		String text = "Clear All";
		WebElement Txt = this.selector("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-questions-filters/div[2]/div[2]/div");
		Thread.sleep(1000);
		assertEquals(text,Txt.getText());
	}
//										Filters One Text Test
	
	@Test
	public void FilterCategoryTextTest() throws InterruptedException {
		String expectText = "CATEGORY";
		Thread.sleep(1000);
		assertEquals(expectText,this.selector("//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[1]").getText());
	}
//										Filters Button One IconOne Test
	
	@Test
	public void FiltersButtonOneIconOneTest() {
		assertTrue(this.selector("//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/div").isDisplayed());

	}
//										Filters Button One IconTwo Test
	
	@Test
	public void FiltersButtonOneIconTwoTest() {
		assertTrue(this.selector("//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon").isDisplayed());
	}
//										Filter Cancel Button Test
	
	@Test
	public void FilterCancelButtonTest() throws InterruptedException {
		this.makeClick("//*[text()='Cancel']");
		String expect = "question-filter-freeze toggle";
		assertEquals(expect,this.selector("#question-filter-freeze").getAttribute("class"));
		Thread.sleep(1000);
		assertFalse(this.selector("#outlet-zask-content-container > zask-content-container > div.zask-content-filter > zask-questions-filters > div.questions-filter-tab > div.questions-filter-header > div > button:nth-child(1)").isDisplayed());
	}
//										Filter One's Filters Count
	
	@Test
	public void FilterOneCountTest() throws InterruptedException {
		Integer val = 6; 
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Integer count = this.Count("#filter > div > lyte-accordion:nth-child(1) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > div");
		assertEquals(val,count);
		Thread.sleep(2000);
		this.makeClick("//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		assertTrue(this.selector("//input[@value='All']").isSelected());
		Thread.sleep(500);
		this.makeClick("//*[text()='Cancel']");	
	}
//									Filter Category My Question
	
	@Test
	public void FilterCategoryMyQuestionsTest() throws InterruptedException {
		String expect = "esakkivash.n+testt25";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.InputClick("//lyte-radiobutton[@lt-prop-value='MyQuestion']","//input[@value='MyQuestion']" ,"//input[@value='All']", "//input[@value='Following']","//input[@value='Favourite']", "//input[@value='Featured']","//input[@value='Unanswered']");		 
		Thread.sleep(1500);
		assertTrue(driver.getCurrentUrl().contains("New&filter=MyQuestion"));
		assertTrue(this.selector("#portal").isSelected());
		Thread.sleep(500);
		assertEquals(expect,this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div/div[2]/div/div/div/span[1]").getText());
	}
//												Filter Category Tags
	
	@Test
	public void FilterCategoryTags() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String expect = "pali";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(1000);
		this.InputClick("//lyte-radiobutton[@lt-prop-value='Following']","//input[@value='Following']" ,"//input[@value='MyQuestion']", "//input[@value='All']","//input[@value='Favourite']", "//input[@value='Featured']","//input[@value='Unanswered']");
		Thread.sleep(2000);
		assertTrue(driver.getCurrentUrl().contains("New&filter=Following"));
		assertEquals(expect,this.selector("//span[@tagname='pali']").getText());
		this.getBtnDotMark("//*[@id=\"question-filter\"]/span[3]");
		Thread.sleep(1000);
		this.getFilterDotMark("#question-filter", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[2]", "//*[text()='Cancel']");
	}
//											Filter Category Favourite
	
	@Test
	public void FilterCategoryFavourite() throws InterruptedException {
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.InputClick("//lyte-radiobutton[@lt-prop-value='Favourite']","//input[@value='Favourite']" ,"//input[@value='MyQuestion']", "//input[@value='All']","//input[@value='Following']", "//input[@value='Featured']","//input[@value='Unanswered']");
		Thread.sleep(1000);
		this.makeClick("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view/zask-ui-card/div/div[2]/link-to/a");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("favouriteClicked"));
		driver.navigate().back();
		Thread.sleep(3000);
		this.getBtnDotMark("//*[@id=\"question-filter\"]/span[3]");
		Thread.sleep(1000);
		this.getFilterDotMark("#question-filter", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[2]", "//*[text()='Cancel']");
	}
//												FilterCatagoryFeatured
	
	@Test
	public void FilterCatagoryFeatured() throws InterruptedException {
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String expect = "Feature";
		this.OpenFilters("#question-filter", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(1000);
		this.InputClick("//lyte-radiobutton[@lt-prop-value='Featured']","//input[@value='Featured']" ,"//input[@value='MyQuestion']", "//input[@value='All']","//input[@value='Following']", "//input[@value='Favourite']","//input[@value='Unanswered']");
		Thread.sleep(3000);
		assertTrue(driver.getCurrentUrl().contains("New&filter=Featured"));
		assertEquals(expect,this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div[2]/div/span[2]").getText());
		Thread.sleep(2000);
		this.getBtnDotMark("//*[@id=\"question-filter\"]/span[3]");
		Thread.sleep(1000);
		this.getFilterDotMark("#question-filter", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[2]", "//*[text()='Cancel']");
	}
//											FilterCatagoryUnanswered		
	
	@Test
	public void FilterCatagoryUnanswered() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String expect = "0";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(1500);
		this.InputClick("//lyte-radiobutton[@lt-prop-value='Unanswered']","//input[@value='Unanswered']" ,"//input[@value='MyQuestion']", "//input[@value='All']","//input[@value='Following']", "//input[@value='Favourite']","//input[@value='Featured']");
		Thread.sleep(2000);
		assertTrue(driver.getCurrentUrl().contains("New&filter=Unanswered"));
		assertEquals(expect,this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div/div[1]/div[1]/div[2]").getText());
		this.getBtnDotMark("//*[@id=\"question-filter\"]/span[3]");
		Thread.sleep(1000);
		this.getFilterDotMark("#question-filter", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[2]", "//*[text()='Cancel']");
	}
//										Filter Category All
	
	@Test
	public void FilterCategoryAll() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(1500);
		this.selector("//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/div").click();
		assertTrue(this.selector("//input[@value='All']").isSelected());
		Thread.sleep(3000);
		assertFalse(this.selector("//*[@id=\"filter\"]/div/lyte-accordion[1]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[2]").isDisplayed());
		Thread.sleep(1000);
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
		assertTrue(driver.getCurrentUrl().contains("New&filter=All"));
		this.selector("//button[text()='Apply']").click();
	}
//										Category Filter Clear Test	
	@Test
	public void CategoryInputClearTest() throws InterruptedException {
		Thread.sleep(1000);
		this.makeClick("//*[@id=\"question-filter\"]");
		Thread.sleep(1500);
		this.makeClick("#filter > div > lyte-accordion:nth-child(1) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div");
		Thread.sleep(1000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		this.selector("#question-filter");
		Thread.sleep(1000);
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(1) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.score.applied-filter-dot").isDisplayed());
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
	}	
//											Tags Filter Text
	
	@Test
	public void TagFilterTextTest() throws InterruptedException {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String text =  "TAGS";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		assertTrue(this.selector("#filter > div > lyte-accordion:nth-child(3) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div").isDisplayed());
		assertTrue(this.selector("#filter > div > lyte-accordion:nth-child(3) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > lyte-icon").isDisplayed());
		Thread.sleep(1000);
		WebElement label = this.selector("#filter > div > lyte-accordion:nth-child(3) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span:nth-child(1)");
		assertEquals(text,label.getText());
	}
//										  Tags Filter dd_1 Test
	
	@Test
	public void TagsFilterDD_1Test() throws InterruptedException { 
		String expect = "All";
		Integer count = 80;
		Thread.sleep(1000);
		this.makeClick("#question-tag-category-filter");
		Thread.sleep(2000);	
		WebElement dd = this.selector("body > lyte-drop-box > lyte-drop-body > lyte-drop-item:nth-child(1)"); 
		dd.click();
		Thread.sleep(3000);
		assertEquals(expect,this.selector("//*[@id=\"question-tag-category-filter\"]").getAttribute("lt-prop-selected"));
		Thread.sleep(1000);
		assertEquals(count,this.Count("#tag-filter-checkbox"));		
		Thread.sleep(3000);
	}
//										 Tags Filter dd_2 Test
	
	@Test
	public void TagsFilterDD_2Test() throws InterruptedException { 
		String expect = "Team";
		Integer count = 32;
		Thread.sleep(1000);
		this.makeClick("#question-tag-category-filter");
		Thread.sleep(2000);	
		WebElement dd = this.selector("body > lyte-drop-box > lyte-drop-body > lyte-drop-item:nth-child(2)"); 
		dd.click();
		Thread.sleep(3000);
		assertEquals(expect,this.selector("//*[@id=\"question-tag-category-filter\"]").getAttribute("lt-prop-selected"));
		Thread.sleep(1000);
		assertEquals(count,this.Count("#tag-filter-checkbox"));		
		Thread.sleep(3000);		
	}
	//								 Tags Filter dd_3 Test
	
	@Test
	public void TagsFilterDD_3Test() throws InterruptedException { 
		String expect = "Technology";
		Integer count = 80;
		Thread.sleep(1000);
		this.makeClick("#question-tag-category-filter");
		Thread.sleep(2500);	
		WebElement dd = this.selector("body > lyte-drop-box > lyte-drop-body > lyte-drop-item:nth-child(3)"); 
		dd.click();
		Thread.sleep(3000);
		assertEquals(expect,this.selector("//*[@id=\"question-tag-category-filter\"]").getAttribute("lt-prop-selected"));
		Thread.sleep(1000);
		assertEquals(count,this.Count("#tag-filter-checkbox"));		
		Thread.sleep(3000);		
	}
//										Tag Search Invalid Test One
	
	@Test
	public void TagSearchInvalidTestOne() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","        ");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//										Tag Search Invalid Test Two
	
	@Test
	public void TagSearchInvalidTestTwo() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","@@@@@@@@@@");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Tag Search Invalid Test Three
	
	@Test
	public void TagSearchInvalidTestThree() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","aaaaaaa");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Tag Search Invalid Test Four
	
	@Test
	public void TagSearchInvalidTestFour() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","a         b");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Tag Search Invalid Test Five		
	
	@Test
	public void TagSearchInvalidTestFive() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","...............");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
// 									Tag Search Invalid Test Six	
	
	@Test
	public void TagSearchInvalidTestSix() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","@!#$%^&*(?~`");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Tag Search Invalid Test Seven	
	
	@Test
	public void TagSearchInvalidTestSeven() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","{esakki}");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Tag Search Invalid Test Eight
	
	@Test
	public void TagSearchInvalidTestEight() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-tags-filter > div:nth-child(1) > input","[123]");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Tag Filter Search Box Valid Test	
	
	@Test
	public void TagFilterSearchBoxTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[2]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.selector("#search-tags-filter").click();
		Thread.sleep(1000);
		this.selector("#search-tags-filter > div:nth-child(1) > input").sendKeys("te");
		Thread.sleep(500);
		this.selector("#search-tags-filter > div:nth-child(1) > input").sendKeys("st");
		Thread.sleep(1000);
		this.makeClick("//*[@id=\"tag-filter-checkbox\"]/label");
		Thread.sleep(1000);
		assertTrue(this.selector("input[value=\"testing\"]").isSelected());
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(2000);
	}
// 								Tag Filter Tags Test	
	
	@Test
	public void TagFilterTagsTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String expect = "testing";
		Thread.sleep(2000);
		assertEquals(expect,this.selector("#zask-questions-pagination > zask-ui-question-summaries > div > zask-ui-question-summary-view:nth-child(1) > zask-ui-card > div > div.question-detail > div > div > zask-ui-tags > zask-ui-tag:nth-child(2)").getText());
	}
//									Tag Filter Clear Test
	
	@Test
	public void TagFilterClearTest() throws InterruptedException {
			this.makeClick("#question-filter");
		Thread.sleep(500);
		this.makeClick("#filter > div > lyte-accordion:nth-child(3) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div");
		Thread.sleep(2500);
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(3) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.tags.applied-filter-dot").isDisplayed());
	}
//									Category Filter Clear Test	
	@Test
	public void TagInputClearTest() throws InterruptedException {
		Thread.sleep(1000);
		this.makeClick("//*[@id=\"question-filter\"]");
		Thread.sleep(1500);
		this.makeClick("#filter > div > lyte-accordion:nth-child(3) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div");
		Thread.sleep(1000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		this.selector("#question-filter");
		Thread.sleep(1000);
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(3) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.score.applied-filter-dot").isDisplayed());
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
	}
//											Author Tag
	
	@Test
	public void AuthorTag() throws InterruptedException {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String text =  "AUTHORS";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		assertTrue(this.selector("#filter > div > lyte-accordion:nth-child(5) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div").isDisplayed());
		assertTrue(this.selector("#filter > div > lyte-accordion:nth-child(5) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > lyte-icon").isDisplayed());
		Thread.sleep(1000);
		WebElement label = this.selector("#filter > div > lyte-accordion:nth-child(5) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span:nth-child(1)");
		assertEquals(text,label.getText());
	}
// 									Authors Search Invalid TestOne
	@Test
	public void AuthorsSearchInvalidTestOne() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","        ");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Authors Search Invalid Test Two				
	@Test
	public void AuthorsSearchInvalidTestTwo() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","@@@@@@@@@@");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Authors Search Invalid Test Two	
	@Test
	public void AuthorsSearchInvalidTestThree() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","aaaaaaa");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//								Authors Search Invalid Test Four
	
	@Test
	public void AuthorsSearchInvalidTestFour() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","a         b");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//								Authors Search Invalid Test Five	
	
	@Test
	public void AuthorsSearchInvalidTestFive() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","...............");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Authors Search Invalid Test Six	
	
	@Test
	public void AuthorsSearchInvalidTestSix() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","@!#$%^&*(?~`");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Authors Search Invalid Test Seven
	
	@Test
	public void AuthorsSearchInvalidTestSeven() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","{esakki}");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//									Authors Search Invalid Test Eight
	
	@Test
	public void AuthorsSearchInvalidTestEight() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#search-authors-filter > div:nth-child(1) > input","[123]");
		this.makeClick("//button[text()='Apply']");
		assertTrue(driver.getPageSource().contains("Please enter valid Tag's filter"));
	}
//								Authors Filter Search  Box Test
	
	@Test
	public void AuthorsFilterSearchBoxTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(1000);
		this.selector("#search-authors-filter").click();
		Thread.sleep(1000);
		this.selector("#search-authors-filter > div:nth-child(1) > input").sendKeys("esakkivash");
		Thread.sleep(500);
		this.selector("#search-authors-filter > div:nth-child(1) > input").sendKeys(".n");
		Thread.sleep(1000);
		this.makeClick("#author-filter-checkbox > label");
		Thread.sleep(1000);
		assertTrue(this.selector("input[value='esakkivash.n']").isSelected());
		this.makeClick("//button[text()='Apply']");
	}
//									Authors Filter Tags Test		
	
	@Test
	public void AuthorsFilterTagsTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String text = "esakkivash.n";
		Thread.sleep(2000);
		assertEquals(text,this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries[1]/div/zask-ui-question-summary-view[1]/zask-ui-card/div/div[2]/div/div/div/span[1]").getText());
	}
//								Authors Filter Indication And Clear Test
	
	@Test
	public void AuthorsFilterIndicationAndClearTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.getBtnDotMark("#question-filter > span.applied-filter-dot");
		this.getFilterDotMark("#question-filter", "//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[2]", "//*[text()='Cancel']");
		this.makeClick("#question-filter");
		this.makeClick("//*[@id=\"filter\"]/div/lyte-accordion[3]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/div");
		this.makeClick("//button[text()='Apply']");
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(5) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.authors.applied-filter-dot").isDisplayed());
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
	}
	//	Category Filter Clear Test	
	@Test
	public void AuthorTagInputClearTest() throws InterruptedException {
		Thread.sleep(1000);
		this.makeClick("//*[@id=\"question-filter\"]");
		Thread.sleep(1500);
		this.makeClick("#filter > div > lyte-accordion:nth-child(5) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div");
		Thread.sleep(1000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		this.selector("#question-filter");
		Thread.sleep(1000);
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(5) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.score.applied-filter-dot").isDisplayed());
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
	}
//										Created Time Tag Label
	
	@Test
	public void CreatedTimeTagLabel() throws InterruptedException {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String text =  "CREATED TIME";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		assertTrue(this.selector("#filter > div > lyte-accordion:nth-child(7) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div").isDisplayed());
		assertTrue(this.selector("#filter > div > lyte-accordion:nth-child(7) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > lyte-icon").isDisplayed());
		Thread.sleep(1000);
		WebElement label = this.selector("#filter > div > lyte-accordion:nth-child(7) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span:nth-child(1)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		assertEquals(text,label.getText());
	}
//										Created Time DD_1Test
	
	@Test
	public void CreatedTimeDD_1Test() throws InterruptedException {
		String expect = "Before";
		Thread.sleep(1500);
		assertEquals(expect,this.selector("#question-created-time-filter > div.lyteDummyEventContainer > lyte-drop-button > span").getText());
	}
//									Created Time Search Box Invalid Test	
	
	@Test
	public void CreatedTimeSearchBoxInvalidTest() throws InterruptedException {
		this.sendValue("#lyteCalendarView","       ");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
		Thread.sleep(2000);
	}
//								Created Time Search Box Invalid Test Two
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestTwo() throws InterruptedException {
		this.sendValue("#lyteCalendarView","aa/bb/abcd");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
		Thread.sleep(2000);
	}
//								Created Time Search Box Invalid Test Nine
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestNine() throws InterruptedException {
//		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#lyteCalendarView","00000000");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//									Created Time Search Box Invalid Test Three
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestThree() throws InterruptedException {
		this.sendValue("#lyteCalendarView","@@/@@/@@@@");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
		Thread.sleep(2000);
	}
//									Created Time Search Box Invalid Test Four
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestFour() throws InterruptedException {
		this.sendValue("#lyteCalendarView","12/345678");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//									Created Time Search Box Invalid Test Ten
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestTen() throws InterruptedException {
//		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#lyteCalendarView",".5.6.20.5");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//									Created Time Search Box Invalid Test Five
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestFive() throws InterruptedException {
		this.sendValue("#lyteCalendarView","12/34/    ");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//								Created Time Search Box Invalid Test Six
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestSix() throws InterruptedException {
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(3000);
		this.sendValue("#lyteCalendarView","//");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//								CreatedTime Search Box Invalid Test Seven
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestSeven() throws InterruptedException {
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#lyteCalendarView","  /  /    ");
		this.makeClick("//button[text()='Apply']");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//								Created Time Search Box Invalid Test Eight
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestEight() throws InterruptedException {
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#lyteCalendarView","00/00/0000");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
		Thread.sleep(2000);
	}
//								Created Time Search Box Invalid Test Eleven
	
	@Test
	public void CreatedTimeSearchBoxInvalidTestEleven() throws InterruptedException {
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#lyteCalendarView",".5/.6/20.5");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//								Created Time Search Box Valid Test One
	
	@Test
	public void CreatedTimeSearchBoxValidTestOne() throws InterruptedException {
		driver.navigate().back();
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#lyteCalendarView","09/21/2021");
		String expect = "September 2021";
		Thread.sleep(1000);
		assertEquals(expect,this.selector("#lyteCalendar > lyte-calendar > div > div > div:nth-child(1) > div > span.lyteCalsCalMon").getText());
		String date = this.selector("#lyteCalendar > lyte-calendar > div > div > div.lyteCalTableContainer > div:nth-child(8) > div.lyteCalCdate.lyteCalSel.lyteCalTableCell").getAttribute("class");
		Thread.sleep(1000);
		assertTrue(date.contains("lyteCalSel"));
		Thread.sleep(1000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(3000);
	}
//	 								Created Time Search Box Valid Test Twelve
	
	@Test
	public void CreatedTimeSearchBoxValidTestTwelve() throws InterruptedException {
		this.sendValue("#lyteCalendarView","13/21/2021");
		String expect = "September 2021";
		Thread.sleep(1000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(3000);
		assertTrue(driver.getPageSource().contains("Please enter valid date filter(s)"));
	}
//									Created Time Search Box Valid Test13
	
	@Test
	public void CreatedTimeSearchBoxValidTest13() throws InterruptedException {
		this.sendValue("#lyteCalendarView","13/32/2021");
		String expect = "September 2021";
		Thread.sleep(1000);
		assertEquals(expect,this.selector("#lyteCalendar > lyte-calendar > div > div > div:nth-child(1) > div > span.lyteCalsCalMon").getText());
		String date = this.selector("#lyteCalendar > lyte-calendar > div > div > div.lyteCalTableContainer > div:nth-child(8) > div.lyteCalCdate.lyteCalSel.lyteCalTableCell").getAttribute("class");
		Thread.sleep(1000);
		assertTrue(date.contains("lyteCalSel"));
		Thread.sleep(1000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(3000);
	}
//											Date Valid Test 
	
	@Test
	public void DateValidtest() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String result = this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div/div[2]/div/div/div/span[2]").getText();
		assertTrue(result.contains("Sep 9 `21"));
	}
//											Created TimeDD_2Test
	
	@Test
	public void CreatedTimeDD_2Test() throws InterruptedException {
		String expect = "After";
		Thread.sleep(1000);
		this.makeClick("#question-created-time-filter > div.lyteDummyEventContainer > lyte-drop-button > span");
		Thread.sleep(1000);
		this.selector("body > lyte-drop-box > lyte-drop-body > lyte-drop-item:nth-child(2)").click();
		assertEquals(expect,this.selector("#question-created-time-filter > div.lyteDummyEventContainer > lyte-drop-button > span").getText());
	}
//										After Time Search Box Valid Test_Two
	
	@Test
	public void AfterTimeSearchBoxValidTest_Two() throws InterruptedException {
		String expect = "February 2021";
		this.sendValue("#lyteCalendarView","02/28/2021");
		Thread.sleep(2000);
		assertEquals(expect,this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[1]/div/span[3]").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[2]/div[5]/div[7]").getAttribute("class").contains("lyteCalSel"));
		Thread.sleep(2000);
		this.makeClick("//button[text()='Apply']");	
	}
//											After Submit After Test			
	
	@Test
	public void AfterSubmitAfterTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String res = this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div[1]/div[2]/div/div/div/span[2]").getText();
		Thread.sleep(2000);
		String res_2 = this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[4]/zask-ui-card/div[1]/div[2]/div/div/div/span[2]").getText();
		Thread.sleep(2000);
		assertTrue(res.contains("Jan 22 21"));
		assertTrue(res_2.contains("Dec 21 `20"));
	}
//											Created	TimeDD_3Test
	
	@Test
	public void CreatedTimeDD_3Test() throws InterruptedException {
		String expect = "Between";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(500);
		this.makeClick("#question-created-time-filter > div.lyteDummyEventContainer > lyte-drop-button");
		this.selector ("body > lyte-drop-box > lyte-drop-body > lyte-drop-item:nth-child(3)").click();
		assertEquals(expect,this.selector("#question-created-time-filter > div.lyteDummyEventContainer > lyte-drop-button > span").getText());
	}
//									Beteween Date 1st Input Test
	
	@Test
	public void BeteweenDate1stInputTest() throws InterruptedException {
		Thread.sleep(1000); 
		this.makeClick("#questionDateFilter");
		this.selector("#questionDateFilter").sendKeys("01/01/2021");
		Thread.sleep(500);
		String result = this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[1]/div/span[3]").getText();
		Thread.sleep(1500);
		assertTrue(result.contains("January 2021"));
		Thread.sleep(1500);
		assertTrue(this.selector("#lyteCalendar > lyte-calendar > div > div > div.lyteCalTableContainer > div:nth-child(2) > div.lyteCalCdate.lyteCalTableCell.lyteCalSel").getAttribute("class").contains("lyteCalSel"));	
	}
//										Beteween Date 2nd Input Test
	
	@Test
	public void BeteweenDate2ndInputTest() throws InterruptedException {
		this.makeClick("#questionDateFilterTo > div > #lyteCalendarView");
		this.selector("#questionDateFilterTo > div > #lyteCalendarView").sendKeys("01/31/2021");
		Thread.sleep(3000);
		String result = this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[1]/div/span[3]").getText();
		System.out.println(this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[1]/div/span[3]").getText());
		assertTrue(result.contains("January 2021"));
		Thread.sleep(1500);
		assertTrue(this.selector("//*[@id=\"lyteCalendar\"]/lyte-calendar/div/div/div[2]/div[6]/div[7]").getAttribute("class").contains("lyteCalSel"));
		Thread.sleep(1000);
	}
//										After Submit Between Date Test_1
	
	@Test
	public void AfterSubmitBetweenDateTest_1() throws InterruptedException {
		this.makeClick("//button[text()='Apply']");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String expectDate = "Jan 22 `21 @ 11:02";
		String res = this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div[1]/div[2]/div/div/div/span[2]").getText();
		Thread.sleep(2000);
		System.out.println(expectDate);
		assertTrue(res.contains(expectDate));		
	}
//									After Submit Between Date Test_2
	
	@Test
	public void AfterSubmitBetweenDateTest_2() throws InterruptedException { 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		String expectDate = "asked on Jan 21 `21 @ 17:25";
		String res = this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[3]/zask-ui-card/div[1]/div[2]/div/div/div/span[2]").getText();
		Thread.sleep(2000);
		assertEquals(expectDate,res);		
	}
//								Time Filter Indication Test
	
	@Test
	public void TimeFilterIndicationTest() throws InterruptedException {
		this.getBtnDotMark("#question-filter > span.applied-filter-dot");
		Thread.sleep(1000);
		this.getFilterDotMark("#question-filter", "#filter > div > lyte-accordion:nth-child(7) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.created-time.applied-filter-dot", "//*[text()='Cancel']");
	}
//								Time Filter Clear sTest
	
	@Test
	public void TimeFilterClearTest() throws InterruptedException {
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(500);
		this.makeClick("//*[@id=\"filter\"]/div/lyte-accordion[4]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/div");
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(7) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.created-time.applied-filter-dot").isDisplayed());
		this.makeClick("//button[text()='Apply']");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
	}
//									Score Label Test
	
	@Test
	public void ScoreFiltersLabelText() throws InterruptedException {
		String text =  "SCORE";
		String text3 = "To";
		String text2 = "From";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		String label2 = this.selector("//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-body/div/p[1]").getText();
		String label =  this.selector("//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[1]/span[1]").getText();
		String label3 = this.selector("//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-body/div/p[2]").getText();
		Thread.sleep(2000);
		assertEquals(text,label);
		assertEquals(text2,label2);
		assertEquals(text3,label3);
	}
//									Score Filter input Invalid Test case
	
	@Test
	public void ScoreInputInvalidTestOne() throws InterruptedException {
		Thread.sleep(3000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-from.lyteInput.vertical > div > input","-");
		Thread.sleep(3000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-to.lyteInput.vertical > div > input", "+");
		Thread.sleep(3000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}	
//									Score Filter input Invalid Test case		
	
	@Test
	public void ScoreInputInvalidTestTwo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-from.lyteInput.vertical > div > input","e");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-to.lyteInput.vertical > div > input", "E");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
//								Score Filter input Invalid Test case		
	
	@Test
	public void ScoreInputInvalidTestThree() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-from.lyteInput.vertical > div > input",".");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-to.lyteInput.vertical > div > input", ".");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
	//								Score Filter input Invalid Test case		
	
	@Test
	public void ScoreInputInvalidTestFour() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-from.lyteInput.vertical > div > input","2003");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-to.lyteInput.vertical > div > input", "2003");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
	//								Score Filter input Invalid Test case		
	
	@Test
	public void ScoreInputInvalidTestFive() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-from.lyteInput.vertical > div > input","0.1");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-to.lyteInput.vertical > div > input", "0.9");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}	
//									Score Filter input Invalid Test case		
	
	@Test
	public void ScoreInputInvalidTestSix() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-from.lyteInput.vertical > div > input","2");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-to.lyteInput.vertical > div > input", "1");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
//								Score Filter input Valid Test case		
	
	@Test
	public void ScoreInputValidTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String score = "2";
		Thread.sleep(1000);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[5]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-from.lyteInput.vertical > div > input","1");
		this.sendValue("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.score-range-to.lyteInput.vertical > div > input", "2");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(2500);
		assertEquals(score,this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div/div[1]/div[2]/div[2]").getText());
	}
//								Score Input Indication Seven	
	
	@Test
	public void ScoreInputIndicationSeven() throws InterruptedException {
		this.getBtnDotMark("#question-filter");
		this.getFilterDotMark("#question-filter", "#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.score.applied-filter-dot", "//button[text()='Cancel']");
	}
// 								 Score Input Clear Test	
	
	@Test
	public void ScoreInputClearTest() throws InterruptedException {
		this.makeClick("#question-filter");
		Thread.sleep(1000);
		this.makeClick("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div");
		Thread.sleep(500);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(3000);
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(9) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.score.applied-filter-dot").isDisplayed());		
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
		Thread.sleep(2000);
	}	

	//						--------     Answer Filter Test Cases   ---------
	
	//								Answer Label Test
	@Test
	public void AnsweFiltersLabelText() throws InterruptedException {
		String text =  "ANSWER COUNT";
		String text2 = "From";
		String text3 = "To";
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(1000);		
		String label =  this.selector("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span:nth-child(1)").getText();
		Thread.sleep(1000);
		String label2 = this.selector("//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-body/div/p[1]").getText();		
		Thread.sleep(1000);
		String label3 = this.selector("//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-body/div/p[2]").getText();
		assertEquals(text,label);
		assertEquals(text2,label2);
		assertEquals(text3,label3);
	}
///									Answer Input Invalid Test One	
	
	@Test
	public void AnswerInputInvalidTestOne() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-from.lyteInput.vertical > div > input","+");
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input", "-");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
	//									Answer Input Invalid Test Two	
	
	@Test
	public void AnswerInputInvalidTestTwo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(2000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-from.lyteInput.vertical > div > input","e");
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input", "E");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}	
//									Answer Input Invalid Test Three	
	
	@Test
	public void AnswerInputInvalidTestThree() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(2000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-from.lyteInput.vertical > div > input",".");
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input", ".");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
//									Answer Input Invalid Test Four	
	
	@Test
	public void AnswerInputInvalidTestFour() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(2000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-from.lyteInput.vertical > div > input","2005");
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input", "2010");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
//									Answer Input Invalid Test Five
	
	@Test
	public void AnswerInputInvalidTestFive() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(2000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-from.lyteInput.vertical > div > input","0.1");
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input", "0.9");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
	
//								Answer Input Invalid Test Six
	
	@Test
	public void AnswerInputInvalidTestSix() throws InterruptedException {
		Thread.sleep(3000);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
//		Thread.sleep(2000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-from.lyteInput.vertical > div > input","2");
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input", "1");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(250);
		assertTrue(driver.getPageSource().contains("Please enter valid score filter"));
	}
//									Answer Filter input Valid Test case		
	@Test
	public void AnswerInputValidTest() throws InterruptedException {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String tag = "Answers";
		String ans = "1";
		Thread.sleep(1000);
		this.OpenFilters("//*[@id=\"question-filter\"]", "//*[@id=\"filter\"]/div/lyte-accordion[6]/lyte-yield/lyte-accordion-item/lyte-accordion-header/div[2]/lyte-icon");
		Thread.sleep(2000);
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-from.lyteInput.vertical > div > input","1");
		this.selector("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input").clear();
		this.sendValue("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-body > div > lyte-input.ans-range-to.lyteInput.vertical > div > input", "2");
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(3500);
		assertEquals(tag,this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div/div[1]/div[1]/div[1]").getText());
		assertEquals(ans,this.selector("//*[@id=\"zask-questions-pagination\"]/zask-ui-question-summaries/div/zask-ui-question-summary-view[1]/zask-ui-card/div/div[1]/div[1]/div[2]").getText());
	}
//								Answer Input Indication Seven	
	@Test
	public void AnswerInputIndicationTest() throws InterruptedException {
		this.getBtnDotMark("#question-filter");
		this.getFilterDotMark("#question-filter", "#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.answer-count.applied-filter-dot", "//button[text()='Cancel']");
	}
//								 Answer Input Clear Test	
	@Test
	public void AnswerInputClearTest() throws InterruptedException {
		Thread.sleep(1000);
		this.makeClick("//*[@id=\"question-filter\"]");
		Thread.sleep(1500);
		this.makeClick("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div.clear-filter > div");
		Thread.sleep(1000);
		this.makeClick("//button[text()='Apply']");
		Thread.sleep(500);
		this.selector("#question-filter");
		Thread.sleep(1000);
		assertFalse(this.selector("#filter > div > lyte-accordion:nth-child(11) > lyte-yield > lyte-accordion-item > lyte-accordion-header > div:nth-child(1) > span.score.applied-filter-dot").isDisplayed());
		assertFalse(this.selector("#question-filter > span.applied-filter-dot").isDisplayed());
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
