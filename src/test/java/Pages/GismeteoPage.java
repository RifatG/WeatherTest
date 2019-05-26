package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GismeteoPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "float_left")
    private WebElement geolinkRegion;

    @FindBy(className = "float_right")
    private WebElement changeRegion;

    @FindBy(xpath = "//a[contains(text(), '10 дней')]")
    private WebElement tempFor10DaysButton;

    public GismeteoPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void setGeolinkRegion(String region) {
        if(!geolinkRegion.getText().equals(region)) {
            changeRegion.click();
            wait.until(ExpectedConditions.urlContains("current-location"));
            WebElement regionInputField = driver.findElement(By.id("js-search"));
            regionInputField.click();
            regionInputField.sendKeys(Keys.chord(region));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("found__header")));
            regionInputField.sendKeys(Keys.ENTER);
        }
    }

    public int[] getMassiveOfTemps() {
        tempFor10DaysButton.click();
        wait.until(ExpectedConditions.urlContains("10-days"));
        WebElement tempFor10Days = driver.findElement(By.className("w_temperature")) ;
        List<WebElement> tempElements = tempFor10Days.findElements(By.className("unit_temperature_c"));
        int[] temps = new int[20];
        for (int i = 0; i < temps.length; i++)
            temps[i] = Integer.parseInt(tempElements.get(i).getText());
        return temps;
    }
}
