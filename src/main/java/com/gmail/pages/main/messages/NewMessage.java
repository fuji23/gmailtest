package com.gmail.pages.main.messages;

import com.codeborne.selenide.Condition;
import com.gmail.users.Users;
import com.gmail.utils.LocatorResolver;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import static com.gmail.utils.Locale.EN;
import static com.gmail.utils.Locale.RU;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

/**
 * Created by Oleksandr_Danchenko on 10.08.2016.
 */
public class NewMessage {

    private LocatorResolver toInput = new LocatorResolver(Pair.of(EN, xpath("//textarea[@aria-label='To']")),
            Pair.of(RU, cssSelector("some locator for ru")));

    private LocatorResolver subjInput = new LocatorResolver(Pair.of(EN, xpath("//input[@placeholder='Subject']")),
            Pair.of(RU, cssSelector("some locator for ru")));

    private LocatorResolver messageText = new LocatorResolver(Pair.of(EN, xpath("//div[@aria-label='Message Body']")),
            Pair.of(RU, cssSelector("some locator for ru")));

    private LocatorResolver sendButton = new LocatorResolver(Pair.of(EN, xpath("//div[contains(@aria-label, 'Send')]")),
            Pair.of(RU, cssSelector("some locator for ru")));

    private LocatorResolver attachFile = new LocatorResolver(Pair.of(EN, xpath("//div[@aria-label='Attach files']")),
            Pair.of(RU, cssSelector("some locator for ru")));

    private LocatorResolver attachedFile = new LocatorResolver(Pair.of(EN, xpath("//div[contains(@aria-label, 'Attachment:')]")),
            Pair.of(RU, cssSelector("some locator for ru")));

    public void fillMessageHeader(Users to, String subject) {
        toInput.getElement().setValue(to.getEmail());
        subjInput.getElement().setValue(subject);
    }

    public void enterMessage(String message) {
        messageText.getElement().setValue(message);
    }

    public void attachFile(File file) {
        attachFile.getElement().click();

        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);

        try {
            Robot robot = new Robot();
            robot.waitForIdle();
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        attachedFile.getElement().shouldBe(Condition.visible);
    }

    public void clickSend() {
        sendButton.getElement().click();
    }
}
