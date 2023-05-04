package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SHOW_MAP("^show map \\-x (?<x>\\d+) \\-y (?<y>\\d+)$"),
    MOVE_IN_MAP("^map ((up||left||down||right)\\s\\d+*)+$"),
    SHOW_DETAILS("^show details \\-x (?<x>\\d+) \\-y (?<y>\\d+)$");

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
