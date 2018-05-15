package dao.ext;

import connectionpull.ConnectionPull;
import dao.IAdminDAO;
import entity.ext.Admin;
import exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements IAdminDAO {
    private final static Logger LOGGER = LogManager.getLogger(AdminDAO.class);
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.login =? AND user.password = ? AND user.role = 1";
    private static final String FIND_ADMIN_BY_LOGIN = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.login =? AND user.role=1";
    private static final String FIND_ALL_ADMINS = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser";
    private ConnectionPull connectionPull = ConnectionPull.getInstance();


    @Override
    public Admin signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin Dao: start SignIn");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Admin admin = null;
        try {
            statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                admin = new Admin();
                admin.setIdUser(resultSet.getInt("user_iduser"));
                admin.setLogin(resultSet.getString("user.login"));
                admin.setPassword(resultSet.getString("user.password"));
                admin.setMain(resultSet.getBoolean("is_main"));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        LOGGER.log(Level.DEBUG, "Admin Dao: finish SignIn");
        return admin;
    }

    @Override
    public boolean findAdminByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin Dao: start findAdminByLogin");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ADMIN_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin Dao: finish findAdminByLogin");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public List<Admin> findAllAdmins() throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: start findAllAdmins");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Admin> admins = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_ALL_ADMINS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                admins.add(createAdminByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin DAO: finish findAllAdmins");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return admins;

    }

    private Admin createAdminByResultSet(ResultSet resultSet) throws DaoException {
        Admin admin = new Admin();
        try {
            admin.setIdUser(resultSet.getInt("user_iduser"));
            admin.setLogin(resultSet.getString("user.login"));
            admin.setPassword(resultSet.getString("user.password"));
            admin.setMain(resultSet.getBoolean("is_main"));
            admin.setRole(resultSet.getBoolean("user.role"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query",e);
        }
        return admin;
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
