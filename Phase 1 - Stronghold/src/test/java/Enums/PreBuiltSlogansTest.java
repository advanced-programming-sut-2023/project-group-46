package Enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreBuiltSlogansTest {

    @Test
    public void testGetSloganByNumber() {
        String slogan = PreBuiltSlogans.getSloganByNumber(1);
        Assertions.assertEquals("I shall have my revenge, in this life or the next!", slogan);

        slogan = PreBuiltSlogans.getSloganByNumber(5);
        Assertions.assertEquals("Victorious warriors win first and then go to war, while defeated warriors go to war first and then seek to win.", slogan);

        // Test with invalid input
        slogan = PreBuiltSlogans.getSloganByNumber(6);
        Assertions.assertNull(slogan);
    }

    @Test
    public void testDisplayAllSlogans() {
        String allSlogans = PreBuiltSlogans.displayAllSlogans();
        String expectedOutput = "Slogan 1: I shall have my revenge, in this life or the next!\n" +
                "Slogan 2: Be where your enemy is not.\n" +
                "Slogan 3: Who does not know the evils of war cannot appreciate its benefits.\n" +
                "Slogan 4: He will win who knows when to fight and when not to fight.\n" +
                "Slogan 5: Victorious warriors win first and then go to war, while defeated warriors go to war first and then seek to win.\n";

        Assertions.assertEquals(expectedOutput, allSlogans);
    }

}