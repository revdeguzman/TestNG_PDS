package base.files;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseFiles {
	
	public static WebDriver driver;
	public static Properties prop = new Properties();
	
	@BeforeTest
	public void startBrowser() throws IOException, InterruptedException {
		
		if(driver == null) {
			
			//Create new object that locate properties file
			FileReader file1 = new FileReader(System.getProperty("user.dir") + "\\src\\resources\\config\\config.properties");
			
			//Read Files
			prop.load(file1);
						
			//Set up Chrome Driver
			WebDriverManager.chromedriver().setup();
			
			//Create Chrome Object
			driver = new ChromeDriver();
			
			//Maximize Browser
			driver.manage().window().maximize();
			
			//Delete Cookies from the browser.
			driver.manage().deleteAllCookies();

			//Set URL to the Browser
			driver.get(prop.getProperty("testurl"));
			
			//Click Advanced
			driver.findElement(By.xpath("//*[@id=\"details-button\"]")).click();
			
			//Click URL
			driver.findElement(By.xpath("//*[@id=\"proceed-link\"]")).click();
			
			//Username Field
			driver.findElement(By.name("input_user")).sendKeys("qc_pds");
			
			//Password Field
			driver.findElement(By.name("input_pass")).sendKeys("P@ssw0rd123");
			
			//Submit Button
			driver.findElement(By.name("submit_login")).click();
			
			//Wait
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logo")));
			
		}
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		
		if(driver != null) {
			
		//Logout Button
		driver.findElement(By.className("dropdown-toggle")).click();
		
		//Click Logout
		driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/span[1]/ul/li/ul/li[3]/a")).click();
		
		Thread.sleep(5000);

		//Close Browser
		driver.close();
		
		//Quit the Drivers
		driver.quit();
		
		} else {
			
			System.out.println("Driver is Null");
			System.out.println("Value of Driver is = " + driver);
			
		}
		
	}
	
}