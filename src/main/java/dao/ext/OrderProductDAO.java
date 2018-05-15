package dao.ext;

import connectionpull.ConnectionPull;
import dao.IOrderProductDAO;
import entity.OrderProduct;
import exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAO implements IOrderProductDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderProductDAO.class);
    private ConnectionPull connectionPull = ConnectionPull.getInstance();
    private static final String ADD_ORDER_PRODUCT = "INSERT INTO scoresystem.order_product (order_idorder,product_idproduct,quantity) VALUES (?,?,?)";
    private static final String FIND_ORDER_PRODUCT_BY_WAITER_ID = "SELECT * FROM scoresystem.order_product JOIN scoresystem.order ON scoresystem.order.idorder = scoresystem.order_product.order_idorder WHERE scoresystem.order.user_iduser =?";
    private static final String REMOVE_ORDER_PRODUCT = "DELETE FROM scoresystem.order_product WHERE scoresystem.order_product.order_idorder=? AND scoresystem.order_product.product_idproduct=?";
    private static final String FIND_ORDER_PRODUCT_BY_PRODUCT_ID = "SELECT * FROM scoresystem.order_product WHERE scoresystem.order_product.product_idproduct =?";
    private static final String FIND_ALL_ORDER_PRODUCT = "SELECT * FROM scoresystem.order_product";
    private static final String FIND_PRODUCT_IN_ACTIVE_ORDER = "SELECT * FROM scoresystem.order_product WHERE scoresystem.order_product.product_idproduct =? AND scoresystem.order_product.order_idorder=?";
    private static final String ADD_ORDER_PRODUCT_QUANTITY = "UPDATE scoresystem.order_product SET scoresystem.order_product.quantity = (scoresystem.order_product.quantity+?) WHERE scoresystem.order_product.product_idproduct = ? AND scoresystem.order_product.order_idorder=?";

    @Override
    public void addOrderProduct(int idOrder, int idProduct, int quantity) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start addOrderProduct");
        try {
            statement = connection.prepareStatement(ADD_ORDER_PRODUCT);
            statement.setInt(1, idOrder);
            statement.setInt(2, idProduct);
            statement.setInt(3, quantity);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish addOrderProduct");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public void removeOrderProduct(int idOrder, int idProduct) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start removeOrderProduct");
        try {
            statement = connection.prepareStatement(REMOVE_ORDER_PRODUCT);
            statement.setInt(1, idOrder);
            statement.setInt(2, idProduct);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish removeOrderProduct");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public List<OrderProduct> findOrderProductsByWaiterId(int idWaiter) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProductDAO: start findOrderProductsByWaiterId");
        List<OrderProduct> orderProducts = new ArrayList<>();
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ORDER_PRODUCT_BY_WAITER_ID);
            statement.setInt(1, idWaiter);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderProducts.add(createOrderProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProductDAO: finish findOrderProductsByWaiterId");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return orderProducts;

    }

    @Override
    public List<OrderProduct> findAllOrderProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProductDAO: start findAllOrderProducts");
        List<OrderProduct> orderProducts = new ArrayList<>();
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ALL_ORDER_PRODUCT);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderProducts.add(createOrderProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProductDAO: finish findAllOrderProducts");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return orderProducts;

    }



    @Override
    public boolean checkActiveOrderProduct(int idProduct) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start checkActiveOrderProduct");
        try {
            statement = connection.prepareStatement(FIND_ORDER_PRODUCT_BY_PRODUCT_ID);
            statement.setInt(1, idProduct);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish checkActiveOrderProduct");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public OrderProduct checkProductInActiveOrder(int idOrder, int idProduct) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start checkProductInActiveOrder");
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_IN_ACTIVE_ORDER);
            statement.setInt(1, idProduct);
            statement.setInt(2, idOrder);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return createOrderProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish checkProductInActiveOrder");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;
    }

    @Override
    public void addOrderProductQuantity(int idOrder, int idProduct, int quantity) throws DaoException {
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start addOrderProductQuantity");
        try {
            statement = connection.prepareStatement(ADD_ORDER_PRODUCT_QUANTITY);
            statement.setInt(1, quantity);
            statement.setInt(2, idProduct);
            statement.setInt(3, idOrder);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish addOrderProductQuantity");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    private OrderProduct createOrderProductByResultSet(ResultSet resultSet) throws DaoException {
        OrderProduct orderProduct = new OrderProduct();
        try {
            orderProduct.setIdOrder(resultSet.getInt("order_idorder"));
            orderProduct.setIdProduct(resultSet.getInt("product_idproduct"));
            orderProduct.setQuantity(resultSet.getInt("quantity"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return orderProduct;
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
