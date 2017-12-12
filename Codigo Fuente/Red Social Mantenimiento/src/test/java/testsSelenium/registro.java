package testsSelenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class registro {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  baseUrl = "https://intravita-g1-maintenance-g4.herokuapp.com/";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testIntravitaMRegP() throws Exception {
    try {
		driver.get(baseUrl);
	    driver.findElement(By.id("register-form-link")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("Miguel.Alvar");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("Miguel.Alvar@alu.uclm.es");
	    driver.findElement(By.id("password-register")).clear();
	    driver.findElement(By.id("password-register")).sendKeys("1234qwerty");
	    driver.findElement(By.id("confirm-password")).clear();
	    driver.findElement(By.id("confirm-password")).sendKeys("1234qwerty");
	    
	    driver.findElement(By.id("register-submit")).click();
	    
    }catch(Error e) {
    	System.out.println("No se puede realizar el captcha");
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
