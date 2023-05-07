package Model;

public class Trade {
    private String wantedResource;
    private int wantedResourceAmount;
    private String givenResource;
    private int givenResourceAmount;
    private String message;
    private Empire senderEmpire;
    private Empire getterEmpire;

    public Trade(String wantedResource, int wantedResourceAmount, String givenResource, int givenResourceAmount, String message, Empire senderEmpire) {
        this.wantedResource = wantedResource;
        this.wantedResourceAmount = wantedResourceAmount;
        this.givenResource = givenResource;
        this.givenResourceAmount = givenResourceAmount;
        this.message = message;
        this.senderEmpire = senderEmpire;
    }

    public Trade() {
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