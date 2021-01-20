package repository;

import models.*;
import org.h2.jdbcx.JdbcDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_Account implements DAO<Account> {

    private final JdbcDataSource dataSource;
    private final String table = "account";

    public DAO_Account(JdbcDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Account get(int id) {

        final String SQL_FIND_BY_ID = String.format("SELECT * FROM %s WHERE id = ?", table);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer accId = resultSet.getInt("id");
                Integer accOwner = resultSet.getInt("acc_owner");
                Integer accNumber = resultSet.getInt("acc_number");
                Integer amount = resultSet.getInt("amount");
                connection.close();
                return new Account(accId, accOwner, accNumber, amount);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public List<Account> getAllForClientById(int clientId, int objectId) {

        final String SQL_FIND_ALL = String.format("SELECT * FROM %s WHERE acc_owner = ?", table);
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer accId = resultSet.getInt("id");
                Integer accOwner = resultSet.getInt("acc_owner");
                Integer accNumber = resultSet.getInt("acc_number");
                Integer amount = resultSet.getInt("amount");
                accounts.add(new Account(accId, accOwner, accNumber, amount));
            }
            return accounts;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public void add(Account account) {

        final String SQL_ADD = "INSERT INTO account(acc_owner, acc_number, amount) VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_ADD);
            statement.setInt(1, account.getClient());
            statement.setInt(2, account.getAccNumber());
            statement.setInt(3, account.getAmount());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        final String SQL_DELETE = String.format("DELETE FROM %s WHERE id=?", table);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public void update(int id, String[] params) {

        final String SQL_ACCOUNT_UPDATE = String.format("UPDATE %s SET amount=? WHERE id =?", table);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_ACCOUNT_UPDATE);
            statement.setInt(1, Integer.parseInt(params[0]));
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException ignored) {
                }
            }
        }
    }
}
