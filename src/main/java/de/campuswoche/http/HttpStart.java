package de.campuswoche.http;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
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
		Connection connection = connect(
				"http://craigslist.pottsfam.com/index872dijasydu2iuad27aysdu2yytaus6d2ajsdhasdasd2.php");
		connection.method(Method.POST);
		connection.data("auid2yjauysd2uasdasdasd", "blabla");
		connection.data("kjauysd6sAJSDhyui2yasd", "blabla");
		connection.followRedirects(false);
		LOG.info("{}", connection.execute().parse());
	}

}
