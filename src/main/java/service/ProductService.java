package service;



import entity.Product;
import exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts() throws ServiceException;

    List<Product> findProductByType(String productType) throws ServiceException;

    Product findProductById(int idProduct) throws ServiceException;

    void deleteProduct(int idProduct) throws ServiceException;

    Product findProductByName(String name) throws ServiceException;

    void addProduct(String type, String name, double cost, String description) throws ServiceException;

    Product findProductByNameAndId(String name, int idProduct) throws ServiceException;

    void editProduct(int idProduct, String type, String name, double cost, String description) throws ServiceException;
}
