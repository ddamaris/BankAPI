package repository;

import java.sql.Connection;

public interface DbConnection {
    Connection GetConnection();
}
