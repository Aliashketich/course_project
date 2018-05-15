package service;

import entity.ext.Waiter;
import exception.ServiceException;

import java.util.List;

public interface WaiterService {
    Waiter signIn(String login,String password) throws ServiceException;
    Waiter findWaiterByLogin(String login);
    Waiter findWaiterByEmail(String email);
    List<Waiter> findAllWaiters() throws ServiceException;
    Waiter findWaiterById(int idWaiter) throws ServiceException;
}
