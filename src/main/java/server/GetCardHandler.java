package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import services.GetCard;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetCardHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String id = exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
        GetCard getCard = new GetCard();
        String card = getCard.getCard(Integer.parseInt(id));
        System.out.println(card);
        if (card == null){
            exchange.sendResponseHeaders( 400, 0);
            card = "No card with id: " + id;
        }
        else if(card.equals("DataBase request problem") || card.equals("Conversion problem")){
            exchange.sendResponseHeaders( 500, 0);
        }
        else {
            exchange.sendResponseHeaders( 200, 0);
        }
        OutputStream os = exchange.getResponseBody();
        os.write(card.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
    }
}
