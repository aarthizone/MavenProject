package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class test1 {
	@Test(priority = 2,dependsOnMethods = "MagentoRegister",enabled=false)
	public void MagentoLogin() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver(); //Invoke the browser
		WebDriverWait wait= new WebDriverWait(driver,30);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://magento.com/"); //Open the url
	   driver.findElement(By.xpath("//*[@id='gnav_509']/span/span/span")).click();
	   driver.findElement(By.id("email")).sendKeys("aarthizone@gmail.com");
	   driver.findElement(By.id("pass")).sendKeys("welcome");
	   driver.findElement(By.id("send2")).click();
	   //Thread.sleep(2000);
	   
	   wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div"), "Invalid login or password."));
	   
	   String actual_errMsg=driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
	   System.out.println("Actual Error Message" + actual_errMsg);
	   String exp_errMsg="Invalid login or password.";
	   System.out.println("Expected error message" + exp_errMsg);
	   if(actual_errMsg.equals(exp_errMsg)== true)
		   System.out.println("TestCase Passed");
	   else
		   System.out.println("TestCase Failed");
		driver.close(); //close the browser

	}
	@Test(priority=3)
	public void MagentoRegister() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://magento.com");
		driver.findElement(By.xpath("//*[@id='gnav_509']/span/span/span")).click();
		driver.findElement(By.id("register")).click();
		driver.findElement(By.id("firstname")).sendKeys("Arthi");
		driver.findElement(By.id("lastname")).sendKeys("sen");
		driver.findElement(By.id("email_address")).sendKeys("arthizone@gmail.com");
		driver.findElement(By.id("self_defined_company")).sendKeys("company");
		
		Select cp= new Select(driver.findElement(By.id("company_type")));
		//cp.selectByIndex(3);
		//cp.selectByValue("deployment");
		cp.selectByVisibleText("Provides deployment, customization and integration services to merchants");

		
		Select role= new Select(driver.findElement(By.id("individual_role")));
		role.selectByIndex(1);
			//role.selectByValue("Technical/developer");
	
		
		Select country= new Select(driver.findElement(By.id("country")));
		country.selectByIndex(15);
		//c3.selectByVisibleText("United States");
		
		
		
		//*[@id="recaptcha-anchor-label"]
		driver.findElement(By.id("password")).sendKeys("Welcome_1234");
		driver.findElement(By.name("password_confirmation")).sendKeys("Welcome_1234");
		
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
		driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).click();
		driver.switchTo().defaultContent();
		if(!driver.findElement(By.id("agree_terms")).isSelected())
			driver.findElement(By.id("agree_terms")).click();
		
		
		driver.close();

	}
}
