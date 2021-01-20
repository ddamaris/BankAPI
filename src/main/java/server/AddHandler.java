package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import services.AddCard;

import java.io.IOException;

public class AddHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String params = exchange.getRequestURI().toString().split("\\?")[1];
        String param1 = params.split("&")[0];
        String param2 = params.split("&")[1];
        String ownerId = param1.split("=")[1];
        String accId = param2.split("=")[1];
        if (AddCard.addCard(Integer.parseInt(ownerId), Integer.parseInt(accId))){
            exchange.sendResponseHeaders( 200, 0);
        }
        else exchange.sendResponseHeaders( 400, 0);

    }
}
