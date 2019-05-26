import Pages.GismeteoPage;
import Pages.YandexPage;
import Pages.YandexWeatherPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherTest extends WebdriverSettings {

    private String region = "Уфа";

    private YandexPage yandexPage;
    private YandexWeatherPage yandexWeatherPage;
    private GismeteoPage gismeteoPage;


    @Test
    public void compareWeathers() {
        yandexPage = PageFactory.initElements(driver, YandexPage.class);
        yandexPage.setGeolinkRegion(region);
        yandexWeatherPage = yandexPage.openWeatherPage();
        int[] yandexTemps = yandexWeatherPage.getMassiveOfTemps();

        driver.get("https://www.gismeteo.ru");
        gismeteoPage = PageFactory.initElements(driver, GismeteoPage.class);
        gismeteoPage.setGeolinkRegion(region);
        int[] gismeteoTemps = gismeteoPage.getMassiveOfTemps();

        Assert.assertEquals(yandexTemps, gismeteoTemps);
    }
}
