package Controller;

import java.util.regex.Matcher;


public class EmpireMenuController {
    // for effect of the variety of foods I calculate that in the model.Empire in the getFoodPopularity method

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

    public static void calculatePopulation() {
        int employedPeople = GameMenuController.getCurrentEmpire().getEmployedPeople();
        int maxPopulation = GameMenuController.getCurrentEmpire().getMaxPopulation();
        int unemployedPeople = GameMenuController.getCurrentEmpire().getUnemployedPeople();
        int popularity = GameMenuController.getCurrentEmpire().getFearPopularity();
        popularity += GameMenuController.getCurrentEmpire().getFoodPopularity();
        popularity += GameMenuController.getCurrentEmpire().getReligionPopularity();
        popularity += GameMenuController.getCurrentEmpire().getTaxPopularity();
        popularity += GameMenuController.getCurrentEmpire().getAleCoverage();
        popularity *= 3;// 3*popularity effects our unemployedPeople
        if (unemployedPeople + employedPeople + popularity >= maxPopulation) {
            GameMenuController.getCurrentEmpire().setUnemployedPeople(maxPopulation - employedPeople);
        } else if (unemployedPeople + popularity <= 0) {
            GameMenuController.getCurrentEmpire().setUnemployedPeople(0);
        } else {
            GameMenuController.getCurrentEmpire().addUnemployedPeople(popularity);
        }
    }

    public static void calculateFoodAndTax() {// if your empire doesn't have enough food or gold your food and your tax rate will be change and you should use change rate commands to set them in another rate
        int allPeople = GameMenuController.getCurrentEmpire().getUnemployedPeople() + GameMenuController.getCurrentEmpire().getEmployedPeople();
        double allFood = GameMenuController.getCurrentEmpire().getFoodStock().getApple() + GameMenuController.getCurrentEmpire().getFoodStock().getBread() + GameMenuController.getCurrentEmpire().getFoodStock().getMeat() + GameMenuController.getCurrentEmpire().getFoodStock().getCheese();
        int gold = GameMenuController.getCurrentEmpire().getResources().getGold();
        if (GameMenuController.getCurrentEmpire().getFoodRate() == -1) {
            if (allFood < allPeople * 0.5) {
                GameMenuController.getCurrentEmpire().setFoodRate(-2);
            }
        } else if (GameMenuController.getCurrentEmpire().getFoodRate() == 0) {
            if (allFood < allPeople * 1.0) {
                if (allFood < allPeople * 0.5) {
                    GameMenuController.getCurrentEmpire().setFoodRate(-2);
                } else {
                    GameMenuController.getCurrentEmpire().setFoodRate(-1);
                }
            }
        } else if (GameMenuController.getCurrentEmpire().getFoodRate() == 1) {
            if (allFood < allPeople * 1.5) {
                if (allFood > allPeople * 1.0) {
                    GameMenuController.getCurrentEmpire().setFoodRate(0);
                } else if (allFood > allPeople * 0.5) {
                    GameMenuController.getCurrentEmpire().setFoodRate(-1);
                } else {
                    GameMenuController.getCurrentEmpire().setFoodRate(-2);
                }
            }
        } else if (GameMenuController.getCurrentEmpire().getFoodRate() == 2) {
            if (allFood < allPeople * 2.0) {
                if (allFood > allPeople * 1.5) {
                    GameMenuController.getCurrentEmpire().setFoodRate(1);
                } else if (allFood > allPeople * 1.0) {
                    GameMenuController.getCurrentEmpire().setFoodRate(0);
                } else if (allFood > allPeople * 0.5) {
                    GameMenuController.getCurrentEmpire().setFoodRate(-1);
                } else {
                    GameMenuController.getCurrentEmpire().setFoodRate(-2);
                }
            }
        }
        GameMenuController.getCurrentEmpire().calculateReductionInTheFoodStock();
        if (GameMenuController.getCurrentEmpire().getTaxRate() == -1) {
            if (gold < allPeople * 0.6) {
                GameMenuController.getCurrentEmpire().setTaxRate(0);
            }
        } else if (GameMenuController.getCurrentEmpire().getTaxRate() == -2) {
            if (gold < allPeople * 0.8) {
                if (gold < allPeople * 0.6) {
                    GameMenuController.getCurrentEmpire().setTaxRate(0);
                } else {
                    GameMenuController.getCurrentEmpire().setTaxRate(-1);
                }
            }
        } else if (GameMenuController.getCurrentEmpire().getTaxRate() == -3) {
            if (gold < allPeople) {
                if (gold > allPeople * 0.8) {
                    GameMenuController.getCurrentEmpire().setTaxRate(-2);
                } else if (gold > allPeople * 0.6) {
                    GameMenuController.getCurrentEmpire().setTaxRate(-1);
                } else {
                    GameMenuController.getCurrentEmpire().setTaxRate(0);
                }
            }
        }
        GameMenuController.getCurrentEmpire().calculateTaxRate();
    }

    public static void checkEffectOfFearRate() {// fear rate cause buildings and units do better
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
        output += "aleCoverage : ";
        output += (GameMenuController.getCurrentEmpire().getAleCoverage()) + "\n";
        return output;
    }

    public int showPopularity() {
        int popularity = GameMenuController.getCurrentEmpire().getFearPopularity();
        popularity += GameMenuController.getCurrentEmpire().getFoodPopularity();
        popularity += GameMenuController.getCurrentEmpire().getReligionPopularity();
        popularity += GameMenuController.getCurrentEmpire().getTaxPopularity();
        popularity += GameMenuController.getCurrentEmpire().getAleCoverage();
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

    public String showArmoury() {
        return "bow->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("bow") + '\n' +
                "crossbow->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("crossbow") + '\n' +
                "spear->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("spear") + '\n' +
                "pike->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("pike") + '\n' +
                "mace->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("mace") + '\n' +
                "sword->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("sword") + '\n' +
                "leatherArmor->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("leatherArmor") + '\n' +
                "metalArmor->" + GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount("metalArmor") + '\n';
    }

    public String showStockpile() {
        return "gold->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("gold") + '\n' +
                "wheat->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("wheat") + '\n' +
                "flour->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("flour") + '\n' +
                "hop->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("hop") + '\n' +
                "ale->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("ale") + '\n' +
                "stone->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("stone") + '\n' +
                "iron->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("iron") + '\n' +
                "wood->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("wood") + '\n' +
                "pitch->" + GameMenuController.getCurrentEmpire().getResources().getResourceAmount("pitch") + '\n';

    }

    public String showColor() {
        return GameMenuController.getCurrentEmpire().getColor();
    }
}
