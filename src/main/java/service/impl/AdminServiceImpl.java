package service.impl;


import entity.User;
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

    @Override
    public void deleteAdmin(int idAdmin) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: Start deleteAdmin");
        try {
            daoFactory.getAdminDao().deleteAdmin(idAdmin);
            daoFactory.getAdminDao().deleteUser(idAdmin);
            LOGGER.log(Level.DEBUG, "Admin Service: Finish deleteAdmin");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Admin findAdminByLogin(String login) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AdminService: Start findAdminByLogin");
        try {
            LOGGER.log(Level.DEBUG, "AdminService: Finish findAdminByLogin");
            return daoFactory.getAdminDao().findAdminByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Admin addAdmin(String login, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: start addAdmin");
        Admin administrator = null;
        User user = null;
        try {
            if (Validator.isNull(login, password) && Validator.isEmptyString(login, password) && Validator.matchLogin(login) && Validator.matchPassword(password)) {
//            password = Hasher.sha1Hash(password);
                user = daoFactory.getAdminDao().addUser(login, password);
                if (user != null) {
                    administrator = daoFactory.getAdminDao().addAdmin(user.getIdUser(), login);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Admin Service: end addAdmin");
        return administrator;
    }

    @Override
    public Admin changePassword(int idAdmin, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: Start changePassword");
        try {
            LOGGER.log(Level.DEBUG, "Admin Service: Finish changePassword");
            return daoFactory.getAdminDao().changePassword(idAdmin, password);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Admin findAdminByIdAndPassword(int idAdmin, String oldPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: Start findAdminByIdAndPassword");
        try {
            LOGGER.log(Level.DEBUG, "Admin Service: Finish findAdminByIdAndPassword");
            return daoFactory.getAdminDao().findAdminByIdAndPassword(idAdmin,oldPassword);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


}
