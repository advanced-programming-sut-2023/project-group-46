package Model.Goods;

public class
Armoury {
    private int horse;
    private int bow;
    private int crossbow;
    private int spear;
    private int pike;
    private int mace;
    private int sword;
    private int leatherArmor;
    private int metalArmor;
    private int freeCapacityArmoury;

    public Armoury(int bow, int crossbow, int spear, int pike, int mace, int sword, int leatherArmor, int metalArmor, int freeCapacityArmoury, int horse) {
        this.bow = bow;
        this.crossbow = crossbow;
        this.spear = spear;
        this.pike = pike;
        this.mace = mace;
        this.sword = sword;
        this.leatherArmor = leatherArmor;
        this.metalArmor = metalArmor;
        this.freeCapacityArmoury = freeCapacityArmoury;
        this.horse = horse;
    }

    public boolean isArmouryType(String name) {
        return name.equals("bow") || name.equals("crossbow") || name.equals("spear") || name.equals("pike") || name.equals("mace") || name.equals("sword") ||
                name.equals("leatherArmor") || name.equals("metalArmor");
    }

    public int getHorse() {
        return horse;
    }

    public int getBow() {
        return bow;
    }

    public int getCrossbow() {
        return crossbow;
    }

    public int getSpear() {
        return spear;
    }

    public int getPike() {
        return pike;
    }

    public int getMace() {
        return mace;
    }

    public int getSword() {
        return sword;
    }

    public int getLeatherArmor() {
        return leatherArmor;
    }

    public int getMetalArmor() {
        return metalArmor;
    }

    public void addBow(int bow) {
        this.bow += bow;
    }

    public void addCrossbow(int crossbow) {
        this.crossbow += crossbow;
    }

    public void addSpear(int spear) {
        this.spear += spear;
    }

    public void addPike(int pike) {
        this.pike += pike;
    }

    public void addMace(int mace) {
        this.mace += mace;
    }

    public void addHorse(int horse) {
        this.horse += horse;
    }

    public void addSword(int sword) {
        this.sword += sword;
    }

    public void addLeatherArmor(int leatherArmor) {
        this.leatherArmor += leatherArmor;
    }

    public void addMetalArmor(int metalArmor) {
        this.metalArmor += metalArmor;
    }

    public void addFreeCapacityArmoury(int amount) {
        this.freeCapacityArmoury += amount;
    }

    public int getFreeCapacityArmoury() {
        return freeCapacityArmoury;
    }

    public void addArmoury(String name, int amount) {
        switch (name) {
            case "bow" -> {
                this.addBow(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
            case "crossbow" -> {
                this.addCrossbow(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
            case "spear" -> {
                this.addSpear(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
            case "pike" -> {
                this.addPike(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
            case "mace" -> {
                this.addMace(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
            case "sword" -> {
                this.addSword(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
            case "leatherArmor" -> {
                this.addLeatherArmor(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
            case "metalArmor" -> {
                this.addMetalArmor(amount);
                this.addFreeCapacityArmoury(-1 * amount);
            }
        }
    }

    public int getArmouryAmount(String name) {
        return switch (name) {
            case "bow" -> this.getBow();
            case "crossbow" -> this.getCrossbow();
            case "spear" -> this.getSpear();
            case "pike" -> this.getPike();
            case "mace" -> this.getMace();
            case "sword" -> this.getSword();
            case "leatherArmor" -> this.getLeatherArmor();
            case "metalArmor" -> this.getMetalArmor();
            default -> -1;
        };
    }
}
