import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListPage extends PageObject {

    @FindBy(xpath = "(//*[@class=\"hb-pl-cn\"])[1]")
    private WebElement product;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailPage goToProductDetail(){
        this.product.click();

        return new ProductDetailPage(this.driver);
    }
}
