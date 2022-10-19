import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class WaitsAndSelectors {

    WebDriver driver;

    public WaitsAndSelectors() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void firstTest()  {
        driver.get("https://demoqa.com/progress-bar");
        driver.manage().window().maximize();

        // Click to 'Start' button
        // Wait until progress bar reach 100 % and print '100%'
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"startStopButton\"]"));
        submitButton.click();
        // as soon ass progress bar is loaded button named 'start' changes content and became 'reset'
        // related xpath is also changing as a result, in this code below we are waiting for loading
        // reset button
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"resetButton\"]")));
        WebElement text = driver.findElement(By.xpath("//*[@id=\"progressBar\"]/div"));
        System.out.println(text.getText());
        Assert.assertEquals(text.getText(),"100%");
    }
    @Test
    public void secondTest(){
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        driver.manage().window().maximize();
        //select one language from dropdown
        WebElement dropDowContent= driver.findElement(By.xpath(("//*[@id=\"dropdowm-menu-1\"]/option[2]")));
        Select select = new Select(driver.findElement(By.id("dropdowm-menu-1")));
        select.selectByVisibleText(dropDowContent.getText());
        select.selectByIndex(1);
        System.out.println("selected language: "+select.getFirstSelectedOption().getText());
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"C#");

    }

    @Test
    public void thirdTest(){
        //Click on all unselected checkboxes
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        driver.manage().window().maximize();
        //select unchecked checkboxes
        List<WebElement> checkboxList=driver.findElements(By.cssSelector("input:not(:checked)[type='checkbox']"));
        for (WebElement element : checkboxList) {
            //if checkbox is not checked, this is not needed
            //because we have selected only unchecked elements
            if(element.getAttribute("checked")==null){
                element.click();
            }
        }
    }
    @Test
    public void fourthTest(){
        //Click on 'Purple' radio button
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        driver.manage().window().maximize();
        /*List<WebElement> radioButtonList=driver.findElements(By.id("radio-buttons"));

        for (WebElement element : radioButtonList) {
            if (element.getText() == "Purple") {

            }
        }*/

        WebElement radioButton = driver.findElement(By.cssSelector("input[value='purple']"));
        radioButton.click();
    }

    @Test
    public void fifthTest(){
        //In 'Selected & Disabled' section check that 'Orange' option in dropdown is disabled
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        driver.manage().window().maximize();
        //get orange's element and check if it is disabled
        WebElement dropDowContent= driver.findElement(By.xpath(("//*[@id=\"fruit-selects\"]/option[2]")));
        Assert.assertEquals(dropDowContent.isEnabled(),false);
    }



}
