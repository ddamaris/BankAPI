package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude
public class BankCard implements Serializable {
    private int id;
    private int ownerId;
    private int cardAccId;
    private String number;
    private int amount;

    public BankCard(int id, int ownerId, int cardAccId, String number, int amount) {
        this.id = id;
        this.ownerId = ownerId;
        this.cardAccId = cardAccId;
        this.number = number;
        this.amount = amount;
    }
    public BankCard(){
        super();
    }

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public Integer getCardAccId() {
        return cardAccId;
    }
    public void setAccount(int cardAccId) {
        this.cardAccId = cardAccId;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return Objects.equals(id, bankCard.id) && Objects.equals(ownerId, bankCard.ownerId) &&
               Objects.equals(cardAccId, bankCard.cardAccId) && Objects.equals(number, bankCard.number) &&
               Objects.equals(amount, bankCard.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, cardAccId, number, amount);
    }

    @Override
    public String toString() {
        return "{\"id\": " + id + ", \"ownerId\": " + ownerId + ", \"account\": " + cardAccId + "," +
                " \"number\": " + number + ", \"amount\": " + amount + "}";
    }
}
