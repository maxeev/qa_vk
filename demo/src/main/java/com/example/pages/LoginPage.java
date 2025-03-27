import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    // Элементы страницы
    private SelenideElement usernameField = $("#field_email"); // Поле для ввода логина
    private SelenideElement passwordField = $("#field_password"); // Поле для ввода пароля
    private SelenideElement loginButton = $(".button-pro"); // Кнопка входа
    private SelenideElement errorMessage = $(".error-message"); // Сообщение об ошибке

    // Методы для взаимодействия с элементами
    public void enterUsername(String username) {
        usernameField.setValue(username);
    }

    public void enterPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}