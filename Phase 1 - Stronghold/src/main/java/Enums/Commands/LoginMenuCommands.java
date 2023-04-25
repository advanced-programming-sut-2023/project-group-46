package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGGING_IN("(?=.* -u (?<username>[^-]*))(?=.* -p (?<password>[^-]*))^user login( *-[up][^-]+){2}(?:(?<stayLoggedInFlag>--stay-logged-in\\s*))?$"),
    FORGOT_PASSWORD("^forgot my password \\-u (?<username>.+)$"),
    ;
    private final String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
