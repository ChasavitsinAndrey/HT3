package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RepoSettingsPage extends AbstractPage
{
    private final String BASE_URL = "https://github.com/andreyTestRepo/settings";

   // @FindBy(xpath = "//*[@id='options_bucket']/div[7]/ul/li[4]/details/summary")
    @FindBy(xpath = "//*[@id='options_bucket']/div[8]/ul/li[4]/details/summary")
    private WebElement deleteRepoButton;

    @FindBy(xpath = "//*[@id='options_bucket']/div[8]/ul/li[4]/details/details-dialog/div[3]/form/p/input")
    private WebElement confirmDeleteTextBox;

    @FindBy(xpath = "//*[@id='options_bucket']/div[8]/ul/li[4]/details/details-dialog/div[3]/form/button")
    private WebElement confirmDeleteButton;

    public RepoSettingsPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickDeleteButton()
    {
        deleteRepoButton.click();
    }

    public void confirmDelete(String deleteTextBox)
    {
        confirmDeleteTextBox.sendKeys(deleteTextBox);
        confirmDeleteButton.click();

    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }
}

