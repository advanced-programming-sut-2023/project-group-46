package org.example;

public class Reaction {
    private ReactionType reaction;
    private String username;

    public Reaction(ReactionType reaction, String username) {
        this.reaction = reaction;
        this.username = username;
    }

    public ReactionType getReaction() {
        return reaction;
    }

    public void setReaction(ReactionType reaction) {
        this.reaction = reaction;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
