package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(className = "o-header__search--wrapper")
    public WebElement searchBar;

    @FindBy(xpath = "//a[@href='/cart']")
    public WebElement cart;



}
