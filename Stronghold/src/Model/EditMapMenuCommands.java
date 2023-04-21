package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EditMapMenuCommands {
    REGEX1("^settexture \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+)$"),
    REGEX2("^settexture \\-x1 (?<x1>\\d+) \\-y1 (?<y1>\\d+) \\-x2 (?<x2>\\d+) \\-y2 (?<y2>\\d+) \\-t (?<type>\\S+)$"),
    REGEX3("^clear \\-x (?<x>\\d+) \\-y (?<y>\\d+)$"),
    REGEX4("^droprock \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-d (?<direction>\\S+)$"),
    REGEX5("^droptree \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+)$"),
    REGEX6("^dropbuilding \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+)$"),
    REGEX7("^dropunit \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+) \\-c (?<count>\\S+)$");

    private String regex;

    private EditMapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, EditMapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
