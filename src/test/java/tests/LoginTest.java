package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);


    @Test(testName = "Проверка позитивного логина", description = "Проверка позитивного логина")
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test(testName = "Проверка пустого логина", description = "Проверка пустого логина")
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не верное");
    }

    @Test(testName = "Проверка пустого пароля", description = "Проверка пустого пароля")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не верное");
    }

    @Test(testName = "Проверка неверного пароля", description = "Проверка неверного пароля")
    public void checkIncorrectPasswordLogin(){
        loginPage.open();
        loginPage.login("user-name", "secret_sauce");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "переход на страницу не выполнен");
    }

    @Test
    public void copy() throws IOException, UnsupportedFlavorException {
        String copyBuffer = "TeachMeSkills";

        StringSelection stringSelection = new StringSelection(copyBuffer);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        System.out.println(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"user-name", "", "Epic sadface: Password is required"},
                {"standard_user", "122334112", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "LoginData")
    public void test(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                loginPage.getErrorMessage(),
                expectedError,
                "переход на страницу не выполнен");
    }
}
