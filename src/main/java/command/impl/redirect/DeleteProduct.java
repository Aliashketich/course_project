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


public class DeleteProduct implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(DeleteProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start delete product");
        int idProduct;
        try {
            idProduct = Integer.parseInt(request.getParameter("idProduct"));
            if (serviceFactory.getOrderProductService().checkActiveOrderProduct(idProduct)) {
                diagnoseDeleteError(request);
            } else {
                serviceFactory.getProductService().deleteProduct(idProduct);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish delete product");
        return pageName.getPath();
    }

    private static void diagnoseDeleteError(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Продукт находится в одном из незакрытых заказов, его нельзя удалить.");
        }
    }

}