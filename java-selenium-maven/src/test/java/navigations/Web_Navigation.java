package navigations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeOptions;

public class Web_Navigation {
	
	public static WebDriver driver;
	public static Actions action;
	public static ChromeOptions option;
	
	public void home_page() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
		//driver.manage().window().maximize();

		option = new ChromeOptions();
		option.addArguments("disable-infobars");
		option.addArguments("--start-maximized");

		driver = new ChromeDriver(option);
		action = new Actions(driver);
	
		try {	
			
			driver.get("https://amazon.com.au");
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);	

			String homepage_title = driver.getTitle(); 
			assertEquals(homepage_title,"Amazon.com.au: Shop online for Electronics, Apparel, Toys, Books, DVDs & more");
			return;
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}


	public void navigate_user_login() {
		
		WebElement menuSignin = driver.findElement(By.id("nav-link-yourAccount"));
		
		try {
			action.moveToElement(menuSignin).build().perform();
			WebElement linkSignin = driver.findElement(By.className("nav-action-inner"));
			assertTrue(linkSignin.isDisplayed());
			
			linkSignin.click();
			
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);	
			WebElement txtboxEmail = driver.findElement(By.id("ap_email"));
			assertTrue(txtboxEmail.isDisplayed());

			return;
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public void navigate_kindle() {
		Boolean submenuKindleDisplay = false;
		
		WebElement menuShopAll = driver.findElement(By.id("nav-link-shopall"));
		action.moveToElement(menuShopAll).build().perform();

		WebElement smnuKindleEReaderBooks = driver.findElement(By.xpath("//span[@class='nav-text' and contains(text(),'Kindle E-readers & Books')]"));
		submenuKindleDisplay = smnuKindleEReaderBooks.isDisplayed();
		
		if (submenuKindleDisplay == true) {
			
			action.moveToElement(smnuKindleEReaderBooks).build().perform();

			WebElement smnuEKindle = driver.findElement(By.xpath("//span[@class='nav-subtext' and contains(text(),'All book. No glare. Zero distractions.')]"));
			assertTrue(smnuEKindle.isDisplayed());
			action.moveToElement(smnuEKindle).click().build().perform();
			
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);	
			
			String pageTitle = driver.getTitle();
			assertEquals(pageTitle, "Kindle E-reader – Amazon Official Site");
		}
		
		
	}
	
	public void kill_browser() {
		driver.close();
	}

}
