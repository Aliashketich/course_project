package dao;

import entity.User;
import entity.ext.Waiter;
import exception.DaoException;

import java.util.List;

public interface IWaiterDAO extends IAbstractDAO {
    Waiter signIn(String login, String password) throws DaoException;
    Waiter findWaiterByLogin(String login) throws DaoException;
    Waiter findWaiterByEmail(String email) throws DaoException;
    Waiter addWaiter(int idUser,String login,String name, String surname, String email)throws DaoException;
    User addUser(String login, String password) throws DaoException;
    User findUserByLogin(String login) throws DaoException;
    List<Waiter> findAllWaiters() throws DaoException;
    Waiter findWaiterById(int idWaiter) throws DaoException;
}
