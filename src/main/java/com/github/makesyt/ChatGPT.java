package com.github.makesyt;



import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ChatGPT {
    private WebDriver driver=null;
    private Boolean flag=false;

    public Boolean getFlag() {
        return flag;
    }

    public File getRe(String question){
        if(!flag)
            return null;
        driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[2]/textarea")).sendKeys(question);
        driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[2]/textarea")).sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
        flag=false;
        int count = 0;
        boolean tryTwice = false;
        while(true){
            if (count>=120){
                break;
            }
            try {
                driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div/span"));
                if (!tryTwice){
                    System.out.println("重试");
                    driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div/button")).click();//请求出现异常
                    tryTwice=true;
                }else break;
            } catch (Exception e) {

            }
            if (driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[2]/button")).isEnabled()) {
                break;
            }
            try {
                Thread.sleep(1000);
                count++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



        List<WebElement> list=driver.findElements(By.xpath("/html/body/div/div/div[1]/main/div[1]/div/div/div/div"));
        flag=true;

        WebElement webElement= driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[1]/div/div/div/div[" + (list.size() - 1) + "]/div/div[2]/div[1]"));



        return webElement.getScreenshotAs(OutputType.FILE);
    }
    public void reload(){
        flag=false;
        driver.navigate().refresh();
        String title = driver.getTitle();
        System.out.printf(title);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        while (driver.getTitle().contains("chat.openai.com")){
            driver.navigate().refresh();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            if (driver.getTitle().contains("Just a moment...")){
                System.out.println("验证");
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("challenge-explainer-btn")));
                    System.out.println("需要验证");
                    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/input")).click();
                } catch (Exception e) {
                    try {
                        System.out.println("第二种验证,请手动完成");
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/div")));
                        Thread.sleep(5000);
                        System.out.println("找到元素");
                        Actions action = new Actions(driver);
                        action.click(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div")));
                        driver.findElement(By.id("content")).click();
                    } catch (Exception ex) {

                    }
                }

            }
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[2]/textarea")));

            System.out.println("下一步骤");
            try {
                Thread.sleep(3000);
                driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[2]/div[4]/button")).click();
            } catch (Exception e) {

            }

            flag=true;
        } catch (Exception e) {

        }
    }
    public ChatGPT(){

        EdgeOptions options = new EdgeOptions();

        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        WebDriver driver = new EdgeDriver(options);
        boolean isGPT=false;
        //获取当前页面句柄
        if (driver.getCurrentUrl().contains("https://chat.openai.com/chat")){
            isGPT=true;

        }else{
            String handle = driver.getWindowHandle();
            for (String handles:driver.getWindowHandles()) {
                if (handles.equals(handle))
                    continue;
                driver.switchTo().window(handles);
                if (driver.getCurrentUrl().contains("https://chat.openai.com/chat")){
                    isGPT=true;
                    break;
                }
            }
        }

        //获取所有句柄，循环判断是否等于当前句柄

        if (!isGPT)
            driver.get("https://chat.openai.com/");
        String title = driver.getTitle();
        System.out.printf(title);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        while (driver.getTitle().contains("chat.openai.com")){
            driver.navigate().refresh();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            if (driver.getTitle().contains("Just a moment...")){
                System.out.println("验证");
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("challenge-explainer-btn")));
                    System.out.println("需要验证");
                    driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/input")).click();
                } catch (Exception e) {
                    try {
                        System.out.println("第二种验证,请手动完成");
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/div")));
                        Thread.sleep(5000);
                        System.out.println("找到元素");
                        Actions action = new Actions(driver);
                        action.click(driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div")));
                        driver.findElement(By.id("content")).click();
                    } catch (Exception ex) {

                    }
                }

            }
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[2]/textarea")));

            System.out.println("下一步骤");
            try {
                Thread.sleep(3000);
                driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[2]/div[4]/button")).click();
            } catch (Exception e) {

            }
            while (true){
                try {
                    Thread.sleep(1000);
                    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[2]/div[4]/button[2]")).click();
                } catch (Exception e) {
                    break;
                }

            }
            flag=true;
            this.driver=driver;
        } catch (Exception e) {

        }
    }
}
