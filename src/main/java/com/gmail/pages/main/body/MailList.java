package com.gmail.pages.main.body;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.gmail.pages.main.messages.ReceivedMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Oleksandr_Danchenko on 11.08.2016.
 */
public class MailList extends ElementsContainer {

    private String rowByName = ".//tr//td[contains(., '%s')]";

    @FindBy(css = "tr")
    private List<SelenideElement> rows;

    public ReceivedMessage openMessage(String name) {
        int attempts = 10;
        By elem = By.xpath(String.format(rowByName, name));
        while (attempts > 0) {
            if (getSelf().$(elem).exists()) {
                getSelf().$(elem).click();
                break;
            } else {
                try {
                    getSelf().$(elem).waitUntil(Condition.visible, 4000, 500);
                } catch (Exception e) {
                    attempts--;
                }
            }
        }

        return page(ReceivedMessage.class);
    }
}
