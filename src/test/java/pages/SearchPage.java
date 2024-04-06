package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class SearchPage extends BasePage{
    public SearchPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="productListTitle")
    public WebElement searchResult;

    @FindBy(xpath = "(//*[@class='m-productImageList'])[1]")
    public WebElement product;
}
