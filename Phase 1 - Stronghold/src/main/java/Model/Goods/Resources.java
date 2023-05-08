package Model.Goods;

public class Resources {
    private int gold;
    private int wheat;
    private int flour;
    private int hop;
    private int ale;
    private int stone;
    private int iron;
    private int wood;
    private int pitch;
    private int freeCapacityStockpile;

    public Resources(int gold, int wheat, int flour, int hop, int ale, int stone, int iron, int wood, int pitch, int freeCapacityStockpile) {
        this.gold = gold;
        this.wheat = wheat;
        this.flour = flour;
        this.hop = hop;
        this.ale = ale;
        this.stone = stone;
        this.iron = iron;
        this.wood = wood;
        this.pitch = pitch;
        this.freeCapacityStockpile = freeCapacityStockpile;
    }

    public Resources() {
    }

    public int getGold() {
        return gold;
    }

    public int getWheat() {
        return wheat;
    }

    public int getFlour() {
        return flour;
    }

    public int getHop() {
        return hop;
    }

    public int getAle() {
        return ale;
    }

    public int getStone() {
        return stone;
    }

    public int getIron() {
        return iron;
    }

    public int getWood() {
        return wood;
    }

    public int getPitch() {
        return pitch;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addWheat(int wheat) {
        this.wheat += wheat;
    }

    public void addFlour(int flour) {
        this.flour += flour;
    }

    public void addHop(int hops) {
        this.hop += hops;
    }

    public void addAle(int ale) {
        this.ale += ale;
    }

    public void addStone(int stone) {
        this.stone += stone;
    }

    public void addIron(int iron) {
        this.iron += iron;
    }

    public void addWood(int wood) {
        this.wood += wood;
    }

    public void addPitch(int pitch) {
        this.pitch += pitch;
    }

    public void addFreeCapacityStockpile(int amount) {
        this.freeCapacityStockpile += amount;
    }

    public int getFreeCapacityStockpile() {
        return freeCapacityStockpile;
    }

    public void addResource(String name, int amount) {
        switch (name) {
            case "gold" -> this.addGold(amount);
            case "wheat" -> {
                this.addWheat(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
            case "flour" -> {
                this.addFlour(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
            case "hop" -> {
                this.addHop(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
            case "ale" -> {
                this.addAle(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
            case "stone" -> {
                this.addStone(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
            case "iron" -> {
                this.addIron(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
            case "wood" -> {
                this.addWood(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
            case "pitch" -> {
                this.addPitch(amount);
                this.addFreeCapacityStockpile(-1 * amount);
            }
        }
    }

    public int getResourceAmount(String name) {
        return switch (name) {
            case "gold" -> this.getGold();
            case "wheat" -> this.getWheat();
            case "flour" -> this.getFlour();
            case "hop" -> this.getHop();
            case "ale" -> this.getAle();
            case "stone" -> this.getStone();
            case "iron" -> this.getIron();
            case "wood" -> this.getWood();
            case "pitch" -> this.getPitch();
            default -> -1;
        };
    }

    public boolean isResourceType(String name) {
        return name.equals("wheat") || name.equals("flour") || name.equals("hop") || name.equals("ale") || name.equals("stone") || name.equals("iron")
                || name.equals("wood") || name.equals("pitch");
    }
}