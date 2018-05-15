package factory;

import dao.ext.AdminDAO;
import dao.ext.WaiterDAO;


public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
    private AdminDAO adminDAO = new AdminDAO();
    private WaiterDAO waiterDao=new WaiterDAO();


    public static DaoFactory getInstance(){
        return INSTANCE;
    }

    public AdminDAO getAdminDao() {
        return adminDAO;
    }

    public WaiterDAO getWaiterDao() {
        return waiterDao;
    }
}
