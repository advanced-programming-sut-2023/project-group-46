package Model;

import Model.Goods.Armoury;
import Model.Goods.FoodStock;
import Model.Goods.Resources;

import java.util.ArrayList;

public class Empire {

    private User user;
    private int empireId;//this is equal to index of the arraylist of empires in the Game
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
    private Armoury armoury;
    private FoodStock foodStock;
    private final ArrayList<Building> buildings;
    private final ArrayList<Unit> units;
    private int kingHp;

    public Empire(User user, int unemployedPeople, int foodRate, int taxRate, int fearRate, int religion, int empireId) {//TODO double check what should we give empires in the start of the game
        this.user = user;
        this.unemployedPeople = unemployedPeople;
        this.employedPeople = 0;
        this.foodRate = foodRate;
        this.taxRate = taxRate;
        this.fearRate = fearRate;
        this.buildings = new ArrayList<>();
        this.units = new ArrayList<>();
        this.empireId = empireId;
        this.religionPopularity = 0;
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

    public int getFearPopularity() {
        return fearPopularity;
    }

    public int getTaxPopularity() {
        return taxPopularity;
    }

    public int getReligionPopularity() {
        return religionPopularity;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public int getFearRate() {
        return fearRate;
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

    public void addUnemployedPeople(int unemployedPeople) {
        this.unemployedPeople += unemployedPeople;
    }

    public void addEmployedPeople(int employedPeople) {
        this.employedPeople += employedPeople;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void setKingHp(int kingHp) {
        this.kingHp = kingHp;
    }

    public int getKingHp() {
        return kingHp;
    }

    public void setFoodPopularity(int foodPopularity) {
        this.foodPopularity = foodPopularity;
    }

    public void setFearPopularity(int fearPopularity) {
        this.fearPopularity = fearPopularity;
    }

    public void setTaxPopularity(int taxPopularity) {
        this.taxPopularity = taxPopularity;
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
}