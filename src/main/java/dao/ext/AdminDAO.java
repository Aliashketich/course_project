package dao.ext;

import connectionpull.ConnectionPull;
import dao.IAdminDAO;
import entity.User;
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
    private static final String ADD_USER = "INSERT INTO scoresystem.user (login,password,role) VALUES (?,?,?)";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user  WHERE user.login =?";
    private static final String ADD_ADMIN = "INSERT INTO scoresystem.admin (user_iduser,is_main) VALUES (?,?)";
    private static final String DELETE_ADMIN = "DELETE FROM scoresystem.admin WHERE scoresystem.admin.user_iduser=?";
    private static final String DELETE_USER = "DELETE FROM scoresystem.user WHERE scoresystem.user.iduser=?";
    private static final String CHANGE_PASSWORD = "UPDATE scoresystem.user SET scoresystem.user.password = ? WHERE scoresystem.user.iduser = ?";
    private static final String FIND_BY_ID = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.iduser =?";
    private static final String FIND_BY_ID_AND_PASSWORD = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.iduser =? AND user.password=?";

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
    public Admin findAdminByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin Dao: start findAdminByLogin");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ADMIN_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return createAdminByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin Dao: finish findAdminByLogin");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;
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

    @Override
    public User addUser(String login, String password) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Admin DAO: start addUser");
        try {
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, 1);
            if (statement.executeUpdate() != 0) {
                System.out.println(findUserByLogin(login));
                return findUserByLogin(login);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin DAO: finish addUser");
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
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getBoolean("role"));
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        return user;
    }

    @Override
    public Admin addAdmin(int idUser, String login) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Admin DAO: start addAdmin");
        try {
            statement = connection.prepareStatement(ADD_ADMIN);
            statement.setInt(1, idUser);
            statement.setInt(2, 0);
            if (statement.executeUpdate() != 0) {
                System.out.println(findAdminByLogin(login));
                return findAdminByLogin(login);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while client executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin DAO: finish addAdmin");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;
    }

    @Override
    public void deleteAdmin(int idAdmin) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Admin DAO: start deleteAdmin");
        try {
            statement = connection.prepareStatement(DELETE_ADMIN);
            statement.setInt(1, idAdmin);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin DAO: finish deleteAdmin");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public void deleteUser(int idAdmin) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Admin DAO: start deleteUser");
        try {
            statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, idAdmin);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin DAO: finish deleteUser");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public Admin changePassword(int idAdmin, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: start changePassword");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setInt(2, idAdmin);
            if (statement.executeUpdate() != 0) {
                System.out.println(findAdminById(idAdmin));
                return findAdminById(idAdmin);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Admin DAO: finish changePassword");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;
    }

    @Override
    public Admin findAdminById(int idAdmin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: start findAdminById");
        Admin administrator = null;
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, idAdmin);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                administrator = createAdminByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        LOGGER.log(Level.DEBUG, "Admin DAO: finish findAdminById");
        return administrator;

    }

    @Override
    public Admin findAdminByIdAndPassword(int idAdmin, String oldPassword) throws DaoException {
        LOGGER.log(Level.DEBUG, "Admin DAO: start findAdminByIdAndPassword");
        Admin admin = null;
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BY_ID_AND_PASSWORD);
            statement.setInt(1, idAdmin);
            statement.setString(2, oldPassword);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                admin = createAdminByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPull.putBack(connection, resultSet, statement);
        }
        LOGGER.log(Level.DEBUG, "Admin DAO: finish findAdminByIdAndPassword");
        return admin;
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
