package com.stepdef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.newpagefactory.PageFactoryLoginXpath;
import com.util.Highlighter;
import com.util.ScreenShots;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefForAmazon {

	WebDriver driver;
	PageFactoryLoginXpath obj;
	Highlighter color;

	@Given("User is able to launch any browser")
	public void user_is_able_to_launch_any_browser() {
		// Operating System
		String os = System.getProperty("os.name").toLowerCase();

		// Windows driver path
		String winPath = System.getProperty("user.dir");
		String winDriverPath = winPath + "\\Driver\\chromedriver.exe";

		// Mac driver path
		String macPath = System.getProperty("user.home");
		String macDriverPath = macPath + "/chromedriver/chromedriver";

		// Tell Eclipse which driver to use depending on OS
		if (os.contains("mac")) {
			System.setProperty("webdriver.chrome.driver", macDriverPath);
		} else {
			System.setProperty("webdriver.chrome.driver", winDriverPath);
		}
		driver = new ChromeDriver();
		obj = PageFactory.initElements(driver, PageFactoryLoginXpath.class);
		color = new Highlighter(driver);
		driver.manage().window().maximize();
	}

	@Given("User is able to navigate to the url")
	public void user_is_able_to_navigate_to_the_url() {
		driver.navigate().to(obj.getURL());
	}

	@When("User clicks on Account & List")
	public void user_clicks_on_Account_List() {
		color.drawBorder(obj.getMyAccount(), "green");
		ScreenShots.captureScreenShot(driver, "LoginPage");
		obj.getMyAccount().click();
	}

	@When("User enters userName")
	public void user_enters_userName() {
		color.drawBorder(obj.getEmailAddress(), "red");
		obj.getEmailAddress().sendKeys(obj.getUserName());
	}

	@When("User enters password")
	public void user_enters_password() {
		color.drawBorder(obj.getPassword(), "blue");
		obj.getPassword().sendKeys(obj.getPass());
	}

	@When("User clicks on SignIn button")
	public void user_clicks_on_SignIn_button() {
		color.drawBorder(obj.getSignIn(), "cyan");
		ScreenShots.captureScreenShot(driver, "Screenshot Email and password input fields");
		obj.getSignIn().click();
		System.out.println("Logged into my account");
	}

	@Then("user should be able to see {string}")
	public void user_should_be_able_to_see(String string) {
		color.drawBorder(obj.getUserNameAfterLogin(), "pink");
		if (obj.getUserNameAfterLogin().getText().equalsIgnoreCase("Hi, Syed")) {
			System.out.println("Login Verification Passed");
		} else {
			System.out.println("Login Varification Failed");
		}
		ScreenShots.captureScreenShot(driver, "Login Verification");
	}

	@Then("user should be able to close the browser")
	public void user_should_be_able_to_close_the_browser() {
		driver.quit();
	}

}
