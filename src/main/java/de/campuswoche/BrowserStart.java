package de.campuswoche;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	private static final Logger LOG =
	        LoggerFactory.getLogger(BrowserStart.class);

	public static void main(String[] args) {
		SpringApplication.run(BrowserStart.class, args);
	}

	@Autowired
	WebDriver driver;
	@Autowired
	WebDriverWait webDriverWait;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		driver.get("http://www.youtube.com");

		List<WebElement> elements = driver.findElements(By.cssSelector("a"));

		List<String> links = elements.stream()
		    .filter(e -> e.getText() != null && !e.getText().isEmpty())
		    .map(WebElement::getText).collect(toList());

		LOG.info("{}", links);

		driver.quit();

	}

}
