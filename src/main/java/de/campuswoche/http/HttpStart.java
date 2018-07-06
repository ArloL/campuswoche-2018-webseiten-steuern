package de.campuswoche.http;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpStart implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(HttpStart.class);

	public static void main(String[] args) {
		SpringApplication.run(HttpStart.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Document document = connect("https://www.youtube.com").get();

		Elements elements = document.select("a");

		List<String> links = elements.stream()
		    .filter(e -> e.text() != null && !e.text().isEmpty())
		    .map(Element::text).collect(toList());

		LOG.info("{}", links);
	}

	Connection connect(String url) {
		return Jsoup.connect(url).proxy("proxy.herbrand.rz.evosec.de", 8080);
	}

}
