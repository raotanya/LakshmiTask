package mercator.steps;

import mercator.driver.BasePage;
import mercator.pages.TestloginPage;
import mercator.pages.TestproductPage;
import mercator.pages.TestcartPage;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutomationTestSteps implements En {

    private Logger log = LoggerFactory.getLogger(AutomationTestSteps.class);

    TestloginPage testloginPage = new TestloginPage();
    TestproductPage productsPage = new TestproductPage();
    TestcartPage testcartPage = new TestcartPage();

    String maxPrice = "";
    public AutomationTestSteps() {

        Given("I open the test app", () -> {
            testloginPage.openUrl(BasePage.getProperty("baseUrl"));
        });

        And("I login to test app", () -> {
            testloginPage.login();
        });

        Then("I assert the page title to be {string}", (String title) -> {
            Assert.assertEquals("Page name not matched", title, productsPage.getPageTitle());
        });

        Then("I add highest price item to cart", () -> {
            maxPrice = productsPage.getMaxPrice();
            System.out.println("Max Item Price: "+maxPrice);
            productsPage.addCostlyItemToCart();
        });

        And("I click on cart icon on the products page", () -> {
            productsPage.clickOnCart();
        });

        Then("I validate the price in the cart page", () -> {
            Assert.assertEquals("Fail: Wrong item added to cart", maxPrice, testcartPage.getTheItemPrice());
        });
    }

}
