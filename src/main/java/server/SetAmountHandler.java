package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import services.GetAllCards;
import services.SetAmount;

import java.io.IOException;

public class SetAmountHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String params = exchange.getRequestURI().toString().split("\\?")[1];
        String param1 = params.split("&")[0];
        String param2 = params.split("&")[1];
        String cardId = param1.split("=")[1];
        String amount = param2.split("=")[1];
        if (SetAmount.setAmount(Integer.parseInt(cardId), Integer.parseInt(amount))) {
            exchange.sendResponseHeaders(200, 0);
        } else exchange.sendResponseHeaders(400, 0);
    }
}
