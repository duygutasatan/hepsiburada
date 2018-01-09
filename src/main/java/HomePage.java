import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    @FindBy(id="productSearch")
    private WebElement search;

    @FindBy(id = "buttonProductSearch")
    private WebElement searchButton;

    private WebElement signIn;

    @FindBy(id="myAccount")
    private WebElement myAccount;

    @FindBy(xpath = "//*[@id=\"myAccount\"]/a[1]")
    private WebElement userName;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goTo(){
        this.driver.get("http://www.hepsiburada.com");
    }

    public LoginPage clickToLogin(){

        Actions action = new Actions(this.driver);
        action.moveToElement(this.driver.findElement(By.id("myAccount"))).build().perform();

        super.waitTo(2000);

        WebElement login = this.driver.findElement(By.id("login"));

        action.moveToElement(login).build().perform();
        login.click();

        return new LoginPage(this.driver);
    }

    public ProductListPage searchFor(String query){
        this.search.sendKeys(query);
        this.searchButton.submit();

        return new ProductListPage(this.driver);
    }

    public Boolean isLoggedIn(){
        return !this.userName.getAttribute("style").equals("display: none");
    }
}
