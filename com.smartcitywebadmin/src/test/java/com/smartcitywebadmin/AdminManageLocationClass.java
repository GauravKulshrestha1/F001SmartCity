package com.smartcitywebadmin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminManageLocationClass {
	
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
		@Test(priority=1)
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
		@Test(priority=2)
		public void classlocationpage() {
			driver.findElement(By.xpath("/html/body/aside/ul/li[4]/a")).click();
		}
		
	}
