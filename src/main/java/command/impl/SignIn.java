package command.impl;

import command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;

        //РЕАЛИЗАЦИЯ ВХОДА В СИСТЕМУ!!!
    }
}
