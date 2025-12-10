import com.epam.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void validateLoginUsingValidCredentials() {
        loginPage.login("standard_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(loginPage.getCurrentUrl(), expectedUrl);
    }

    @Test
    public void validateLoginUsingInvalidUsername() {
        loginPage.login("invalid_user","secret_sauce");
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }

    @Test
    public void validateLoginUsingInvalidPassword() {
        loginPage.login("standard_user","invalid_password");
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }

    @Test
    public void validateLoginUsingInvalidCredentials() {
        loginPage.login("invalid_user","invalid_password");
        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }

    @Test
    public void validateLoginUsingEmptyUsername() {
        loginPage.login("","secret_sauce");
        String expectedMessage = "Epic sadface: Username is required";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }

    @Test
    public void validateLoginUsingEmptyPassword() {
        loginPage.login("standard_user","");
        String expectedMessage = "Epic sadface: Password is required";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }

    @Test
    public void validateLoginUsingEmptyCredentials() {
        loginPage.login("","");
        String expectedMessage = "Epic sadface: Username is required ";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
