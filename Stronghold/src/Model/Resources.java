package Model;

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

    public void addResource(String name, int amount) {
        switch (name) {
            case "gold" -> this.addGold(amount);
            case "wheat" -> this.addWheat(amount);
            case "flour" -> this.addFlour(amount);
            case "hop" -> this.addHop(amount);
            case "ale" -> this.addAle(amount);
            case "stone" -> this.addStone(amount);
            case "iron" -> this.addIron(amount);
            case "wood" -> this.addWood(amount);
            case "pitch" -> this.addPitch(amount);
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
