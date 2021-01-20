package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import services.GetAllCards;
import services.GetCard;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetAllCardHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String params = exchange.getRequestURI().toString().split("\\?")[1];
        String param1 = params.split("&")[0];
        String param2 = params.split("&")[1];
        String ownerId = param1.split("=")[1];
        String accId = param2.split("=")[1];
        String cards= GetAllCards.getAllCards(Integer.parseInt(ownerId), Integer.parseInt(accId));
        if (cards == null){
            exchange.sendResponseHeaders( 400, cards.length() );
        }
        else if(cards.equals("DataBase request problem") || cards.equals("Conversion problem")){
            exchange.sendResponseHeaders( 500, cards.length() );
        }
        else {
            exchange.sendResponseHeaders( 200, cards.length() );
        }
        OutputStream os = exchange.getResponseBody();
        os.write(cards.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
    }
}
