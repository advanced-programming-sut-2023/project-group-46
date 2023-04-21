package Model;

public enum PreBuiltSlogans {

    FIRST_SLOGAN("I shall have my revenge, in this life or the next!" , 1);

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
}
