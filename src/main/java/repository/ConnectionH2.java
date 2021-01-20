package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionH2 implements DbConnection{

    private final String db;

    ConnectionH2 (String db){
        this.db = db;
    }

    public Connection GetConnection() {
        Connection connection = null;
        try{
            String DB_DRIVER = "org.h2.Driver";
            Class.forName(DB_DRIVER);
            String DB_PASSWORD = "";
            String DB_USERNAME = "";
            String DB_URL = "jdbc:h2:~/";
            connection = DriverManager.getConnection(DB_URL + db, DB_USERNAME, DB_PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
