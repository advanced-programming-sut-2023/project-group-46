package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    REGEX1("^show  map \\-x (?<x>\\d+) \\-y (?<y>\\d+)$"),
    REGEX2("^map ((up||left||down||right)\\s\\d+*)+$"),
    REGEX3("^show details \\-x (?<x>\\d+) \\-y (?<y>\\d+)$");

    private final String regex;

    private MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
