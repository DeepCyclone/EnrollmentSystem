package by.epamtc.enrollmentsystem.controller.action;

import by.epamtc.enrollmentsystem.controller.action.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static Map<CommandType,Command> actions = new HashMap<>();

    private CommandProvider(){
        actions.put(CommandType.SIGN_UP,new SignupCommand());
        actions.put(CommandType.AUTHORIZATION,new LoginCommand());
        actions.put(CommandType.LOGOUT,new LogoutCommand());
        actions.put(CommandType.UPDATE_INFO,new UpdateInfoCommand());
        actions.put(CommandType.PRELOAD_WELCOMEPAGE,new PreloadWelcomePageCommand());
        actions.put(CommandType.UPLOAD_USERINFO,new UploadUserInfoCommand());
        actions.put(CommandType.EMPTY,new EmptyCommand());
    }

    private static volatile CommandProvider instance;

    public static CommandProvider getInstance() {
        CommandProvider localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandProvider.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandProvider();
                }
            }
        }
        return localInstance;
    }

    public Command getCommand(String commandName) {
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());

        Command command;

        if(commandType != null){
            command = actions.get(commandType);
        }
        else{
            command = actions.get(CommandType.EMPTY);
        }

        return command;
    }
}
