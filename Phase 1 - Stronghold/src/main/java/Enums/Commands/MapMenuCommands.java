package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SHOW_MAP("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^show map( *-[xy]+ \\S+){2}$"),
    MOVE_IN_MAP("^move map (?<command>.+)$"),
    SHOW_DETAILS("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^show details( *-[xy]+ \\S+){2}$");

    private final String regex;

    MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}