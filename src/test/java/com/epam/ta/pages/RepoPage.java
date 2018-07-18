package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RepoPage extends AbstractPage
{
    private final String BASE_URL = "https://github.com/andreyTestRepo";

    @FindBy(xpath = "//*[@id='js-repo-pjax-container']/div/nav/a[4]")
    private WebElement settingsButton;

    public RepoPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickSettingsButton()
    {
        settingsButton.click();
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }
}
