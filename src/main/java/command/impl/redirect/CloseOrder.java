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


public class CloseOrder implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(CloseOrder.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.ORDERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start close order");
        int idWaiter, idOrder;

        try {
            idOrder = Integer.parseInt(request.getParameter("idOrder"));
            if (idOrder!=0)  {
                serviceFactory.getOrderService().closeOrder(idOrder);
            } else {
                diagnoseEmptyOrder(request);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e)

        {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish close order");
        return pageName.getPath();
    }



    private static void diagnoseEmptyOrder(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Заказ с таким id не существует!");
        }
    }




}