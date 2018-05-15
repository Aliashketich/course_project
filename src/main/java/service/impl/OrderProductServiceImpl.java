package service.impl;

import entity.OrderProduct;
import exception.DaoException;
import exception.ServiceException;
import factory.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.OrderProductService;

import java.util.List;

public class OrderProductServiceImpl implements OrderProductService {

    private static final Logger LOGGER = LogManager.getLogger(OrderProductServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    @Override
    public void addOrderProduct(int idOrder, int idProduct, int quantity) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start addOrderProduct");
        try {
            daoFactory.getOrderProductDao().addOrderProduct(idOrder, idProduct, quantity);
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish addOrderProduct");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void removeOrderProduct(int idOrder, int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start removeOrderProduct");
        try {
            daoFactory.getOrderProductDao().removeOrderProduct(idOrder, idProduct);
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish removeOrderProduct");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<OrderProduct> findOrderProductsByWaiterId(int idWaiter) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start findOrderProductsByWaiterId");
        try {
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish findOrderProductsByWaiterId");
            return daoFactory.getOrderProductDao().findOrderProductsByWaiterId(idWaiter);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public List<OrderProduct> findAllOrderProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start findAllOrderProducts");
        try {
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish findAllOrderProducts");
            return daoFactory.getOrderProductDao().findAllOrderProducts();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }


    @Override
    public boolean checkActiveOrderProduct(int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start checkActiveOrderProduct");
        try {
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish checkActiveOrderProduct");
            return daoFactory.getOrderProductDao().checkActiveOrderProduct(idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public OrderProduct checkProductInActiveOrder(int idOrder, int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start checkProductInActiveOrder");
        try {
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish checkProductInActiveOrder");
            return daoFactory.getOrderProductDao().checkProductInActiveOrder(idOrder, idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void addOrderProductQuantity(int idOrder, int idProduct, int quantity) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start addOrderProductQuantity");
        try {
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish addOrderProductQuantity");
            daoFactory.getOrderProductDao().addOrderProductQuantity(idOrder, idProduct, quantity);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
