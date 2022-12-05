package com.smartcitywebadmin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminloginClass {
	
	public String baseUrl = "http://smartcityqa.projectstatus.in/";
	String driverPath = "D://chromedriver_win32//chromedriver.exe";
	public WebDriver driver ;

	@BeforeTest
	public void launchBrowser() {
		System.out.println("launching Chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize(); 

	}

	@Test(priority=1,description="Check URL" , groups="Smoke")
	public void CheckPageURL() {

		String url1=driver.getCurrentUrl();
		//System.out.println(url1);

		if (url1.contains("http://smartcityqa.projectstatus.in/")){
			System.out.println("URL Verification: "+ "It is an Internal Link – Passed");
		}
		else{
			System.out.println("URL Verification: “+ “It is Not an Internal Link – Failed");
		}
	}

	@Test(priority=2,groups="Smoke")
	public void forgottpasswordLink() {
		try{
			boolean linkExistence=driver.findElement(By.linkText("abcd")).isDisplayed();

			if (linkExistence == true){
				System.out.println("Forgot Password Link Exists – Passed");
			}
		}
		catch(NoSuchElementException e){
			System.out.println("Forgot Password Link Not Exists – Failed");
		}
	}
	/*@Test
	public void verifyHomepageTitle() {
	String expectedTitle = "Parking Dashboard - Smart City";
	String actualTitle = driver.getTitle();
	Assert.assertEquals(actualTitle, expectedTitle);
	}*/
	@Test(priority=3)
	public void InsertCredentials() {
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys("admin@gmail.com");

		driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("123456");

		driver.findElement(By.xpath("/html/body/main/div/div/div/div/form/div/div[3]/button")).click();

		try {
			boolean linkExists = driver.findElement(By.linkText("Admin")).isDisplayed();

			if (linkExists == true) {
				System.out.println("Admin Login is Successful – Passed");
			}
		}
		catch (NoSuchElementException e1) {
			System.out.println("Admin Login is Unsuccessful – Failed");
		}
	}
	
	@Test(priority=4)
	public void ParkingDashboard() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.id("StartDate")).click();
		System.out.println("Select Start Date Correctlyt");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"EndDate\"]")).click();
		System.out.println("Select Ebd Date Successfully");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		driver.findElement(By.xpath("//*[@id=\"dvDeliverBlock\"]/div/div/div[3]/div/button")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		System.out.println("Press Search button for getting Data");
		
		
	}
	@Test(priority=5)
	public void createcountry() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/aside/ul/li[3]/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[1]/a")).click();
		Thread.sleep(5000);
		/*Here Check The Case of Text Box Null? */
		WebElement Ele = driver.findElement(By.xpath("//*[@id=\"CountryName\"]"));
		String Clob = Ele.getAttribute("value");
		if(Clob.isEmpty()) {
			System.out.println("Yes Text Box Is Clear");
		}else {
			System.out.println("No Text Box is not Clear");
		}
		Thread.sleep(5000);
		/*Here Check The Case of Text Box Null? */
		WebElement WB = driver.findElement(By.xpath("//*[@id=\"CountryName\"]"));
		String WB1 = WB.getAttribute("placeholder");
		if(WB1.equals("Please enter country name")){
			System.out.println("Yes Valid Place Holder");
		}else {
			System.out.println("No Invalid Place Holder");
		}
		Thread.sleep(8000);
		/* Here Insert The Values */
		WebElement Wb = driver.findElement(By.xpath("//*[@id=\"CountryName\"]"));
		Wb.sendKeys("America");
		Thread.sleep(5000);
		String txt = Wb.getAttribute("Value");
		System.out.println(txt);
		
		/*Now Save this */
		driver.findElement(By.xpath("//*[@id=\"btnsubmit\"]")).click();
		Thread.sleep(5000);
		System.out.println("Press submit button for Adding Country");		
	}
	@Test(priority=6)
	public void DeleteCountry() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/aside/ul/li[3]/a")).click();
		Thread.sleep(5000);
		/* CHECK DATA INTO TABLE */
		WebElement Table = driver.findElement(By.id("tblCountry"));
		List<WebElement> allRows = Table.findElements(By.tagName("tr"));
		for(WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for(WebElement cell : cells) {
				System.out.println("Content >>" + cell.getText());
			}
		}
		/*Delete */
		driver.findElement(By.xpath("//*[@id=\"tblCountry\"]/tbody/tr[3]/td[2]/div/span/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/button[2]")).click();
		Thread.sleep(3000);
	}
	
	/*@Test
	public void ErrorValidationmessage() {
		String message = driver.findElement(By.xpath("/html/body/main/div/div/div/div/form/div/span")).getText();

				if (message.contains("")) {
				System.out.println("User Registration is Successful – Passed So login successfully");
				}
				else {
				System.out.println("User Registration is Unsuccessful – Failed So Login Failed");
				}
	}*/
	@Test(priority=7)
	public void Logout_successfully() {
		
		driver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]")).click();
		
		driver.findElement(By.xpath("/html/body/header/div/div[2]/div/div[2]/a")).click();
		String url1 = driver.getCurrentUrl();
		//System.out.println(url1);

		if (url1.equals("http://smartcityqa.projectstatus.in/")) {
			System.out.println("After Log Out – Page is redirecting from admin to user interface – Passed");
		}
		else {
			System.out.println("After Log Out – Page is Not redirecting from admin to user interface – Failed");		}
	}

	/*@Test(priority=5)
	public void Report() {
		//Step1
		ExtentReports extent = new ExtentReports();
		//Step2
		ExtentSparkReporter spark =new ExtentSparkReporter("target/AdminPageReport.html");
		//step3
		spark.config().setReportName("Smart City Web Admin Login Page Report");
		spark.config().setDocumentTitle("Admin Login Page Report on QA Report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setEncoding("utf-8");
		//step4
		extent.attachReporter(spark);
		//step 5 create Test Node
		ExtentTest test=  extent.createTest("Open the Browser");
		test.log( Status.INFO, "Start launch Browser Verify the Test Case");//create Test Step Node
		test.log( Status.INFO, "Start Maximize Browser and Verify the Test Case");
		test.log( Status.INFO, "Browser Successfully Open and Maximized");
		test.log( Status.PASS, "Test Case Pass");
		Arrays.asList( new String[]{"Selenium" , "Appium", "Java"}).forEach(test::pass);
		test.pass(MarkupHelper.createLabel("Test Case Pass", ExtentColor.LIME));

		ExtentTest test1=  extent.createTest("Check Page URL").assignAuthor("Dotsquares Pvt. Ltd.").assignDevice("Chrome 107").assignCategory("Smoke");
		test1.log( Status.INFO, "Start Check Page URL Verify the Test Case");//create Test Step Node
		test1.log( Status.INFO, "Page URL matched");
		test1.log( Status.FAIL, "Page Url is not Matched so Fail");
		test1.log( Status.PASS, "Test Case Pass");

		ExtentTest test2=  extent.createTest("forgot password Link search");
		test2.log( Status.INFO, "Start Check forgot password Link search on page & Verify the Test Case");//create Test Step Node
		test2.log( Status.INFO, "forgot password Link search matched");
		test2.addScreenCaptureFromPath("screenshot.png");
		test2.log( Status.PASS, "Test Case Pass");

		ExtentTest test3=  extent.createTest("Insert Credentials for Login");
		test3.log( Status.INFO, "Start Check Inserted Credentials & Verify the Test Case");//create Test Step Node
		test3.log( Status.INFO, "Insert Credentials matched");
		test3.log( Status.FAIL, "InsertCredentials not Matched so Fail");
		test3.log( Status.INFO, "Corrct login(Details)");
		test3.log( Status.PASS, "Test Case Pass");
		//step6
		extent.flush();

	}*/
	

	@AfterTest
	public void terminateBrowser(){
		driver.close();
		System.out.println("Browser is Down successfully");
	}


}
