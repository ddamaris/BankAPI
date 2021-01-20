package models;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private Integer id;
    private Integer client;
    private Integer accNumber;
    private Integer amount;

    public Account(Integer id, Integer client, Integer accNumber, Integer amount) {
        this.id = id;
        this.client = client;
        this.accNumber = accNumber;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClient() { return client; }
    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getAccNumber() { return accNumber; }
    public void setAccNumber(Integer acc_number) { this.accNumber = acc_number; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(client, account.client) && Objects.equals(accNumber, account.accNumber) && Objects.equals(amount, account.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, accNumber, amount);
    }

    @Override
    public String toString() {
        return "{\"id\": " + id + ", \"client\": " + client + ", \"number\": " + accNumber + ", \"amount\" :" + amount + '}';
    }
}
