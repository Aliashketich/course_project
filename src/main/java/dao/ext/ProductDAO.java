package dao.ext;

import connectionpull.ConnectionPull;
import dao.IProductDAO;
import entity.Product;
import exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);
    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM menu";
    private static final String FIND_PRODUCT_BY_TYPE = "SELECT * FROM menu WHERE type=? AND exist=1";
    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM menu WHERE idproduct=?";
    private static final String DELETE_PRODUCT = "DELETE FROM menu WHERE menu.idproduct=?";
    private static final String FIND_PRODUCT_BY_NAME = "SELECT * FROM menu WHERE menu.name=?";
    private static final String FIND_PRODUCT_BY_NAME_AND_ID = "SELECT * FROM menu WHERE menu.name=? AND menu.idproduct!=?";  // а надо ли оно мне?
    private static final String ADD_PRODUCT = "INSERT INTO menu (name,type,cost,description) VALUES (?,?,?,?)";
    private static final String EDIT_PRODUCT = "UPDATE scoresystem.menu SET scoresystem.menu.name = ?,scoresystem.menu.type=?,scoresystem.menu.cost=?,scoresystem.menu.description=? WHERE scoresystem.menu.idproduct = ?";
    private ConnectionPull connectionPull = ConnectionPull.getInstance();


    @Override
    public List<Product> findAllProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find all products");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> products;
        try {
            statement = connection.prepareStatement(FIND_ALL_PRODUCTS);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish get all products");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return products;
    }

    @Override
    public void deleteProduct(int idProduct) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Product DAO: start deleteProduct");
        try {
            statement = connection.prepareStatement(DELETE_PRODUCT);
            statement.setInt(1, idProduct);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish deleteProduct");
            connectionPull.putBack(connection, resultSet, statement);
        }

    }

    @Override
    public Product findProductByName(String name) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by name.");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Product product=null;
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                product=createProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by name.");
            connectionPull.putBack(connection, resultSet, statement);
        }
        System.out.println(product);
        return product;

    }

    @Override
    public void addProduct(String type, String name, double cost, String description) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product Dao: start addProduct");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_PRODUCT);
            statement.setString(1, name);
            statement.setString(2, type);
            statement.setDouble(3, cost);
            statement.setString(4, description);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Product Dao: finish addProduct");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public Product findProductByNameAndId(String name,int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start findProductByNameAndId.");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Product product=null;
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME_AND_ID);
            statement.setString(1, name);
            statement.setInt(2, idProduct);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                product=createProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish findProductByNameAndId.");
            connectionPull.putBack(connection, resultSet, statement);
        }
        System.out.println(product);
        return product;
    }

    @Override
    public void editProduct(int idProduct, String type, String name, double cost, String description) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product Dao: start editProduct");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(EDIT_PRODUCT);
            statement.setString(1, name);
            statement.setString(2, type);
            statement.setDouble(3, cost);
            statement.setString(4, description);
            statement.setInt(5, idProduct);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Product Dao: finish editProduct");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public List<Product> findProductByType(String productType) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by type.");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> products;
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_BY_TYPE);
            statement.setString(1, productType);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by type.");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return products;

    }

    @Override
    public Product findProductById(int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by id.");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Product product;
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            statement.setInt(1, idProduct);
            resultSet = statement.executeQuery();
            product = new Product();
            while (resultSet.next()) {
                product=createProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by id.");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return product;

    }

    private Product createProductByResultSet(ResultSet resultSet) throws DaoException {
        Product product = new Product();
        try {
            product.setIdProduct(resultSet.getInt("idproduct"));
            product.setName(resultSet.getString("name"));
            product.setCost(resultSet.getDouble("cost"));
            product.setType(resultSet.getString("type"));
            product.setDescription(resultSet.getString("description"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query",e);
        }
        return product;
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
