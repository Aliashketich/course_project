package command.impl.forward;

import command.ICommand;
import webenum.PageName;
import webenum.PageNameRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class Index implements ICommand {
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == null || locale.isEmpty()) {
            locale = Locale.getDefault().getLanguage();
        }
        System.out.println(locale);
        request.getSession().setAttribute("pageCommand", PageNameRedirect.INDEX.getPath());

        request.getSession().setAttribute("locale",locale);
        return pageName.getPath();
    }
}