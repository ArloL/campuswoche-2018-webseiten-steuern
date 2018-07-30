package de.campuswoche.http;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class HttpBase {

	@Autowired
	Environment environment;
	Document document;

	public void openWebsite(String url) throws IOException {
		document = connect(url).get();
	}

	public Connection connect(String url) {
		Connection connect = Jsoup.connect(url);

		String host = environment.getProperty("jbd.proxyhost");
		Integer port = environment.getProperty("jbd.proxyport", Integer.class);
		if (host != null && port != null) {
			return connect.proxy(host, port);
		}

		return connect;
	}

}
