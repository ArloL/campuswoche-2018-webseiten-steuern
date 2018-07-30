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

		openWebsite("https://www.cinemaxx.de/kinoprogramm/munchen");

		sleep();

		openWebsite(
				"https://www.cinemaxx.de/film/harry-potter-und-der-stein-der-weisen/");

		sleep();

		clickLinkWithText("20:00");

		sleep();

		findElement(By.id("step2-btn")).click();

		sleep(500);
	}

}
