package com.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class PersonalInfoPage implements CheckPage{

    private final By mainInfoPath = By.xpath(".//button[contains(@class, 'modal-trigger')]");
    private final By changeNamePath = By.xpath(".//*[contains(@class,'text-input-wrapper')]/input");
    private final By saveChangesPath = By.xpath(".//button[text()='Сохранить']"); // точки в локаторах для того, чтобы поиск нужного эоемента осуществлялся от родительского элемента, а не от корня документа. помогает защищаться от ложных срабатываний
    private final By updatedNamePath = By.xpath(".//span[contains(@class, 'tip__v631b')]");

    @Override
    public void checkPage() {
        $(mainInfoPath).shouldBe(visible.because("Main info button is not visible"));
        $(changeNamePath).shouldBe(visible.because("Change name field is not visible"));
        $(saveChangesPath).shouldBe(visible.because("Save changes button is not visible"));
        $(updatedNamePath).shouldBe(visible.because("New name span is not visible"));

    }

    public PersonalInfoPage mainInfoClick() {
        $(mainInfoPath).click();
        return this;
    }

    public PersonalInfoPage setNewName(String newName) {
        $(changeNamePath).setValue(newName);
        return this;
    }

    public PersonalInfoPage saveChangesClick() {
        $(saveChangesPath).click();
        return this;
    }

    public String getUpdatedName() {
    return $(updatedNamePath).getText().split(" ")[0];
}
}   
