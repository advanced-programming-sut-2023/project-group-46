package Enums;

public enum PreBuiltSlogans {

    FIRST_SLOGAN("I shall have my revenge, in this life or the next!" , 1),
    SECOND_SLOGAN("Be where your enemy is not." , 2),
    THIRD_SLOGAN("Who does not know the evils of war cannot appreciate its benefits." , 3),
    FOURTH_SLOGAN("He will win who knows when to fight and when not to fight." , 4),
    FIFTH_SLOGAN("Victorious warriors win first and then go to war, while defeated warriors go to war first and then seek to win." , 5);

    private String sloganText;
    private int sloganNumber;

    PreBuiltSlogans(String sloganText, int sloganNumber) {
        this.sloganText = sloganText;
        this.sloganNumber = sloganNumber;
    }

    public String getSloganText() {
        return sloganText;
    }

    public int getSloganNumber() {
        return sloganNumber;
    }

    public static String getSloganByNumber(int number)
    {
        for(PreBuiltSlogans preBuiltSlogan : PreBuiltSlogans.values())
        {
            if(preBuiltSlogan.getSloganNumber() == number)
                return preBuiltSlogan.sloganText;
        }
        return null;
    }

    public static String displayAllSlogans() {
        String s = "";

        for (PreBuiltSlogans preBuiltSlogan : PreBuiltSlogans.values()) {
            s += ("Slogan " + preBuiltSlogan.getSloganNumber() + ": " + preBuiltSlogan.getSloganText() + "\n");
        }
        return s;
    }

}
