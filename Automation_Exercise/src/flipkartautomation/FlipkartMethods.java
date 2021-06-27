package flipkartautomation;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartMethods {
	private WebDriver driver;
	private WebDriverWait wait;
	final String chromeDriver = "driver/chromedriver.exe";
	final String geckoDriver = "driver/geckodriver.exe";
	final String url = "https://www.google.com";
	final int timeout = 100;

	//Flipkart reusable functions
	public void enterInSearch(final String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		WebElement searchInput = driver.findElement(By.xpath("//input[@type='text']"));
		searchInput.click();
		searchInput.sendKeys(value);
		waitForPageload();
	}

	public void getSuggestions() {
		try {
			printSuggestions();
		}
		catch (Exception e) {
			//Facing issue in FireFox
			printSuggestions(); 
		}
	}

	public void printSuggestions() {
		waitForPageload();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='listbox']")));
		WebElement searchList = driver.findElement(By.xpath("//ul[@role='listbox']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//div[@role='option']//div[1]//span")));
		List<WebElement> listItems = searchList.findElements(By.xpath("//li//div[@role='option']//div[1]//span"));
		System.out.println("Google Suggestions: -");
		for(int i = 0; i<listItems.size(); i++) {
			String result = listItems.get(i).getText();
			System.out.println(result);
		}
	}

	public void clickOnSearch() {
		WebElement searchButton = driver.findElement(By.xpath("(//input[@value='Google Search'])[1]"));
		searchButton.click();
		waitForPageload();
	}

	public void clickOnFlipkart() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Flipkart']")));
		WebElement findLink = driver.findElement(By.xpath("//h3[text()='Flipkart']"));
		findLink.click();
		waitForPageload();
	}

	public void closeLoginPopup() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='✕']")));
		WebElement closeButton = driver.findElement(By.xpath("//button[text()='✕']"));
		closeButton.click();
		waitForPageload();
	}

	public void goToItem(final String value) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//img[@alt='"+value+"']")));
		WebElement selectItem = driver.findElement(By.xpath("//a//img[@alt='"+value+"']"));
		moveToElement(selectItem);
		waitForPageload();
	}

	public void chooseAppliancesItem(final String value) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//a[text()='"+value+"']")));
		WebElement selectItem = driver.findElement(By.xpath("//div//a[text()='"+value+"']"));
		moveToElement(selectItem);
		waitForPageload();
	}

	public void clickAppliancesItem(final String value) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//a[text()='"+value+"']")));
		WebElement selectItem = driver.findElement(By.xpath("//div//a[text()='"+value+"']"));
		selectItem.click();
		waitForPageload();
	}

	public void selectCompareCheckBox(final int itemNo) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='_1YokD2 _3Mn1Gg'])[2]")));
		WebElement frame = driver.findElement(By.xpath("(//div[@class='_1YokD2 _3Mn1Gg'])[2]"));
		WebElement selectCheckbox = frame.findElement(By.xpath("(//span//input[@type='checkbox'])["+itemNo+"]"));
		moveToElementUsingJS(selectCheckbox);
		waitForPageload();
	}

	public void clickCompareButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[text()='COMPARE']")));
		WebElement compareButton = driver.findElement(By.xpath("//a//span[text()='COMPARE']"));
		compareButton.click();
		waitForPageload();
	}

	public void printProductNameandPrice() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'col col-3')]//a")));
		List<WebElement> listItems1 = driver.findElements(By.xpath("//div[contains(@class,'col col-3')]//a"));
		List<WebElement> listItems2 = driver.findElements(By.xpath("//div[contains(@class,'col col-3')]//div[contains(@class,'_25')]//div[1]"));
		System.out.println("Product Names & Prices: -");
		for(int i = 0; i<listItems1.size(); i++) {
			String result1 = listItems1.get(i).getText();
			String result2 = listItems2.get(i).getText();
			System.out.println(result1 + ": " + result2);
		}
	}

	public void addToCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row EcigTC']//a//img")));
		List<WebElement> listItems1 = driver.findElements(By.xpath("//div[@class='row EcigTC']//a//img"));
		for(int i = 0; i<listItems1.size(); i++) {
			if(i != listItems1.size()) {
				WebElement iconImg = driver.findElement(By.xpath("(//div[@class='row EcigTC']//a//img)["+(i+1)+"]"));
				iconImg.click();
				waitForPageload();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='container']//div[1]//li[1]/button[text()='ADD TO CART']")));
				WebElement cartButton = driver.findElement(By.xpath("//*[@id='container']//div[1]//li[1]/button[text()='ADD TO CART']"));
				cartButton.click();
				waitForPageload();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'My Cart')]")));
			}
			if(i != listItems1.size()-1) {
				driver.navigate().back();
				waitForPageload();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='GO TO CART']")));
				driver.navigate().back();
				waitForPageload();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='row EcigTC']//a//img")));
			}
		}
	}

	public void enterPinCode(final String pin) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter delivery pincode']")));
		WebElement pincode = driver.findElement(By.xpath("//input[@placeholder='Enter delivery pincode']"));
		pincode.clear();
		pincode.sendKeys(pin);
	}

	public void clickCheckButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span[text()='Check']")));
		WebElement check = driver.findElement(By.xpath("//div//span[text()='Check']"));
		check.click();
		waitForPageload();
	}

	public void clickOnDropdown() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[contains(@src,'data:image')])[2]")));
		WebElement clickDropdown = driver.findElement(By.xpath("(//img[contains(@src,'data:image')])[2]"));
		clickDropdown.click();
		waitForPageload();
	}

	public void printMessage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='container']//div[5])[2]")));
		WebElement msg = driver.findElement(By.xpath("(//*[@id='container']//div[5])[2]"));
		System.out.println("\nDelivery Message: -\n" + msg.getText());
	}

	//Reusable Functions
	public void launchUrl(String browser) {
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", geckoDriver);
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			driver = new ChromeDriver();
		}
		else{
			System.out.println("Browser is not correct");
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, timeout);
	}

	public void closeWindow() {
		waitForPageload();
		driver.quit();
	} 	

	public void moveToElement(final WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public void moveToElementAndClick(final WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	public void moveToElementUsingJS(final WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void waitForPageload() {
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
}
