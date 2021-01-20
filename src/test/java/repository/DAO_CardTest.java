package repository;

import models.BankCard;
import org.h2.tools.DeleteDbFiles;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;

class DAO_CardTest {

    @Test
    public void notNullConnection () {

        try {
            DbConnection dbConnection = new ConnectionH2("test");
            Connection connection = dbConnection.GetConnection();
            assertNotNull(connection);
            System.out.println("Connection Ok");
            connection.close();
        }
        catch (SQLException e) {
            assertTrue(false);
            System.out.println("Connection failed");
        }
    }
    @Test
    void get_Valid() throws SQLException {

        DeleteDbFiles.execute("~", "test1", true);
        DbConnection dbConnection = new ConnectionH2("test1");
        Connection conn = dbConnection.GetConnection();

        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");
        statement.execute("INSERT INTO client(name) VALUES('John')");
        statement.execute("INSERT INTO client(name) VALUES('Smith')");
        statement.execute("INSERT INTO client(name) VALUES('Mary')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('1', '1111', '1000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('2', '2222', '2000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('3', '3333', '3000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('1', '1', '1111 1111 1111 1111', '1000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('2', '2', '2222 2222 2222 1111', '2000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('3', '3', '3333 3333 3333 1111', '3000')");
        conn.close();

        DAO<BankCard> card = new DAO_Card("test1");
        BankCard testCard = card.get(3);
        assertEquals(3000, (int) card.get(3).getAmount());
        DeleteDbFiles.execute("~", "test1", true);
    }

    @Test
    void get_InValid() throws SQLException {

        DeleteDbFiles.execute("~", "test1", true);
        DbConnection dbConnection = new ConnectionH2("test1");
        Connection connection = dbConnection.GetConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");
        statement.execute("INSERT INTO client(name) VALUES('John')");
        statement.execute("INSERT INTO client(name) VALUES('Smith')");
        statement.execute("INSERT INTO client(name) VALUES('Mary')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('1', '1111', '1000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('2', '2222', '2000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('3', '3333', '3000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('1', '1', '1111 1111 1111 1111', '1000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('2', '2', '2222 2222 2222 1111', '2000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('3', '3', '3333 3333 3333 1111', '3000')");
        connection.close();

        DAO<BankCard> card = new DAO_Card("test1");
        assertNull(card.get(4));
        DeleteDbFiles.execute("~", "test1", true);
    }

    @Test
    void getAll() throws SQLException {

        DeleteDbFiles.execute("~", "test1", true);
        DbConnection dbConnection = new ConnectionH2("test1");
        Connection connection = dbConnection.GetConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");
        statement.execute("INSERT INTO client(name) VALUES('John')");
        statement.execute("INSERT INTO client(name) VALUES('Smith')");
        statement.execute("INSERT INTO client(name) VALUES('Mary')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('1', '1111', '1000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('2', '2222', '2000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('3', '3333', '3000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('1', '1', '1111 1111 1111 1111', '1000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('2', '2', '2222 2222 2222 1111', '2000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('3', '3', '3333 3333 3333 1111', '3000')");
        connection.close();

        DAO<BankCard> card = new DAO_Card("test1");
        assertEquals(1, (int) card.getAllForClientById(1, 1).size());
        DeleteDbFiles.execute("~", "test1", true);
    }

    @Test
    void getAllNull() throws SQLException {

        DeleteDbFiles.execute("~", "test1", true);
        DbConnection dbConnection = new ConnectionH2("test1");
        Connection connection = dbConnection.GetConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");
        connection.close();

        DAO<BankCard> card = new DAO_Card("test1");
        assertTrue(card.getAllForClientById(1, 1).isEmpty());
        DeleteDbFiles.execute("~", "test1", true);
    }

    @Test
    void add() throws SQLException {

        DeleteDbFiles.execute("~", "test1", true);
        DbConnection dbConnection = new ConnectionH2("test1");
        Connection connection = dbConnection.GetConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");
        statement.execute("INSERT INTO client(name) VALUES('John')");
        statement.execute("INSERT INTO client(name) VALUES('Smith')");
        statement.execute("INSERT INTO client(name) VALUES('Mary')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('1', '1111', '1000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('2', '2222', '2000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('3', '3333', '3000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('1', '1', '1111 1111 1111 1111', '1000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('2', '2', '2222 2222 2222 1111', '2000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('3', '3', '3333 3333 3333 1111', '3000')");
        connection.close();

        DAO<BankCard> card = new DAO_Card("test1");
        card.add(new BankCard(0, 1, 1, "1111 1111 1111 2222", 234));
        assertEquals(2, (int) card.getAllForClientById(1, 1).size());
        DeleteDbFiles.execute("~", "test1", true);
    }

    @Test
    void delete() throws SQLException {

        DeleteDbFiles.execute("~", "test1", true);
        DbConnection dbConnection = new ConnectionH2("test1");
        Connection connection = dbConnection.GetConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");
        statement.execute("INSERT INTO client(name) VALUES('John')");
        statement.execute("INSERT INTO client(name) VALUES('Smith')");
        statement.execute("INSERT INTO client(name) VALUES('Mary')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('1', '1111', '1000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('2', '2222', '2000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('3', '3333', '3000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('1', '1', '1111 1111 1111 1111', '1000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('2', '2', '2222 2222 2222 1111', '2000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('3', '3', '3333 3333 3333 1111', '3000')");
        connection.close();

        DAO<BankCard> card = new DAO_Card("test1");
        card.delete(3);
        assertTrue(card.get(3) == null);

        DeleteDbFiles.execute("~", "test1", true);
    }

    @Test
    void update() throws SQLException {

        DeleteDbFiles.execute("~", "test1", true);
        DbConnection dbConnection = new ConnectionH2("test1");
        Connection connection = dbConnection.GetConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS client (id SERIAL PRIMARY KEY,name TEXT NOT NULL)");
        statement.execute("CREATE TABLE IF NOT EXISTS account (id SERIAL PRIMARY KEY,acc_owner INTEGER REFERENCES client(id), acc_number INTEGER,amount INTEGER)");
        statement.execute("CREATE TABLE IF NOT EXISTS card (id SERIAL PRIMARY KEY, card_owner INTEGER REFERENCES client(id), card_acc INTEGER REFERENCES account(id), card_number VARCHAR(20),amount INTEGER)");
        statement.execute("INSERT INTO client(name) VALUES('John')");
        statement.execute("INSERT INTO client(name) VALUES('Smith')");
        statement.execute("INSERT INTO client(name) VALUES('Mary')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('1', '1111', '1000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('2', '2222', '2000')");
        statement.execute("INSERT INTO account(acc_owner, acc_number, amount) VALUES('3', '3333', '3000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('1', '1', '1111 1111 1111 1111', '1000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('2', '2', '2222 2222 2222 1111', '2000')");
        statement.execute("INSERT INTO card(card_owner, card_acc, card_number, amount) VALUES('3', '3', '3333 3333 3333 1111', '3000')");
        connection.close();

        DAO<BankCard> card = new DAO_Card("test1");
        String[] params = {"432", "1"};
        card.update(3, params);
        assertEquals((int) card.get(3).getAmount(), 432);
        DeleteDbFiles.execute("~", "test1", true);
    }
}
