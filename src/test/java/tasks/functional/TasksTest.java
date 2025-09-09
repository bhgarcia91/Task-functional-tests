package tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.DesiredCapabilities.*;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
     //   WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.23:4444"), options);
        driver.navigate().to("http://192.168.0.23:8001/tasks");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    return driver;
    }


    @Test
    public void deveSalvarTarefaSucesso() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {

            //Clicar no todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a Descrição
            driver.findElement(By.id("Task")).sendKeys("Teste UI Selenium");
            //Escrever a Data
            driver.findElement(By.id("dueDate")).sendKeys("20/12/2025");

            //Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            //Verifica Mensagem
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        }
        finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarSemDescricao() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {

            //Clicar no todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a Data
            driver.findElement(By.id("dueDate")).sendKeys("20/12/2025");

            //Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            //Verifica Mensagem
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);
        }
        finally {
            driver.quit();
        }
    }

@Test
public void naoDeveSalvarSemData() throws MalformedURLException {
    WebDriver driver = acessarAplicacao();
    try {
        //Clicar no todo
        driver.findElement(By.id("addTodo")).click();

        //Escrever a Descrição
        driver.findElement(By.id("Task")).sendKeys("Teste UI Selenium");

        //Clicar em Salvar
        driver.findElement(By.id("saveButton")).click();

        //Verifica Mensagem
        String message = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the due date", message);
    }
    finally {
        driver.quit();
    }
}

    @Test
    public void deveSalvarTarefaDataPassada() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();
        try {

            //Clicar no todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a Descrição
            driver.findElement(By.id("Task")).sendKeys("Teste UI Selenium");
            //Escrever a Data
            driver.findElement(By.id("dueDate")).sendKeys("20/12/2024");

            //Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            //Verifica Mensagem
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        }
        finally {
            driver.quit();
        }
    }


}
