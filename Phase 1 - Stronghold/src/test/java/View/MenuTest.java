package View;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void getMatcher_shouldReturnNullWhenNoMatchFound() {
        String command = "not_a_match";
        String regex = "match_pattern";
        Matcher matcher = Menu.getMatcher(command, regex);
        Assertions.assertNull(matcher);
    }

    @Test
    void getMatcher_shouldReturnMatcherWhenMatchFound() {
        String command = "match_pattern";
        String regex = "match_pattern";
        Matcher matcher = Menu.getMatcher(command, regex);
        Assertions.assertNotNull(matcher);
    }

    @Test
    void getScanner_shouldReturnScannerInstance() {
        Scanner scanner = Menu.getScanner();
        Assertions.assertNotNull(scanner);
    }

}