package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

@Epic("Авторизация пользователей")
public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test(testName = "Проверка позитивного логина", description = "Проверка позитивного логина")
    @Feature("Логин")
    @Story("Позитивный сценарий логина с корректными данными")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет успешный вход в систему с правильным логином и паролем.")
    public void checkLogin() {
        loginPage.open();
        loginPage.login(user, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsPage.TITLE));
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Переход на страницу не выполнен");
    }

    @Test(testName = "Проверка пустого логина", description = "Проверка пустого логина")
    @Feature("Логин")
    @Story("Проверка ошибки при пустом логине")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет ошибку, если имя пользователя пустое.")
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не верное");
    }

    @Test(testName = "Проверка пустого пароля", description = "Проверка пустого пароля")
    @Feature("Логин")
    @Story("Проверка ошибки при пустом пароле")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет ошибку, если пароль пустой.")
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login(user, "");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не верное");
    }

    @Test(testName = "Проверка неверного пароля", description = "Проверка неверного пароля")
    @Feature("Логин")
    @Story("Проверка ошибки при неверном пароле")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тест проверяет ошибку при неверном пароле для существующего пользователя.")
    public void checkIncorrectPasswordLogin() {
        loginPage.open();
        loginPage.login("user-name", password);
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Ошибка при логине с неправильными данными не отображается");
    }

    @Test
    @Feature("Буфер обмена")
    @Story("Тестирование копирования текста в буфер обмена")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Тест копирует строку в буфер обмена и выводит содержимое.")
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
    @Feature("Логин")
    @Story("Проверка различных сценариев логина с ошибками")
    @Severity(SeverityLevel.MINOR)
    @Description("Тест проверяет ошибки при логине с некорректными данными: пустой логин, пустой пароль и неверные данные.")
    public void test(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                loginPage.getErrorMessage(),
                expectedError,
                "Сообщение об ошибке не соответствует ожидаемому");
    }
}
