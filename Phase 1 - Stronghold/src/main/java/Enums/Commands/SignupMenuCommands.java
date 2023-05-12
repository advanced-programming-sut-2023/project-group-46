package Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupMenuCommands {

    CREATE_A_NEW_USER("(?=.* -u (?<username>[^-]*))(?=.* -e (?<email>[^-]*))(?=.* -n (?<nickname>[^-]*))(?=.* -p (?<password>[^-]*))(?=.* -c (?<passwordConfirmation>[^-]*))^user create( *-[upcne][^-]+){5}(?:(?<slogan>-s .*))?$"),
    PICK_A_SECURITY_QUESTION("(?=.* -q (?<questionNumber>\\d+))(?=.* -a (?<answer>[^-]+))(?=.* -c (?<answerConfirmation>[^-]*))^question pick( *-[qac][^-]+){3}$"),
    CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD("(?=.* -u (?<username>[^-]*))(?=.* -e (?<email>[^-]*))(?=.* -n (?<nickname>[^-]*))(?=.* -p (?<password>random))^user create( *-[upne][^-]+){4}(?:(?<slogan>-s .*))?$"),
    ;
    private final String regex;
    private SignupMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, SignupMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
