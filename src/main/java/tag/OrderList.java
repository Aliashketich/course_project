package tag;


import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class OrderList extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        Integer role = (Integer) pageContext.getSession().getAttribute("role");
        String productList = null;
        if (role != null) {
            switch (role) {
                case 1:
                    productList = "/front/jsp/admin/orderlist.jsp";
                    break;
                case 2:
                    productList = "/front/jsp/waiter/orderlist.jsp";
                    break;
            }
        }
        try {
            pageContext.include(productList);
        } catch (IOException | ServletException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
