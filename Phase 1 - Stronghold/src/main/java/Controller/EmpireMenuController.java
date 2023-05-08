package Controller;

import java.util.regex.Matcher;


public class EmpireMenuController {
    // for effect of the variety of foods I calculate that in the model.Empire in the getFoodPopularity method

    public String showPopularityFactors() {
        String output = "";
        output += "Food : ";
        output += (GameMenuController.getCurrentEmpire().getFoodPopularity()) + "\n";
        output += "Tax : ";
        output += (GameMenuController.getCurrentEmpire().getTaxPopularity()) + "\n";
        output += "Fear : ";
        output += (GameMenuController.getCurrentEmpire().getFearPopularity()) + "\n";
        output += "Religion : ";
        output += (GameMenuController.getCurrentEmpire().getReligionPopularity()) + "\n";
        return output;
    }

    public int showPopularity() {
        int popularity = GameMenuController.getCurrentEmpire().getFearPopularity();
        popularity += GameMenuController.getCurrentEmpire().getFoodPopularity();
        popularity += GameMenuController.getCurrentEmpire().getReligionPopularity();
        popularity += GameMenuController.getCurrentEmpire().getTaxPopularity();
        return popularity;
    }

    public String showFoodList() {
        StringBuilder output = new StringBuilder();
        int bread = (int) GameMenuController.getCurrentEmpire().getFoodStock().getBread();
        int meat = (int) GameMenuController.getCurrentEmpire().getFoodStock().getMeat();
        int apple = (int) GameMenuController.getCurrentEmpire().getFoodStock().getApple();
        int cheese = (int) GameMenuController.getCurrentEmpire().getFoodStock().getCheese();
        output.append("bread->").append(bread).append('\n');
        output.append("meat->").append(meat).append('\n');
        output.append("apple->").append(apple).append('\n');
        output.append("cheese->").append(cheese).append('\n');
        return output.toString();
    }

    public void changeFoodRate(Matcher matcher) {
        GameMenuController.getCurrentEmpire().setFoodRate(Integer.parseInt(matcher.group("rate")));
    }

    public int foodRateShow() {
        return GameMenuController.getCurrentEmpire().getFoodRate();
    }

    public void changeTaxRate(Matcher matcher) {
        GameMenuController.getCurrentEmpire().setTaxRate(Integer.parseInt(matcher.group("rate")));
    }

    public int taxRateShow() {
        return GameMenuController.getCurrentEmpire().getTaxRate();
    }

    public void changeFearRate(Matcher matcher) {
        GameMenuController.getCurrentEmpire().setFearRate(Integer.parseInt(matcher.group("rate")));
    }

    public int fearRateShow() {
        return GameMenuController.getCurrentEmpire().getFearRate();
    }

    public static void calculatePopularityFactors() {
        switch (GameMenuController.getCurrentEmpire().getFoodRate()) {
            case -2 -> GameMenuController.getCurrentEmpire().setFoodPopularity(-8);
            case -1 -> GameMenuController.getCurrentEmpire().setFoodPopularity(-4);
            case 0 -> GameMenuController.getCurrentEmpire().setFoodPopularity(0);
            case 1 -> GameMenuController.getCurrentEmpire().setFoodPopularity(4);
            case 2 -> GameMenuController.getCurrentEmpire().setFoodPopularity(8);
        }
        switch (GameMenuController.getCurrentEmpire().getTaxRate()) {
            case -3 -> GameMenuController.getCurrentEmpire().setTaxPopularity(7);
            case -2 -> GameMenuController.getCurrentEmpire().setTaxPopularity(5);
            case -1 -> GameMenuController.getCurrentEmpire().setTaxPopularity(3);
            case 0 -> GameMenuController.getCurrentEmpire().setTaxPopularity(1);
            case 1 -> GameMenuController.getCurrentEmpire().setTaxPopularity(-2);
            case 2 -> GameMenuController.getCurrentEmpire().setTaxPopularity(-4);
            case 3 -> GameMenuController.getCurrentEmpire().setFearPopularity(-6);
            case 4 -> GameMenuController.getCurrentEmpire().setTaxPopularity(-8);
            case 5 -> GameMenuController.getCurrentEmpire().setTaxPopularity(-12);
            case 6 -> GameMenuController.getCurrentEmpire().setTaxPopularity(-16);
            case 7 -> GameMenuController.getCurrentEmpire().setTaxPopularity(-20);
            case 8 -> GameMenuController.getCurrentEmpire().setTaxPopularity(-24);
        }
        GameMenuController.getCurrentEmpire().setFearPopularity(GameMenuController.getCurrentEmpire().getFearRate());
    }

    public void checkEffectOfFearRate() {// fear rate cause buildings and units do better
        int fearRate = GameMenuController.getCurrentEmpire().getFearRate();
        for (int i = 0; i < GameMenuController.getCurrentEmpire().getUnits().size(); i++) {
            int defaultAttackPower = GameMenuController.getCurrentEmpire().getUnits().get(i).getUnitType().getAttackPower();
            GameMenuController.getCurrentEmpire().getUnits().get(i).setAttackPower((int) (defaultAttackPower * (1 + (fearRate * 0.1))));
        }
        for (int i = 0; i < GameMenuController.getCurrentEmpire().getBuildings().size(); i++) {
            int defaultWorkingRate = GameMenuController.getCurrentEmpire().getBuildings().get(i).getBuildingType().getRate();
            GameMenuController.getCurrentEmpire().getBuildings().get(i).setRate(defaultWorkingRate + (int) (-0.5 * fearRate));
        }
    }
    //TODO check if the empire doesn't have enough food set the rate -2 (look at the getFoodPopularity  method)
    //TODO check for money(tax) and food that increases and decreases in resources (dependency <-> foodRate & taxRate)
}
