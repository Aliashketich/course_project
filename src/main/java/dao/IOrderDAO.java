package dao;

import entity.Order;
import exception.DaoException;

import java.util.List;

public interface IOrderDAO extends IAbstractDAO {
    Order findActiveOrderByWaiterId(int idWaiter)throws DaoException;

    boolean addOrder(int idClient)throws DaoException;

    List<Order> findAllOrdersByWaiterId(int idWaiter)throws DaoException;

    List<Order> findAllOrders()throws DaoException;

    Order findOrderByOrderId(int idOrder)throws DaoException;

    void closeOrder(int idOrder)throws DaoException;

    boolean editOrderCost(int idOrder, double deltaTotalCost)throws DaoException;

    boolean addDiscountInOrder(int idOrder, double discount)throws DaoException;
}
