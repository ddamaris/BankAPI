package server;

import com.sun.net.httpserver.Headers;
import models.BankCard;
import org.junit.jupiter.api.Test;
import repository.DAO_Card;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddHandlerTest {

    @Test
    void handle() throws IOException, SQLException {
        int ownerId = 1;
        int accId = 1;
        HttpURLConnection connection = (HttpURLConnection) new URL
            ("http://localhost:8085/add?ownerId=" + ownerId + "&accId=" + accId).openConnection();
        InputStream response = connection.getInputStream();
        DAO_Card daoCard = new DAO_Card("test");
        List<BankCard> cards = daoCard.getAllForClientById(1, 1);
        BankCard card = cards.get(cards.size() - 1);
        assertTrue(card.getOwnerId() == ownerId && card.getCardAccId() == accId && card.getAmount() == 0);
        System.out.println(card);
    }
}