package command.impl;

import command.ICommand;
import entity.ext.Admin;
import exception.ServiceException;
import factory.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import webenum.PageName;
import webenum.PageNameRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class SignIn implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(SignIn.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Sign in started.");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
//        boolean role;
//        if (request.getParameter("check")!=null) {
//            role = true;
//        } else {
//            role = false;
//        }
        System.out.println(login + password);
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            diagnoseIncorrectSignIn(request);
            return pageName.getPath();
        }

        try {
                Admin admin = serviceFactory.getAdministratorService().signIn(login, password);
                if (admin != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("role", 1);
                    session.setAttribute("admin", admin);
                    LOGGER.log(Level.INFO, "Successful sign in account as administrator " + admin.getLogin());
                    response.sendRedirect(PageNameRedirect.INDEX.getPath());
                } else {
                    diagnoseIncorrectSignIn(request);
                    return pageName.getPath();
                }
//            } else {
//                Client client = serviceFactory.getClientService().signIn(login, password);
//                if (client != null) {
//                    HttpSession session = request.getSession();
//                    session.setAttribute("role", 2);
//                    session.setAttribute("client", client);
//                    LOGGER.log(Level.INFO, "Successful sign in account as client " + client.getLogin());
//                    response.sendRedirect(PageNameRedirect.INDEX.getPath());
//                } else {
//                    diagnoseIncorrectSignIn(request);
//                    return pageName.getPath();
//                }
//            }
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Sign in finished.");
        return pageName.getPath();
    }

    private static void diagnoseIncorrectSignIn(HttpServletRequest request) {
        request.setAttribute("error_data","логин и пароль пустые");
        request.setAttribute("error_data", "Неверно введен логин или пароль. Повторите вход.");
    }
}
