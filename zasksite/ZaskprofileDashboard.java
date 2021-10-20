package zasksite;

import static org.junit.Assert.*;

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
import org.openqa.selenium.interactions.Actions;

public class ZaskprofileDashboard {

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
	public void LogoutPage(String elem, String elemTwo) throws InterruptedException {
		this.makeClick(elem);
		Thread.sleep(500);
		this.makeClick(elemTwo);
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
		assertTrue(this.selector(element).isDisplayed());
	}
	public String FindId(String element) {
		String id =  this.selector(element).getAttribute("id");
		System.out.println(id);
		return id;
	}
	public void MakeScroll(String elem) {
		WebElement element = this.selector(elem);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void OpenTab(String elem, String elemTwo) throws InterruptedException {
		this.MakeScroll(elem);
		Thread.sleep(500);
		this.makeClick(elemTwo);
		Thread.sleep(1000);
	}
	public void postComment(String elem, String elemTwo,String value) throws InterruptedException {
		this.sendValue("//textarea[@placeholder='Enter your comment ...']", "Testing comment");
		Thread.sleep(500);
		this.makeClick("//div[@lt-prop-title='Post']");
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
	//									Open Dashboard	
	@Test
	public void OpenDashboard() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//		Open Dashboard
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[1]");
		Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().contains("users/57888501"));
	}
	//									Open Accepted Answer	
	//@Test
	public void OpenAcceptedAnswer() throws InterruptedException {
		String expect  = "rgba(0, 181, 112, 1)";
		assertEquals(expect,this.selector("#top-answers > zask-ui-user-top-question-answer:nth-child(1) > div > div > span.sp14").getCssValue("color"));
		this.makeClick("//*[@id=\"top-answers\"]/zask-ui-user-top-question-answer[1]/div/div/span[1]");
		Thread.sleep(1000);		
	}
	//								Accepted Answer Score Test	
	//	@Test
	public void AcceptedAnswerTitleTest() throws InterruptedException {
		String expected = "How to post a question with answer?";
		String date 	= " Jun 14 `19";
		String id = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-question-detail/zask-ui-card/zask-ui-question-detail-view/div[2]");	
		Thread.sleep(1000);
		assertEquals(expected,this.selector("//*[@id= " + id + "]/div[2]/div[1]/p").getText());
		assertTrue(this.selector("//*[@id= " + id + "]/div[2]/div[1]/div[1]/div/div/span[2]").getText().contains(date));
	}
	//									Accepted Answer Score Test		
	//@Test
	public void AcceptedAnswerScoreTest() throws InterruptedException {
		String answer   = "2";
		String id = this.FindId("#zask-question-detail-pagination > zask-ui-answers > zask-ui-answer-detail-view:nth-child(1) > div > div.answer-detail > div.answer-header");
		Thread.sleep(1000);
		assertEquals(answer,this.selector("//*[@id=\"zask-question-detail-pagination\"]/zask-ui-answers/zask-ui-answer-detail-view[1]/div/div[1]/span").getText());
		Thread.sleep(1000);
		assertTrue(this.selector("//*[@id='"+ id +"']/div[2]/span[1]").getAttribute("class").contains("tickmarkClicked"));		
	}		
	//										Open Top Question		
	@Test
	public void OpenMypost() throws InterruptedException {
		//		driver.navigate().back();
		String color = "rgba(47, 175, 249, 1)";
		this.OpenTab("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[2]/div[2]/div[2]/div[2]/span[1]","//label[@for='user-questions-header']/span");
		assertEquals(color,this.selector("//label[@for='user-questions-header']/span").getCssValue("color"));
	}
	//											Question Page Test
	//	@Test
	public void QuestionPageTest() throws InterruptedException {	
		String score = "1";
		String tag	 = "Questions";
		Thread.sleep(1000);
		String id = this.FindId("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div[1]/zask-question-detail/zask-ui-card/zask-ui-question-detail-view/div[2]");
		assertEquals(tag,this.selector("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/div/ul/li[1]/link-to/a").getText());
		assertEquals(score,this.selector("//*[@id='vote-"+ id +"']/span").getText());
		driver.navigate().back();
	}
	//										Post New Question	
	@Test
	public void PostNewQuestion() throws InterruptedException {
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(1000);
		this.makeClick("//zask-ui-button[text() ='ASK QUESTION']");
		this.sendValue("#add-question-title", "What is good Question?");
		this.sendValue("//*[@id=\"editor-outlet\"]/div[1]", "Learn about Question");
		this.MakeScroll("//input[@placeholder='Add tags.']");
		Thread.sleep(2000);
		this.sendValue("//input[@placeholder='Add tags.']", "test");
		Thread.sleep(1000);
		this.selector("//input[@placeholder='Add tags.']").sendKeys(Keys.ENTER);
		this.makeClick("//*[@id=\"add-question-submit\"]");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Confirmation"));
		this.makeClick("#modal-btn");
		Thread.sleep(500);
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div","//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[1]/div/div[5]/button[2]");
		Thread.sleep(2000);
		assertTrue(driver.getPageSource().contains("Your post has been sent for review"));
		Thread.sleep(1000);
	}
	//										LogIn Admin	Pannel
	@Test
	public void LogInAdmin() throws InterruptedException {		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.LogInButton("//a[text()='Login']");
		Thread.sleep(1000);
		this.LoginPage("#login_id", "#nextbtn", "#password","", "");
		Thread.sleep(2500);
		if(driver.getPageSource().contains("User ticket invalid")) {
			driver.navigate().back();
		}else {
			Thread.sleep(500);
		}
	}
	//										Open Notification
	@Test
	public void OpenNotification() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String question = "What is good Question?";
		this.makeClick("//span[@lt-prop-title='Notifications']");
		Thread.sleep(1000);
		this.makeClick("#notificationsBody > zask-ui-notification-body:nth-child(1) > div:nth-child(1) > link-to:nth-child(1) > a:nth-child(1)");
		Thread.sleep(1000);
		System.out.println(this.selector("//p[text()='What is good Question?']").getText());
		assertEquals(question,this.selector("//p[text()='What is good Question?']").getText());
	}
	//											Click like
	@Test public void ClickLike() throws InterruptedException {
		String like = "1";
		this.makeClick("//div[@vote_value='0']");
		Thread.sleep(1500);
		assertEquals(like, this.selector("//span[text()='1']").getText());
	}
	//											Accept And LogIn User	
	@Test
	public void AcceptAndLogInUser() throws InterruptedException {
		this.makeClick("#accept");
		this.makeClick("//zask-ui-button[text()='Cancel']");
		Thread.sleep(1000);
		this.makeClick("#accept");
		Thread.sleep(500);
		this.makeClick("//zask-ui-button[text()='Accept']");
		Thread.sleep(2000);
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div","//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[1]/div/div[5]/button[2]");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.LogInButton("//a[text()='Login']");
		Thread.sleep(1000);
		this.LoginPage("#login_id", "#nextbtn", "#password","", "");
	}
	//											After Question Post Check Notification	
	@Ignore
	@Test
	public void AfterQuestionPostCheckNotification() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String text = "Your question: What is good Question? has been accepted.";
		this.makeClick("//span[@lt-prop-title='Notifications']");
		assertEquals(text,this.selector("#notificationsBody > zask-ui-notification-body:nth-child(1) > div > link-to > a > div > p").getText());
		Thread.sleep(1000);
		this.makeClick("#notificationsBody > zask-ui-notification-body:nth-child(1) > div > link-to > a > div > p");
	}
	//									 After Accept QuestionTest
	@Test
	public void AfterAcceptQuestionCheck() throws InterruptedException {
		this.makeClick("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div");
		Thread.sleep(500);
		this.makeClick("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[1]");
		Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().contains("users/57888501"));
	}				
	//								Post New Article	
	@Test
	public void PostNewArticle() throws InterruptedException {
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.makeClick("//a[text()='Articles']");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.selector("#outlet-zask-content-container > zask-content-container > div.zask-content-aside > zask-add-solution-panel > div > zask-ui-button").click();
		Thread.sleep(1000);
		this.sendValue("#add-question-title", "What does a good article look like?");
		this.sendValue("//*[@id=\"editor-outlet\"]/div[1]", "Add a title and provide in depth details about your article.");
		this.sendValue("//*[@id=\"repoLink\"]","https://zask.localpali.io/zask/add");
		this.sendValue("//input[@placeholder='Add Authors.']","hariharan29220");
		Thread.sleep(1000);
		this.selector("//input[@placeholder='Add Authors.']").sendKeys(Keys.ENTER);
		this.sendValue("//input[@placeholder='Add tags.']", "test");
		Thread.sleep(1000);
		this.selector("//input[@placeholder='Add tags.']").sendKeys(Keys.ENTER);
		this.makeClick("//*[@id=\"add-question-submit\"]");
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains("Confirmation"));
		this.makeClick("#modal-btn");
		Thread.sleep(500);
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div","//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[1]/div/div[5]/button[2]");
		//		assertTrue(driver.getPageSource().contains("Your post has been sent for review"));
		//		Thread.sleep(2000);
	}
	//									LogIn Admin	Pannel
	@Test
	public void LogInAdmin2() throws InterruptedException {		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.LogInButton("//a[text()='Login']");
		Thread.sleep(1000);
		this.LoginPage("#login_id", "#nextbtn", "#password","", "");
		Thread.sleep(2500);
		if(driver.getPageSource().contains("User ticket invalid")) {
			driver.navigate().back();
		}else {
			Thread.sleep(500);
		}
	}
	//										Open Notification
	@Test
	public void OpenNotification2() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String question = "What does a good article look like?";
		this.makeClick("//span[@lt-prop-title='Notifications']");
		Thread.sleep(1000);
		this.makeClick("#notificationsBody > zask-ui-notification-body:nth-child(1) > div:nth-child(1) > link-to:nth-child(1) > a:nth-child(1)");
		Thread.sleep(1000);
		assertEquals(question,this.selector("//p[text()='What does a good article look like?']").getText());
	}
	//											Click like
	@Test public void ClickLike2() throws InterruptedException {
		String like = "1";
		this.makeClick("//div[@vote_value='0']");
		Thread.sleep(1500);
		assertEquals(like, this.selector("//span[text()='1']").getText());
	}
	//											Accept And LogIn User	
	@Test
	public void AcceptAndLogInUser2() throws InterruptedException {
		this.makeClick("#accept");
		Thread.sleep(2000);
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div","//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[1]/div/div[5]/button[2]");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.LogInButton("//a[text()='Login']");
		Thread.sleep(1000);
		this.LoginPage("#login_id", "#nextbtn", "#password","", "");
		Thread.sleep(2500);
		if(driver.getPageSource().contains("User ticket invalid")) {
			driver.navigate().back();
		}else {
			Thread.sleep(500);
		}
	}		
	//											Open Dash board	
	@Test
	public void OpenDashboard2() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//		Open Dashboard
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[1]");
		Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().contains("users/57888501"));
	}
	//									After Accept Open my post	
	@Test
	public void AfterAcceptOpenMypost() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String color = "rgba(47, 175, 249, 1)";
		this.OpenTab("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[2]/div[2]/div[2]/div[2]/span[1]", "//label[@for='user-questions-header']/span");
		assertEquals(color,this.selector("//label[@for='user-questions-header']/span").getCssValue("color"));
	}	
	//										Articles Question Test
	@Test
	public void ArticlTitleTest() throws InterruptedException {
		String tag	 = "Articles";
		Thread.sleep(1000);
		this.makeClick("//*[@id=\"user-questions\"]/div[1]/zask-ui-user-top-question-answer[1]/div/div/span[1]");
		assertEquals(tag,this.selector("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/div/ul/li[2]/link-to/a").getText());
	}
	//											 Check MyPost
	@Test
	public void CheckMyPost() throws InterruptedException {
		String question = "What does a good article look like?";
		String count	= "1";
		assertEquals(count,this.selector("//*[@id=\"user-questions\"]/div[1]/zask-ui-user-top-question-answer[1]/div/div/span[1]").getText());
		assertEquals(question,this.selector("//*[@id=\"user-questions\"]/div[1]/zask-ui-user-top-question-answer[1]/div/div/span[2]").getText());
	}
	//											 Post Invalid comment
	@Test
	public void PostInvalidComment() throws InterruptedException {
		String id = this.FindId("#outlet-zask-content-container > zask-content-container > div.zask-content-main > zask-solution-detail > zask-ui-card > zask-ui-solution-detail-view > div.question-detail-container");
		this.makeClick("//*[@id="+id+"]/div[2]/div[3]/div/span");
		assertTrue(this.selector("//textarea[@placeholder='Enter your comment ...']").isDisplayed());
		this.postComment("//textarea[@placeholder='Enter your comment ...']", "//div[@lt-prop-title='Post']", "");
		Thread.sleep(1800);
		assertTrue(driver.getPageSource().contains("Comment cannot be empty."));
	}
	//											 Post Invalid comment -2
	@Test
	public void PostInvalidComment2() throws InterruptedException {
		String id = this.FindId("#outlet-zask-content-container > zask-content-container > div.zask-content-main > zask-solution-detail > zask-ui-card > zask-ui-solution-detail-view > div.question-detail-container");
		this.makeClick("//*[@id="+id+"]/div[2]/div[3]/div/span");
		assertTrue(this.selector("//textarea[@placeholder='Enter your comment ...']").isDisplayed());
		this.postComment("//textarea[@placeholder='Enter your comment ...']", "//div[@lt-prop-title='Post']", "          ");
		Thread.sleep(1800);
		assertTrue(driver.getPageSource().contains("Comment cannot be empty."));
	}
//	 											Post Invalid comment -3
	@Test
	public void PostInvalidComment3() throws InterruptedException {
		String id = this.FindId("#outlet-zask-content-container > zask-content-container > div.zask-content-main > zask-solution-detail > zask-ui-card > zask-ui-solution-detail-view > div.question-detail-container");
		this.makeClick("//*[@id="+id+"]/div[2]/div[3]/div/span");
		assertTrue(this.selector("//textarea[@placeholder='Enter your comment ...']").isDisplayed());
		this.postComment("//textarea[@placeholder='Enter your comment ...']", "//div[@lt-prop-title='Post']", "!@#$%^&*??//!!~~``((){}");
		Thread.sleep(1800);
		assertTrue(driver.getPageSource().contains("Error"));
	}
	//									Post Valid comment 
	@Test
	public void PostValidComment() throws InterruptedException {
		String id = this.FindId("#outlet-zask-content-container > zask-content-container > div.zask-content-main > zask-solution-detail > zask-ui-card > zask-ui-solution-detail-view > div.question-detail-container");
		this.makeClick("//*[@id="+id+"]/div[2]/div[3]/div/span");
		assertTrue(this.selector("//textarea[@placeholder='Enter your comment ...']").isDisplayed());
		this.postComment("//textarea[@placeholder='Enter your comment ...']", "//div[@lt-prop-title='Post']", "Testing comment");
		Thread.sleep(1800);
		assertTrue(driver.getPageSource().contains("Your comment has been edited"));
	}
	//											Open Dash board	
	@Test	
	public void OpenDashboard3() throws InterruptedException {
		//Open Dashboard
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[1]");
		Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().contains("users/57888501"));
	}
	//										Open Top My comments		
	@Test
	public void OpenMyComments() throws InterruptedException {
		String color = "rgba(47, 175, 249, 1)";
		String txt   = "What does a good article look like?";
		this.OpenTab("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[2]/div[2]/div[2]/div[2]","//label[@for='my-comment-header']/span");
		assertEquals(color,this.selector("//label[@for='my-comment-header']/span").getCssValue("color"));
		assertEquals(txt,this.selector("//*[@id=\"my-comment\"]/div[1]/zask-ui-user-top-question-answer[1]/div/div/span[2]").getText());
	}
	@Test
	public void OpenQuestions() throws InterruptedException {
		this.makeClick("//a[text()='Questions']");
		this.MakeScroll("//a[@href='zask/questions/8246209950']");
		Thread.sleep(500);
		this.makeClick("//a[@href='zask/questions/8246209950']");
		Thread.sleep(1000);
		this.postComment("//span[text()='Add Comment']", "//textarea[@placeholder='Enter your comment ...']", "Testing comment");		
		assertTrue(driver.getPageSource().contains("Your comment has been posted"));
	}
	//										Open Dash board	
	@Test	
	public void OpenDashboard4() throws InterruptedException {
		//Open Dashboard
		this.LogoutPage("//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/div[1]/div/div[1]/div", "//*[@id=\"outlet\"]/zask-container/zask-ui-header/div[1]/div/zask-ui-user-panel/zask-ui-user-profile/div[2]/div[2]/div[3]/a[1]");
		Thread.sleep(1000);
		assertTrue(driver.getCurrentUrl().contains("users/57888501"));
	}
	//										Open Top My comments		
	@Test
	public void OpenMyComments4() throws InterruptedException {
		String color = "rgba(47, 175, 249, 1)";
		String txt   = "What is good question?";
		this.OpenTab("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[2]/div[2]/div[2]/div[2]","//label[@for='my-comment-header']/span");
		assertEquals(color,this.selector("//label[@for='my-comment-header']/span").getCssValue("color"));
		assertEquals(txt,this.selector("//*[@id=\"my-comment\"]/div[1]/zask-ui-user-top-question-answer[1]/div/div/span[2]").getText());
	}
	@Test
	public void OpenActivityLog() throws InterruptedException {
		String color = "rgba(47, 175, 249, 1)";
		String txt   = "What is good question?";
		this.OpenTab("//*[@id=\"outlet-zask-content-container\"]/zask-content-container/div/zask-user-profile/div/div[2]/div[2]/div[2]/div[2]","//label[@for='activity-log-header']/span");
		assertEquals(color,this.selector("//label[@for='my-comment-header']/span").getCssValue("color"));
		assertEquals(txt,this.selector("//*[@id=\"my-comment\"]/div[1]/zask-ui-user-top-question-answer[1]/div/div/span[2]").getText());
	}
	







	//	@AfterClass
	public static void Quit() throws InterruptedException {
		WebElement profileBtn = driver.findElement(By.cssSelector("#outlet > zask-container > zask-ui-header > div:nth-child(1) > div > zask-ui-user-panel > div.user-profile-container > div > div.user-profile > div"));
		profileBtn.click();
		Thread.sleep(1000);
		WebElement LogoutBtn   = driver.findElement(By.xpath("//button[text()='Sign Out']"));
		LogoutBtn.click();
		driver.close();	
	}


}
