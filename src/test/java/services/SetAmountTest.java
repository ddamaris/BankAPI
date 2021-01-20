package services;

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

class SetAmountTest {

    @Test
    void setAmount() throws IOException, SQLException {
        int cardId = 1;
        int amount = 7654321;
        HttpURLConnection connection = (HttpURLConnection) new URL
                ("http://localhost:8085/setamount?cardId=" + cardId + "&amount=" + amount).openConnection();
        InputStream response = connection.getInputStream();
        DAO_Card daoCard = new DAO_Card("test");
        BankCard card = daoCard.get(cardId);
        assertEquals(amount, card.getAmount());
        System.out.println(card);

    }
}