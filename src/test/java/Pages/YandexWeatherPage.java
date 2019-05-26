package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class YandexWeatherPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "forecast-briefly__days")
    private WebElement tempFor10Days;

    public YandexWeatherPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public int[] getMassiveOfTemps() {
        wait.until(ExpectedConditions.visibilityOf(tempFor10Days));
        List<WebElement> tempElements = tempFor10Days.findElements(By.className("temp__value"));
        int[] temps = new int[20];
        for (int i = 0; i < temps.length; i++)
            temps[i] = Integer.parseInt(tempElements.get(i).getText());
        return temps;
    }
}
