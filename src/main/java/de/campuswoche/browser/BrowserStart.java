package de.campuswoche.browser;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrowserStart implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(BrowserStart.class);

	public static void main(String[] args) {
		SpringApplication.run(BrowserStart.class, args);
	}

	@Autowired
	WebDriver browser;
	@Autowired
	WebDriverWait webDriverWait;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		qis();

		vertretungsplan();

		beiMilla();

		truckstopBocholt();

		browser.quit();
	}

	private void qis() {
		browser.get("https://qis.w-hs.de");

		WebElement benutzername = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#asdf")));
		benutzername.sendKeys("123456");

		WebElement password = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#fdsa")));
		password.sendKeys("123456");

		WebElement anmelden = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.submit")));
		anmelden.click();
	}

	private void vertretungsplan() {
		browser.get("http://www.bkbocholt-west.de/medien/Stundenplan/Klassenplan/index_ITM.htm");

		WebElement vertretungsplan = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table[rules=all]")));

		LOG.info("Vetretungsplan: {}", vertretungsplan.getText());
	}

	private void beiMilla() {
		browser.get("https://cityhotel-bocholt.de/wochen-mittagskarte/");

		WebElement milla = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.aligncenter.size-large")));

		LOG.info("Milla Links: {}", milla.getAttribute("src"));
	}

	private void truckstopBocholt() {
		browser.get("http://truckstop-bocholt.de/");

		WebElement mittagstisch = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Mittagstisch")));

		String link = mittagstisch.getAttribute("href");

		browser.get(link);

		browser.switchTo().frame("CustomTappIframe");

		List<WebElement> elements = webDriverWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("iframe")));

		LOG.info("TruckStop Links: {}", elements.stream().map(e -> e.getAttribute("src")).collect(Collectors.toList()));
	}

}
