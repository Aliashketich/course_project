package command.impl.redirect;

import command.ICommand;
import factory.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.AdminService;
import service.WaiterService;
import util.SessionElements;
import webenum.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAdmin implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddAdmin.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add admin ");
        WaiterService waiterService = serviceFactory.getWaiterService();
        AdminService adminService = serviceFactory.getAdminService();
        String login = request.getParameter("login-admin");
        String password = request.getParameter("password-admin");
        System.out.println(login+password);
        try {
            if (waiterService.findWaiterByLogin(login) != null || adminService.findAdminByLogin(login) != null) {
                diagnoseCommonLogin(request);
            } else {
                adminService.addAdmin(login,password);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add admin");
        return pageName.getPath();
    }

    private void diagnoseCommonLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким логином уже сужествует.");
        }
    }

}