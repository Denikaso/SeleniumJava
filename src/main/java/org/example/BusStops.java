package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BusStops {
    private WebDriver driver;

    public BusStops(WebDriver driver) {
        this.driver = driver;
    }

    public void execute() {
        // Шаг 1: Открытие сайта
        driver.get("https://m.cdsvyatka.com/");

        // Находим выпадающий список маршрутов
        Select routeDropdown = new Select(driver.findElement(By.id("marshlist")));

        // Выбираем нужный маршрут (например, 23 автобус)
        routeDropdown.selectByValue("1023");

        // Находим кнопку "Найти" внутри формы
        WebElement searchButton = driver.findElement(By.xpath("//form[@id='marshSearch']//input[@value='Найти']"));

        // Нажимаем кнопку для выполнения поиска
        searchButton.click();

        // Ожидание загрузки новой страницы (например, по заголовку)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Остановки на маршруте"));

        // Шаг 2: Получение списка остановок
        List<WebElement> stopElements = driver.findElements(By.xpath("//a[contains(@href, 'prediction.php?busstop=')]"));

        // Используем множество для хранения уникальных значений
        Set<String> uniqueStops = new HashSet<>();

        for (WebElement stopElement : stopElements) {
            uniqueStops.add(stopElement.getText());
        }

        // Выводим уникальные остановки в консоль
        System.out.println("Список остановок:");
        for (String uniqueStop : uniqueStops) {
            System.out.println(uniqueStop);
        }
    }
}
