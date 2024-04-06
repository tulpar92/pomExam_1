package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;
import utils.ConfigReader;
import utils.Driver;
import utils.Product;


import static utils.Driver.wait;
import static utils.Methods.*;

public class TS_1 {

    @Test
    public void Beymen() throws InterruptedException {
        //ön tanımlar
        Actions action = new Actions(Driver.getDriver());
        HomePage homepage=new HomePage();
        SearchPage searchPage= new SearchPage();
        ProductPage productpage= new ProductPage();
        CartPage cartpage=new CartPage();

        //beymen sitesine girili
        Driver.getDriver().get(ConfigReader.getProperty("beymenURL"));

        //çerezleri reddet ve cinsiyet seçimini erkek yap
        wait.until(ExpectedConditions.visibilityOf(homepage.rejectCookies));
        homepage.rejectCookies.click();
        homepage.genderMan.click();

        //excelden veri oku 1-1
        String excelpath=ConfigReader.getProperty("excelpath");
        String searchproduct=readExcelCell(excelpath,1,1);
        action
                .moveToElement(homepage.searchBar)
                .click()
                .sendKeys(searchproduct)
                .build().perform();

        try {
            homepage.searchBar.clear();
        }
        catch (InvalidElementStateException exception) {
                action.moveToElement(homepage.searchBar).click().
                sendKeys(Keys.BACK_SPACE).
                sendKeys(Keys.BACK_SPACE).
                sendKeys(Keys.BACK_SPACE).
                sendKeys(Keys.BACK_SPACE).
                        build().perform();
        }

        //excelden veri oku 1-2
        searchproduct=readExcelCell(excelpath,1,2);
        action
                .sendKeys(homepage.searchBar,searchproduct)
                .sendKeys(Keys.ENTER)
                .build().perform();

        wait.until(ExpectedConditions.visibilityOf(searchPage.searchResult));

        searchPage.product.click();
        wait.until(ExpectedConditions.elementToBeClickable(productpage.addBasket));

        //ürün isim ve fiyatı kaydedildi
        Product p = new Product();
        p.productName=productpage.productName.getText();
        p.productPrice=productpage.price.getText();

        //ürün bedeni seçilip sepete eklendi
        productpage.sizeM.click();
        productpage.addBasket.click();

        //ürün bilgileri txt dosyasına yazdırıldı
        writeProductToFile(p.productName,p.productPrice);


        //sepete git
        productpage.cart.click();
        wait.until(ExpectedConditions.elementToBeClickable(cartpage.amount));

        Assert.assertEquals(parsePrice(p.productPrice),parsePrice(cartpage.cartPrice.getText()));

        //adet artır
        Select dbx_ProductCount=new Select(cartpage.productCount);
        dbx_ProductCount.selectByValue("2");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("notifyTitle")));

        //ürünü sil
        action.
                moveToElement(cartpage.remove).
                click().
                build().perform();

        Driver.getDriver().navigate().refresh();
        WebElement message= Driver.getDriver().findElement(By.xpath("(//*[@class='m-empty__messageTitle'])[1]"));
        Assert.assertEquals("SEPETINIZDE ÜRÜN BULUNMAMAKTADIR",message.getText());


    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }
}
