package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {

    static WebDriver driver;

    public static WebDriverWait wait;
    public static WebDriver getDriver(){
        if (driver==null)
        {   //multibrowser test hazırlanmak istenirse bu yapı epey pratik olacak
            switch (ConfigReader.getProperty("browser")){
                case "chrome":
                    driver=new ChromeDriver();
                    break;

                case "edge":
                    driver=new EdgeDriver();
                    break;

                case "firefox":
                    driver=new FirefoxDriver();
                    break;

                default:
                    driver=new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void closeDriver(){//tarayıcıyı kapatır, driver çalışmaya devam eder
        if (driver!=null) {
            driver.close();
            driver=null;
        }
    }

    public static void quitDriver(){//driver tamamen kapanır
        if (driver!=null) {
            driver.quit();
            driver=null;
        }
    }

}
