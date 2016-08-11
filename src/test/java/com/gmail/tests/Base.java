package com.gmail.tests;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;

/**
 * Created by Oleksandr_Danchenko on 10.08.2016.
 */
public class Base {

    //debug
/*
    static {
        System.setProperty("selenide.browser", "chrome");
        System.setProperty("locale", "en");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }
*/

    public final String baseUrl = "https://mail.google.com/mail";

    @AfterMethod
    public void end(){
        WebDriverRunner.closeWebDriver();
    }

}
