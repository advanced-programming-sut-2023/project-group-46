package Model;

import Enums.BuildingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void mapReturnsSizeCorrectly() throws Exception {
        Map map = new Map(100);
        assertEquals(map.getSize() , 100);
    }


}