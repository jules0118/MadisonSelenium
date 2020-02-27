import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.browser.Browser;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class MadisonTest {
      WebDriver driver;

@Before
    public void OpenDriver(){
        System.setProperty("webdriver.chrome.driver", "C:/Users/iuliamusat/IdeaProjects/selenium/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com/");
    }
   // @After
 //   public void CloseDriver(){
   // driver.quit();
    //}

    @Test
    public void checkHomepage() {
//2. Get the title
        assert(driver.getTitle().equals("Madison Island"));
        System.out.println(driver.getTitle());
        //3.Get the current url
        System.out.println(driver.getTitle());
        driver.getCurrentUrl();
        //System.out.println(driver.findElement(By.linkText("http://qa3.dev.evozon.com/")));
        //3.click on the logo
        driver.findElement(By.className("logo")).click();
        //Check that the logo is there
        WebElement logocheck = driver.findElement(By.className("large"));
        boolean logocheck2 =  driver.findElement(By.className("large")).isDisplayed();
        assertTrue(logocheck2);

        driver.navigate().to("http://qa3.dev.evozon.com/");
        // url check
        boolean urlcheck = driver.getCurrentUrl().equals("http://qa3.dev.evozon.com/");
        assertTrue(urlcheck);
        boolean logoPresent = driver.findElement(By.className("logo")).isDisplayed();
        assertTrue(logoPresent);

        //Navigate to a different page
        driver.navigate().to("http://qa3.dev.evozon.com/home-decor.html");
        //Navigate back
        driver.navigate().back();
        //Navigate forward
        driver.navigate().forward();
        //refresh the page
        driver.navigate().refresh();
    }

    @Test
    public void checkDropDownAccount() {
        //2.Click on the account link from the header
        WebElement account =  driver.findElement(By.cssSelector("a.skip-account"));
        account.click();

        //2. dropdown appears
        Assert.assertTrue(driver.findElement(By.cssSelector("#header-account ul")).isDisplayed());
        //3. Account appears
      Assert.assertEquals("ACCOUNT",account.getText());

    }

    @Test
    public void checkLanguages() {
        //2.List the number of languages
        driver.findElement(By.cssSelector("#select-language"));
        WebElement dropdown = driver.findElement(By.cssSelector("#select-language"));
        dropdown.click();
        List list = driver.findElements(By.cssSelector("#select-language > option"));
        System.out.println(list.size());
        Select sec = new Select(dropdown);
        sec.selectByVisibleText("French");
        //Make sure there are only 3 languages
        Assert.assertEquals(list.size(),3);
    }

    @Test
    public void checkSearch() {
        //2.Clear search field
        WebElement search = driver.findElement(By.cssSelector("#search"));
        search.clear();
        //3. Woman
        WebElement text = driver.findElement(By.cssSelector("#search"));
        text.sendKeys("woman");
        search.submit();
        //Search text
        String title = driver.findElement(By.className("page-title")).getText().toUpperCase();
        Assert.assertTrue(title.contains("WOMAN"));
    }

    @Test
    public void ProductsList() {
        //2.List the number new products
        List list = driver.findElements(By.cssSelector(".item.last"));
        System.out.println(list.size());
        //3.list name
        List<WebElement> products1 = driver.findElements(By.cssSelector(".product-name"));
        System.out.println(products1);
        Iterator<WebElement> itr = products1.iterator();
        while (itr.hasNext()) {
            String itemText = itr.next().getText();
            System.out.println(itemText);
        }
    }

    @Test
    public void checkNavigationMenu() {
        List list = driver.findElements(By.cssSelector(".item.last"));
        System.out.println(list.size());
        //3.list name
        List<WebElement> products2 = driver.findElements(By.cssSelector("#header-nav"));
        System.out.println(products2);
        Iterator<WebElement> itr = products2.iterator();
        while (itr.hasNext()) {
            String itemText = itr.next().getText();
            System.out.println(itemText);
            //get on a specific item on sale
         driver.findElement(By.xpath("//a[text()='Sale']")).click();
         //go to a product on sale
            driver.findElement(By.id("product-collection-image-423")).click();
            Assert.assertEquals("http://qa2.dev.evozon.com/racer-back-maxi-dress-613.html",driver.getCurrentUrl());
        }
    }
    @Test
    public void Checkout() {
        Actions actions = new Actions(driver);
        WebElement womenCategory = driver.findElement(By.xpath("//ol/li/a[text()='Women']"));
        actions.moveToElement(womenCategory).build().perform();
        driver.findElement(By.xpath("//a[text()='Tops & Blouses']")).click();
        driver.findElement(By.id("product-collection-image-421")).click();
        driver.findElement(By.cssSelector("#option21")).click();
        driver.findElement(By.cssSelector("#option80")).click();
        driver.findElement(By.className("add-to-cart-buttons")).click();
        WebElement search = driver.findElement(By.cssSelector("#search"));
        WebElement text = driver.findElement(By.cssSelector("#search"));
        text.sendKeys("glasses");
        search.submit();
        driver.findElement(By.id("product-collection-image-338")).click();
        driver.findElement(By.className("add-to-cart-buttons")).click();
        driver.findElement(By.className("method-checkout-cart-methods-onepage-bottom")).click();
        WebElement checkbox1 = driver.findElement(By.cssSelector("li.control:nth-child(1)"));
        checkbox1.click();
        driver.findElement(By.cssSelector("button#onepage-guest-register-button")).click();
        String stringCotains = driver.findElement(By.className("page-title")).getText().toUpperCase();
        String searchWord = driver.findElement(By.cssSelector("#search")).getText().toUpperCase();
        assertTrue(stringCotains.contains(searchWord));
        driver.findElement(By.className("skip-cart")).click();
        driver.findElement(By.className("checkout-button")).click();
        driver.findElement(By.cssSelector("#onepage-guest-register-button")).click();
        driver.findElement(By.id("billing:firstname")).click();
        WebElement firstname = driver.findElement(By.id("billing:firstname"));
        firstname.sendKeys("First name");
        firstname.submit();
        driver.findElement(By.id("billing:middlename")).click();
        WebElement middlename = driver.findElement(By.id("billing:middlename"));
        middlename.sendKeys("Middle name");
        middlename.submit();
        WebElement lastname = driver.findElement(By.id("billing:lastname"));
        lastname.sendKeys("last name");
        lastname.submit();
        WebElement email = driver.findElement(By.id("billing:email"));
        email.sendKeys("email@domain.com");
        email.submit();
        WebElement address = driver.findElement(By.id("billing:street1"));
        address.sendKeys("strada faranume, nr 1");
        address.submit();
        WebElement city= driver.findElement(By.id("billing:city"));
        city.sendKeys("oras");
        city.submit();
        driver.findElement(By.id("billing:region_id"));
        Select country = new Select(driver.findElement(By.id("billing:country_id")));
        country.selectByValue("RO");
        Select state = new Select(driver.findElement(By.id("billing:region_id")));
        state.selectByValue("278");
        WebElement postcode= driver.findElement(By.id("billing:postcode"));
        postcode.sendKeys("postcode");
        postcode.submit();
        WebElement phone= driver.findElement(By.id("billing:telephone"));
        phone.sendKeys("telefon");
        phone.submit();
        driver.findElement(By.id("billing:use_for_shipping_no")).click();
//        driver.findElement(By.id("billing-buttons-container")).click();
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //driver.findElement(By.)
        driver.findElement(By.cssSelector("#billing-buttons-container > button")).click();


// // driver.findElement(By.id("billing-buttons-container")).click();
// driver.findElement(By.cssSelector("#billing-buttons-container > button")).click();
//
//        driver.findElement(By.cssSelector("#opc-shipping > div.step-title > h2")).click();
// driver.findElement(By.cssSelector("#opc-shipping > div.step-title > h2")).click();


        WebElement firstNameSi = driver.findElement(By.id("shipping:firstname"));//select first name
        while(true){
            try{
                WebDriverWait wait = new WebDriverWait(driver,10);
                wait.until(ExpectedConditions.visibilityOf(firstNameSi)).sendKeys("Maria");
                break;
            }catch (Exception e){
                driver.findElement(By.cssSelector("#opc-shipping > div.step-title > h2")).click();

            }
        }
        WebElement lastNameSi = driver.findElement(By.id("shipping:lastname"));
        lastNameSi.sendKeys("lastnameSi");
        lastNameSi.submit();
        WebElement addressSi = driver.findElement(By.id("shipping:street1"));
        addressSi.sendKeys("strada faranume, nr 1");
        addressSi.submit();
        WebElement citySi= driver.findElement(By.id("shipping:city"));
        citySi.sendKeys("oras");
        citySi.submit();
        driver.findElement(By.id("shipping:region_id"));
        Select countrySi = new Select(driver.findElement(By.id("shipping:country_id")));
        countrySi.selectByValue("RO");
        Select stateSi = new Select(driver.findElement(By.id("shipping:region_id")));
        stateSi.selectByValue("278");
        WebElement postcodeSi= driver.findElement(By.id("shipping:postcode"));
        postcodeSi.sendKeys("postcode");
        postcodeSi.submit();
        WebElement phoneSi= driver.findElement(By.id("shipping:telephone"));
        phoneSi.sendKeys("telefon");
        phoneSi.submit();
        WebElement continueButtonShippingTab = driver.findElement(By.cssSelector("#shipping-buttons-container button[title=\"Continue\"]"));
        continueButtonShippingTab.click();
// driver.findElement(By.cssSelector(".sp-methods label[for*='free']")).click();
//        WebElement ButtonShippingMethod = driver.findElement(By.cssSelector(".sp-methods label[for*='free']"));
//        ButtonShippingMethod.click();
//        driver.findElement(By.id("shipping-method-buttons-container")).click();
//        driver.findElement(By.id("payment-buttons-container")).click();
//        driver.findElement(By.id("review-buttons-container")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("s_method_freeshipping_freeshipping"))).click();
        driver.findElement(By.cssSelector("#shipping-method-buttons-container .button")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-buttons-container .button"))).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#review-buttons-container .button"))).click();
        String orderPLacedMessage = "THANK YOU FOR YOUR PURCHASE!";
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sub-title"))).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".sub-title")).getText().toUpperCase(), orderPLacedMessage.toUpperCase());

    }
}