package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class CartPage {
    public CartPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//*[@class='icon icon-remove-new'][1]")
    public WebElement remove;

    @FindBy(className = "priceBox__salePrice")
    public WebElement cartPrice;

    @FindBy(xpath = "(//*[@class='m-orderSummary__value'])[3]")
    public WebElement amount;

    @FindBy(id = "quantitySelect0-key-0")
    public WebElement productCount;


}
