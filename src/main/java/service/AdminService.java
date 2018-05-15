package service;

import entity.ext.Admin;
import exception.ServiceException;

import java.util.List;

public interface AdminService {
    Admin signIn(String login, String password);
    List<Admin> findAllAdmins() throws ServiceException;
}
