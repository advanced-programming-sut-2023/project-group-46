package Client;

public enum ReactionType {
    LAUGH(":)"),
    SAD(":("),
    POKER(":|"),
    KISS(":*");
    private final String reaction;

    ReactionType(String reaction) {
        this.reaction = reaction;
    }
}
