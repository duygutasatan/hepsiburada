import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"addToCart\"]")
    private WebElement addToCart;

    @FindBy(xpath = "//*[@id=\"offering-price\"]")
    private WebElement price;

    @FindBy(xpath = "/html/body/div[4]/main/div[2]/section[1]/div[4]/div/div[4]/div[2]/div[2]/div/div[1]/a")
    private WebElement otherSeller;

    @FindBy(xpath = "//*[@id=\"merchant-list\"]/tbody/tr[3]/td[1]/div[1]/span")
    private WebElement otherSellerProduct;


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }


    public CartPage addToCart(){
        this.addToCart.click();
        return new CartPage(this.driver);
    }

    public ProductDetailPage goToOtherSeller(){

        this.otherSeller.click();

        super.waitTo(2000);

        this.otherSellerProduct.click();

        super.waitTo(2000);

        try{
            WebElement kampanya = this.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[5]/div[2]"));
            kampanya.click();
        }
        catch (Exception ex){
        }

        super.waitTo(2000);
        return new ProductDetailPage(this.driver);

    }

    public String getPrice(){
        return this.price.getText();
    }
}
