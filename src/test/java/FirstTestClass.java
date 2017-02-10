import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.util.concurrent.TimeUnit;

public class FirstTestClass {
    private WebDriver driver;
    private String userID;
    private String basicUrl;
    private String currentDriver;
    private String userName;
    private String userPassword;
    private  String driverPath;
    @Test
    public void main() {

        LoginClass loginObject = new LoginClass(driver);
        loginObject.moveToLoginPage();
        loginObject.feelUserNameField(userName);
        loginObject.feelPasswordField(userPassword);
        loginObject.loginSubmit();
        MyFormsClass myFormsObject = new MyFormsClass(driver,basicUrl+"en/forms.htm");
        userID = myFormsObject.GetUserId();
        //myFormsObject.AddNewDocument();
    }

    @BeforeMethod
    public void beforeMethod() {


        try {
            File fXmlFile = new File("src\\test\\java\\TestConfig.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            NodeList nList = doc.getElementsByTagName("mainConfig");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    currentDriver = eElement.getElementsByTagName("currentWebDriver").item(0).getTextContent();
                    System.out.println(currentDriver);
                    System.out.println(currentDriver.toCharArray()[0]);
                    switch (currentDriver.toCharArray()[0]){
                        case 'C':
                            driverPath = eElement.getElementsByTagName("chromePath").item(0).getTextContent();
                            System.setProperty("webdriver.chrome.driver", driverPath);
                            driver = new ChromeDriver();
                            break;
                        case 'F':
                            driverPath = eElement.getElementsByTagName("firefoxPath").item(0).getTextContent();
                            System.setProperty("firefoxdriver.chrome.driver", driverPath);
                            driver = new FirefoxDriver();
                            break;
                        case 'E':
                            driverPath = eElement.getElementsByTagName("explorerPath").item(0).getTextContent();
                            System.setProperty("internetexplorer.chrome.driver", driverPath);
                            driver = new InternetExplorerDriver();
                            break;
                        default:
                            System.setProperty("webdriver.chrome.driver", driverPath);
                            driverPath = eElement.getElementsByTagName("chromePath").item(0).getTextContent();
                            driver = new ChromeDriver();
                            break;

                    }

                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    basicUrl = eElement.getElementsByTagName("basicURL").item(0).getTextContent();
                    userName = eElement.getElementsByTagName("userName").item(0).getTextContent();
                    userPassword = eElement.getElementsByTagName("password").item(0).getTextContent();

                }
            }


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error while reading config");
        }


        driver.get(basicUrl);
    }

    @AfterMethod
    public void afterMethod() {

        // Close the driver

        driver.quit();

    }
}
