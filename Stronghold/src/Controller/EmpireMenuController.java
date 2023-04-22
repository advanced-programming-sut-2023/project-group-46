package Controller;

import View.EmpireMenu;

import java.util.Map;
import java.util.regex.Matcher;


public class EmpireMenuController {

    public String showPopularityFactors() {
        String output = "";
        output += "Food : ";
        output += (GameMenuController.getCurrentEmpire().getFoodPopularity()) + '\n';
        output += "Tax : ";
        output += (GameMenuController.getCurrentEmpire().getTaxPopularity()) + '\n';
        output += "Fear : ";
        output += (GameMenuController.getCurrentEmpire().getFearPopularity()) + '\n';
        output += "Religion : ";
        output += (GameMenuController.getCurrentEmpire().getReligionPopularity()) + '\n';
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
}
