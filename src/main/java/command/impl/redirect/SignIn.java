package command.impl.redirect;

import command.ICommand;
import entity.ext.Admin;
import entity.ext.Waiter;
import exception.ServiceException;
import factory.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import util.SessionElements;
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
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            diagnoseIncorrectSignIn(request);
            return pageName.getPath();
        }

        try {
            Admin admin = serviceFactory.getAdminService().signIn(login, password);
            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("role", 1);
                session.setAttribute("admin", admin);
                LOGGER.log(Level.INFO, "Successful sign in account as admin " + admin.getLogin());
                response.sendRedirect(SessionElements.getPageCommand(request));
            } else {
                Waiter waiter = serviceFactory.getWaiterService().signIn(login, password);
                if (waiter != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("role", 2);
                    session.setAttribute("waiter", waiter);
                    LOGGER.log(Level.INFO, "Successful sign in account as waiter " + waiter.getLogin());
                    response.sendRedirect(SessionElements.getPageCommand(request));
                } else {
                    diagnoseIncorrectSignIn(request);
                    response.sendRedirect(SessionElements.getPageCommand(request));
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Sign in finished.");
        return pageName.getPath();
    }

    private static void diagnoseIncorrectSignIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Неверно введен логин или пароль. Повторите вход.");
        }
    }
}
