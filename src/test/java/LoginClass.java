import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginClass {

    private By mainPageMenu = By.cssSelector("a.h-nav__link");
    private By loginField = By.id("form-login-email");
    private By passwordField = By.id("form-login-password");
    private By loginButton = By.id("form-login-submit");

    public WebDriver driver;

    public LoginClass(WebDriver driver) {
        this.driver = driver;

        System.out.println(driver.getTitle());
        if (!"PDFfiller. On-line PDF form Filler, Editor, Type on PDF ; Fill, Print, Email, Fax and Export".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the login page");
        }
    }


    public LoginClass feelUserNameField(String username) {
        driver.findElement(loginField).sendKeys(username);
        return this;
    }


    public LoginClass moveToLoginPage()
    {
        this.driver = driver;
        driver.findElements(mainPageMenu).get(3).click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return this;
    }

    public LoginClass feelPasswordField(String password)
    {
        this.driver = driver;
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginClass loginSubmit()
    {
        this.driver = driver;
        driver.findElement(loginButton).click();
        return this;
    }
/*
    @Test
    public void main() {

        // Find the element that's ID attribute is 'user_login' (Username)

        // Enter Username on the element found by above desc.

        driver.findElement(By.id("user_login")).sendKeys("subscriber");

        // Find the element that's ID attribute is 'user_pass' (Password)

        // Enter Password on the element found by the above desc.

        driver.findElement(By.id("user_pass")).sendKeys("2016subscriberpasssword2016ok");

        // Now submit the form. WebDriver will find the form for us from the element

        driver.findElement(By.id("wp-submit")).click();

        if (driver.findElement(By.id("profile-page")).isDisplayed())
        {
            // Print a Log In message to the screen
            System.out.println("Login successfully.");
        }
    }

    @BeforeMethod
    public void beforeMethod() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alobanovskiy\\Documents\\Visual Studio 2015\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://dev3.pdffiller.com/en/forms.htm");

    }

    @AfterMethod
    public void afterMethod() {

        // Close the driver

        driver.quit();

    }
*/
}