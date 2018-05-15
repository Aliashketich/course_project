package factory;

import dao.IOrderProductDAO;
import dao.ext.*;


public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
    private AdminDAO adminDAO = new AdminDAO();
    private WaiterDAO waiterDao = new WaiterDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderProductDAO orderProductDAO = new OrderProductDAO();


    public static DaoFactory getInstance(){
        return INSTANCE;
    }

    public AdminDAO getAdminDao() {
        return adminDAO;
    }

    public WaiterDAO getWaiterDao() {
        return waiterDao;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public OrderDAO getOrderDao() {
        return orderDAO;
    }

    public OrderProductDAO getOrderProductDao() {
        return orderProductDAO;
    }
}
