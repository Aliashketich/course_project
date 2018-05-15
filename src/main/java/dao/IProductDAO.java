package dao;

import entity.Product;
import exception.DaoException;

import java.util.List;

public interface IProductDAO extends IAbstractDAO {

    List<Product> findAllProducts()throws DaoException;

    void deleteProduct(int idProduct)throws DaoException;

    Product findProductByName(String name)throws DaoException;

    void addProduct(String type, String name, double cost, String description)throws DaoException;

    Product findProductByNameAndId(String name, int idProduct)throws DaoException;

    void editProduct(int idProduct, String type, String name, double cost, String description)throws DaoException;

    List<Product> findProductByType(String productType)throws DaoException;

    Product findProductById(int idProduct)throws DaoException;
}
