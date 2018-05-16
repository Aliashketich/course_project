package command.impl.redirect;

import command.ICommand;
import exception.ServiceException;
import factory.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import util.SessionElements;
import webenum.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteAdmin implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteAdmin.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start DeleteAdmin");
        int idAdmin;
        try {
            idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
            serviceFactory.getAdminService().deleteAdmin(idAdmin);
            diagnoseDeleteAdmin(request);
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish DeleteAdmin");
        return pageName.getPath();
    }

    private static void diagnoseDeleteAdmin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Администратор удален.");
        }
    }

}