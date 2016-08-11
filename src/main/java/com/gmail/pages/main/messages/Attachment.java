package com.gmail.pages.main.messages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Oleksandr_Danchenko on 11.08.2016.
 */
public class Attachment {

    @FindBy(xpath = "//div[@role='document']")
    public SelenideElement attachmentBody;

}
