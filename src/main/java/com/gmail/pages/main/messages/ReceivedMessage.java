package com.gmail.pages.main.messages;

import com.codeborne.selenide.Condition;
import com.gmail.utils.LocatorResolver;
import org.apache.commons.lang3.tuple.Pair;

import static com.gmail.utils.Locale.EN;
import static com.gmail.utils.Locale.RU;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

import static com.codeborne.selenide.Selenide.*;


/**
 * Created by Oleksandr_Danchenko on 11.08.2016.
 */
public class ReceivedMessage {

    private LocatorResolver attachment = new LocatorResolver(Pair.of(EN, xpath("//span[@download_url]")),
            Pair.of(RU, cssSelector("some locator for ru")));

    public Attachment openAttachment() {
        attachment.getElement().waitUntil(Condition.visible, 8000).click();
        return page(Attachment.class);
    }
}
