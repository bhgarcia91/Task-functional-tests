package tasks.prod;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class HealthCheckIT {

    @Test
    public void healthCheck() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.23:4444"), options);
        try{
            driver.navigate().to("http://192.168.0.23:9999/tasks");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            String version = driver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build"));
        }
        finally {
            driver.quit();
        }
    }
}
