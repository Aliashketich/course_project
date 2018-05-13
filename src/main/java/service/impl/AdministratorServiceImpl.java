package service.impl;


import entity.ext.Admin;
import exception.DaoException;
import factory.DaoFactory;
import service.AdministratorService;
import util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class AdministratorServiceImpl implements AdministratorService {
    private static final Logger LOGGER = LogManager.getLogger(AdministratorServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Admin signIn(String login, String password) {
        LOGGER.log(Level.DEBUG, "Administrator Service: start SignIn");
        try {
            Validator.isNull(login, password);
            Validator.isEmptyString(login, password);
            Validator.matchLogin(login);
            Validator.matchPassword(password);
            LOGGER.log(Level.DEBUG, "Administrator Service: end SignIn");
            return daoFactory.getAdministratorDao().signIn(login, password);
        } catch (DaoException e) {
            return null;
        }
    }


}
