import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ChromeTest {
    public String username = "anubhavb";
    public String accesskey = "jfIpDzBoFpX3ZMxwDCOsz1b8qlvo8npozRHsLqpeifQoOwiLDK";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;

    @Parameters(value = {"version", "platform"})
    @BeforeClass
    public void setUp(String version, String platform) throws Exception {

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName(platform);
        browserOptions.setBrowserVersion(version);
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "anubhavb");
        ltOptions.put("accessKey", "jfIpDzBoFpX3ZMxwDCOsz1b8qlvo8npozRHsLqpeifQoOwiLDK");
        ltOptions.put("build", "AB_Demo");
        ltOptions.put("project", "Untitled");
        ltOptions.put("tunnel", true);
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        System.out.println("Capabilities Set");
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testSimple() throws Exception {
        try {//Check Locally Hosted Page
            driver.get("http://localhost:4173/");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/main/div[3]/article[1]/div/a")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/div[3]/div[4]/button[1]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[2]/div/div/a/span")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[4]/div/div[2]/button[2]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/a/button")).click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
        else {
            System.out.println("Driver is coming as NULL in Tear Down");
        }
    }
}