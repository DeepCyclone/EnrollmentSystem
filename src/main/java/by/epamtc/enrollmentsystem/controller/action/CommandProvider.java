package by.epamtc.enrollmentsystem.controller.action;

import by.epamtc.enrollmentsystem.controller.action.impl.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final Map<CommandType,Command> actions = new HashMap<>();

    private CommandProvider(){
        actions.put(CommandType.SIGN_UP,new SignupCommand());
        actions.put(CommandType.AUTHORIZATION,new AuthenticationCommand());
        actions.put(CommandType.LOGOUT,new LogoutCommand());
        actions.put(CommandType.UPDATE_INFO,new UpdateUserInfoCommand());
        actions.put(CommandType.UPDATE_STUDYING_INFO,new UpdateStudyingInfoCommand());
        actions.put(CommandType.GET_USER_PROFILES,new GetUserProfilesCommand());
        actions.put(CommandType.PRELOAD_WELCOMEPAGE,new PreloadWelcomePageCommand());
        actions.put(CommandType.PRELOAD_USERINFO_PAGE,new PreloadUserInfoCommand());
        actions.put(CommandType.PRELOAD_FACULTIES_PAGE,new PreloadFacultiesPageCommand());
        actions.put(CommandType.PRELOAD_ADMIN_PAGE,new PreloadAdminPanelCommand());
        actions.put(CommandType.PRELOAD_SELECTED_FACULTIES,new PreloadSelectedFacultiesCommand());
        actions.put(CommandType.PRELOAD_FACILITIES_TAB,new PreloadFacilitiesTabCommand());
        actions.put(CommandType.CHANGE_LANGUAGE,new LanguageChangerCommand());
        actions.put(CommandType.UPDATE_ENROLLMENT_STATUS,new EnrollmentStatusUpdateCommand());
        actions.put(CommandType.START_ENROLLMENT,new StartEnrollmentCommand());
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
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());//TODO prevent invalid command exception

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
