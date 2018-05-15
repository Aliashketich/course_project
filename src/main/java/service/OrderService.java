package service;

import entity.Order;
import exception.ServiceException;

import java.util.List;

public interface OrderService {
    Order findActiveOrderByWaiterId(int idWaiter) throws ServiceException;

    void editOrderCost(int idOrder, double deltaTotalCost) throws ServiceException;
    void addDiscountInOrder(int idOrder, double discount) throws ServiceException;

    void addOrder(int idClient) throws ServiceException;

    List<Order> findAllOrdersByWaiterId(int idWaiter) throws ServiceException;

    List<Order> findAllOrders() throws ServiceException;

    Order findOrderByOrderId(int idOrder) throws ServiceException;

    void closeOrder(int idOrder) throws ServiceException;
}
