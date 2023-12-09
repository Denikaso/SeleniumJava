package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtzyvRu {
    private WebDriver driver;
    public OtzyvRu(WebDriver driver){this.driver = driver;}

    public void execute() {

        // Шаг 1: Открытие сайта https://otzyv.ru/
        driver.get("https://otzyv.ru/");

        // Шаг 2: Переход на страницу Форумы
        WebElement forumLink = driver.findElement(By.xpath("//a[text()='Форумы']"));
        forumLink.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://forum.otzyv.ru/allforums.php"));

        WebElement searchInput = driver.findElement(By.id("s"));
        searchInput.sendKeys("Россия");

        Select selectSubforum = new Select(driver.findElement(By.xpath("//select[@name = 'f']")));
        selectSubforum.selectByValue("57");

        WebElement onlyThemeCheckBox = driver.findElement(By.xpath("//input[@name = 'onlytheme']"));
        onlyThemeCheckBox.click();

        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"mainCol\"]/div[4]/form/div[8]/input"));
        searchButton.click();
    }

}
