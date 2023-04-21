package Model;

public class FoodStock {
    private double meat;
    private double apple;
    private double cheese;
    private double bread;

    public double getMeat() {
        return meat;
    }

    public double getApple() {
        return apple;
    }

    public double getCheese() {
        return cheese;
    }

    public double getBread() {
        return bread;
    }

    public void addMeat(double meat) {
        this.meat += meat;
    }

    public void addApple(double apple) {
        this.apple += apple;
    }

    public void addCheese(double cheese) {
        this.cheese += cheese;
    }

    public void addBread(double bread) {
        this.bread += bread;
    }

    public void addFood(String name, double amount) {
        switch (name) {
            case "meat" -> this.addMeat(amount);
            case "apple" -> this.addApple(amount);
            case "cheese" -> this.addCheese(amount);
            case "bread" -> this.addBread(amount);
        }
    }

    public double getFoodAmount(String name) {
        return switch (name) {
            case "meat" -> this.getMeat();
            case "apple" -> this.getApple();
            case "cheese" -> this.getCheese();
            case "bread" -> this.getBread();
            default -> -1;
        };
    }

    public boolean isFoodType(String name) {
        return name.equals("meat") || name.equals("apple") || name.equals("cheese") || name.equals("bread");
    }
}
