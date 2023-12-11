package org.example;

import lombok.Data;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
public class Otzovik {
    private static final String SITE = "https://otzovik.com/";
    private final WebDriver driver;
    private final Path inputPath;
    private List<String> data;
    public Otzovik(WebDriver driver, String path) {
        this.driver = driver;
        this.inputPath = Paths.get(path);
    }

    public void Execute(){
        driver.get(SITE);

        data = GetData(inputPath);
        val product = data.get(0);
        val rating = data.get(1);
        val reviewTitle = data.get(2);
        val review = data.get(3);
        val pros = data.get(4);
        val cons = data.get(5);
        val recommendValue = data.get(6);

        val postReviewButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/a[2]"));
        postReviewButton.click();

        val wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains(SITE + "postreview.php"));

        val productInput = driver.findElement(By.id("tproduct"));
        productInput.sendKeys(product);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product_select\"]/div[2]/div[2]/div")));

        val onwardButton = driver.findElement(By.id("noproduct"));
        onwardButton.click();

        wait.until(ExpectedConditions.urlContains(SITE + "postreview.php?pid=2311284&"));

        val selectRating = new Select(driver.findElement(By.id("rating0")));
        selectRating.selectByValue(rating);

        val contentTitle = driver.findElement(By.id("content_title"));
        contentTitle.sendKeys(reviewTitle);

        val editorContent = driver.findElement(By.xpath("//*[@id=\"content_body_main\"]/div[2]/div[2]/div/textarea[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(editorContent));
        editorContent.sendKeys(review);

        val contentPros = driver.findElement(By.xpath("//*[@id=\"review_post\"]/div[4]/div[2]/textarea"));
        contentPros.sendKeys(pros);

        val contentCons = driver.findElement(By.xpath("//*[@id=\"review_post\"]/div[5]/div[2]/textarea"));
        contentCons.sendKeys(cons);

        val recommendButton = SelectRecommendButton(recommendValue);
        recommendButton.click();

        val previewButton = driver.findElement(By.id("previewbtn"));
        previewButton.click();
    }

    private List<String> GetData(Path inputPath) {
        try {
            List<String> lines = Files.readAllLines(inputPath);
            List<String> result = new ArrayList<>();
            StringBuilder currentBlock = new StringBuilder();

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    result.add(currentBlock.toString().trim());
                    currentBlock = new StringBuilder();
                } else {
                    currentBlock.append(line).append("\n");
                }
            }

            if (currentBlock.length() > 0) {
                result.add(currentBlock.toString().trim());
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private WebElement SelectRecommendButton(String recommendValue) {
        WebElement recommendButton;
        if ("Рекомендую".equalsIgnoreCase(recommendValue)) {
            recommendButton = driver.findElement(By.xpath("//*[@id=\"review_post\"]/div[6]/div[1]/div[2]/label[1]/span"));
        } else if ("Не рекомендую".equalsIgnoreCase(recommendValue)) {
            recommendButton = driver.findElement(By.xpath("//*[@id=\"review_post\"]/div[7]/div[1]/div[2]/label[2]/span"));
        } else {
            throw new IllegalStateException("Некорректное значение для рекомендации: " + recommendValue);
        }
        return recommendButton;
    }
}
