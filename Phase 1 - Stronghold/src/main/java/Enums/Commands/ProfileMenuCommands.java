package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {

    CHANGE_USERNAME("^profile change \\-u (?<username>.+)$"),
    CHANGE_NICKNAME("^profile change \\-n (?<nickname>.+)$"),
    CHANGE_PASSWORD("(?=.* -o (?<oldPassword>[^-]*))(?=.* -n (?<newPassword>[^-]*))^profile change( *-[on][^-]+){2}$"),
    CHANGE_EMAIL("^profile change \\-e (?<email>.+)$"),
    CHANGE_SLOGAN("^profile change \\-s (?<slogan>.+)$"),
    ;
    private final String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
