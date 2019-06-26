package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webPages.DarkLoginPage;
import framework.webPages.DarkSkyHomePage;
import framework.webPages.HomePage;
import org.testng.Assert;

public class DarkSkySD {

    private HomePage homePage = new HomePage();
    private DarkLoginPage loginPage = new DarkLoginPage();
    private DarkSkyHomePage HomePage = new DarkSkyHomePage();

    @Given("^I am on the darksky Register page$")
    public void iAmOnRegisterPage() throws InterruptedException{
        SharedSD.getDriver().manage().window().maximize();
        Thread.sleep(2000);
        homePage.clickOnApi();
        Thread.sleep(2000);
        homePage.clickOnLogin();
    }
    @When("^When i click on Register button$")
    public void registerVerify(){
        loginPage.clickOnLoginButton();
    }

    @Then("^I verify error message \"please fill out this field\"$")
    public void verifyErrorMessage(){
        Assert.assertEquals("https://darksky.net/dev/login", SharedSD.getDriver().getCurrentUrl());
    }

    @Given("^I am on Darksky Home Page$")
    public void OnHomePage(){
        SharedSD.getDriver().manage().window().maximize();
    }

    @Then("^I verify current temperature is not greater or less then temps from daily timeline$")
    public void WeatherChecking(){
        HomePage.checkTheWeather();

    }

    @When("^I expand todays timeline$")
    public void Timeline(){
        HomePage.clickOnTodayExpandButton();
    }

    @Then("^I verify lowest and highest temp is displayed correctly$")
    public void WeatherComparison(){
        HomePage.TodaysWeather();
    }


    @Then("^I verify timeline is displayed with two hours incremented$")
    public void TimeIncrementation(){
        HomePage.timeIncrement();
    }
}

