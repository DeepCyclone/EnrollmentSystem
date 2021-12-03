package by.epamtc.enrollmentsystem.controller.action;

import by.epamtc.enrollmentsystem.controller.action.impl.*;

import java.util.HashMap;
import java.util.Map;

/*
* This class contains an URL actions associated with java commands, that allows to call methods associated with request
* @author Flexus
*/

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
        actions.put(CommandType.PRELOAD_FACULTIES,new PreloadFacultiesCommand());
        actions.put(CommandType.PRELOAD_ABOUTUS_PAGE,new AboutUsPreloaderCommand());
        actions.put(CommandType.PRELOAD_FACILITIES_TAB,new PreloadFacilitiesTabCommand());
        actions.put(CommandType.CHANGE_LANGUAGE,new LanguageChangerCommand());
        actions.put(CommandType.UPDATE_ENROLLMENT_STATUS,new EnrollmentStatusUpdateCommand());
        actions.put(CommandType.START_ENROLLMENT,new StartEnrollmentCommand());
        actions.put(CommandType.PRELOAD_USER_POPUP,new PreloadUserPopupCommand());
        actions.put(CommandType.DELETE_USER,new DeleteUserCommand());
        actions.put(CommandType.EMPTY,new EmptyCommand());
    }

    private static volatile CommandProvider instance;

    /*
     * Double-checked singleton pattern provides an effective way to create single objects with thread-safety
     */
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
        Command command;
        try {
            CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
            command = actions.get(commandType);
        }
        catch (IllegalArgumentException exception) {
            command = actions.get(CommandType.EMPTY);
        }
        return command;
    }

    public void addCommand(CommandType type,Command command){
        actions.put(type,command);
    }
}
