package factory;

import dao.IAdminDAO;
import service.*;
import service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private AdminService adminService = new AdminServiceImpl();
    private WaiterService waiterService = new WaiterServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private OrderProductService orderProductService=new OrderProductServiceImpl();

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public AdminService getAdminService() {
        return adminService;
    }
    public WaiterService getWaiterService() {
        return waiterService;
    }
    public ProductService getProductService() {
        return productService;
    }
    public OrderService getOrderService() {
        return orderService;
    }
    public OrderProductService getOrderProductService() {
        return orderProductService;
    }
}