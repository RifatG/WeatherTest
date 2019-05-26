package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexGeoPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@name='auto']")
    private WebElement autoGeoCheckbox;

    @FindBy(id = "city__front-input")
    private WebElement regionInputField;

    public YandexGeoPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void setRegion(String region) {
        if (autoGeoCheckbox.isSelected()) autoGeoCheckbox.click();
        regionInputField.clear();
        regionInputField.sendKeys(region);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup__under")));
        regionInputField.sendKeys(Keys.ENTER);
    }
}
