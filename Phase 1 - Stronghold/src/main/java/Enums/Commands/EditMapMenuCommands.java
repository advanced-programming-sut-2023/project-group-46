package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EditMapMenuCommands {
    SET_TEXTURE("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))(?=.* -t (?<type>\\S+))^settexture ( *-[xyt]+ \\S+){3}$"),
    SET_TEXTURE_RECTANGLE("(?=.* -x (?<x1>\\d+))(?=.* -y (?<y1>\\d+))(?=.* -x (?<x2>\\d+))(?=.* -y (?<y2>\\d+))(?=.* -t (?<type>\\S+))^settexture ( *-[x1y1x2y2t]+ \\S+){5}$"),
    CLEAR("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^clear( *-[xy]+ \\S+){2}$"),
    DROP_ROCK("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))(?=.* -d (?<direction>\\S+))^droprock ( *-[xyd]+ \\S+){3}$"),
    DROP_TREE("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))(?=.* -t (?<type>\\S+))^droptree ( *-[xyt]+ \\S+){3}$"),
    DROP_BUILDING("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))(?=.* -t (?<type>\\S+))^dropbuilding ( *-[xyt]+ \\S+){3}$"),
    DROP_UNIT("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))(?=.* -t (?<type>\\S+))(?=.* -c (?<count>\\d+))^dropunit ( *-[xytc]+ \\S+){4}$");

    private final String regex;

    EditMapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, EditMapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
