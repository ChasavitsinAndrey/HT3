package com.epam.ta.steps;

import java.util.concurrent.TimeUnit;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Steps
{
	private  String newRepoName = "andreyTestRepo";
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser()
	{
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver()
	{
		DriverSingleton.closeDriver();
	}

	public void loginGithub(String username, String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}

	public boolean isLoggedIn(String username)
	{
		LoginPage loginPage = new LoginPage(driver);
		String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
		logger.info("Actual username: " + actualUsername);
		return actualUsername.equals(username);
	}

	public boolean createNewRepository(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
		return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
	}

	public boolean currentRepositoryIsEmpty()
	{
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

	public  void createNewRepo(String repositoryName, String repositoryDescription)
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.clickOnCreateNewRepositoryButton();
		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
		createNewRepositoryPage.createNewRepository(repositoryName,repositoryDescription);

	}
	public boolean checkRepoExistence()
	{
		try
		{
			MainPage mainPage = new MainPage(driver);
			mainPage.openPage();
			mainPage.fillSearchBox(newRepoName);
			return mainPage.repoExist();
		}
		catch (Exception e)
		{
			return false;
		}

	}
	public boolean checkSignOut()
	{
		MainPage mainPage = new MainPage(driver);
		return mainPage.signOut();
	}
	public void GoToRepoAndClickSettings()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.fillSearchBox(newRepoName);
		mainPage.clickOnRepoHref();
		RepoPage repoPage = new RepoPage(driver);
		repoPage.clickSettingsButton();
	}
	public void DeleteRepo()
	{
		RepoSettingsPage repoSettingsPage = new RepoSettingsPage(driver);
		repoSettingsPage.clickDeleteButton();
		repoSettingsPage.confirmDelete(newRepoName);


	}

}
