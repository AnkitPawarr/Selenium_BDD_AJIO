package com.Jio;

import com.Jio.uiPOJO.PhoneNumbers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static com.Jio.utilities.Constants.LOGIN_JSON_PATH;

public class dryRun {

    public static void main(String[] args) throws IOException {
       /* WebDriver driver = new ChromeDriver();
        driver.get("https://www.ajio.com/");

        driver.findElement(By.id("loginAjio")).click();

        *//*System.out.println("Before error: " + driver.findElement(By.id("error-msg")).getText());
        String text = driver.findElement(By.id("error-msg")).getText();
        if (text.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            System.out.println("NOT EMPTY");
        }*//*


        driver.findElement(By.xpath("//div[@class='modal-login-container']//input[@type='number']")).sendKeys("9820654890");
        driver.findElement(By.xpath("//div[@class='modal-login-container']//input[@type='submit']")).click();


        System.out.println(driver.findElement(By.id("error-msg")).getText());
        driver.quit();*/

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(LOGIN_JSON_PATH);

        PhoneNumbers p = objectMapper.readValue(file, PhoneNumbers.class);
        System.out.println(p.getNumbers());

    }
}