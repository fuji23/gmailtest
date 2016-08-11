package com.gmail.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gmail.pages.main.MainPage;
import com.gmail.users.Users;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Oleksandr_Danchenko on 10.08.2016.
 */
public class LoginPage {

    private String pageUrl = "https://accounts.google.com/ServiceLogin#identifier";

    @FindBy(css = "#Email")
    private SelenideElement emailInput;

    @FindBy(css = "#next")
    private SelenideElement nextButton;

    @FindBy(css = "#Passwd")
    private SelenideElement passwordInput;

    @FindBy(css = "#signIn")
    private SelenideElement enterButton;

    public void login(Users user) {
        open(pageUrl);
        emailInput.setValue(user.getEmail());
        nextButton.click();
        passwordInput.shouldBe(Condition.visible).setValue(user.getPassword());
        enterButton.click();
    }
}
