package dao;

import entity.ext.Admin;
import exception.DaoException;

public interface IAdminDAO {
    Admin signIn(String login, String password) throws DaoException;
    boolean findAdministratorByLogin(String login) throws DaoException;
}
