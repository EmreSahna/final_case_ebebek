import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SeleniumTest
{
    @Test
    public void firstProductMustBeFirstItemSearchedInBasket(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.e-bebek.com/");
        WebElement element = driver.findElement(By.id("txtSearchBox"));
        element.sendKeys("biberon" + Keys.ENTER);
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlToBe("https://www.e-bebek.com/search?text=biberon"));
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.className("product-item")));
        List<WebElement> elements = driver.findElements(By.cssSelector("a.product-item-anchor"));
        String firstProduct = elements.get(0).getAttribute("href");
        driver.navigate().to(elements.get(0).getAttribute("href"));
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("addToCartBtn")));
        driver.findElement(By.id("addToCartBtn")).submit();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("btnShowCart")));
        driver.findElement(By.id("btnShowCart")).click();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cx-link")));
        List<WebElement> basketElements = driver.findElements(By.className("cx-link"));
        for (WebElement item:basketElements) {
            if(item.getAttribute("href").equals(firstProduct)){
                assertEquals(item.getAttribute("href"),firstProduct);
            }
        }
        driver.quit();

    }
}
