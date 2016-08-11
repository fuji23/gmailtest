package com.gmail.pages.main.menu;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import com.gmail.pages.main.messages.NewMessage;
import com.gmail.utils.LocatorResolver;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;
import static com.gmail.utils.Locale.EN;
import static com.gmail.utils.Locale.RU;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

/**
 * Created by Oleksandr_Danchenko on 10.08.2016.
 */
public class LeftMenu extends ElementsContainer {

    private LocatorResolver compose = new LocatorResolver(Pair.of(EN, xpath("//div[@role='button'][contains(., 'COMPOSE')]")),
            Pair.of(RU, cssSelector("some locator for ru")));

    public NewMessage writeMessage() {
        getSelf().$(compose.getLocator()).click();
        return page(NewMessage.class);
    }

}
