package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.BankCard;
import org.junit.jupiter.api.Test;
import repository.DAO_Card;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetCardHandlerTest {

    @Test
    void handle() throws SQLException {

        try {
            String id = "5";

            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8085/getone?cardId=" + id).openConnection();
            InputStream response = connection.getInputStream();

            Scanner in = new Scanner(response);
            String jsonCard = in.nextLine();
            System.out.println(jsonCard);
            DAO_Card daoCard = new DAO_Card("test");
            BankCard card1 = daoCard.get(Integer.parseInt(id));
            ObjectMapper mapper = new ObjectMapper();
            BankCard card2 = mapper.readValue(jsonCard, BankCard.class);
            assertEquals(card1, card2);
        }
        catch (IOException e){
            System.out.println("No such Card");
        }
    }
}