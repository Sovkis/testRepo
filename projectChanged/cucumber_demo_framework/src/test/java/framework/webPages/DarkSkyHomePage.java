package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DarkSkyHomePage extends BasePage {

    private By currentTemperature = By.xpath("//span[@class='summary swap']");
    private By dailyTemperature = By.xpath("//div[@id='timeline']//div[@class='temps']");
    private By lowestTodayWeatherShown = By.xpath("//a[1]//span[2]//span[1]");
    private By highestTodayWeatherShown = By.xpath("//a[1]//span[2]//span[3]");
    private By lowestTodayWeatherHidden = By.xpath("/html[1]/body[1]/div[6]/div[2]/div[1]/div[2]/div[1]/span[1]/span[1]");
    private By highestTodayWeatherHidden = By.xpath("/html[1]/body[1]/div[6]/div[2]/div[1]/div[2]/div[1]/span[3]/span[1]");
    private By timeOfTheDay = By.xpath("//div[@id='timeline']//div[@class='hours']");
    private By expandWeather = By.xpath("//body[@class='forecast']/div[@id='week']/a[1]");
    private By everyHourAfterStart = By.xpath("//span[contains(@class,'hour')]");
    int num = 2;


    public void timeIncrement(){

                    try {
            List<WebElement> allHours = SharedSD.getDriver().findElements(everyHourAfterStart);
            List<String> expectedTime = new ArrayList<>();
            List<String> actualTime = new ArrayList<>();
            String hour = getTimeInHours(num);

            for (WebElement timeExpectation : allHours) {
                expectedTime.add(timeExpectation.getText());
            }

            String currentHour = "Now";
            actualTime.add(0, currentHour);
            for (int i = 1; i < allHours.size(); i++) {
                hour.toLowerCase();

                num = num + 2;

            }
            Assert.assertEquals(expectedTime, actualTime, "Time equal now.");
        } catch (IllegalArgumentException iae) {
            System.out.println("Error found");
            iae.printStackTrace();
        }
    }

    public void checkTheWeather(){
        List<WebElement> temperatures = SharedSD.getDriver().findElements(dailyTemperature);
        List<String> temprs = new ArrayList<>();
        String text = SharedSD.getDriver().findElement(currentTemperature).getText();
        String[] split = text.split("˚");
        String current = split[0];
        int currentint = Integer.parseInt(current);

        for (WebElement temp : temperatures){
            temprs.add(temp.getText());
        }
        for (String tempr: temprs){
            String [] splitDaily = tempr.split("°");
            String spDaily = splitDaily[0];
            int dailyint = Integer.parseInt(spDaily);
            int currentMinusDaily=(currentint - dailyint);
            Assert.assertTrue(currentMinusDaily < Math.abs(10));

        }
    }
    public void clickOnTodayExpandButton(){
        JavascriptExecutor js = (JavascriptExecutor) SharedSD.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", SharedSD.getDriver().findElement(expandWeather));
        js.executeScript("arguments[0].click();",SharedSD.getDriver().findElement(expandWeather));
    }

    public void TodaysWeather(){
        String one = SharedSD.getDriver().findElement(lowestTodayWeatherHidden).getText();
        String two = SharedSD.getDriver().findElement(highestTodayWeatherHidden).getText();
        String three = SharedSD.getDriver().findElement(lowestTodayWeatherShown).getText();
        String four = SharedSD.getDriver().findElement(highestTodayWeatherShown).getText();
        String [] splitLo = one.split("˚");
        String [] splitHi = two.split("˚");
        String [] splitLoSh = three.split("˚");
        String [] splitHiSho = four.split("˚");
        String loSho = splitLoSh[0];
        String hiSho = splitHiSho[0];
        String loHi = splitLo[0];
        String hiHi = splitHi[0];

        int intLoSho = Integer.parseInt(loSho);
        int intHiSho = Integer.parseInt(hiSho);
        int intLoHi = Integer.parseInt(loHi);
        int intHiHi = Integer.parseInt(hiHi);

        Assert.assertEquals(intLoHi,intLoSho);
        Assert.assertEquals(intHiHi,intHiSho);
    }
}