package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Otzovik {
    private WebDriver driver;
    public Otzovik(WebDriver driver) {
        this.driver = driver;
    }

    public void execute(){
        driver.get("https://otzovik.com");

        WebElement postReviewButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/a[2]"));
        postReviewButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://otzovik.com/postreview.php"));

        WebElement productInput = driver.findElement(By.id("tproduct"));
        productInput.sendKeys("Фильм \"Проклятие Дудочника\" (2023)");

        wait.until(ExpectedConditions.textToBePresentInElementValue(productInput, "Фильм \"Проклятие Дудочника\" (2023)"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement onwardButton = driver.findElement(By.id("noproduct"));
        onwardButton.click();

        wait.until(ExpectedConditions.urlContains("https://otzovik.com/postreview.php?pid=2311284&"));

        Select selectRating = new Select(driver.findElement(By.id("rating0")));
        selectRating.selectByValue("4");

        WebElement contentTitle = driver.findElement(By.id("content_title"));
        contentTitle.sendKeys("Неплохой фильм, поднимающий актуальные проблемы");

        WebElement editorContent = driver.findElement(By.xpath("//*[@id=\"content_body_main\"]/div[2]/div[2]/div/textarea[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(editorContent));
        editorContent.sendKeys("Если честно, фильм я даже не смотрел. Отзыв я пишу для выполнения задания лабораторной работы " +
                "в курсе программирования на Java. Но, судя по названию и году выхода, фильм " +
                "про куренией вейпов и вред от них, а пропоганду ЗОЖ мы одобряем");

        WebElement contentPros = driver.findElement(By.xpath("//*[@id=\"review_post\"]/div[4]/div[2]/textarea"));
        contentPros.sendKeys("Интригующее название)");

        WebElement contentCons = driver.findElement(By.xpath("//*[@id=\"review_post\"]/div[5]/div[2]/textarea"));
        contentCons.sendKeys("Интригующее название(");

        WebElement recommendButton = driver.findElement(By.xpath("//*[@id=\"review_post\"]/div[6]/div[1]/div[2]/label[1]/span"));
        recommendButton.click();

        WebElement previewButton = driver.findElement(By.id("previewbtn"));
        previewButton.click();
    }
}
