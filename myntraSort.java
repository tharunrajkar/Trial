package proj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class myntraSort {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"/Users/saranraj/eclipse/Testing/Automation/Drivers/chromedriver-mac-arm64/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://www.myntra.com/");

		Actions act = new Actions(driver);

		WebElement mini = driver.findElement(By.xpath("(//a[@class='desktop-main'])[3]"));
		act.moveToElement(mini).build().perform();

		WebElement tshirt = driver.findElement(By.xpath("//a[text()='T-Shirts']"));
		act.moveToElement(tshirt).build().perform();
		Thread.sleep(30000);
		act.click(tshirt).build().perform();

		// List<WebElement> price =
		// driver.findElements(By.xpath("//div[@class='product-price']"));
//        int prod= price.size();
//        System.out.println("total no of products: "+prod);

		List<WebElement> discount = driver.findElements
				(By.xpath("//span[@class='product-discountedPrice']"));
		discount.size();

		for (WebElement webElement : discount) {
			System.out.println(webElement.getText());

		}

		List<Integer> values = new ArrayList<Integer>();

		for (int i = 0; i < discount.size(); i++) {
			String text = discount.get(i).getText();
			String replace = text.replace("Rs. ", "").trim();
			//System.out.println(replace);
			Integer int_val = Integer.valueOf(replace);
			//System.out.println(int_val);
			values.add(int_val);

		}

		Integer min = Collections.min(values);
		Integer max = Collections.max(values);
		//System.out.println("minimum values" + min);
		System.out.println("maximum values" + max);
		
		WebElement minPrice = driver.findElement
				(By.xpath("//span[@class='product-discountedPrice' and text()='"+min+"']//ancestor::div[@class='product-productMetaInfo']"));
		System.out.println(minPrice.getText());
		WebElement maxPrice = driver.findElement
				(By.xpath("//span[@class='product-discountedPrice' and text()='"+max+"']//ancestor::div[@class='product-productMetaInfo']"));
		System.out.println(maxPrice.getText());
	}

}
