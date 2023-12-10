package org.example;

import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class InputHandler {
    public void handleArguments(String[] args){
        val task = Integer.parseInt(args[0]);
        WebDriver driver = new EdgeDriver();
        switch (task){
            case 1:{
                try {
                    BusStops busStopsTest = new BusStops(driver);
                    busStopsTest.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            case 2:{
                try {
                    OtzyvRu otzyvRu = new OtzyvRu(driver);
                    otzyvRu.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            case 3:{
                try {
                    Otzovik otzovik = new Otzovik(driver);
                    otzovik.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            case 4:{
                try {
                    Irecommend irecommend = new Irecommend(driver);
                    irecommend.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            case 5:{
                try {
                    Images images = new Images(driver);
                    images.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
