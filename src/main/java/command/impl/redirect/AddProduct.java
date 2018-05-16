package command.impl.redirect;

import command.ICommand;
import entity.Product;
import factory.ServiceFactory;
import service.ProductService;
import util.SessionElements;
import webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddProduct implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add product ");
        Product product = null;
        ProductService productService = serviceFactory.getProductService();
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        Double cost = Double.parseDouble(request.getParameter("cost"));
        String description = request.getParameter("description");
        try {
            if (productService.findProductByName(name) != null) {
                diagnoseCommonName(request);
            } else {
                productService.addProduct(type, name, cost, description);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add product");
        return pageName.getPath();
    }

    private static void diagnoseCommonName(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Такой продукт уже есть в меню.");
        }
    }

}