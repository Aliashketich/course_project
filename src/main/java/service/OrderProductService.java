package service;

import entity.OrderProduct;
import exception.ServiceException;

import java.util.List;

public interface OrderProductService {
    void addOrderProduct(int idOrder, int idProduct, int quantity) throws ServiceException;

    void removeOrderProduct(int idOrder, int idProduct) throws ServiceException;

    List<OrderProduct> findOrderProductsByWaiterId(int idWaiter) throws ServiceException;

    List<OrderProduct> findAllOrderProducts() throws ServiceException;

    boolean checkActiveOrderProduct(int idProduct) throws ServiceException;

    OrderProduct checkProductInActiveOrder(int idOrder,int  idProduct) throws ServiceException;

    void addOrderProductQuantity(int idOrder,int idProduct,int quantity) throws ServiceException;

}
