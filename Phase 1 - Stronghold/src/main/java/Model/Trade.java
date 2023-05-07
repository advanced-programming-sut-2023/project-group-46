package Model;

public class Trade {
    private final String wantedResource;
    private final int wantedResourceAmount;
    private final String givenResource;
    private final int givenResourceAmount;
    private final String message;
    private final Empire senderEmpire;
    private Empire getterEmpire;

    public Trade(String wantedResource, int wantedResourceAmount, String givenResource, int givenResourceAmount, String message, Empire senderEmpire) {
        this.wantedResource = wantedResource;
        this.wantedResourceAmount = wantedResourceAmount;
        this.givenResource = givenResource;
        this.givenResourceAmount = givenResourceAmount;
        this.message = message;
        this.senderEmpire = senderEmpire;
    }

    public String getWantedResource() {
        return wantedResource;
    }

    public int getWantedResourceAmount() {
        return wantedResourceAmount;
    }

    public String getGivenResource() {
        return givenResource;
    }

    public int getGivenResourceAmount() {
        return givenResourceAmount;
    }

    public String getMessage() {
        return message;
    }

    public Empire getSenderEmpire() {
        return senderEmpire;
    }

    public Empire getGetterEmpire() {
        return getterEmpire;
    }

    public void setGetterEmpire(Empire getterEmpire) {
        this.getterEmpire = getterEmpire;
    }
}