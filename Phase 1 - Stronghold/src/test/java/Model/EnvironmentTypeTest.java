package Model;

import Enums.EnvironmentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTypeTest {

    @Test
    void returnedNameOfEnvironmentIsCorrect() throws Exception {
        EnvironmentType environmentType = EnvironmentType.EARTH;
        assertEquals(environmentType.getName() , "earth");
    }

}