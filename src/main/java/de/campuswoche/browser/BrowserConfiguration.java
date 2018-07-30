package de.campuswoche.browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;

@Configuration
public class BrowserConfiguration {

	long timeout = 15L;

	@Bean(destroyMethod = "quit")
	JBrowserDriver driver() {
		Settings.Builder settingsBuilder = Settings.builder();
		settingsBuilder.logger(null);
		settingsBuilder.headless(false);
		settingsBuilder.screen(new Dimension(1366, 768));

		Settings settings = settingsBuilder.build();

		JBrowserDriver driver = new JBrowserDriver(settings);

		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);

		return driver;
	}

	@Bean
	WebDriverWait webDriverWait() {
		return new WebDriverWait(driver(), timeout);
	}

}
