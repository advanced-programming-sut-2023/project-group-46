package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
    TRADE_ACCEPT_REGEX("^trade accept -i (?<id>\\d+)$"),
    TRADE_REGEX("(?=.* -m (?<message>\\S+))(?=.* -w (?<wantedResource>\\S+))(?=.* -g (?<givenResource>\\S+))(?=.* -wa (?<wantedAmount>\\S+))(?=.* -ga (?<givenAmount>\\S+))^trade( *-[wgma]+ \\S+){5}$"),
    ;//trade -m salam -w ale -g mace -wa 5 -ga 2(wa:wanted amount)(ga:given amount)
    private final String regex;

    private TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, TradeMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
