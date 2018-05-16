package dao;

import entity.User;
import entity.ext.Admin;
import exception.DaoException;

import java.util.List;

public interface IAdminDAO extends IAbstractDAO {
    Admin signIn(String login, String password) throws DaoException;
    Admin findAdminByLogin(String login) throws DaoException;
    List<Admin> findAllAdmins() throws DaoException;

    User addUser(String login, String password)throws DaoException;
    User findUserByLogin(String login) throws DaoException;

    Admin addAdmin(int idUser, String login)throws DaoException;

    void deleteAdmin(int idAdmin)throws DaoException;

    void deleteUser(int idAdmin)throws DaoException;

    Admin changePassword(int idAdmin, String password)throws DaoException;

    Admin findAdminByIdAndPassword(int idAdmin, String oldPassword)throws DaoException;
}
