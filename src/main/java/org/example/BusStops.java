package org.example;

import lombok.Data;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Data
public class BusStops {
    private static final String SITE =  "https://m.cdsvyatka.com/";
    private final WebDriver driver;
    private String route;

    public BusStops(WebDriver driver, String route){
        this.driver =driver;
        this.route = route;
    }

    public void Execute() {
        driver.get(SITE);
        route = HandleRoute(route);

        val routeDropdown = new Select(driver.findElement(By.id("marshlist")));
        routeDropdown.selectByValue(route);

        val searchButton = driver.findElement(By.xpath("//form[@id='marshSearch']//input[@value='Найти']"));
        searchButton.click();

        val wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Остановки на маршруте"));
    }
    public String HandleRoute(String route) {
        if (route.matches("Авт \\d{1,2}")) {
            return "10" + route.substring(4);
        } else if (route.matches("Авт \\d{1,3}")) {
            return "3" + route.substring(4);
        } else if (route.matches("Тролл Т\\d{1,2}")) {
            return "500" + route.substring(7, 8) + "0";
        }
        return null;
    }
}
