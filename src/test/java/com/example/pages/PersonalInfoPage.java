package com.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class PersonalInfoPage implements CheckPage{

    private final SelenideElement mainInfoPath = $x(".//button[contains(@class, 'modal-trigger')]");
    private final SelenideElement changeNamePath = $x(".//*[contains(@class,'text-input-wrapper')]/input");
    private final SelenideElement saveChangesPath = $x(".//button[text()='Сохранить']"); // точки в локаторах для того, чтобы поиск нужного эоемента осуществлялся от родительского элемента, а не от корня документа. помогает защищаться от ложных срабатываний
    private final SelenideElement updatedNamePath = $x(".//span[contains(@class, 'tip__v631b')]");

    @Override
    public void checkPage() {
        mainInfoPath.shouldBe(visible.because("Main info button is not visible"));
        changeNamePath.shouldBe(visible.because("Change name field is not visible"));
        saveChangesPath.shouldBe(visible.because("Save changes button is not visible"));
        updatedNamePath.shouldBe(visible.because("New name span is not visible"));

    }

    public PersonalInfoPage mainInfoClick() {
        mainInfoPath.click();
        return this;
    }

    public PersonalInfoPage setNewName(String newName) {
        changeNamePath.setValue(newName);
        return this;
    }

    public PersonalInfoPage saveChangesClick() {
        saveChangesPath.click();
        return this;
    }

    public String getUpdatedName() {
    return updatedNamePath.getText().split(" ")[0];
}
}   
