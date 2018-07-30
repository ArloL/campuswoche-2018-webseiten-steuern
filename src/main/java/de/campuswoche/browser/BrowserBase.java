package de.campuswoche.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;

public class BrowserBase {

	@Autowired
	WebDriverWait webDriverWait;
	@Autowired
	JBrowserDriver browser;
	@Autowired
	JavaMailSender emailSender;

	public void openWebsite(String url) {
		browser.get(url);
	}

	public WebElement findElement(By by) {
		return webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void clickLinkWithText(String linkText) {
		WebElement element = findElement(By.partialLinkText(linkText));
		element.click();
	}

	public void sleep(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(5);
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

	public void sendSimpleMessage(String from, String to, String subject,
			String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

}
