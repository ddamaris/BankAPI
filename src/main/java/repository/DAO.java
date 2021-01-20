package repository;


import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    T get(int id) throws SQLException;
    List<T> getAllForClientById(int clientId, int objectId) throws SQLException;
    void add(T t) throws SQLException;
    void update(int id, String[] params) throws SQLException;
    void delete(int id) throws SQLException;
}
