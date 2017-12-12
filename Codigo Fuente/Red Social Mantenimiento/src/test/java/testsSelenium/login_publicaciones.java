package testsSelenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class login_publicaciones {
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
  public void testIntravitaMLoginPCambioPass() throws Exception {
    try {
    	driver.get(baseUrl);
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("Miguel.Alvarez@alu.uclm.es");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("1234asdf");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();	    	
    	//driver.findElement(By.cssSelector("div.recaptcha-checkbox-checkmark")).click();
	    fail();
	    
	    //El siguiente codigo es para cuando no habia captcha
	    /*
        assertEquals("Actualizar Contraseña - IntraVita", driver.getTitle());
	    driver.findElement(By.name("txtUsuarioClave")).clear();
	    driver.findElement(By.name("txtUsuarioClave")).sendKeys("1234asdf");
	    driver.findElement(By.name("txtUsuarioClave1")).clear();
	    driver.findElement(By.name("txtUsuarioClave1")).sendKeys("1234asdf");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.id("perfil-user")).click();
	    assertEquals("Nombre: Miguel.Alvarez", driver.findElement(By.cssSelector("label")).getText());
	    assertEquals("Email: Miguel.Alvarez@alu.uclm.es", driver.findElement(By.xpath("//li[2]/label")).getText());
	    driver.findElement(By.cssSelector("aside > div > div.col-md-12")).click();
	    assertEquals("Intravita", driver.getTitle());
	    driver.findElement(By.linkText("Publicaciones")).click();
	    driver.findElement(By.name("mensaje")).clear();
	    driver.findElement(By.name("mensaje")).sendKeys("Hola.");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    assertEquals("Hola.", driver.findElement(By.cssSelector("p")).getText());
	    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	    assertEquals("(1)", driver.findElement(By.cssSelector("button.btn.btn-default")).getText());
	    driver.findElement(By.xpath("//div/a/i")).click();
	    assertEquals("Editar publicación", driver.findElement(By.cssSelector("h4")).getText());
	    driver.findElement(By.name("update-mensaje")).clear();
	    driver.findElement(By.name("update-mensaje")).sendKeys("Hola. Adios.");
	    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    //assertTrue(closeAlertAndGetItsText().matches("^¿Estás seguro de eliminar la publicación[\\s\\S]$"));
	    driver.findElement(By.linkText("Salir")).click(); 
	    */
    }catch(Error e){ 
    	verificationErrors.append(e.toString());    	
    }
  }
  
  @After
  public void tearDown() throws Exception{
	  driver.quit();
  }

  /*@After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }*/

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
