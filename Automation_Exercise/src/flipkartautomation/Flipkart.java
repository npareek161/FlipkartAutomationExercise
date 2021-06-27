package flipkartautomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Flipkart {
	private FlipkartMethods fm;

	@BeforeTest
	@Parameters("browser")
	public void setUp(String browser) {
		fm = new FlipkartMethods();
		fm.launchUrl(browser);
	}

	@Test
	public void flipkart() {
		fm.enterInSearch("flipkart");
		fm.getSuggestions();
		fm.clickOnSearch();
		fm.clickOnFlipkart();
		fm.closeLoginPopup();
		fm.goToItem("Appliances");
		fm.chooseAppliancesItem("Air Conditioners");
		fm.clickAppliancesItem("Window ACs");
		fm.selectCompareCheckBox(2);
		fm.selectCompareCheckBox(3);
		fm.selectCompareCheckBox(6);
		fm.clickCompareButton();
		fm.printProductNameandPrice();
		fm.addToCart();
		fm.enterPinCode("302001");
		fm.clickCheckButton();
		fm.printMessage();
		fm.clickOnDropdown();
		fm.enterPinCode("411057");
		fm.clickCheckButton();
		fm.printMessage();
	}

	@AfterTest
	public void closeBrowser() {
		fm.closeWindow();
	}
}
