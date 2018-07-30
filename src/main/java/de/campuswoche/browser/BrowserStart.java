package de.campuswoche.browser;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrowserStart extends BrowserBase implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(BrowserStart.class);

	public static void main(String[] args) {
		SpringApplication.run(BrowserStart.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("Reserviere Plätze");

		openWebsite("https://www.kinodrom.de/");

		sleep(5);

		clickLinkWithText("Programm/Tickets");

		sleep(5);

		clickLinkWithText("Ant-Man and the Wasp");

		sleep(5);

		findElement(By.cssSelector("a[data-bis='2018-07-30*20:00:00']"))
				.click();

		sleep(5);

		browser.switchTo().frame("kinoheld-widget");

		findElement(By.cssSelector("div[data-r='Q'][data-n='1']")).click();

		sleep(5);

		findElement(By.cssSelector("button[data-type=reservation]")).click();

		sleep(5);

		findElement(By.id("reservation--email")).click();

		sendKeys("a.okeeffe");
		sendAtKey();
		sendKeys("evosec.de");

		sleep(5);

		findElement(By.cssSelector("label[for=reservation--tos] p")).click();

		sleep(5);

		findElement(By.cssSelector("button[data-type=next]")).click();

		sleep(5);

		String stornoLink = findElement(By.xpath(
				"//p[contains(text(),'Stornierung der Reservierung')]/a"))
						.getAttribute("href");

		openWebsite(stornoLink);

		sleep(5);

		findElement(By.cssSelector("button[data-type=cancel_reservation]"))
				.click();

		LOG.info("Plätze reserviert");

		browser.quit();
	}

}
