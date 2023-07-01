package Client;

import java.time.LocalTime;
import java.util.ArrayList;

public class Message {
    public boolean isDeleted;
    public String senderUsername;
    public String payam;
    public ArrayList<Reaction> reaction;
    public String time;
    public ArrayList<String> seenUsernames;

    public Message(String sender, String payam) {
        this.reaction = new ArrayList<>();
        this.seenUsernames = new ArrayList<>();
        this.senderUsername = sender;
        this.payam = payam;
        String min = String.format("%02d", LocalTime.now().getMinute());
        String hor = String.format("%02d", LocalTime.now().getHour());
        this.time = hor + ":" + min;
        this.isDeleted = false;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public ArrayList<String> getSeenUsernames() {
        return seenUsernames;
    }

    public void setSeenUsernames(ArrayList<String> seenUsernames) {
        this.seenUsernames = seenUsernames;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getPayam() {
        return payam;
    }

    public void setPayam(String payam) {
        this.payam = payam;
    }

    public ArrayList<Reaction> getReaction() {
        return reaction;
    }

    public void setReaction(ArrayList<Reaction> reaction) {
        this.reaction = reaction;
    }
}
