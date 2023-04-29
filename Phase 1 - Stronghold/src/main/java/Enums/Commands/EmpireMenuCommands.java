package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EmpireMenuCommands {
    FOOD_RATE_REGEX("^food rate -r (?<rate>-?\\d+)$"),
    TAX_RATE_REGEX("tax rate -r (?<rate>-?\\d+)"),
    FEAR_RATE_REGEX("fear rate -r (?<rate>-?\\d+)");
    private final String regex;

    EmpireMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, EmpireMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
