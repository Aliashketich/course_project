package dao.ext;

import connectionpull.ConnectionPull;
import dao.IOrderDAO;
import entity.Order;
import exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    private ConnectionPull connectionPull = ConnectionPull.getInstance();
    private static final String FIND_ACTIVE_ORDER_BY_WAITER_ID = "SELECT * FROM scoresystem.order WHERE scoresystem.order.user_iduser =? AND scoresystem.order.status=1";
    private static final String EDIT_ORDER_COST = "UPDATE scoresystem.order SET scoresystem.order.total_cost = (scoresystem.order.total_cost + ?) WHERE scoresystem.order.idorder = ?";
    private static final String ADD_ORDER = "INSERT INTO scoresystem.order (date,user_iduser,status) VALUES (CURRENT_DATE,?,1)";
    private static final String FIND_ORDERS_BY_WAITER_ID = "SELECT * FROM scoresystem.order WHERE scoresystem.order.user_iduser =?";
    private static final String FIND_ORDER_BY_ORDER_ID = "SELECT * FROM scoresystem.order WHERE scoresystem.order.idorder =?";
    private static final String CLOSE_ORDER = "UPDATE scoresystem.order SET scoresystem.order.status = 0 WHERE scoresystem.order.idorder = ?";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM scoresystem.order";
    private static final String ADD_DISCOUNT_IN_ORDER="UPDATE scoresystem.order SET scoresystem.order.cost_with_discount = (scoresystem.order.total_cost * ?) WHERE scoresystem.order.idorder = ?";
    @Override
    public Order findActiveOrderByWaiterId(int idWaiter) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start findActiveOrderByWaiterId");
        Order order = null;
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ACTIVE_ORDER_BY_WAITER_ID);
            statement.setInt(1, idWaiter);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return createOrderByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish findActiveOrderByWaiterId");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;
    }

    @Override
    public boolean editOrderCost(int idOrder, double deltaTotalCost) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start editOrderCost");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(EDIT_ORDER_COST);
            statement.setDouble(1, deltaTotalCost);
            statement.setInt(2, idOrder);
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query order editing", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish editOrderCost");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public boolean addDiscountInOrder(int idOrder, double discount) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start addDiscountInOrder");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_DISCOUNT_IN_ORDER);
            statement.setDouble(1, (1-discount));
            statement.setInt(2, idOrder);
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query order editing", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish addDiscountInOrder");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public boolean addOrder(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start addOrder");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_ORDER);
            statement.setInt(1, idClient);
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query order adding", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish addOrder");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public List<Order> findAllOrdersByWaiterId(int idWaiter) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findAllOrdersByWaiterId.");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Order> orders;
        try {
            statement = connection.prepareStatement(FIND_ORDERS_BY_WAITER_ID);
            statement.setInt(1, idWaiter);
            resultSet = statement.executeQuery();
            orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "OrderDao: Finish findAllOrdersByWaiterId.");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return orders;

    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findAllOrders.");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Order> orders;
        try {
            statement = connection.prepareStatement(FIND_ALL_ORDERS);
            resultSet = statement.executeQuery();
            orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "OrderDao: Finish findAllOrders.");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return orders;

    }

    @Override
    public Order findOrderByOrderId(int idOrder) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findOrderByOrderId.");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ORDER_BY_ORDER_ID);
            statement.setInt(1, idOrder);
            resultSet = statement.executeQuery();
            while (resultSet.first()) {
                return createOrderByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "OrderDao: Finish findOrderByOrderId.");
            connectionPull.putBack(connection, resultSet, statement);
        }
        return null;

    }

    @Override
    public void closeOrder(int idOrder) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start closeOrder");
        Connection connection = connectionPull.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CLOSE_ORDER);
            statement.setInt(1, idOrder);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query order adding", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish closeOrder");
            connectionPull.putBack(connection, resultSet, statement);
        }
    }

    private Order createOrderByResultSet(ResultSet resultSet) throws DaoException {
        Order order;
        try {
            order = new Order();
            order.setIdOrder(resultSet.getInt("idorder"));
            order.setDate(resultSet.getString("date"));
            order.setTotalCost(resultSet.getDouble("total_cost"));
            order.setCostWithDiscount(resultSet.getDouble("cost_with_discount"));
            order.setIdWaiter(resultSet.getInt("user_iduser"));
            order.setStatus(resultSet.getBoolean("status"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return order;
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
