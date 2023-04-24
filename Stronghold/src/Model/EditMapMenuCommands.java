package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EditMapMenuCommands {
    SET_TEXTURE("^settexture \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+)$"),
    SET_TEXTURE_RECTANGLE("^settexture \\-x1 (?<x1>\\d+) \\-y1 (?<y1>\\d+) \\-x2 (?<x2>\\d+) \\-y2 (?<y2>\\d+) \\-t (?<type>\\S+)$"),
    CLEAR("^clear \\-x (?<x>\\d+) \\-y (?<y>\\d+)$"),
    DROP_ROCK("^droprock \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-d (?<direction>\\S+)$"),
    DROP_TREE("^droptree \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+)$"),
    DROP_BUILDING("^dropbuilding \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+)$"),
    DROP_UNIT("^dropunit \\-x (?<x>\\d+) \\-y (?<y>\\d+) \\-t (?<type>\\S+) \\-c (?<count>\\S+)$");

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
