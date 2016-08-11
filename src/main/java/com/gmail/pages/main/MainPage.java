package com.gmail.pages.main;

import com.gmail.pages.main.body.MailList;
import com.gmail.pages.main.menu.LeftMenu;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Oleksandr_Danchenko on 10.08.2016.
 */
public class MainPage {

    @FindBy(css = "div[class~='aeN']")
    public LeftMenu leftMenu;

    @FindBy(xpath = "//table[colgroup]")
    public MailList mailList;
}
