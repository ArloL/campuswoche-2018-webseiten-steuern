package de.campuswoche.browser;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;

public class BrowserBase {

	@Autowired
	WebDriverWait webDriverWait;
	@Autowired
	JBrowserDriver browser;

	public void openWebsite(String url) {
		browser.get(url);
	}

	public WebElement findElement(By by) {
		return webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public List<WebElement> findElements(By by) {
		return webDriverWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	public void clickLinkWithText(String linkText) {
		WebElement element = findElement(By.partialLinkText(linkText));
		scrollIntoView(element);
		removeCssClass(findElement(By.className("nav")), "sticky");
		element.click();
	}

	public void sleep() {
		sleep(5);
	}

	public void sleep(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
		}
	}

	public void sendKeys(String keys) {
		browser.getKeyboard().sendKeys(keys);
	}

	public void sendAtKey() {
		Keyboard keyboard = browser.getKeyboard();

		keyboard.pressKey(Keys.CONTROL);
		keyboard.pressKey(Keys.ALT);
		keyboard.pressKey("q");
		keyboard.releaseKey("q");
		keyboard.releaseKey(Keys.ALT);
		keyboard.releaseKey(Keys.CONTROL);
	}

	public void removeElement(String cssSelector) {
		String script = "";
		script += "var node = document.querySelector('";
		script += cssSelector;
		script += "');";
		script += "node.parentNode.removeChild(node);";
		browser.executeScript(script);
	}

	public void removeElements(String cssSelector) {
		String script = "var nodes = document.querySelectorAll(' ";
		script += cssSelector;
		script += "'), i;";
		script += "for (i = 0; i < nodes.length; ++i) {";
		script += "  var node = nodes[i];";
		script += "  node.parentNode.removeChild(node);";
		script += "}";
		browser.executeScript(script);
	}

	public void setValue(String cssSelector, String value) {
		String script = "";
		script += "var node = document.querySelector('";
		script += cssSelector;
		script += "');";
		script += "node.value = '" + value + "';";
		browser.executeScript(script);
	}

	public void setValue(WebElement element, String value) {
		String script = "arguments[0].value = '" + value + "';";
		browser.executeScript(script, element);
	}

	public void scrollIntoView(WebElement element) {
		browser.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void removeCssClass(WebElement element, String cssClass) {
		browser.executeScript(
				"arguments[0].classList.remove('" + cssClass + "');", element);
	}

}
