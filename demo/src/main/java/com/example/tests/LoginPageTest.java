import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {

    private LoginPage LoginPage = new LoginPage();

    @Test
    public void testUnsuccessfulLogin() {
        open("https://ok.ru/"); 
        okLoginPage.enterUsername("invalidUser "); // Неверный логин
        okLoginPage.enterPassword("invalidPassword"); // Неверный пароль
        okLoginPage.clickLoginButton();

        String expectedErrorMessage = "Неправильно указан логин и/или пароль"; 
        assertEquals(expectedErrorMessage, okLoginPage.getErrorMessage());
    }

    @Test
    public void testEmptyUsername() {
        open("https://ok.ru/"); 
        okLoginPage.enterPassword("somePassword"); // Вводим только пароль
        okLoginPage.clickLoginButton();

        String expectedErrorMessage = "Введите логин"; 
        assertEquals(expectedErrorMessage, okLoginPage.getErrorMessage());
    }

    @Test
    public void testEmptyPassword() {
        open("https://ok.ru/"); 
        okLoginPage.enterUsername("someUser "); // Вводим только логин
        okLoginPage.clickLoginButton();

        String expectedErrorMessage = "Введите пароль"; 
        assertEquals(expectedErrorMessage, okLoginPage.getErrorMessage());
    }
}