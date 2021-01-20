package models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Client implements Serializable {
    private String name;
    private Long id;
    private List<Account> accountsList;
    private List<BankCard> bankCardsList;

    public Client(String name, Long id, List<Account> accounts, List<BankCard> bankCards) {
        this.name = name;
        this.id = id;
        this.accountsList = accounts;
        this.bankCardsList = bankCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Account> getAccounts() {
        return accountsList;
    }

    public void setAccounts(List<Account> accounts) {
        this.accountsList = accounts;
    }

    public List<BankCard> getBankCards() {
        return bankCardsList;
    }

    public void setBankCards(List<BankCard> bankCards) {
        this.bankCardsList = bankCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(id, client.id) &&
               Objects.equals(accountsList, client.accountsList) &&
               Objects.equals(bankCardsList, client.bankCardsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, accountsList, bankCardsList);
    }

    @Override
    public String toString() {
        return "models.Client{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", accounts=" + accountsList +
                ", bankCards=" + bankCardsList +
                '}';
    }
}
