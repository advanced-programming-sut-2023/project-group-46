package Model;

import Controller.GameMenuController;
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
    private int aleCoverage;
    private Resources resources;// it should be given to empires in start of the game
    private int[] keepCoordinates;
    private int maxPopulation;

    public Empire(User user, int empireId, int x, int y) {
        this.user = user;
        this.unemployedPeople = 10;
        this.employedPeople = 0;
        this.maxPopulation = 10;
        this.aleCoverage = 0;
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
        this.resources = new Resources(5000, 0, 0, 0, 0, 50, 0, 100, 0, -150);
        this.armoury = new Armoury(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.foodStock = new FoodStock(0, 0, 0, 60, -60);
        this.color = EmpireColors.getEmpireColorByNumber(empireId).getName();
    }

    public Empire() {
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void addMaxPopulation(int maxPopulation) {
        this.maxPopulation += maxPopulation;
    }

    public int getAleCoverage() {
        return aleCoverage;
    }

    public void addAleCoverage(int aleCoverage) {
        this.aleCoverage += aleCoverage;
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

    public void setUnemployedPeople(int unemployedPeople) {
        this.unemployedPeople = unemployedPeople;
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

    public void addUnemployedPeople(int unemployedPeople) {
        this.unemployedPeople += unemployedPeople;
    }

    public void addEmployedPeople(int employedPeople) {
        this.employedPeople += employedPeople;
        this.unemployedPeople -= employedPeople;
    }

    public void addReligionPopularity(int religionPopularity) {
        this.religionPopularity += religionPopularity;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
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
        int allPeople = unemployedPeople + employedPeople;
        double foodNeeded = ((foodRate * 0.5) + 1) * allPeople;
        while (foodNeeded > 0) {
            if (GameMenuController.getCurrentEmpire().getFoodStock().getBread() > 0.0) {
                GameMenuController.getCurrentEmpire().getFoodStock().addFood("bread", -1 * 0.5);
                foodNeeded -= 0.5;
                continue;
            }
            if (GameMenuController.getCurrentEmpire().getFoodStock().getMeat() > 0.0) {
                GameMenuController.getCurrentEmpire().getFoodStock().addFood("meat", -1 * 0.5);
                foodNeeded -= 0.5;
                continue;
            }
            if (GameMenuController.getCurrentEmpire().getFoodStock().getCheese() > 0.0) {
                GameMenuController.getCurrentEmpire().getFoodStock().addFood("cheese", -1 * 0.5);
                foodNeeded -= 0.5;
                continue;
            }
            if (GameMenuController.getCurrentEmpire().getFoodStock().getApple() > 0.0) {
                GameMenuController.getCurrentEmpire().getFoodStock().addFood("apple", -1 * 0.5);
                foodNeeded -= 0.5;
            }
        }
    }

    public void calculateTaxRate() {
        int allPeople = unemployedPeople + employedPeople;
        switch (taxRate) {
            case -3 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold(-1 * allPeople);
            }
            case -2 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (-1 * 0.8 * allPeople));
            }
            case -1 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (-1 * 0.6 * allPeople));
            }
            case 1 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (0.6 * allPeople));
            }
            case 2 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (0.8 * allPeople));
            }
            case 3 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold(allPeople);
            }
            case 4 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (allPeople * 1.2));
            }
            case 5 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (allPeople * 1.4));
            }
            case 6 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (allPeople * 1.6));
            }
            case 7 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold((int) (allPeople * 1.8));
            }
            case 8 -> {
                GameMenuController.getCurrentEmpire().getResources().addGold(allPeople * 2);
            }
        }
    }
}