package service.impl;

import dao.ext.WaiterDAO;
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
        WaiterDAO waiterDao = daoFactory.getWaiterDao();
        Waiter waiter = null;
        try {
            waiter = waiterDao.findWaiterByLogin(login);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "");
        }
        return waiter;
    }

    @Override
    public Waiter findWaiterByEmail(String email) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Waiter Service: Start findWaiterByEmail");
        try {
            LOGGER.log(Level.DEBUG, "Waiter Service: Finish findWaiterByEmail");
            return daoFactory.getWaiterDao().findWaiterByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<Waiter> findAllWaiters() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Waiter Service: Start findAllWaiters");
        try {
            LOGGER.log(Level.DEBUG, "Waiter Service: Finish findAllWaiters");
            return daoFactory.getWaiterDao().findAllWaiters();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Waiter findWaiterById(int idWaiter) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Waiter Service: Start findWaiterById");
        try {
            LOGGER.log(Level.DEBUG, "Waiter Service: Finish findWaiterById");
            return daoFactory.getWaiterDao().findWaiterById(idWaiter);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Waiter editWaiter(int idWaiter, String surname, String name, String email)throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start editClient");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish editClient");
            return daoFactory.getWaiterDao().editWaiter(idWaiter, surname, name, email);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Waiter findWaiterByIdAndPassword(int idWaiter, String oldPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Waiter Service: Start findWaiterByIdAndPassword");
        try {
            LOGGER.log(Level.DEBUG, "Waiter Service: Finish findWaiterByIdAndPassword");
            return daoFactory.getWaiterDao().findWaiterByIdAndPassword(idWaiter, oldPassword);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Waiter changePassword(int idWaiter, String newPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start changePassword");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish changePassword");
            return daoFactory.getWaiterDao().changePassword(idWaiter, newPassword);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
