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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangePassword implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(ChangePassword.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start ChangePassword");
        String newPassword = (String) request.getParameter("password-new");
        String oldPassword = (String) request.getParameter("password-old");
        System.out.println(oldPassword);
        try {
            if ((int) request.getSession().getAttribute("role") == 2) {
                int idWaiter = ((Waiter) request.getSession().getAttribute("waiter")).getIdUser();
                if (serviceFactory.getWaiterService().findWaiterByIdAndPassword(idWaiter, oldPassword) != null) {
                    Waiter waiter = serviceFactory.getWaiterService().changePassword(idWaiter, newPassword);
                    request.getSession().setAttribute("waiter", waiter);
                    diagnoseChangePassword(request);
                } else {
                    diagnoseWrongOldPassword(request);
                }
            } else {
                int idAdmin = ((Admin) request.getSession().getAttribute("admin")).getIdUser();
                if (serviceFactory.getAdminService().findAdminByIdAndPassword(idAdmin, oldPassword) != null) {
                    System.out.println("old correct");
                    Admin admin = serviceFactory.getAdminService().changePassword(idAdmin, newPassword);
                    request.getSession().setAttribute("admin", admin);
                    diagnoseChangePassword(request);
                } else {
                    System.out.println("old incorrect");
                    diagnoseWrongOldPassword(request);
                }
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish ChangePassword");
        return pageName.getPath();
    }

    private static void diagnoseChangePassword(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пароль изменен.");
        }
    }

    private static void diagnoseWrongOldPassword(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Старый пароль введен неверно.");
        }
    }

}