package webserver;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;

public class Webserver {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Home
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String html = new String(Files.readAllBytes(Paths.get("home.html")));

                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");

                exchange.sendResponseHeaders(200, html.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(html.getBytes());
                os.close();
            }
        });

        // Orari
        server.createContext("/orari", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String html = new String(Files.readAllBytes(Paths.get("orari.html")));

                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");

                exchange.sendResponseHeaders(200, html.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(html.getBytes());
                os.close();
            }
        });

        // Registrator
        server.createContext("/registrator", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String html = new String(Files.readAllBytes(Paths.get("registrator.html")));

                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");

                exchange.sendResponseHeaders(200, html.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(html.getBytes());
                os.close();
            }
        });

        // CSS
        server.createContext("/style.css", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String css = new String(Files.readAllBytes(Paths.get("style.css")));

                exchange.getResponseHeaders().add("Content-Type", "text/css; charset=UTF-8");

                exchange.sendResponseHeaders(200, css.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(css.getBytes());
                os.close();
            }
        });

        // Avvio server
        System.out.println("Server in ascolto sulla porta 8080...");
        server.start();
    }
}
