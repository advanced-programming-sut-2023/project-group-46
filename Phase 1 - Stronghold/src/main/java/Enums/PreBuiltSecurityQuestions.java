package Enums;

public enum PreBuiltSecurityQuestions {

    FIRST_QUESTION("What is my father’s name?" , 1),
    SECOND_QUESTION("What is my favorite color?" , 2),
    THIRD_QUESTION("What is my mother’s last name?" , 3);
    private String question;
    private int questionNumber;

    PreBuiltSecurityQuestions(String question, int questionNumber) {
        this.question = question;
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public static String getSecurityQuestionByNumber(int number)
    {
        for(PreBuiltSecurityQuestions preBuiltSecurityQuestion : PreBuiltSecurityQuestions.values())
        {
            if(preBuiltSecurityQuestion.getQuestionNumber() == number)
                return preBuiltSecurityQuestion.getQuestion();
        }
        return null;
    }

}
