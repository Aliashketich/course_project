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

public class EditProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start EditProduct");
        int idProduct = Integer.parseInt(request.getParameter("idproduct"));
        System.out.println(idProduct);
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        Double cost = Double.parseDouble(request.getParameter("cost"));
        String description = request.getParameter("description");
        try {
            if (serviceFactory.getProductService().findProductByNameAndId(name,idProduct) != null) {
                diagnoseCommonName(request);
            } else {
                serviceFactory.getProductService().editProduct(idProduct,type, name, cost, description);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish EditProduct");
        return pageName.getPath();
    }

    private static void diagnoseCommonName(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Продукт с таким названием уже существует в меню.");
        }
    }

}
