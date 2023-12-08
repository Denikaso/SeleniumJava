package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\danii\\Downloads\\msedgedriver.exe");
        // Инициализация WebDriver
        WebDriver driver = new EdgeDriver();

        try {
            // Создание экземпляра BusStopsTest
            BusStopsTest busStopsTest = new BusStopsTest(driver);

            // Вызов метода execute
            busStopsTest.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Вы можете добавить здесь код для дополнительных действий или просто пропустить этот блок
    }
}