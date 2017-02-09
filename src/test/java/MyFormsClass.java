import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MyFormsClass {

    private By accountButton = By.cssSelector("div.h-accautWrap");
    private By accountId = By.cssSelector("dd");
    private By addNewDocButton = By.cssSelector(".g-btn.g-btn-auto-width.g-btn-primary.mf-nav-action-btn.mf-btn-add-doc");
    private By orrangeButtons = By.cssSelector(".btn.-orange");
    private By downloadMyDoc = By.id("fileupload");
    private By pageOnThumbnails = By.cssSelector(".jsf-left-sb-editor-item__inner");
    public WebDriver driver;

    public MyFormsClass(WebDriver driver, String UrlToVerify){
        this.driver = driver;

        System.out.println(driver.getCurrentUrl());
        if (!UrlToVerify.equals(driver.getCurrentUrl())) {
            throw new IllegalStateException("This is not my forms");
        }

    }

    public String GetUserId(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountButton));
        driver.findElement(accountButton).click();
        String userID = driver.findElement(accountId).getText();
        driver.findElement(accountButton).click();
        return userID;
    }

    public MyFormsClass AddNewDocument(){

        driver.get("https://dev3.pdffiller.com/en/forms.htm?newforms=1");
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountButton));
        driver.findElement(addNewDocButton).click();
        driver.findElement(downloadMyDoc).sendKeys("C://test.pdf");
        wait.until(ExpectedConditions.elementToBeClickable(pageOnThumbnails));
        System.out.println(driver.getCurrentUrl());
        //Match match = Regex.Match(driver.getCurrentUrl(), "projectId=(?<pId>\\d+)");
        //documentID = match.Groups[0].Value;
        //Console.WriteLine(match.Groups[0].Value);
        return this;

    }

}
