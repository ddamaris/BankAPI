package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import models.BankCard;
import org.junit.jupiter.api.Test;
import repository.DAO_Card;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GetAllCardHandlerTest {

    @Test
    void handle() throws IOException, SQLException {
        int ownerId = 1;
        int accId = 1;
        DAO_Card daoCard = new DAO_Card("test");
        daoCard.add(new BankCard(1, 1, 1, "9999 9999 990" + ownerId + " 990" + accId, 100500));
        HttpURLConnection connection = (HttpURLConnection) new URL
                ("http://localhost:8085/getall?ownerId=" + ownerId + "&accId=" + accId).openConnection();
        InputStream response = connection.getInputStream();
        Scanner in = new Scanner(response);
        String jsonCards = in.nextLine();
        List<BankCard> cards1 = daoCard.getAllForClientById(1, 1);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<BankCard>> listType = new TypeReference<List<BankCard>>() {};
        List<BankCard> cards2 = mapper.readValue(jsonCards, listType);
        for(BankCard card : cards2){
            System.out.println(card);
        }
        assertEquals(cards1, cards2);
    }
}