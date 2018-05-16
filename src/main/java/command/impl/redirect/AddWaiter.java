package command.impl.redirect;

import command.ICommand;
import entity.ext.Waiter;
import factory.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.WaiterService;
import webenum.PageName;
import webenum.PageNameRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddWaiter implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddWaiter.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.WAITERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Sign up started.");
        Waiter waiter =null;
        WaiterService waiterService = serviceFactory.getWaiterService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        try {
            if (waiterService.findWaiterByEmail(email) != null) {
                diagnoseCommonEmail(request);
            } else {
                if (waiterService.findWaiterByLogin(login) != null) {
                    diagnoseCommonLogin(request);
                } else {
                    waiter = waiterService.registrationWaiter(login, password, name, surname, email);
                    if(waiter==null){
                        diagnoseCommonLogin(request);
                    }
                }
            }
            response.sendRedirect(PageNameRedirect.WAITERS.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Sign Up finished.");
        return pageName.getPath();
    }


    private void diagnoseCommonLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким логином уже сужествует.");
        }
    }


    private void diagnoseCommonEmail(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким адресом электронной почты уже сужествует.");
        }
    }
}