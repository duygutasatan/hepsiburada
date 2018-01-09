import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"form-item-list\"]/ul/li[1]/div/div[3]/div[2]")
    private WebElement price;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkPrice(String price){
        return this.price.getText().equals(price);
    }
}
