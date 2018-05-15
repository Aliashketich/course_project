package dao;

import entity.ext.Admin;
import exception.DaoException;

import java.util.List;

public interface IAdminDAO extends IAbstractDAO {
    Admin signIn(String login, String password) throws DaoException;
    boolean findAdminByLogin(String login) throws DaoException;
    List<Admin> findAllAdmins() throws DaoException;
}
