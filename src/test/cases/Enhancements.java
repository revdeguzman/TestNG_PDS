package test.cases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.files.BaseFiles;
import base.files.Screenshot;

public class Enhancements extends BaseFiles {

	@Test(priority = 1)
	public void AddUser() throws InterruptedException, IOException {
		
		//Click User Module
		driver.findElement(By.xpath("//*[@id=\"main-menu\"]/li[2]/ul/li[3]/a")).click();
		
		//Add Screenshot
		Screenshot screenshot = new Screenshot();
		String folderPath = System.getProperty("user.dir") + "\\Screenshots\\";
		
		//Wait for Elements
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		//Wait for the Element to Load
		WebElement usersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='index.php?menu=userlist']/span[text()='Users']")));
		
		//Click the Element
		usersLink.click();

		//Wait if Buttons appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idformgrid\"]/div[1]/a/div/button")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idformgrid\"]/div[1]/div[1]/button")));
		System.out.println("Buttons are displayed.");
		
		//Create New User
		driver.findElement(By.xpath("//*[@id=\"idformgrid\"]/div[1]/a/div/button")).click();
		
		//Validate Module
		String description = driver.findElement(By.xpath("//*[@id=\"neo-contentbox-maincolumn\"]/div/form/table/tbody/tr[2]/td/fieldset[1]/legend/b")).getText();
		Assert.assertEquals(description, "Main Fields");
		
		System.out.println("Module Header is: " + description);
		
		//Login Field
		driver.findElement(By.name("name")).sendKeys("QMDTEST");
		
		//Password Field
		driver.findElement(By.name("password1")).sendKeys("P@ssw0rd");
		
		//Expiration Date Field
		WebElement expDate = driver.findElement(By.name("expiration"));
		
		String expDateValue = expDate.getAttribute("value");
		
		if (!expDateValue.isEmpty()) {
			expDate.clear();
			expDate.sendKeys("30 Nov 2024");
		} else {
			System.out.println("Field is already empty.");
		}
			
		//Group Field
		WebElement dropdownElement = driver.findElement(By.id("group"));
		
		//Create Group Dropdown Object
		Select GroupDropdown = new Select(dropdownElement);
		
		//Select Value from dropdown
		GroupDropdown.selectByValue("1");
		
		//Name Field
		driver.findElement(By.name("description")).sendKeys("John Doe");
		
		//Retype Password Field
		driver.findElement(By.name("password2")).sendKeys("P@ssw0rd");
		
		//Extension Field
		WebElement extensionDropdown = driver.findElement(By.name("extension"));
		
		//Create Extension Dropdown
		Select ExtensionField = new Select(extensionDropdown);
		
		//Select Value from Dropdown
		ExtensionField.selectByValue("9006");
		
		//Webmail User Field
		driver.findElement(By.name("webmailuser")).sendKeys("QMDTEST");
		
		//Webmail Password Field
		driver.findElement(By.name("webmailpassword1")).sendKeys("P@ssw0rd");
		
		//Webmain Domain Field
		driver.findElement(By.name("webmaildomain")).sendKeys("Test");
		
		//Screenshot
		screenshot.FullPageScreenShot(folderPath);
		
		//Save Button
		driver.findElement(By.name("save")).click();
		
		//Screenshot
		screenshot.FullPageScreenShot(folderPath);
		
	}
	
}
