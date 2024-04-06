package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class ProductPage extends BasePage{
    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(className = "o-productDetail__brandLink")
    public WebElement productName;

    @FindBy(xpath = "(//*[@class='m-price__new'])[1]")
    public WebElement price;

    @FindBy(xpath = "//*[@id='sizes']/div/span[2]")
    public WebElement sizeM;

    @FindBy(id = "addBasket")
    public WebElement addBasket;


}
