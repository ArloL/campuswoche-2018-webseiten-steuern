package de.campuswoche.http;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpStart extends HttpBase implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(HttpStart.class);

	public static void main(String[] args) {
		SpringApplication.run(HttpStart.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		openWebsite("https://www.kinodrom.de/programm");
		
		Elements links = document.select("a");

		LOG.info("{}", links);
	}

}
