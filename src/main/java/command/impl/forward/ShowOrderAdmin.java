package command.impl.forward;


import command.ICommand;
import entity.Order;
import entity.OrderProduct;
import entity.Product;
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
import java.util.List;

public class ShowOrderAdmin implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ShowOrderAdmin.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.ORDERS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ShowOrderAdmin started.");
        try {
            List<Order> orders = serviceFactory.getOrderService().findAllOrders();
            List<OrderProduct> orderProducts = serviceFactory.getOrderProductService().findAllOrderProducts();
            List<Product> products = serviceFactory.getProductService().findAllProducts();
            List<Waiter> waiters = serviceFactory.getWaiterService().findAllWaiters();
            request.setAttribute("waiters", waiters);
            request.setAttribute("orders", orders);
            request.setAttribute("order_products",orderProducts);
            request.setAttribute("products", products);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.ORDERS.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: ShowOrderAdmin finished.");
        return pageName.getPath();

    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

}
