package factory;

import dao.ext.AdminDAO;
//import course_project.dao.impl.ClientDao;


public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
//    private ClientDao clientDao = new ClientDao();
    private AdminDAO adminDAO = new AdminDAO();


    public static DaoFactory getInstance(){
        return INSTANCE;
    }

//    public ClientDao getClientDao() {
//        return clientDao;
//    }

    public AdminDAO getAdministratorDao() {
        return adminDAO;
    }
}
