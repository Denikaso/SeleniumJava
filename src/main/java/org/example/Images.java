package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Images {
    private WebDriver driver;
    public Images(WebDriver driver){
        this.driver = driver;
    }

    public void execute() throws InterruptedException {
        driver.get("https://yandex.ru/images");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement searcTextBox = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[1]/form/div[1]/span/span/input"));
        wait.until(ExpectedConditions.elementToBeClickable(searcTextBox));
        searcTextBox.sendKeys("гугл");

        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[1]/form/div[2]/button/div[3]"));
        searchButton.click();

        wait.until(ExpectedConditions.urlContains("https://yandex.ru/images/search?text=%D0%B3%D1%83%D0%B3%D0%BB"));

        for (int i = 0; i < 8; i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            try {
                Thread.sleep(2000); // Подождать загрузки новых изображений
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //wait.until(ExpectedConditions.presenceOfElementLocated(By("SimpleImage-Image SimpleImage-Image_clickable")));
        List<WebElement> imageThumbnails = driver.findElements(By.cssSelector("img.SimpleImage-Image.SimpleImage-Image_clickable"));

        int count = 200;
        String destinationDirectory = "C:\\Уник\\Java\\SeleniumJava\\images\\";
        for (int i = 0; i < count; i++) {
            String imageUrl = imageThumbnails.get(i).getAttribute("src");

            // Скачиваем изображение
            downloadImage(imageUrl, destinationDirectory + i + ".jpg");
        }
    }
    public static void downloadImage(String imageUrl, String destination) {
        try {
            // Открываем поток изображения и копируем его в указанное место на диске
            InputStream in = new URL(imageUrl).openStream();
            Files.copy(in, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            in.close();
        } catch (IOException e) {
            // Обработка ошибок при скачивании
            e.printStackTrace();
        }
    }
}
