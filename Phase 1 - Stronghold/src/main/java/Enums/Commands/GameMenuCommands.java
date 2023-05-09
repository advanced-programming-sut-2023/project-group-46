package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    DROP_BUILDING_REGEX("(?=.* -x (?<x>\\S+))(?=.* -y (?<y>\\S+))(?=.* -t (?<type>.+))^dropbuilding( *-[xyt]+ \\S+){3}$"),
    SELECT_BUILDING_REGEX("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^select building( *-[xy] \\d+){2}$"),
    SELECT_UNIT_REGEX("(?=.* -x (?<x>\\S+))(?=.* -y (?<y>\\S+))(?=.* -t (?<type>\\S+))*^select unit( *-[xyt] \\S+){2,3}$"),
    MOVE_UNIT_REGEX("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^move unit to( *-[xy] \\d+){2}$"),
    PATROL_UNIT_REGEX("(?=.* -x1 (?<x1>\\d+))(?=.* -y1 (?<y1>\\d+))(?=.* -x2 (?<x2>\\d+))(?=.* -y2 (?<y2>\\d+))^patrol unit( *-[xy12]+ \\d+){4}$"),
    ATTACK_SPECIAL_ENEMY_REGEX("^attack -e (?<x>\\d+) (?<y>\\d+)$"),
    ATTACK_ARCHER_REGEX("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^attack( *-[xy] \\d+){2}$"),
    POUR_OIL_REGEX("^pour oil -d (?<direction>\\S+)$"),
    DIG_TUNNEL_REGEX("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^dig tunnel( *-[xy] \\d+){2}$"),
    BUILD_REGEX("^build -q (?<equipment>.+)$"),
    SET_UNIT_MODE_REGEX("^set -s (?<mode>\\S+)$"),
    CREATE_UNIT_REGEX("(?=.* -t (?<type>\\S+))(?=.* -c (?<count>\\S+))^createunit( *-[tc] \\S+){2}$"),
    CHANGE_MODE_ARMOUR_BUILDING_REGEX("^change mode -m (?<mode>\\S+)$"),
    DROP_UNIT_REGEX("(?=.* -x (?<x>\\S+))(?=.* -y (?<y>\\S+))(?=.* -t (?<type>\\S+))(?=.* -c (?<count>\\S+))^dropunit( *-[xytc]+ \\S+){4}$"),
    ATTACK_MACHINES_REGEX("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^attack machines( *-[xy] \\d+){2}$"),
    SET_OIL_FOR_ENGINEER("set oil for engineers -c (?<count>\\d+)"),//count is number of engineers that you want to do this task
    DIG_MOAT("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^dig moat( *-[xy] \\d+){2}$"),
    NO_MOAT("(?=.* -x (?<x>\\d+))(?=.* -y (?<y>\\d+))^no moat( *-[xy] \\d+){2}$");
    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
