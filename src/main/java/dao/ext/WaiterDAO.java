package dao.ext;

import connectionpull.ConnectionPull;
import dao.IWaiterDAO;
import entity.User;
import entity.ext.Waiter;
import exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaiterDAO implements IWaiterDAO {
    private static final Logger LOGGER = Logger.getLogger(WaiterDAO.class);
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM waiter JOIN user ON waiter.user_iduser=user.iduser WHERE user.login =? AND user.password = ? AND user.role = 0";
    private static final String FIND_WAITER_BY_LOGIN = "SELECT * FROM waiter JOIN user ON waiter.user_iduser=user.iduser WHERE user.login =?";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user  WHERE user.login =?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM waiter  WHERE waiter.email =?";
    private static final String ADD_USER = "INSERT INTO user (login,password,role) VALUES (?,?,?)";
    private static final String ADD_CLIENT = "INSERT INTO waiter (user_iduser,name,surname,email) VALUES (?,?,?,?)";
    private static final String FIND_ALL_WAITERS = "SELECT * FROM waiter JOIN user ON user.iduser=waiter.user_iduser";
    private static final String FIND_BY_ID = "SELECT * FROM waiter JOIN user ON waiter.user_iduser=user.iduser WHERE user.iduser =?";
    private ConnectionPull connectionPull = ConnectionPull.getInstance();


    @Override
    public Waiter signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Waiter Dao: start SignIn");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Waiter waiter = null;
        try {
            statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                waiter = new Waiter();
                waiter.setIdUser(resultSet.getInt("user_iduser"));
                waiter.setLogin(resultSet.getString("user.login"));
                waiter.setPassword(resultSet.getString("user.password"));
                waiter.setName(resultSet.getString("name"));
                waiter.setSurname(resultSet.getString("surname"));
                waiter.setEmail(resultSet.getString("email"));
                waiter.setRole(resultSet.getBoolean("user.role"));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        LOGGER.log(Level.DEBUG, "Waiter Dao: finish SignIn");
        return waiter;
    }

    @Override
    public Waiter findWaiterByLogin(String login) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Waiter waiter = null;
        try {
            statement = connection.prepareStatement(FIND_WAITER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                waiter = new Waiter();
                waiter.setIdUser(resultSet.getInt("user_iduser"));
                waiter.setLogin(resultSet.getString("user.login"));
                waiter.setPassword(resultSet.getString("user.password"));
                waiter.setName(resultSet.getString("name"));
                waiter.setSurname(resultSet.getString("surname"));
                waiter.setEmail(resultSet.getString("email"));
                waiter.setRole(resultSet.getBoolean("user.role"));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        return waiter;
    }

    @Override
    public Waiter findWaiterByEmail(String email) throws DaoException {
        LOGGER.log(Level.DEBUG, "Waiter DAO: start findWaiterByEmail");
        Waiter waiter=null;
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                waiter = new Waiter();
                waiter.setIdUser(resultSet.getInt("user_iduser"));
                waiter.setLogin(resultSet.getString("user.login"));
                waiter.setPassword(resultSet.getString("user.password"));
                waiter.setName(resultSet.getString("name"));
                waiter.setSurname(resultSet.getString("surname"));
                waiter.setEmail(resultSet.getString("email"));
                waiter.setRole(resultSet.getBoolean("user.role"));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        LOGGER.log(Level.DEBUG, "Waiter DAO: finish findWaiterByEmail");
        return waiter;
    }

    @Override
    public User addUser(String login, String password) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Waiter DAO: start addUser");
        try {
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, 0);
            if (statement.executeUpdate() != 0) {
                return findUserByLogin(login);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while user executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Waiter DAO: finish addUser");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;
    }

    @Override
    public Waiter addWaiter(int idUser, String login, String name, String surname, String email) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Waiter DAO: start addWaiter");
        try {
            statement = connection.prepareStatement(ADD_CLIENT);
            statement.setInt(1, idUser);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, email);
            if (statement.executeUpdate() != 0) {
                return findWaiterByLogin(login);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while client executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Waiter DAO: finish addWaiter");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                user = new User();
                user.setIdUser(resultSet.getInt("iduser"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setRole(resultSet.getBoolean("user.role"));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        return user;
    }

    @Override
    public List<Waiter> findAllWaiters() throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Waiter> waiters = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_ALL_WAITERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                waiters.add(createWaiterByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Waiter DAO: finish findAllWaiters");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return waiters;
    }

    @Override
    public Waiter findWaiterById(int idWaiter) throws DaoException {
        LOGGER.log(Level.DEBUG, "Waiter DAO: start findWaiterById");
        Waiter waiter=null;
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, idWaiter);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                waiter = createWaiterByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        LOGGER.log(Level.DEBUG, "Waiter DAO: finish findWaiterById");
        return waiter;

    }
    private Waiter createWaiterByResultSet(ResultSet resultSet) throws DaoException {
        Waiter waiter = new Waiter();
        try {
            waiter.setIdUser(resultSet.getInt("user_iduser"));
            waiter.setLogin(resultSet.getString("user.login"));
            waiter.setPassword(resultSet.getString("user.password"));
            waiter.setName(resultSet.getString("name"));
            waiter.setSurname(resultSet.getString("surname"));
            waiter.setEmail(resultSet.getString("email"));
            waiter.setRole(resultSet.getBoolean("user.role"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query",e);
        }
        return waiter;
    }

    @Override
    public void close(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
