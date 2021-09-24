package zaskasite;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
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

public class ZaskArticlePage {

	public static WebDriver driver;
	public static JavascriptExecutor js; 
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
	public void TagInput(String elem,String elemOne,String value) throws InterruptedException {
		this.selector(elem).click();
		this.selector(elemOne).click();
		this.selector(elemOne).sendKeys(Keys.BACK_SPACE);
		this.selector(elemOne).sendKeys(Keys.BACK_SPACE);
		this.sendValue(elemOne, value);
		this.selector(elem).click();
	}public void TagInputValue(String element,String value) throws InterruptedException {
		this.selector(element).click();
		this.clearTags(element);
		this.selector(element).sendKeys(value);
		Thread.sleep(1000);
		this.selector(element).sendKeys(Keys.ENTER);
	}
	public void DescriptionInput(String elem,String elemOne,String value) throws InterruptedException {
		this.selector(elem).click();	
		Thread.sleep(1000);
		this.selector(elemOne).click();
		Thread.sleep(1000);
		this.selector(elemOne).sendKeys(value);
		Thread.sleep(1000);
	}
	public void clearTags(String element) {
		this.selector(element).sendKeys(Keys.BACK_SPACE);
		this.selector(element).sendKeys(Keys.BACK_SPACE);
	}
	public void TwoComboCheck(String elem,String elemOne,String elemTwo,String elemThree,String value) {
		this.selector(elem).click();
		this.selector(elem).clear();
		this.selector(elemOne).click();
		this.selector(elem).sendKeys(value);
		this.selector(elem).sendKeys(Keys.CONTROL+"A");
		this.selector(elemTwo).click();
		this.selector(elem).click();
		this.selector(elem).sendKeys(Keys.CONTROL+"A");
		this.selector(elemThree).click();
	}
	public String FindId(String element) {
		String id =  this.selector(element).getAttribute("id");
		System.out.println(id);
		return id;
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
	//						.....     ZAsk Login URL Test     .....
	@Test
	public void ZaskLoginURLTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.LogInButton("//a[text()='Login']");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.LoginPage("#login_id", "#nextbtn", "#password", "esakkivash.n+testt25@zohocorp.com", "Tester@07");
	}
	//						.....     ArticlepageURLTest     .....
	@Test 
	public void ArticlesURLTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expectedUrl = "https://zask.localpali.io/zask/articles?sort=New&filter=All";
		this.makeClick("//a[text()='Articles']");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualUrl  = driver.getCurrentUrl();
		assertEquals(expectedUrl,actualUrl);
	}						//	.....     ArticleButtonTest     .....
	@Test 
	public void ArticlesButtonTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expected = "rgba(47, 175, 249, 1)";
		String actual = this.selector("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/div/ul/li[2]/link-to/a").getCssValue("background-color");
		System.out.println(actual);
		assertEquals(expected,actual);
	}
	//	.....    Add ArticleButtonTest     .....
	@Test 
	public void ADDArticlesButtonTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expected = "rgba(91, 131, 112, 1)";
		String actual = this.selector("#outlet-zask-content-container > zask-content-container > div.zask-content-aside > zask-add-solution-panel > div > zask-ui-button").getCssValue("background-color");
		assertEquals(expected,actual);
	}
	//						.....     Add Article page URL Test     .....
	@Test
	public void AddArticlepageURLTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.makeClick("#outlet-zask-content-container > zask-content-container > div.zask-content-aside > zask-add-solution-panel > div > zask-ui-button");
		String expected  = "https://zask.localpali.io/zask/add";
		Thread.sleep(1000);
		String actual 	 =  driver.getCurrentUrl();
		assertEquals(expected,actual);	
	}
	//						.....     Title Label Test     .....
	@Test
	public void TitleLabelTest() throws InterruptedException {
		String expect = "Title";
		assertEquals(expect,this.selector("label[for=add-question-title]").getText());
		Thread.sleep(400);
		driver.navigate().refresh();
	}
	//						.....     Empty Check     ......
	@Test
	public void EmptyCheck() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.selector(".ql-editor").clear();
		Thread.sleep(500);
		this.makeClick("#add-question-submit");
		assertTrue(driver.getPageSource().contains("Enter a post title of at least 15 characters."));
		driver.navigate().refresh();
	}
	//						.....     Title Invalid Test One    .....
	@Test
	public void TitleInvalidTestOne() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.selector(".ql-editor").clear();
		this.sendValue("#add-question-title", "13243546576812343456678979806785");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(300);
		assertFalse(driver.getPageSource().contains("Enter a post title of at least 15 characters."));
		driver.navigate().refresh();
	}
	//						.....     Title Invalid Test Two     ......
	@Test
	public void TitleInvalidTestTwo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.selector(".ql-editor").clear();
		this.sendValue("#add-question-title", "q@@##@@##@@2233e");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		assertFalse(driver.getPageSource().contains("Enter a post title of at least 15 characters."));
		driver.navigate().refresh();
	}
	//						.....     Title Invalid Test Three     .....
	@Test
	public void TitleInvalidTestThree() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.selector(".ql-editor").clear();
		this.sendValue("#add-question-title", "````Hi Hlo everyone``````");
		this.makeClick("#add-question-submit");
		this.selector("zask-ui-tags-multi-selector[restrict='restrict_max']").sendKeys(Keys.BACK_SPACE.BACK_SPACE);
		Thread.sleep(300);
		assertFalse(driver.getPageSource().contains("Enter a post title of at least 15 characters."));
		driver.navigate().refresh();
	}
	//						.....     Title Invalid Test Four     .....
	@Test
	public void TitleInvalidTestFour() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.selector(".ql-editor").clear();
		Thread.sleep(2000);
		this.sendValue("#add-question-title", "a@          $''");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(300);
		assertTrue(driver.getPageSource().contains("Enter a post title of at least 15 characters."));
		driver.navigate().refresh();
	}
	//						.....	  Title Invalid Test Five	  .....
	@Test
	public void TitleInvalidTestFive() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.selector("#editor-outlet > div.ql-editor").clear();
		Thread.sleep(1000);
		this.sendValue("#add-question-title", "An article can be structured similar to the place holder in the description section. Although, a few things to keep in mind while posting a good article are to:\n" + 
				"An article can be structured similar to the place holder in the description section. Although, a few things to keep in mind while posting a good article are to:\n" + 
				"An article can be structured similar to the place holder in the description section. Although, a few things to keep in mind while posting a good article are to:\n" + 
				"");
		this.makeClick("#add-question-submit");
		Thread.sleep(300);
		assertTrue(driver.getPageSource().contains("Post title cannot exceed 150 characters."));
		driver.navigate().refresh();
	}
	//							.....     Title Invalid Test Six	 .....
	@Test
	public void TitleInvalidTestSix() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.selector("#editor-outlet > div.ql-editor").clear();
		Thread.sleep(1000);
		this.sendValue("#add-question-title", "a```~~~~~~~~~~~~a");
		this.makeClick("#add-question-submit");
		Thread.sleep(300);
		assertTrue(driver.getPageSource().contains("Enter a post title of at least 15 characters."));
		driver.navigate().refresh();
	}
	//								.....     Question Title Test     ......
	@Test
	public void QuestionTitleTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.selector("#editor-outlet > div.ql-editor").clear();
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		String expectedText = "Add a title and provide in depth details about your article.";
		Thread.sleep(500);
		String actualText  = this.selector("#add-question-title").getAttribute("value");
		assertEquals(expectedText,actualText);
		this.makeClick("#add-question-submit");
		Thread.sleep(300);
		assertTrue(driver.getPageSource().contains("Enter at least one tag to post."));
		driver.navigate().refresh();
	}
	//							.....    Tag Input suggestion count Test 
	@Test
	public void TagInputSugCountTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("input[placeholder='Add tags.']", "test");
		Thread.sleep(1000);
		List<WebElement> result = driver.findElements(By.tagName("zask-ui-search-result"));
		int expected  = 7;
		Thread.sleep(1000);
		assertEquals(expected,result.size());
	}	
	//							.....    Tag Input Tag Test
	//	@Test
	//	public void TagInputSuggestionTest() throws InterruptedException {
	//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//		this.sendValue("input[placeholder='Add tags.']","test");
	//		Thread.sleep(1000);
	//		List<WebElement> result = driver.findElements(By.tagName("zask-ui-search-result"));
	//		Thread.sleep(500);
	//		System.out.println(result.contains("test"));
	//		assertTrue(result.contains("test"));
	//		Thread.sleep(3000);
	//	}
	//							.....     Tags Text Test      .....
	@Test
	public void TagTextTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		String expect = "testing";
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		WebElement result = this.selector("zask-ui-tag-closable[tagname='testing']");
		assertTrue(result.isDisplayed());
		driver.navigate().refresh();
	}
	//								.....     Description Invalid Test One    .....
	@Test
	public void DescriptionInvalidTestOne() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.selector("#editor-outlet > div.ql-editor").clear();
		this.sendValue("#editor-outlet > div.ql-editor.ql-blank > p", "abcdefgh");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		Thread.sleep(1000);
		String expected = "abcdefgh";
		System.out.println(this.selector("#editor-outlet > div.ql-editor > p").getText());
		assertEquals(expected,this.selector("#editor-outlet > div.ql-editor > p").getText());
		this.makeClick("#add-question-submit");
		Thread.sleep(300);
		assertTrue(driver.getPageSource().contains("Enter valid post content of at least 15 characters"));
		driver.navigate().refresh();
	}
	//								.....     Description Invalid Test Two     ......
	@Test
	public void DescriptionInvalidTestTwo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor.ql-blank > p", "aaaavvvvvbbbbbbbbbbbb");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		Thread.sleep(1000);
		String expected = "aaaavvvvvbbbbbbbbbbbb";
		assertEquals(expected,this.selector("#editor-outlet > div.ql-editor > p").getText());
		this.makeClick("#add-question-submit");
		assertTrue(driver.getPageSource().contains("Enter valid post content of at least 15 characters"));
		driver.navigate().refresh();
	}
	//								.....     Description Invalid Test Three     .....
	@Test
	public void DescriptionInvalidTestThree() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor.ql-blank", "aa          ..           ..        .     .");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		String expected = "aa          ..           ..        .     .";
		assertEquals(expected,this.selector("#editor-outlet > div.ql-editor > p").getText());
		this.makeClick("#add-question-submit");
		Thread.sleep(300);
		assertTrue(driver.getPageSource().contains("Enter valid post content of at least 15 characters"));
		driver.navigate().refresh();
	}
	//						....     Bold Hover color Test     .....
	@Test
	public void BoldHovercolorTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "rgba(0, 102, 204, 1)";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[1]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getCssValue("color");
		assertEquals(expect,actual);
	}
	//	....     Bold Tool tip Test     .....
	@Test
	public void BoldTooltipTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "Bold";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[1]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getAttribute("lt-prop-title");
		assertEquals(expect,actual);
	}
	//							....     Italic Hover color Test     .....
	@Test
	public void ItalicHovercolorTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "rgba(0, 102, 204, 1)";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[2]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getCssValue("color");
		assertEquals(expect,actual);
	}
	//						....    Italic Tool tip Test     .....
	@Test
	public void ItalicTooltipTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "Italic";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[2]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getAttribute("lt-prop-title");
		assertEquals(expect,actual);
	}
	//							....     Underline Hover color Test     .....
	@Test
	public void UnderlineHovercolorTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "rgba(0, 102, 204, 1)";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[3]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getCssValue("color");
		assertEquals(expect,actual);
	}
	//	....     Underline Tool tip Test     .....
	@Test
	public void UnderlineTooltipTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "Underline";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[3]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getAttribute("lt-prop-title");
		assertEquals(expect,actual);
	}
	//							....     Strike Hover color Test     .....
	@Test
	public void BoldStrikecolorTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "rgba(0, 102, 204, 1)";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[4]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getCssValue("color");
		assertEquals(expect,actual);
	}
	//				....     Strike Tool tip Test     .....
	@Test
	public void StrikeTooltipTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String expect = "Strike";
		WebElement bold = this.selector("//*[@id=\"editor-1\"]/div[1]/span/button[4]");
		Actions actions = new Actions(driver);
		actions.moveToElement(bold).perform();
		String actual = bold.getAttribute("lt-prop-title");
		assertEquals(expect,actual);
	}
	//								.....      Description Box Combo Tags Test -1   .....
	@Test
	public void DescriptionBoxIAllTagsTest() throws InterruptedException {
		this.selector("#editor-outlet > div.ql-editor").clear();
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.selector("#editor-outlet > div.ql-editor").clear();
		this.DescriptionInput("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-outlet > div.ql-editor.ql-blank", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		String expect = "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.";
		Thread.sleep(500);
		String actual = this.selector("#editor-outlet > .ql-editor> p>strong").getText();
		assertEquals(expect,actual);
	}
	//								.....      Description Box Combo Tags Test -2   .....
	@Test
	public void DescriptionBoxIALlTagsTestTwo() throws InterruptedException {
		this.DescriptionInput("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		String expect = "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.";
		Thread.sleep(500);
		String actual = this.selector("#editor-outlet > .ql-editor p > em").getText();
		Thread.sleep(1000);
		assertEquals(expect,actual);
		this.selector("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic").click();
	}
	//								.....      Description Box Combo Tags Test -3   .....
	@Test
	public void DescriptionBoxIALlTagsTestThree() throws InterruptedException {
		this.DescriptionInput("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		String expect = "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.";
		Thread.sleep(1000);
		String actual = this.selector("#editor-outlet > .ql-editor > p > u").getText();
		Thread.sleep(1000);
		assertEquals(expect,actual);
		this.selector("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline").click();
	}
	//								.....      Description Box Combo Tags Test - 4   .....
	@Test
	public void DescriptionBoxIALlTagsTestFur() throws InterruptedException {
		this.DescriptionInput("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		Thread.sleep(1000);
		String expect = "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.";
		String actual = this.selector("#editor-outlet > .ql-editor > p > s").getText();
		Thread.sleep(1000);
		assertEquals(expect,actual);
		this.selector("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike").click();
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxB_I_UComboTest() throws InterruptedException {
		this.selector("#editor-outlet > div.ql-editor").clear();
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > u").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxB_I_SComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > s").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxB_U_SComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > s > u").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxB_S_UComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > s > u").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxB_S_IComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > s").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxI_B_UComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > u").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxI_B_SComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > s").isDisplayed());
	}
	//								.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxI_U_SComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > em > s > u").isDisplayed());
	}
	//								.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxI_S_BComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > s").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxU_S_BComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > s > u").isDisplayed());
	}
	//								.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxU_S_IComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > em > s > u").isDisplayed());
	}
	//							.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxU_B_IComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > u").isDisplayed());
	}
	//						.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxU_I_SComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > em > s > u").isDisplayed());
	}
	//								.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxU_I_BComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong >  em > u").isDisplayed());
	}
	//										.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxS_B_IComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > s").isDisplayed());
	}
	//										.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxS_B_UComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > s > u").isDisplayed());
	}
	//	.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxS_I_UComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > em > s > u").isDisplayed());
	}
	//	.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxS_I_BComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "Description editor check");
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > em > s").isDisplayed());
	}
	//	.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxS_U_BComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong > s > u").isDisplayed());
	}
	//	.....      Description Box Combo Text Tag Test     .....
	@Test
	public void DescriptionBoxS_U_IComboTest() throws InterruptedException {
		this.TwoComboCheck("#editor-outlet > div.ql-editor > p", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline", "#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic", "Description editor check");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > em > s > u").isDisplayed());
		driver.navigate().refresh();
	}
	//	.....      Description Box Bold Text Tag Test     .....
	@Test
	public void DescriptionBoxBoldTextTagTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.selector("#editor-outlet > div.ql-editor").clear();
		this.selector("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-bold").click();
		this.selector("#editor-outlet > div.ql-editor > p").sendKeys("This is");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > strong").isDisplayed());
	}
	//							.....      Description Box Italic Text Tag Test     .....
	@Test
	public void DescriptionBoxItalicTagTest() throws InterruptedException {
		this.selector("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-italic").click();
		this.selector(".ql-editor").click();
		this.selector("#editor-outlet > div.ql-editor ").sendKeys("Junit");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p > em").isDisplayed());
	}
	//					.....      Description Box Underline Text Tag Test     .....
	@Test
	public void DescriptionBoxUnderlineTagTest() throws InterruptedException {
		this.selector("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-underline").click();
		this.selector(".ql-editor").click();
		this.selector("#editor-outlet > div.ql-editor ").sendKeys("automation");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet > div.ql-editor > p>strong > u").isDisplayed());
		this.makeClick("#add-question-submit");
		Thread.sleep(1000);
		this.makeClick("#modal-btn");
	}	
	//							.....      Description Box Delete Tag Test     .....
	@Test
	public void DescriptionBoxDeleteTagTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.selector("#editor-1 > div.ql-toolbar.ql-snow > span > button.ql-strike").click();
		this.selector(".ql-editor").click();
		this.selector("#editor-outlet > div.ql-editor ").sendKeys("Testcase");
		Thread.sleep(1000);
		assertTrue(this.selector("#editor-outlet >div.ql-editor > p > s ").isDisplayed());
	}
	//							.....     Description  after submit Bold text test    ......
	@Test
	public void DescriptionBoxExSubmitBoldTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String expected = "This is";
		String id  = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-solution-detail/zask-ui-card/zask-ui-solution-detail-view/div[2]");
		String actual = this.selector("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/strong").getText();
		System.out.println("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/strong");
		assertEquals(expected,actual);
	}
	//							.....     Description after submit Italic test    ......
	@Test
	public void DescriptionBoxExSubmitItalicTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String expected = "Junit";
		String id  = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-solution-detail/zask-ui-card/zask-ui-solution-detail-view/div[2]");
		String actual = this.selector("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/em").getText();
		System.out.println(this.selector("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/em"));
		assertEquals(expected,actual);
	}
	//							.....     Description after submit Underline test    ......
	@Test
	public void DescriptionBoxExSubmitUnderlineTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String expected = "automation";
		String id  = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-solution-detail/zask-ui-card/zask-ui-solution-detail-view/div[2]");
		String actual = this.selector("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/u").getText();
		System.out.println("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/u");
		assertEquals(expected,actual);
	}
	//							.....     Description after submit Strike test    ......
	@Test
	public void DescriptionBoxExSubmitStrikeTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String expected = "Testcase";
		String id  = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-solution-detail/zask-ui-card/zask-ui-solution-detail-view/div[2]");
		String actual = this.selector("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/s").getText();
		System.out.println("//*[@id='"+ id +"']/div[2]/div[2]/div[2]/p/s");
		assertEquals(expected,actual);
		driver.navigate().refresh();
	}	
	//		.....      Description check     ......
	@Test
	public void DescriptionBoxTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.selector("#editor-outlet > div.ql-editor").clear();
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");  
		String expectedText = "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.";
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		String actualText  = this.selector("#editor-outlet > div.ql-editor").getText();
		assertEquals(expectedText,actualText);
		Thread.sleep(500);
	}
	//								.....     Link Label Test     .....
	@Test
	public void LinkLabelTest() throws InterruptedException {
		String expect = "Link";
		assertEquals(expect,this.selector(".add-link>div:first-of-type").getText());
		System.out.println(this.selector(".add-link>div:first-of-type").getText());
		Thread.sleep(400);
		driver.navigate().refresh();
	}
	//							.....		Link Input invalid  test     ..... 

	@Test
	public void LinkInputInvalidTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "www.google.com");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(400);
		assertTrue(driver.getPageSource().contains("Enter a valid Link"));
		driver.navigate().refresh();
	}
	//							.....		Link Input Invalid Test Two    ..... 
	@Test
	public void LinkInputInvalidTestTwo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "http.zasklocalpaliio/zask/add");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(400);
		assertTrue(driver.getPageSource().contains("Enter a valid Link"));
		driver.navigate().refresh();
	}
	// 								.....     Link Input invalid Test Three 
	@Test
	public void LinkInputInvalidTestThree() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "http.zasklocalpali.io/zask/add");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(400);
		assertTrue(driver.getPageSource().contains("Enter a valid Link"));		
		Thread.sleep(1000);
		driver.navigate().refresh();
	}
	// 								.....  ink Input invalid Test Four 
	@Test
	public void LinkInputInvalidTestFour() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "http:zasklocalpali.io/zask/add");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(400);
		assertTrue(driver.getPageSource().contains("Enter a valid Link"));
		driver.navigate().refresh();
	}
	// 	.....     Link Input invalid Test Five 
	@Test
	public void LinkInputInvalidTestFive() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "http//zasklocalpali.io/zask/add");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(400);
		assertTrue(driver.getPageSource().contains("Enter a valid Link"));
		driver.navigate().refresh();
	}
	//							.....    Link Input invalid Test six     .....
	@Test
	public void LinkInputInvalidTestSix() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "http//aa.bb.cc");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(400);
		assertTrue(driver.getPageSource().contains("Enter a valid Link"));
		driver.navigate().refresh();
	}
	//						 	.....     Link Input valid Test 
	@Test
	public void LinkInputValidTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "https://www.javatpoint.com/selenium-css-selector-inner-text");
		Thread.sleep(1000);
		this.makeClick("#add-question-submit");
		Thread.sleep(400);
		assertFalse(driver.getPageSource().contains("Enter a valid Link"));
		driver.navigate().refresh();
	}
	// 							.....    Author Input Tag Test
	//	@Test
	//	public void AuthorInputInvalidTest() throws InterruptedException {
	//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//		this.sendValue("input[placeholder='Add Authors.']","ab");
	//		Thread.sleep(1000);
	//		List<WebElement> result = driver.findElements(By.tagName("zask-ui-search-result"));
	//		Thread.sleep(5000);
	//		assertTrue(result.contains("ab"));
	//	}
	//						.....     Authors Label Test     .....
	@Test
	public void AuthorsLabelTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("#add-question-title", "Add a title and provide in depth details about your article.");
		this.sendValue("#editor-outlet > div.ql-editor", "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.");
		this.TagInputValue("input[placeholder='Add tags.']", "testing");
		this.sendValue("#repoLink", "https://www.javatpoint.com/selenium-css-selector-inner-text");
		String expect = "Authors";
		assertEquals(expect,this.selector(".add-owners>div:first-of-type").getText());
		Thread.sleep(400);
	}
	// 					.....    Author Input suggestion count Test 
	@Test
	public void AuthorInputSugCountTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.sendValue("input[placeholder='Add Authors.']", "ab");
		Thread.sleep(1000);
		List<WebElement> result = driver.findElements(By.tagName("zask-ui-search-result"));
		int expected  = 10;
		Thread.sleep(1000);
		assertEquals(expected,result.size());
	}
	//						.....    Author Input Tag Test
	@Test
	public void AuthorInputValidTest() throws InterruptedException {
		this.selector("input[placeholder='Add Authors.']").clear();
		this.selector("input[placeholder='Add Authors.']").sendKeys( "hariharan29220");
		Thread.sleep(1000);
		this.selector("input[placeholder='Add Authors.']").sendKeys(Keys.ENTER);
		assertTrue(this.selector("zask-ui-user-closable[username='hariharan29220']").isDisplayed());	
		Thread.sleep(1000);
	}
	//							.....     Authors Label Test     .....
	@Test
	public void TagLabelTest() throws InterruptedException {
		String expect = "Tags";
		assertEquals(expect,this.selector(".add-tags>div:first-of-type").getText());
		Thread.sleep(400);
	}
	//							.....    Confirmation Box Heading Test     .....
	@Test
	public void SubmitAlertBoxTitleTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.selector("#add-question-submit").click();
		String expected = "Confirmation";
		String actual = this.selector("body > lyte-wormhole > lyte-yield > div > div > lyte-yield > lyte-modal-header").getText();
		Thread.sleep(1000);
		assertEquals(expected,actual);	
	}
	//							.....    Confirmation Box Text Test     .....
	@Test
	public void SubmitAlertBoxTextTest() throws InterruptedException {
		String expected = "Your post will be sent for review.\nDo you want to proceed?";
		String actual = this.selector("#modal-desc").getText();
		System.out.println("submit"+this.selector("//div[@id='modal-desc']").getText());
		assertEquals(expected,actual);	
	}
	////						.....    Confirmation Box Cancel button color Test     .....

	@Test
	public void SubmitAlertBoxButtoncancelColorTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String expect = "rgba(255, 0, 0, 0.667)";
		String actual = this.selector(".cancel").getCssValue("background-color");
		Thread.sleep(1000);
		assertEquals(expect,actual);
	} 
	//						.....    Confirmation Box OK button color Test     .....
	@Test
	public void SubmitAlertBoxButtonOklColorTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String expect = "rgba(118, 174, 118, 1)";
		String actual = this.selector("#modal-btn").getCssValue("background-color");
		Thread.sleep(1000);
		assertEquals(expect,actual);
	}
	//						.....     After submit cancel title text Test     .....
	@Test
	public void AfterSubmitcancelTitleTextTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.selector("#add-question-submit");
		Thread.sleep(1000);
		this.makeClick(".cancel");
		Thread.sleep(1000);
		String expect = "Add a title and provide in depth details about your article.";
		String actual = this.selector("#add-question-title").getText();
		System.out.println(this.selector("#add-question-title").getText());
		assertEquals(expect,actual);
	}
	//					.....    After submit cancel Description Text Test     .....
	@Test		
	public void AfterSubmitCancelDescriptionTextTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String expect = "Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed.";
		String actual = this.selector("#editor-outlet>div.ql-editor>p").getText();
		assertEquals(expect,actual);
	}
	//						.....    After submit cancel  Tag Test     .....
	@Test
	public void AfterSubmitCancelTagTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		assertTrue(this.selector("zask-ui-tag-closable[tagname='testing']").isDisplayed());
		System.out.println(this.selector("Tag"+"zask-ui-tag-closable[tagname='testing']").isDisplayed());
	} 	
	//					....     After Submit Ok alert Test     .....
	@Test
	public void SubmitOk() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.makeClick("#add-question-submit");
		Thread.sleep(2000);
		this.selector("#modal-btn").click();
		Thread.sleep(3000);
		assertTrue(driver.getPageSource().contains("Your post has been sent for review"));
	}
	//								....     After Submit Ok Title Test     .....
	@Test
	public void AfterSubmitOkURLTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String id  = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-solution-detail/zask-ui-card/zask-ui-solution-detail-view/div[2]");
		Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().contains(id));
	}
	//							....     After Submit Ok Title Test     .....
	@Test
	public void AfterSubmitOkTitleTest() {
		assertTrue(driver.getPageSource().contains("Add a title and provide in depth details about your article."));
	}
	//	....     After Submit Ok Tag Test     .....
	@Test
	public void AfterSubmitOkTagTest() {
		String expect = "testing";
		WebElement tag = this.selector("link-to > a > span[tagname='testing']"); 
		String actual = tag.getText();
		assertEquals(expect,actual);
	}
	//	....     After Submit Ok Tag Test     .....
	@Test
	public void AfterSubmitOkTagTextTest() {
		WebElement tag = this.selector("link-to > a > span[tagname='testing']"); 
		Actions actions = new Actions(driver);
		actions.moveToElement(tag).perform();
		String expect = "rgba(0, 162, 255, 1)";
		assertEquals(expect,tag.getCssValue("background-color"));
	}
	//							....     After Submit Ok Description Test     .....
	@Test
	public void AfterSubmitOkDescriptionTest() {
		assertTrue(driver.getPageSource().contains("Explain what your article is about. Make sure you tell the users how to use the presented code and highlight areas of interest if needed."));
	}
	//								....     After Submit Ok Link Test     .....
	@Test
	public void AfterSubmitOkLinkTest() {
		assertTrue(driver.getPageSource().contains("https://www.javatpoint.com/selenium-css-selector-inner-text"));

	}
	//					....     After Submit Ok Author Test     .....
	@Test
	public void AfterSubmitOkAuthorTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String id  = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-solution-detail/zask-ui-card/zask-ui-solution-detail-view/div[2]");
		WebElement actual = this.selector("//*[@id='vote-"+ id +"']/zask-ui-solution-authors-chip/div/div[3]");
		System.out.println("//div[@id="+ id +"]/zask-ui-solution-authors-chip/div/div[2]");
		Actions actions = new Actions(driver);
		actions.moveToElement(actual).perform();
		assertTrue(this.selector("#co-author-pic").isDisplayed());
		assertTrue(this.selector("//span[text()= 'hariharan29220']").isDisplayed());
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
