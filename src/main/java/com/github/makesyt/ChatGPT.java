package com.github.makesyt;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ChatGPT {
    private WebDriver driver=null;
    private Boolean flag=false;

    public Boolean getFlag() {
        return flag;
    }

    public String getRe(String question){
        if(!flag)
            return "ChatGPT异常/正在工作";
        driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[2]/textarea")).sendKeys(question);
        driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[2]/textarea")).sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
        flag=false;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[1]/main/div[2]/form/div/div[1]/button")));
        List<WebElement> list=driver.findElements(By.xpath("/html/body/div/div/div[1]/main/div[1]/div/div/div/div"));
        flag=true;
        return driver.findElement(By.xpath("/html/body/div/div/div[1]/main/div[1]/div/div/div/div["+(list.size()-1)+"]/div/div[2]/div[1]/div/p")).getText();
    }
    public ChatGPT(String token,String cf_clearance,String __cf_bm,String hostToken){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.14");
        WebDriver driver = new EdgeDriver(options);

        driver.get("https://chat.openai.com/");
        Cookie cookie = new Cookie("__Secure-next-auth.session-token",token);
        Cookie cookie1 =new Cookie("cf_clearance",cf_clearance);
        Cookie cookie2 =new Cookie("__cf_bm",__cf_bm);
        Cookie cookie3 =new Cookie("__Secure-next-auth.callback-url","https%3A%2F%2Fchat.openai.com");
        Cookie cookie4 =new Cookie("__Host-next-auth.csrf-token",hostToken);
        Cookie cookie5 =new Cookie("cf_chl_rc_m","1");
        Cookie cookie6 =new Cookie("cf_chl_2","");
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);
        driver.manage().addCookie(cookie3);
        driver.manage().addCookie(cookie4);
        driver.manage().addCookie(cookie5);
        driver.manage().addCookie(cookie6);
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
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[2]/div[4]/button")));
            driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[2]/div[4]/button")).click();
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