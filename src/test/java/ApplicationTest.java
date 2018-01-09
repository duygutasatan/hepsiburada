import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationTest {

    private HomePage homePage;

    private String email = "ozgunduygu@yahoo.com";
    private String password = "123456dt";

    public ApplicationTest(){
        String chromeDriverPth = "./src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPth);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        this.homePage = new HomePage(driver);
    }

    @Test
    public void addProductToCartFromHBIfUserNotLoggedIn(){

        homePage.goTo();

        ProductDetailPage product = homePage.searchFor("iphone").goToProductDetail();

        String price = product.getPrice();

        CartPage cart = product.addToCart();

        waitTo(3000);

        Boolean result = cart.checkPrice(price);

        Assertions.assertTrue(result);
    }

    @Test
    public void addProductToCartFromOtherSellerIfUserNotLoggedIn(){

        homePage.goTo();

        ProductDetailPage product = homePage.searchFor("iphone").goToProductDetail();

        waitTo(2000);

        ProductDetailPage productOtherSeller = product.goToOtherSeller();

        waitTo(3000);

        String price = productOtherSeller.getPrice();

        CartPage cart = productOtherSeller.addToCart();

        waitTo(3000);

        Boolean result = cart.checkPrice(price);

        Assertions.assertTrue(result);
    }

    @Test
    public void addProductToCartFromHBIfUserLoggedIn(){

        homePage.goTo();
        LoginPage login = homePage.clickToLogin();

        waitTo(1000);

        login.login(email,password);

        waitTo(2000);

        Boolean success = homePage.isLoggedIn();

        if (success){

            ProductDetailPage product = homePage.searchFor("iphone").goToProductDetail();

            waitTo(2000);

            String price = product.getPrice();
            CartPage cart = product.addToCart();

            waitTo(3000);

            Boolean result = cart.checkPrice(price);

            Assertions.assertTrue(result);
        }
    }

    @Test
    public void addProductToCartFromOtherSellerIfUserLoggedIn(){

        homePage.goTo();
        LoginPage login = homePage.clickToLogin();

        waitTo(1000);

        login.login(email,password);

        waitTo(2000);

        Boolean success = homePage.isLoggedIn();

        if (success){

            ProductDetailPage product = homePage.searchFor("iphone").goToProductDetail();

            waitTo(2000);

            product = product.goToOtherSeller();

            waitTo(2000);

            String price = product.getPrice();
            CartPage cart = product.addToCart();

            waitTo(3000);

            Boolean result = cart.checkPrice(price);

            Assertions.assertTrue(result);
        }
    }

    private void waitTo(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
