package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.BankCard;
import repository.DAO_Card;

import java.sql.SQLException;
import java.util.List;

public class GetAllCards {
    public static String getAllCards(int ownerId, int accId) {
        try {
            DAO_Card daoCard = new DAO_Card("test");
            List<BankCard> cards = daoCard.getAllForClientById(ownerId, accId);
            if (cards == null)
                return null;
            ObjectMapper mapper = new ObjectMapper();
            String jsonCard = mapper.writeValueAsString(cards);
            return jsonCard;
        } catch (
                SQLException e) {
            String reply;
            return reply = "DataBase request problem";
        } catch (
                JsonProcessingException e) {
            String reply;
            return reply = "Conversion problem";
        }
    }
}
