package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    BUY_REGEX("(?=.* -i (?<name>\\S+))(?=.* -a (?<amount>\\S+))^buy( *\\-[ai] \\S+){2}$"),
    SELL_REGEX("(?=.* -i (?<name>\\S+))(?=.* -a (?<amount>\\S+))^sell( *\\-[ai] \\S+){2}$"),
    ;
    private final String regex;

    private ShopMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ShopMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
