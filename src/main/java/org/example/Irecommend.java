package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Irecommend {
    private WebDriver driver;
    public Irecommend(WebDriver driver){
        this.driver = driver;
    }

    public void execute(){
        driver.get("https://irecommend.ru/");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/a/button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://irecommend.ru/user/login?destination=home"));

        WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"user-login\"]/div/div/div[6]/div[2]/a"));
        registerButton.click();

        wait.until(ExpectedConditions.urlContains("https://irecommend.ru/user/register?destination=home"));

        WebElement nameTextBox = driver.findElement(By.xpath("//*[@id=\"edit-name\"]"));
        nameTextBox.sendKeys("Denikaso");

        WebElement mailTextBox = driver.findElement(By.xpath("//*[@id=\"edit-mail\"]"));
        mailTextBox.sendKeys("daniil.tata@mail.ru");

        WebElement passwordTextBox = driver.findElement(By.xpath("//*[@id=\"edit-pass-pass1\"]"));
        passwordTextBox.sendKeys("435363");

        WebElement password2TextBox = driver.findElement(By.xpath("//*[@id=\"edit-pass-pass2\"]"));
        password2TextBox.sendKeys("435363");

        WebElement regPolicyCheckBox = driver.findElement(By.id("edit-reg-policy"));
        regPolicyCheckBox.click();

    }
}
