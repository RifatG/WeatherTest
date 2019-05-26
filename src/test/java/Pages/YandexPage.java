package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private YandexGeoPage yandexGeoPage;

    @FindBy(className = "geolink__reg")
    private WebElement geolinkRegion;

    @FindBy(linkText = "yandex.ru/pogoda")
    private WebElement pogodaLink;

    public YandexPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }


    public void setGeolinkRegion(String region) {
        if(!geolinkRegion.getText().equals(region)) {
            geolinkRegion.click();
            wait.until(ExpectedConditions.urlContains("yandex.ru/tune/"));
            yandexGeoPage = PageFactory.initElements(driver, YandexGeoPage.class);
            yandexGeoPage.setRegion(region);
            wait.until(ExpectedConditions.visibilityOf(geolinkRegion));
        }
    }

    public YandexWeatherPage openWeatherPage() {
        driver.navigate().to("https://yandex.ru/pogoda");
        return PageFactory.initElements(driver, YandexWeatherPage.class);
    }
}
