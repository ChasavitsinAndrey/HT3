package com.epam.ta;

import com.epam.ta.pages.CreateNewRepositoryPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";
	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(enabled = false)
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
		Assert.assertTrue(steps.currentRepositoryIsEmpty());
		// do not use lots of asserts
	}

	@Test(description = "Login to Github")
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(USERNAME));
	}

	@Test(description = "Create new repository + check its existence")
	public void checkNewRepo()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewRepo("andreyTestRepo","some description");
		Assert.assertTrue(steps.checkRepoExistence());
	}


	@Test(dependsOnMethods = {"checkNewRepo"}, description = "DeleteRepo")
	public void deleteRepo()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.checkRepoExistence());
		steps.GoToRepoAndClickSettings();
		steps.DeleteRepo();								// тут нафейлил и захардкодил имя репозитория для удаления
		Assert.assertFalse(steps.checkRepoExistence());
	}

	@Test( description = "Sign out")
	public void signOut()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.checkSignOut());
	}
	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}

}
