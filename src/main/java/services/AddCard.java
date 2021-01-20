package services;

import models.BankCard;
import repository.DAO;
import repository.DAO_Card;

import java.sql.SQLException;

public class AddCard {

    public static boolean addCard(int ownerId, int accId){

        try{
            DAO<BankCard> daoCard = new DAO_Card("test");
            BankCard card = new BankCard(0, ownerId, accId, "9999 9999 990" +
                                          ownerId + " 999" + accId, 0);
            daoCard.add(card);
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }
}
