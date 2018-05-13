package service;

import entity.ext.Admin;
import exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    Admin signIn(String login, String password);
    List<Admin> findAllAdministrators() throws ServiceException;
}
