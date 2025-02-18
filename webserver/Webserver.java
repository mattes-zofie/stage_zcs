package webserver;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class Webserver {

    private static final String FRONTEND_DIR = "frontend";
    private static final String CHARSET = "UTF-8";
    private static final int PORT = 8080;
    private static final String TEXT_HTML = "text/html";
    
    private static final Logger logger = Logger.getLogger(Webserver.class.getName());

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", createHandler("index.html", TEXT_HTML));
        server.createContext("/registrator.html", createHandler("registrator.html", TEXT_HTML));
        server.createContext("/stampo.html", createHandler("stampo.html", TEXT_HTML));
        server.createContext("/style.css", createHandler("style.css", "text/css"));

        logger.info("Server in ascolto sulla porta " + PORT + "...");
        server.start();
    }

    private static HttpHandler createHandler(final String fileName, final String mimeType) {
        return new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                logger.info("Request received: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());
                byte[] response = Files.readAllBytes(Paths.get(FRONTEND_DIR, fileName));
                exchange.getResponseHeaders().set("Content-Type", mimeType + "; charset=" + CHARSET);
                exchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
                logger.info("Served " + fileName + " (" + response.length + " bytes)");
            }
        };
    }
}
