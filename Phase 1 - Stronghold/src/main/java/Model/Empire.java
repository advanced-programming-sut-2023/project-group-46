package Model;

import Enums.EmpireColors;
import Model.Goods.Armoury;
import Model.Goods.FoodStock;
import Model.Goods.Resources;

import java.util.ArrayList;

public class Empire {

    private ArrayList<Building> buildings;
    private ArrayList<Unit> units;
    private User user;
    private int empireId;//this is equal to index of the arraylist of empires in the Game
    private String color;
    private Armoury armoury;
    private FoodStock foodStock;
    private int unemployedPeople;
    private int employedPeople;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private int foodPopularity;
    private int fearPopularity;
    private int taxPopularity;
    private int religionPopularity;
    private Resources resources;// it should be given to empires in start of the game
    private int kingHp;
    private int[] keepCoordinates;

    public Empire(User user, int empireId, int x, int y) {//TODO check for the rates in the start of the game
        this.user = user;
        this.unemployedPeople = 10;
        this.employedPeople = 0;
        this.foodRate = 0;
        this.taxRate = 0;
        this.fearRate = 0;
        this.buildings = new ArrayList<>();
        this.units = new ArrayList<>();
        keepCoordinates = new int[2];
        this.empireId = empireId;
        this.religionPopularity = 0;
        this.keepCoordinates[0] = x;
        this.keepCoordinates[1] = y;
        //TODo add number to popularity factors
        this.resources = new Resources(5000, 0, 0, 0, 0, 50, 0, 100, 0, -150);
        this.armoury = new Armoury(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.foodStock = new FoodStock(0, 0, 0, 60, -60);
        this.color = EmpireColors.getEmpireColorByNumber(empireId).getName();
    }

    public Empire() {
    }

    public int[] getKeepCoordinates() {
        return keepCoordinates;
    }

    public String getColor() {
        return color;
    }

    public FoodStock getFoodStock() {
        return foodStock;
    }

    public User getUser() {
        return user;
    }

    public int getUnemployedPeople() {
        return unemployedPeople;
    }

    public int getEmployedPeople() {
        return employedPeople;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getFoodPopularity() {
        int counter = 0;
        if (foodStock.getApple() > 0) counter++;
        if (foodStock.getBread() > 0) counter++;
        if (foodStock.getCheese() > 0) counter++;
        if (foodStock.getMeat() > 0) counter++;
        if (counter == 0)
            return -8;
        return this.foodPopularity + counter - 1;
    }

    public void setFoodPopularity(int foodPopularity) {
        this.foodPopularity = foodPopularity;
    }

    public int getFearPopularity() {
        return fearPopularity;
    }

    public void setFearPopularity(int fearPopularity) {
        this.fearPopularity = fearPopularity;
    }

    public int getTaxPopularity() {
        return taxPopularity;
    }

    public void setTaxPopularity(int taxPopularity) {
        this.taxPopularity = taxPopularity;
    }

    public int getReligionPopularity() {
        return religionPopularity;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getEmpireId() {
        return empireId;
    }

    public Armoury getArmoury() {
        return armoury;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void addUnemployedPeople(int unemployedPeople) {
        this.unemployedPeople += unemployedPeople;
    }

    public void addEmployedPeople(int employedPeople) {
        this.employedPeople += employedPeople;
        this.unemployedPeople -= employedPeople;
    }

    public int getKingHp() {
        return kingHp;
    }

    public void setKingHp(int kingHp) {
        this.kingHp = kingHp;
    }

    public void addReligionPopularity(int religionPopularity) {
        this.religionPopularity += religionPopularity;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public Building getBuildingByName(String name) {
        for (Building building : buildings) {
            if (building.getBuildingType().getName().equals(name))
                return building;
        }
        return null;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Boolean haveThisBuilding(String name) {
        for (Building building : buildings) {
            if (building.getBuildingType().getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void calculateReductionInTheFoodStock() {
        //TODO complete depends on rate
        //TODO pay attention to if any number of food became 0 it changes food popularity in it should be changed
    }

    public void calculateTaxRate() {
        //TODO complete this part depends on tax rate
    }
}