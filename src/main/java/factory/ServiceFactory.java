package factory;

import dao.IAdminDAO;
import service.AdminService;
import service.WaiterService;
import service.impl.AdminServiceImpl;
import service.impl.WaiterServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private AdminService adminService = new AdminServiceImpl();
    private WaiterService waiterService = new WaiterServiceImpl();

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public AdminService getAdminService() {
        return adminService;
    }
    public WaiterService getWaiterService() {
        return waiterService;
    }
}