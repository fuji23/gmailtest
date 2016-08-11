package com.gmail.tests.sendemail;

import com.codeborne.selenide.Condition;
import com.gmail.pages.login.LoginPage;
import com.gmail.pages.main.messages.NewMessage;
import com.gmail.pages.main.messages.ReceivedMessage;
import com.gmail.tests.Base;
import com.gmail.pages.main.MainPage;
import com.gmail.users.Users;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Oleksandr_Danchenko on 10.08.2016.
 */
public class SendMail extends Base {

    String date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    @DataProvider(name = "testdata")
    private Object[][] dataProvider() {
        return new Object[][]{
                {date, new File("src\\test\\resources\\attachment.txt")}
        };
    }

    @Test(dataProvider = "testdata")
    public void sendMail(String subject, File path) {
        Reporter.log("Step 1. Login. Expected: user is logged in.");
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(Users.USER1);

        Reporter.log("Step 2. Write new message with attachment. Expected: New message is sent to recipient.");
        MainPage mainPage = open(baseUrl, MainPage.class);
        NewMessage newMessage = mainPage.leftMenu.writeMessage();

        newMessage.fillMessageHeader(Users.USER2, subject);
        newMessage.enterMessage("Please see an attachment");
        newMessage.attachFile(path);
        newMessage.clickSend();
    }

    @Test(dependsOnMethods = {"sendMail"}, dataProvider = "testdata")
    public void readForAttachment(String subject, File path) throws IOException {
        Reporter.log("Step 1. Login. Expected: user is logged in.");
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(Users.USER2);

        Reporter.log("Step 2. read an attachment. Expected: attachment can be read.");
        MainPage mainPage = open(baseUrl, MainPage.class);
        ReceivedMessage receivedMessage = mainPage.mailList.openMessage(subject);

        receivedMessage.openAttachment().attachmentBody.shouldHave(Condition.hasText(new String(Files.readAllBytes(path.toPath()))));
    }
}
