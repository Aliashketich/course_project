package service.impl;

import entity.Order;
import exception.DaoException;
import exception.ServiceException;
import factory.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    @Override
    public Order findActiveOrderByWaiterId(int idWaiter) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Order Service: Start findActiveOrderByWaiterId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish findActiveOrderByWaiterId");
            return daoFactory.getOrderDao().findActiveOrderByWaiterId(idWaiter);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void editOrderCost(int idOrder, double deltaTotalCost) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start editOrderCost");
        try {
            daoFactory.getOrderDao().editOrderCost(idOrder, deltaTotalCost);
            LOGGER.log(Level.DEBUG, "OrderService: Finish editOrderCost");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void addDiscountInOrder(int idOrder, double discount) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start addDiscountInOrder");
        try {
            daoFactory.getOrderDao().addDiscountInOrder(idOrder, discount);
            LOGGER.log(Level.DEBUG, "OrderService: Finish addDiscountInOrder");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void addOrder(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start addOrder");
        try {
            daoFactory.getOrderDao().addOrder(idClient);
            LOGGER.log(Level.DEBUG, "OrderService: Finish addOrder");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<Order> findAllOrdersByWaiterId(int idWaiter) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start findAllOrdersByWaiterId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish findAllOrdersByWaiterId");
            return daoFactory.getOrderDao().findAllOrdersByWaiterId(idWaiter);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start findAllOrders");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish findAllOrders");
            return daoFactory.getOrderDao().findAllOrders();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Order findOrderByOrderId(int idOrder) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start findOrderByOrderId");
        try {
            LOGGER.log(Level.DEBUG, "Order Service: Finish findOrderByOrderId");
            return daoFactory.getOrderDao().findOrderByOrderId(idOrder);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void closeOrder(int idOrder) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start closeOrder");
        try {
            daoFactory.getOrderDao().closeOrder(idOrder);
            LOGGER.log(Level.DEBUG, "Order Service: Finish closeOrder");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}

