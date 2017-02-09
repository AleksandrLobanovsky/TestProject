import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    public WebDriver driver;
    public String userID;
    public String basicUrl ="https://dev3.pdffiller.com/";
    public String currentDriver;
    @Test
    public void main() {

        LoginClass loginObject = new LoginClass(driver);
        loginObject.moveToLoginPage();
        loginObject.feelUserNameField("lobanovsky.a.o@gmail.com");
        loginObject.feelPasswordField("Test1234");
        loginObject.loginSubmit();
        MyFormsClass myFormsObject = new MyFormsClass(driver,basicUrl+"en/forms.htm");
        userID = myFormsObject.GetUserId();
        //myFormsObject.AddNewDocument();
    }

    @BeforeMethod
    public void beforeMethod() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alobanovskiy\\Documents\\Visual Studio 2015\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(basicUrl);
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
                    //System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());

                }
            }


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error while reading config");
        }

    }

    @AfterMethod
    public void afterMethod() {

        // Close the driver

        driver.quit();

    }
}
