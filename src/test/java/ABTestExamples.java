import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;


public class ABTestExamples {
    @org.testng.annotations.Test
    public void firstTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        //1
        WebElement login=driver.findElement(By.xpath("//*[@id=\"userName\"]"));
        login.sendKeys("test123");
        WebElement password=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("Automation@123");

        WebElement logIn=driver.findElement(By.xpath("//*[@id=\"login\"]"));
        logIn.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"submit\"]")));
        WebElement text = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        System.out.println(text.getText());
        Assert.assertEquals(text.getText(),"Log out");

        //2
        WebElement goToStoreButton= driver.findElement(By.id("gotoStore"));
        Thread.sleep(20000);
        goToStoreButton.click();

        //if elements are loaded and are clickable
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"see-book-Git Pocket Guide\"]/a")));
        List<WebElement>  bookList = driver.findElements(By.className("action-buttons"));
        Assert.assertEquals(bookList.size(),8);

        //3
        //check if table data is loaded
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"see-book-Git Pocket Guide\"]/a"))).click();
        sleep(2000);
        WebElement bookTitle=driver.findElement(By.xpath("//*[@id=\"title-wrapper\"]/div[2]"));
        Assert.assertEquals(bookTitle.getText(),"Git Pocket Guide");

        //4
        //we have two buttons with similar id-es so we need to get one with text
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add To Your Collection']")));
        WebElement addToCollection = driver.findElement(By.xpath("//button[text()='Add To Your Collection']"));
        Thread.sleep(20000);
        addToCollection.click();

        //5
        //we have two buttons with similar id-es so we need to get one with text
        WebElement goBackToBookStore = driver.findElement(By.xpath("//button[text()='Back To Book Store']"));
        Thread.sleep(30000);
        goBackToBookStore.click();
        //if elements are loaded and are clickable
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"see-book-Git Pocket Guide\"]/a")));
        List<WebElement>  newBookList = driver.findElements(By.className("action-buttons"));
        Assert.assertEquals(newBookList.size(),8);

        //6
        WebElement logOut = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        Thread.sleep(30000);
        logOut.click();
    }
}
