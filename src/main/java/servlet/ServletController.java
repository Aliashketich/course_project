package servlet;

import connectionpull.ConnectionPull;

import command.CommandProvider;
import command.ICommand;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Controller",
        urlPatterns="/cafe.by/*")

public class ServletController extends HttpServlet {
    private final static Logger LOGGER =  Logger.getLogger(ServletController.class);
    private final static CommandProvider commandProvider = CommandProvider.getInstance();
    private static ConnectionPull connectionPull;

    @Override
    public void init() {
//        connectionPull = ConnectionPull.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.log(Level.INFO, request.getRequestURI());
        ICommand command = commandProvider.getCommand(request);
        String page = command.execute(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null && !response.isCommitted()) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}