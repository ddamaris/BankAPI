package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.BankCard;
import repository.DAO_Card;

import java.sql.SQLException;

public class GetCard {

    public String getCard(int id) {
        try {
            DAO_Card daoCard = new DAO_Card("test");
            BankCard card = daoCard.get(id);
            if (card == null)
                return null;
            ObjectMapper mapper = new ObjectMapper();
            String jsonCard = mapper.writeValueAsString(card);
            return jsonCard;
        }
        catch (SQLException e) {
            String reply;
            return reply = "DataBase request problem";
        }
        catch (JsonProcessingException e) {
            String reply;
            return reply = "Conversion problem";
        }
    }
}
