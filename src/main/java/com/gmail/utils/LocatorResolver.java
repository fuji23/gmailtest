package com.gmail.utils;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Class was created to return locale based locator which mainly based on string value from element attribute or property,
 * because gmail doesn't have fine structured locators.
 */
public class LocatorResolver {

    private List<Pair<Locale, By>> locatorMap = new ArrayList<>();

    @SafeVarargs
    public LocatorResolver(Pair<Locale, By>... locators){
        Assert.assertTrue(locators.length > 0, "Locators must have at least one pair.");
        locatorMap.addAll(Arrays.asList(locators));
    }

    /**
     * Get element accordingly to chosen locale.
     * @return
     */
    public SelenideElement getElement(){
        String configLocale = System.getProperty("locale");
        Locale locale = Locale.valueOf(configLocale.toUpperCase());
        return $(locatorMap.stream().filter(p -> p.getKey().equals(locale)).findFirst().get().getValue());
    }

    public By getLocator(){
        String configLocale = System.getProperty("locale");
        Locale locale = Locale.valueOf(configLocale.toUpperCase());
        return locatorMap.stream().filter(p -> p.getKey().equals(locale)).findFirst().get().getValue();
    }

}
