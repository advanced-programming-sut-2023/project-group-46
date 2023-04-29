package Model;

public enum EnvironmentType {
    EARTH("earth" , "\u001b[42m", true, true,true),
    EARTH_AND_SAND("earthAndSand" , "\u001b[42m", true, true,true),
    BOULDER("boulder" , "\u001b[46m", false, true,true),
    ROCK_TEXTURE("rockTexture" ,"\u001b[41m", false, false,false),
    IRON_TEXTURE("ironTexture" ,"\u001b[41m", false ,true,true),
    GRASS("grass" ,"\u001b[42m", true, true,true),
    THICK_GRASS("thickGrass" ,"\u001b[42m", true, true,true),
    OASIS_GRASS("oasisGrass" ,"\u001b[42m",true , true,false),
    OIL("oil" ,"\u001b[45m", false, true,true),
    MARSH("marsh" ,"\u001b[43m", false, false,false),
    PLAIN_TEXTURE("plainTexture" , "\u001b[44m",false, true,true),
    LOW_DEPTH_WATER("lowDepthWater" ,"\u001b[44m", false, true,false),
    RIVER("river" ,"\u001b[44m", false, false,false),
    SMALL_POND("smallPond" ,"\u001b[44m", false, false,false),
    LARGE_POND("largePond" ,"\u001b[44m", false, false,false),
    BEACH("beach" ,"\u001b[43m", false, true,true),
    SEA("sea" , "\u001b[44m",false, false,false);


    private String name;
    private String color;
    private boolean dropTree;
    private boolean dropBuilding;
    private boolean passable;

    EnvironmentType(String name, String color, boolean dropTree, boolean dropBuilding, boolean passable) {
        this.name = name;
        this.color = color;
        this.dropTree = dropTree;
        this.dropBuilding = dropBuilding;
        this.passable = passable;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public boolean isDropTree() {
        return dropTree;
    }

    public boolean isDropBuilding() {
        return dropBuilding;
    }

    public boolean isPassable() {
        return passable;
    }

    public static EnvironmentType getEnvironmentTypeByName(String name) {
        for (EnvironmentType type : EnvironmentType.values()) {
            if (name.equalsIgnoreCase(type.name))
                return type;
        }
        return null;
    }
}
