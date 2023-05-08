package Controller;

import Enums.BuildingType;
import Enums.EnvironmentType;
import Enums.UnitType;
import Model.Building;
import Model.Map;
import Model.Unit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;

public class EditMapMenuController {
    private Map map;
    private String username = LoginMenuController.getLoggedInUser().getUsername();

    public String setTexture(Matcher matcher) {
        readMap();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 || y < 0) || (x >= map.getSize() || y >= map.getSize())) return "Invalid coordinate";
        if (map.getMap()[x][y].getBuilding() != null) return "Not empty";
        String type = matcher.group("type");
        if (EnvironmentType.getEnvironmentTypeByName(type) == null) {
            return "invalid type name";
        }
        map.getMap()[x][y].setType(type);
        writeMap();
        return "Success";
    }

    public String setTextureRectangle(Matcher matcher) {
        readMap();
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if ((x1 < 0 || y1 < 0) || (x2 >= map.getSize() || y2 >= map.getSize())) return "Invalid coordinate";
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                if (map.getMap()[x][y].getBuilding() != null) return "Not empty";
            }
        }
        String type = matcher.group("type");
        if (EnvironmentType.getEnvironmentTypeByName(type) == null) {
            return "invalid type name";
        }
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                map.getMap()[x][y].setType(type);
            }
        }
        writeMap();
        return "Success";
    }

    public String clear(Matcher matcher) {
        readMap();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 || y < 0) || (x >= map.getSize() || y >= map.getSize())) return "Invalid coordinate";
        map.getMap()[x][y].setBuilding(null);
        map.getMap()[x][y].getUnits().clear();
        map.getMap()[x][y].setType("earth");
        map.getMap()[x][y].setEnvironmentName(null);
        writeMap();
        return "Success";
    }

    public String dropRock(Matcher matcher) {
        readMap();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 || y < 0) || (x >= map.getSize() || y >= map.getSize())) return "Invalid coordinate";
        String[] typeDirection = {"n", "e", "w", "s"};
        String direction = matcher.group("direction");
        if (direction.equals("r")) {
            Random random = new Random();
            direction = typeDirection[random.nextInt(4)];
        } else if (Arrays.asList(typeDirection).contains(direction)) return "Invalid direction";
        if (map.getMap()[x][y].getBuilding() != null || map.getMap()[x][y].getUnits().size() > 0 || map.getMap()[x][y].getEnvironmentName() != null) {
            return "there are some other things in this place";
        }
        map.getMap()[x][y].setEnvironmentName("rock " + direction);
        writeMap();
        return "Success";
    }

    public String dropTree(Matcher matcher) {
        readMap();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if ((x < 0 || y < 0) || (x >= map.getSize() || y >= map.getSize())) return "Invalid coordinate";
        if (!EnvironmentType.getEnvironmentTypeByName(map.getMap()[x][y].getType()).isDropTree())
            return "Invalid cell type";
        if (!type.equals("datePalm") && !type.equals("cherryPalm") && !type.equals("desertShrub") && !type.equals("coconutPalm") && !type.equals("oliveTree")) {
            return "Invalid type for tree";
        }
        if (map.getMap()[x][y].getBuilding() != null || map.getMap()[x][y].getUnits().size() > 0 || map.getMap()[x][y].getEnvironmentName() != null) {
            return "there are some other things in this place";
        }
        map.getMap()[x][y].setEnvironmentName(type);
        writeMap();
        return "Success";
    }

    public String dropBuilding(Matcher matcher) {
        readMap();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String buildingName = matcher.group("type");
        if (BuildingType.getBuildingByName(buildingName) == null) {
            return "invalid building type";
        }
        if (x > map.getSize() || x < 0 || y > map.getSize() || y < 0) {
            return "invalid coordinate";
        }
        if (map.getMap()[x][y].getBuilding() != null || map.getMap()[x][y].getUnits().size() > 0 || map.getMap()[x][y].getEnvironmentName() != null) {
            return "there are some other things in this place";
        }
        String cellType = map.getMap()[x][y].getType();
        if (BuildingType.getBuildingByName(buildingName).getType().equals("FarmBuildings")) {
            if (!cellType.equals("thickGrass") && !cellType.equals("oasisGrass")) {
                return "can't drop farm buildings in this place";
            }
        } else if (buildingName.equals("IronMine")) {
            if (!cellType.equals("ironTexture")) {
                return "IronMine must be built on IronTexture";
            }
        } else if (buildingName.equals("Quarry")) {
            if (!cellType.equals("boulder")) {
                return "Quarry must be built on Boulder";
            }
        } else if (buildingName.equals("PitchRig")) {
            if (!cellType.equals("oil")) {//TODO check for environment type
                return "PitchRig must be built on Oil";
            }
        } else {//regular buildings
            if (!cellType.equals("earth") && !cellType.equals("earthAndStone") && !cellType.equals("scrub") && !cellType.equals("thickGrass") && !cellType.equals("oasisGrass") && !cellType.equals("beach")) {
                return "can't drop this building in this place";
            }
            if (buildingName.equals("keep") && map.getEmpireCoordinates().size() == 8) {
                return "Too much keepBuildings";
            }
        }
        if (buildingName.equalsIgnoreCase("keep")) {
            int[] coordinates = {x, y};
            map.getEmpireCoordinates().add(coordinates);
        }
        map.getMap()[x][y].setBuilding(new Building(BuildingType.getBuildingByName(buildingName)));
        writeMap();
        return "Success";
    }


    public String dropUnit(Matcher matcher) {
        readMap();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        if ((x < 0 || y < 0) || (x >= map.getSize() || y >= map.getSize())) return "Invalid coordinate";
        if (UnitType.getUnitByName(type) == null) {
            return "invalid name for unit";
        }
        int count = Integer.parseInt(matcher.group("count"));
        Unit unit = new Unit(UnitType.getUnitByName(type));
        for (int i = 0; i < count; i++) {
            map.getMap()[x][y].addUnit(unit);
        }
        writeMap();
        return "Success";
    }

    public String checkCountEmpires() {
        readMap();
        if (map.getEmpireCoordinates().size() < 2) return "Need more keepBuilding";
        return "";
    }

    private void readMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        try {
            map = objectMapper.readValue(new File(username + ".json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        try {
            objectMapper.writeValue(new File(username + ".json"), map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}