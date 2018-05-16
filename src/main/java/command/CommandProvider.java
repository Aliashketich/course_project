package command;

import command.impl.forward.*;
import command.impl.redirect.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.Map;


public class CommandProvider {
    private final static CommandProvider INSTANCE = new CommandProvider();
    private Map<CommandName, ICommand> commands = new EnumMap<CommandName,ICommand>(CommandName.class);

    public CommandProvider() {
        commands.put(CommandName.SIGN_IN,new SignIn());
        commands.put(CommandName.INDEX,new Index());
        commands.put(CommandName.ADD_PRODUCT,new AddProduct());
        commands.put(CommandName.SIGN_OUT, new SignOut());
        commands.put(CommandName.SHOW_ORDER_ADMIN,new ShowOrderAdmin());
        commands.put(CommandName.SHOW_ORDER_WAITER,new ShowOrderWaiter());
        commands.put(CommandName.CLOSE_ORDER,new CloseOrder());
        commands.put(CommandName.FIND_BY_TYPE,new FindByType());
        commands.put(CommandName.DELETE_PRODUCT,new DeleteProduct());
        commands.put(CommandName.WAITER_PROFILE,new WaiterProfile());
        commands.put(CommandName.EDIT_PROFILE, new EditProfile());
        commands.put(CommandName.SHOW_WAITER,new ShowWaiter());
        commands.put(CommandName.SHOW_PRODUCT,new ShowProduct());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }


    public ICommand getCommand(HttpServletRequest request) {
        ICommand iCommand = commands.get(CommandName.WRONG_REQUEST);
        String command = request.getRequestURI();
        command=command.replace("/cafe.by/","");
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            iCommand = commands.get(commandName);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return iCommand;
    }
}