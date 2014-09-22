package com.carma.swagger.doclet.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * The App represents
 * @version $Id$
 * @author conor.roche
 */
public class App {

	/**
	 * Starts the lightweight HTTP server serving the JAX-RS application.
	 * @return new instance of the lightweight HTTP server
	 * @throws IOException
	 */
	static HttpServer startServer() throws IOException {
		// create a new server listening at port 8080
		HttpServer server = HttpServer.create(new InetSocketAddress(getBaseURI().getPort()), 0);

		// create a handler wrapping the JAX-RS application
		HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JaxRsApplication(), HttpHandler.class);

		// map JAX-RS handler to the server root
		server.createContext(getBaseURI().getPath(), handler);

		// start the server
		server.start();

		return server;
	}

	/**
	 * This runs the jersey example
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Swagger Doclet Jersey 2 Example Application");

		HttpServer server = startServer();

		System.out.println("Application started.\n" + "Try accessing " + getBaseURI() + "beanparam in the browser.\n" + "Hit enter to stop the application...");
		System.in.read();
		server.stop(0);
	}

	private static int getPort(int defaultPort) {
		final String port = System.getProperty("jersey.config.test.container.port");
		if (null != port) {
			try {
				return Integer.parseInt(port);
			} catch (NumberFormatException e) {
				System.out.println("Value of jersey.config.test.container.port property" + " is not a valid positive integer [" + port + "]."
						+ " Reverting to default [" + defaultPort + "].");
			}
		}
		return defaultPort;
	}

	/**
	 * Gets base {@link URI}.
	 * @return base {@link URI}.
	 */
	public static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(getPort(8080)).build();
	}

}
