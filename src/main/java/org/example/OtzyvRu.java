package org.example;

import lombok.Data;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class OtzyvRu {
    private static final String SITE = "https://otzyv.ru/";
    private final WebDriver driver;
    public OtzyvRu(WebDriver driver){this.driver = driver;}

    public void execute() {
        driver.get(SITE);

        val forumLink = driver.findElement(By.xpath("//a[text()='Форумы']"));
        forumLink.click();

        val wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://forum.otzyv.ru/allforums.php"));

        val searchInput = driver.findElement(By.id("s"));
        searchInput.sendKeys("Россия");

        val selectSubforum = new Select(driver.findElement(By.xpath("//select[@name = 'f']")));
        selectSubforum.selectByValue("57");

        val onlyThemeCheckBox = driver.findElement(By.xpath("//input[@name = 'onlytheme']"));
        onlyThemeCheckBox.click();

        val searchButton = driver.findElement(By.xpath("//*[@id=\"mainCol\"]/div[4]/form/div[8]/input"));
        searchButton.click();
    }
}
