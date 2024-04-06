package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class HomePage extends BasePage{
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(className = "reject-btn-container")
    public WebElement rejectCookies;

    @FindBy(xpath = "(//*[@class='a-primaryButton genderPopup__button'])[2]")
    public WebElement genderMan;




}
