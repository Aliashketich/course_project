package factory;

import service.AdministratorService;
import service.impl.AdministratorServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
//    private ClientService clientService = new ClientServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

//    public ClientService getClientService() {
//        return clientService;
//    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }
}