package top.ncserver.chatsync.Until;

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
    public ChatGPT(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.14");
        WebDriver driver = new EdgeDriver(options);

        driver.get("https://chat.openai.com/");
        Cookie cookie = new Cookie("__Secure-next-auth.session-token","eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..olyeMmF29G7EPHqn.H4iwbqckXaPx-YUPRqaBZClhmmxvCqv0XaL9y-37KE0hssVYFX_5x2g53PNBIOhWJO73TB8DrfweAgtmI5_run80jir0iU0WVjyWKsnghWY5CI1sXshnJr-VZhIXwwGzLSHh0cFPZeYHN6rP3CYQ7Z4FZm-gN2XrtnQLYzq26XDC_XKCFwmDEgr2wFQ8aTQmcI_MqnZ5tDdalV8wxHZwmTpoqhUKpspX3ZBYxVhRg-ZnWePcY-O9LSdeigNadz5kRzNaWG-iGAM1Cyw70BWRlK94x2gWU3SY9Fl_x_W15yG_y7aWiPGWQ2Z6w_7Pk0N_g51PoG3ACxCj06dNxkReWlDDiy5lVBXRU7BRQ2NlM2Gzha7Rv5c1awE_Yf_Ff4YEmKIps7pXJxAwNMsyffvJImhN1khyRpHPHAms6B9HArMpqikrMcJX0Qq2HBDEElMln_EP_nHKBTxx_LaAyNLgbM8XG6458biSkIc9ijxXfRxT8D3S6e_eePB8BY-SGWAd7eHDFITLueSa6oqlrjSSIzXhCQkMsw4oWi91mG3qLVwG23Gwb2Mr4uoYwoONY9R7kMcLU7y9RUqEWxnLzgifdcsjdczmmhs0lK-Xqrc4ErVYbyV_8HUX0RLRa78UUHRVo5kLOdJJy0w-aiddzlYiXxounkY2U8HSaUmYBwh3fHYZnCRT2C73TMqyN-aCubJsTHV_am2cXQDyHsitc0kWAvtnPi5WRw-Yi-R5vC_Di4u2mjxqNBA5rcMENN3TBmOzjnWblYRHbP9emzxaknSB80fqDeVIjvTivNTyqD89OTF9pi66PS_qFFSyeJH6mu8Hi7TiaPV0GV12ug56DJfC7rzPx0SFV4Esqc_BicnQZyEEOZCzhDtuY1qv8j6Vid9OZ5QCpoocN38hObDWza53kinK5kZ9MiC42ySfcLnUr8XeDRdWk-AOGesGk2rVcspJSL9_tA1LYxl3cgqqQPlXG4VxUknUkIk4ZnykPBXebfvjYKomm-Z5alOZCnkWE33qyVsMEDhyDMqvli7RDLXPjfnmeVqgxLRar2SSVlkv8JC_3Jxpy7XDsTBHHyd8LkD6uqFFRIjj0b6XB2g73oNZvGPgQUdNMRyg1N9TcQFKH7D9JayZ72kQtGOhDcCY85PxvvvajOwI8mHpE1Am4AtzO3CscdUxo3RyY7x2BMJwClAuQCg1q6KxT46BHVkdH0H0YpBjqYgAVuKB7fHAIZ5zsQLlHnl7UhG8iH-6yiGO2HVVEnTtEwIWN0J1Fo0myqFEc8mPUr1PWubEYg1LwwtfnbCqPVQ9jZFxvJz8UTUINP8Hrkc5IGri-lOPlxaAu1-jlZQ97ecuPqV65epjL_ahAlT8ceQeM7kOHEZPWJwTG_EHALY5GIY-iz2qVAO-cvUSiUXv6DtvZfrsjx5ZT9MTUHsQ9wY4YaktjUoq8YGF1iGZyoGtZ1Yncqafc4iFneHhc1_CxVgn76TwQhflvgwwrKc0V7wixp1nZ4QUvEGXZbCc1CyfeLz8aPFHWDSj5G2Adt88xOTKexKHX9t1zaZr7dg4lEhTRme0hOsQ0O3LuNOw5HnhXg93uqrz2bx3xOkou--nJQv_p_eG_c4nJFDj48UJU-Xu5h09TxxDGOL3k7UriHWphyaeIoTS32vLiu0ZCQpL6LHDJkTCok681kAHFTx4TeRBYYXJJSKdUzKCERy0oRzQ8464lnlTprP9MIjRsfboeUH3O0PYNfqMCHYCfUpScCG_yYk0LcHiUUGqqwvaxfOEyh5FFMWmmOZpLOZ2sWpEww76vVLwu0ZBx3KEeeMnEteU2fiDYvVwOzSvZsftI3lzKgUXBoLM2rk0WAGz2VjZ7_J3ub_QWoOUWx1vKezIDEOR2v-aOqq_lfVUlN1d2OznSX7PZ_KdsQOY1dvJAjtdL64esb892YZZZcSM_d8bRlPYhXItDGyrl4lFyElgT8ne_EkG6xagnlZL5_vUawRCHv7gojolHiQMFWCjwe077e-EFAw0i4mFqkBQonVe_71PP-t3BLLXYy5o37WUbZ8hlc88T2gJqO0OChw53O93Zh_f5J0TSCU_LaNLrkToVrYPTVi9jl-jy7ol4zYYwPgqTOT6vFJs5Kp1gDdpZ5J-Yq7NKttgspXGG5qX7UwioSLIeCBhLFztRU2_8juOOIgox0W1mLlXh3qiy0H9LPqnpwsAF4V2yrorUbJvMv7_3hCozaKMxRafEFHjriMJ5ttAU4NDC55UF6zlhzjhHgXxdcP_Yi1uDfn5gaUxxtlcrwawrtzxQ8wnxNY9pjcd_jgteq6GxgZTg239DwtWFFtJh_KxFOxtH_gtp9SMWytWp47ly77Fscs.MBfSXzWhSMOR3SYUKZQBlQ");
        Cookie cookie1 =new Cookie("cf_clearance","PM9FRVrjxRErUNTQA.CSJzB1SCrmMM0S5tYmq8eujd8-1671008254-0-1-40c63d79.3297c0ae.2d803039-160");
        Cookie cookie2 =new Cookie("__cf_bm","ePHAK8OZ_.zFMzZUgxV0CiPHsIC64I4cdqqVLfuYKsw-1671002934-0-AQveLRPcTJcUF4vyWkOpgi/8JVuPXqDYE/vcGy4AViNXLuRUXMe2FFl9KaTxptiRKF0xF3jOS/wBEhpkrm1B+neLBSN+FXtMZoA9QEE9QtdbWbFxXwLA9hghH/vtPM64dx9VslVWjpo0xV8Ba5zHieXrpjvyOEht2+JYHuVbDyjrYVYt3oDZHadD+iAd1tavcw==");
        Cookie cookie3 =new Cookie("__Secure-next-auth.callback-url","https%3A%2F%2Fchat.openai.com");
        Cookie cookie4 =new Cookie("__Host-next-auth.csrf-token","6f5196d5d3c48f36006f987ece93a24f022d2660fb647dd942840766727cee77%7Cb52d74341f25d9ea2a16b9e43cc8b1226838dcaa94a7d7a38150754ea2a5287e");
        Cookie cookie5 =new Cookie("cf_chl_rc_m","1");
        Cookie cookie6 =new Cookie("cf_chl_2","aa108a2e9500f3b");
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
