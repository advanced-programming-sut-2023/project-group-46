package Model;

public class Building {
    private int hp;
    private String name;
    private int capacity;
    private BuildingType buildingType;
    private Empire owner;

    public Building(BuildingType buildingType, Empire owner) {
        this.owner = owner;
        this.name = buildingType.getName();
        this.hp = buildingType.getHp();
        this.capacity = buildingType.getCapacity();
        this.buildingType = buildingType;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getCapacity() {
        return capacity;
    }

    public Empire getOwner() {
        return owner;
    }
}
