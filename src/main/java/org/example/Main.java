package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\danii\\Downloads\\msedgedriver.exe");
        InputHandler inputHandler = new InputHandler();
        inputHandler.handleArguments(args);
    }
}