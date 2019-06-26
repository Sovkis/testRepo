package framework.webPages;

import org.openqa.selenium.By;

public class DarkLoginPage extends BasePage {


    private By loginButton = By.xpath("//button[contains(text(),'Log in')]");

    public void clickOnLoginButton(){
        clickOn(loginButton);
    }


}