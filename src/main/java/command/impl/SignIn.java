package command.impl;

import command.ICommand;
import exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import webenum.PageName;
import webenum.PageNameRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
//    private static final Logger LOGGER = Logger.getLogger(SignIn.class);
//    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
//    private PageName pageName = PageName.INDEX;
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        LOGGER.log(Level.INFO, "Command: Sign in started.");
//        String login = request.getParameter("login_in");
//        String password = request.getParameter("password_in");
//        boolean role;
//        if (request.getParameter("check")!=null) {
//            role = true;
//        } else {
//            role = false;
//        }
//        System.out.println(login + password + role);
//        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
//            diagnoseIncorrectSignIn(request);
//            return pageName.getPath();
//        }
//
//        try {
//            if (role) {
//                Administrator administrator = serviceFactory.getAdministratorService().signIn(login, password);
//                if (administrator != null) {
//                    HttpSession session = request.getSession();
//                    session.setAttribute("role", 1);
//                    session.setAttribute("admin", administrator);
//                    LOGGER.log(Level.INFO, "Successful sign in account as administrator " + administrator.getLogin());
//                    response.sendRedirect(PageNameRedirect.INDEX.getPath());
//                } else {
//                    diagnoseIncorrectSignIn(request);
//                    return pageName.getPath();
//                }
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
//        } catch (IOException | ServiceException e) {
//            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
//            pageName = pageName.ERROR;
//        }
//        LOGGER.log(Level.INFO, "Sign in finished.");
//        return pageName.getPath();
//    }
//
//    private static void diagnoseIncorrectSignIn(HttpServletRequest request) {
//        request.setAttribute("error_data", "Неверно введен логин или пароль. Повторите вход.");
//    }
}
