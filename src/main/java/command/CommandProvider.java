package command;

import command.impl.forward.Index;
import command.impl.forward.ShowOrderAdmin;
import command.impl.redirect.AddProduct;
import command.impl.redirect.SignIn;
import command.impl.redirect.SignOut;

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