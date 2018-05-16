package command.impl.redirect;

import command.ICommand;
import entity.ext.Waiter;
import exception.ServiceException;
import factory.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import util.SessionElements;
import webenum.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProfile implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditProfile.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start EditProfile");
        Waiter waiter = null;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String clientEmail = ((Waiter) request.getSession().getAttribute("waiter")).getEmail();
        int idWaiter = ((Waiter) request.getSession().getAttribute("waiter")).getIdUser();
        try {
            if (!clientEmail.equals(email)) {
                System.out.println(serviceFactory.getWaiterService().findWaiterByEmail(email));
                if (serviceFactory.getWaiterService().findWaiterByEmail(email) == null) {
                    waiter = serviceFactory.getWaiterService().editWaiter(idWaiter, surname, name, email);
                    request.getSession().setAttribute("waiter", waiter);
                } else {
                    diagnoseCommonEmail(request);
                }
            } else {
                waiter = serviceFactory.getWaiterService().editWaiter(idWaiter, surname, name, email);
                request.getSession().setAttribute("waiter", waiter);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish EditProfile");
        return pageName.getPath();
    }

    private void diagnoseCommonEmail(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким адресом электронной почты уже сужествует.");
        }
    }


}
