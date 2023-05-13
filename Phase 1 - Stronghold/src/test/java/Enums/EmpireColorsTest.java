package Enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpireColorsTest {

    @Test
    void getNumberOfColor() {
        assertEquals(EmpireColors.COLOR3.getNumberOfColor() , 2);

    }

    @Test
    void values() {

        assertNull(EmpireColors.getEmpireColorByNumber(15));
    }
}