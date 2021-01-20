package services;

import models.BankCard;
import repository.DAO_Card;

import java.sql.SQLException;

public class SetAmount {
    public static boolean setAmount(int cardId, int amount) {
        try {
            DAO_Card daoCard = new DAO_Card("test");
            BankCard card = daoCard.get(cardId);
            int accId = card.getCardAccId();
            String[] params = {Integer.toString(amount), Integer.toString(accId)};
            daoCard.update(cardId, params);
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }
}
