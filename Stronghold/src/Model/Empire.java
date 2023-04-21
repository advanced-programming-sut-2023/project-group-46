package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Empire {

    private User user;
    private int empireId;//this is equal to index of the arraylist of empires in the Game
    private int unemployedPeople;
    private int employedPeople;
    private HashMap<String, Integer> foods;
    private int foodCount;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private int religion;
    private int foodPopularity;
    private int fearPopularity;
    private int taxPopularity;
    private int relogionPopularity;
    private Resources resources;// it should be given to empires in start of the game
    private Armoury armoury;
    private ArrayList<Trade> availableTrades;
    private ArrayList<Trade> historyTrades;
    private ArrayList<Building> buildings;
    private int kingHp;

    public Empire(User user, int unemployedPeople, int foodCount, int foodRate, int taxRate, int fearRate, int religion, int empireId) {//TODO double check
        this.user = user;
        this.unemployedPeople = unemployedPeople;
        this.employedPeople = 0;
        this.foodCount = foodCount;
        this.foodRate = foodRate;
        this.taxRate = taxRate;
        this.fearRate = fearRate;
        this.religion = religion;
        this.historyTrades = new ArrayList<>();
        this.availableTrades = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.empireId = empireId;
    }

    public int getReligion() {
        return religion;
    }

    public ArrayList<Trade> getAvailableTrades() {
        return availableTrades;
    }

    public ArrayList<Trade> getHistoryTrades() {
        return historyTrades;
    }

    public User getUser() {
        return user;
    }

    public void setRelegious(int religion) {
        this.religion = religion;
    }

    public int getRelegious() {
        return religion;
    }

    public int getUnemployedPeople() {
        return unemployedPeople;
    }

    public int getEmployedPeople() {
        return employedPeople;
    }

    public HashMap<String, Integer> getFoods() {
        return foods;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getFoodPopularity() {
        return foodPopularity;
    }

    public int getFearPopularity() {
        return fearPopularity;
    }

    public int getTaxPopularity() {
        return taxPopularity;
    }

    public int getReligionPopularity() {
        return relogionPopularity;
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

    public void setUnemployedPeople(int unemployedPeople) {
        this.unemployedPeople = unemployedPeople;
    }

    public void setEmployedPeople(int employedPeople) {
        this.employedPeople = employedPeople;
    }

    public void setFoods(HashMap<String, Integer> foods) {
        this.foods = foods;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
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

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public Building getBuildingByName(String username) {
        for (Building building : buildings) {
            if (building.getBuildingType().getName().equals(username))
                return building;
        }
        return null;
    }
}
