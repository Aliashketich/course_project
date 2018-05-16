package command.impl.forward;

import command.ICommand;
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

public class WaiterProfile implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(WaiterProfile.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.PROFILE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ClientProfile started.");
        try {
            int idWaiter = ((Waiter) request.getSession().getAttribute("waiter")).getIdUser();
            Waiter waiter = serviceFactory.getWaiterService().findWaiterById(idWaiter);
            request.setAttribute("waiter_account", waiter);
            System.out.println((Waiter)request.getAttribute("waiter_account"));
            request.getSession().setAttribute("pageCommand", PageNameRedirect.PROFILE.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: ClientProfile finished.");
        return pageName.getPath();

    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

}
