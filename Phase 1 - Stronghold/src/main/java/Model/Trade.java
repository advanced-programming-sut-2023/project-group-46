package Model;

public class Trade {
    private String resourceType;
    private int resourceAmount;
    private int price;
    private String message;
    private Empire senderEmpire;
    private Empire getterEmpire;

    public Trade(String resourceType, int resourceAmount, int price, String message, Empire senderEmpire, Empire getterEmpire) {
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.message = message;
        this.senderEmpire = senderEmpire;
        this.getterEmpire = getterEmpire;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public Empire getGiverEmpire() {
        return senderEmpire;
    }

    public Empire getGetterEmpire() {
        return getterEmpire;
    }

    public void setGetterEmpire(Empire getterEmpire) {
        this.getterEmpire = getterEmpire;
    }

    public Empire getSenderEmpire() {
        return senderEmpire;
    }
}
