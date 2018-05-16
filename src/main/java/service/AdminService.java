package service;

import entity.ext.Admin;
import exception.ServiceException;

import java.util.List;

public interface AdminService {
    Admin signIn(String login, String password);

    List<Admin> findAllAdmins() throws ServiceException;

    void deleteAdmin(int idAdmin) throws ServiceException;

    Admin findAdminByLogin(String login) throws ServiceException;

    Admin addAdmin(String login,String password) throws ServiceException;

    Admin changePassword(int idAdmin, String password) throws ServiceException;

    Admin findAdminByIdAndPassword(int idAdmin,String oldPassword) throws ServiceException;
}
