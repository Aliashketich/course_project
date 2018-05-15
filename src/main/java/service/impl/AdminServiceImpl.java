package service.impl;


import entity.ext.Admin;
import exception.DaoException;
import exception.ServiceException;
import factory.DaoFactory;
import service.AdminService;
import util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = LogManager.getLogger(AdminServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Admin signIn(String login, String password) {
        LOGGER.log(Level.DEBUG, "AdminService: start SignIn");
        try {
            Validator.isNull(login, password);
            Validator.isEmptyString(login, password);
            Validator.matchLogin(login);
            Validator.matchPassword(password);
            LOGGER.log(Level.DEBUG, "AdminService: end SignIn");
            return daoFactory.getAdminDao().signIn(login, password);
        } catch (DaoException e) {
            return null;
        }
    }

    @Override
    public List<Admin> findAllAdmins() throws ServiceException {
        LOGGER.log(Level.DEBUG, "AdminService: Start findAllAdmins");
        try {
            LOGGER.log(Level.DEBUG, "AdminService: Finish findAllAdmins");
            return daoFactory.getAdminDao().findAllAdmins();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }


}
