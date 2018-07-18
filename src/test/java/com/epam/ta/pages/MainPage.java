package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage
{


	private final String BASE_URL = "https://github.com/";

	private final String newRepoName = "andreyTestRepo";

	@FindBy(xpath = "//*[@id='dashboard']/div/div/div/h3/a")
	private WebElement buttonCreateNew1;

	@FindBy(xpath = "//a[contains(@aria-label, 'Create new')]")
	private WebElement buttonCreateNew;

	@FindBy(xpath = "//a[contains(text(), 'New repository')]")
	private WebElement linkNewRepository;

	@FindBy(id = "dashboard-repos-filter")
	private WebElement searchTextBox;

	@FindBy(xpath = ("//*[@href='/testautomationuser/andreyTestRepo']" ))
	private WebElement repoHref;

	@FindBy(xpath = "//*[@id='user-links']/li[3]")
	private WebElement settingsButton;

	@FindBy(xpath = "//*[@id='user-links']/li[3]/details/details-menu/ul/li[9]")
	private WebElement signOutButton;

	@FindBy(id = "user[login]")
	private WebElement userLoginId;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public void clickOnCreateNewRepositoryButton()
	{
		buttonCreateNew1.click();
		//buttonCreateNew.click();
		//linkNewRepository.click();
	}

	public void clickOnRepoHref()
	{
		repoHref.click();
	}

	public void fillSearchBox(String newRepoName)
	{
		searchTextBox.sendKeys(newRepoName);
	}
	public boolean repoExist ()
	{
		return repoHref.isDisplayed();
	}


	public boolean signOut()
	{
		settingsButton.click();
		signOutButton.click();
		return userLoginId.isDisplayed();
	}


	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}
}
