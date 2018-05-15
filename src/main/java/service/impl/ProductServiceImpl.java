package service.impl;

import entity.Product;
import exception.DaoException;
import exception.ServiceException;
import factory.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.ProductService;
import util.Validator;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start get all products");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get all products");
            return daoFactory.getProductDAO().findAllProducts();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<Product> findProductByType(String productType) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start find product by type");
        try {
            if (Validator.isNull(productType) && Validator.isEmptyString(productType)) {
                LOGGER.log(Level.DEBUG, "ProductService: Finish find product by type");
                return daoFactory.getProductDAO().findProductByType(productType);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null; //???
    }

    @Override
    public Product findProductById(int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start find product by id");
        try {
            LOGGER.log(Level.DEBUG, "ProductService: Finish find product by id");
            return daoFactory.getProductDAO().findProductById(idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start deleteProduct");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish deleteProduct");
            daoFactory.getProductDAO().deleteProduct(idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Product findProductByName(String name) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start find product by name");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish find product by name");
            return daoFactory.getProductDAO().findProductByName(name);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void addProduct(String type, String name, double cost, String description) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: start addProduct");
        try {
            if (Validator.isNull(type, name, cost, description) && Validator.isEmptyString(type, name, description) ) {
                daoFactory.getProductDAO().addProduct(type, name, cost, description);
                LOGGER.log(Level.DEBUG, "Product Service: end addProduct");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Product findProductByNameAndId(String name, int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start findProductByNameAndId");
        try {
            LOGGER.log(Level.DEBUG, "ProductService: Finish findProductByNameAndId");
            return daoFactory.getProductDAO().findProductByNameAndId(name,idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void editProduct(int idProduct, String type, String name, double cost, String description) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: start editProduct");
        try {
            if (Validator.isNull(type, name, cost, description) && Validator.isEmptyString(type, name, description)) {
                daoFactory.getProductDAO().editProduct(idProduct,type,name, cost,description);
                LOGGER.log(Level.DEBUG, "Product Service: end editProduct");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
