package dao;

import entity.OrderProduct;
import exception.DaoException;

import java.util.List;

public interface IOrderProductDAO extends IAbstractDAO {
    void addOrderProduct(int idOrder, int idProduct, int quantity) throws DaoException;

    void removeOrderProduct(int idOrder, int idProduct) throws DaoException;

    List<OrderProduct> findOrderProductsByWaiterId(int idWaiter) throws DaoException;

    List<OrderProduct> findAllOrderProducts() throws DaoException;

    boolean checkActiveOrderProduct(int idProduct) throws DaoException;

    OrderProduct checkProductInActiveOrder(int idOrder, int idProduct) throws DaoException;

    void addOrderProductQuantity(int idOrder, int idProduct, int quantity) throws DaoException;
}
