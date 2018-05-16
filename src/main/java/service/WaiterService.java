package service;

import entity.ext.Waiter;
import exception.ServiceException;

import java.util.List;

public interface WaiterService {
    Waiter signIn(String login,String password) throws ServiceException;
    Waiter findWaiterByLogin(String login)throws ServiceException;
    Waiter findWaiterByEmail(String email)throws ServiceException;
    List<Waiter> findAllWaiters() throws ServiceException;
    Waiter findWaiterById(int idWaiter) throws ServiceException;

    Waiter editWaiter(int idWaiter, String surname, String name, String email)throws ServiceException;

    Waiter findWaiterByIdAndPassword(int idWaiter, String oldPassword)throws ServiceException;

    Waiter changePassword(int idWaiter, String newPassword)throws ServiceException;

    Waiter registrationWaiter(String login, String password, String name, String surname, String email)throws ServiceException;
}
