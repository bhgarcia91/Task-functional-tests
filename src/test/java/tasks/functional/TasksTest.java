package tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    return driver;
    }


    @Test
    public void deveSalvarTarefaSucesso(){
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
    public void naoDeveSalvarSemDescricao(){
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
public void naoDeveSalvarSemData(){
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
    public void deveSalvarTarefaDataPassada(){
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
