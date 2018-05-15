package service.impl;

import entity.ext.Waiter;
import exception.DaoException;
import exception.ServiceException;
import factory.DaoFactory;
import org.apache.log4j.Level;
import service.WaiterService;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.Validator;

public class WaiterServiceImpl implements WaiterService {

    private final static Logger LOGGER= LogManager.getLogger(WaiterServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public Waiter signIn(String login, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Waiter Service: start SignIn");
        Waiter waiter = null;
        try {
            if (Validator.isNull(login, password) && Validator.isEmptyString(login, password) && Validator.matchLogin(login) && Validator.matchPassword(password)) {
                waiter = daoFactory.getWaiterDao().signIn(login, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Waiter Service: end SignIn");
        return waiter;
    }

    @Override
    public Waiter findWaiterByLogin(String login) {
        return null;
    }

    @Override
    public Waiter findWaiterByEmail(String email) {
        return null;
    }

    @Override
    public List<Waiter> findAllWaiters() throws ServiceException {
        return null;
    }

    @Override
    public Waiter findWaiterById(int idWaiter) throws ServiceException {
        return null;
    }
}
