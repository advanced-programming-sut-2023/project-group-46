package View;

import java.util.Objects;

public class Captcha {

    public static String generateCaptcha() {
        String code = "";
        int length = (int) (Math.random() * 4) + 1; // generate a random length between 4 and 8
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = (int) (Math.random() * 10); // generate a random digit
            code += digit;
            char[][] digitArt = getDigitArt(digit); // get ascii art for the digit
            addNoise(digitArt); // add noise to the digit art
            for (char[] row : digitArt) {
                captcha.append(row).append("\n"); // append the digit art to the captcha with a newline
            }
        }
        return captcha.toString() + "@" + code;
    }

    private static char[][] getDigitArt(int digit) {
        char[][] art = new char[5][];
        switch (digit) {
            case 0:
                art[0] = " ___ ".toCharArray();
                art[1] = "/ _ \\".toCharArray();
                art[2] = "| | | |".toCharArray();
                art[3] = "| |_| |".toCharArray();
                art[4] = " \\___/".toCharArray();
                break;

            case 1:
                art[0] = " _".toCharArray();
                art[1] = "/ |".toCharArray();
                art[2] = "| |".toCharArray();
                art[3] = "| |".toCharArray();
                art[4] = "|_|".toCharArray();
                break;

            case 2:
                art[0] = " ____ ".toCharArray();
                art[1] = "|___ \\".toCharArray();
                art[2] = " __) |".toCharArray();
                art[3] = " / __/".toCharArray();
                art[4] = "|_____|\n".toCharArray();
                break;

            case 3:
                art[0] = " _____".toCharArray();
                art[1] = "|___ /".toCharArray();
                art[2] = " |_ \\".toCharArray();
                art[3] = " ___) |".toCharArray();
                art[4] = "|____/".toCharArray();
                break;

            case 4:
                art[0] = " _  _  ".toCharArray();
                art[1] = "| || | ".toCharArray();
                art[2] = "| || |_".toCharArray();
                art[3] = "|___ _|".toCharArray();
                art[4] = " |_| ".toCharArray();
                break;

            case 5:
                art[0] = " ____ ".toCharArray();
                art[1] = "| ___|".toCharArray();
                art[2] = "|___ \\".toCharArray();
                art[3] = " ___) |".toCharArray();
                art[4] = "|____/".toCharArray();
                break;

            case 6:
                art[0] = " __  ".toCharArray();
                art[1] = " / /_ ".toCharArray();
                art[2] = "| '_ \\".toCharArray();
                art[3] = "| (_) |".toCharArray();
                art[4] = " \\___/".toCharArray();
                break;

            case 7:
                art[0] = " _____".toCharArray();
                art[1] = "|___ /".toCharArray();
                art[2] = " / /".toCharArray();
                art[3] = " / / ".toCharArray();
                art[4] = "/_/  ".toCharArray();
                break;

            case 8:
                art[0] = " ___ ".toCharArray();
                art[1] = "( _ )".toCharArray();
                art[2] = " / _ \\".toCharArray();
                art[3] = "| (_) |".toCharArray();
                art[4] = " \\___/".toCharArray();
                break;

            case 9:
                art[0] = " ___ ".toCharArray();
                art[1] = " / _ \\".toCharArray();
                art[2] = "| (_) |".toCharArray();
                art[3] = "\\__, |".toCharArray();
                art[4] = " /_/ ".toCharArray();
                break;

            default:
                for (int i = 0; i < 5; i++) {
                    art[i] = new char[0];
                }
        }
        return art;
    }
    private static void addNoise(char[][] art) {
        for (int i = 0; i < art.length; i++) {
            for (int j = 0; j < art[i].length; j++) {
                if (art[i][j] != ' ') {
                    if (Math.random() < 0.1) {
                        art[i][j] = '.';
                    }
                }
            }
        }
    }

    public static boolean verifyCaptcha(boolean isFromRegisterMenu) {
        String captcha = Captcha.generateCaptcha();
        String partOfCaptchaToShowUser = "";
        String answerOfCaptcha = "";
        int i;
        for(i = 0 ; i < captcha.length() ; i++)
        {
            if(captcha.charAt(i) != '@')
                partOfCaptchaToShowUser += captcha.charAt(i);
            else
            {
                i++;
                break;
            }
        }

        for( ; i < captcha.length() ; i++)
        {
            answerOfCaptcha += captcha.charAt(i);
        }

        if(isFromRegisterMenu)
            System.out.println("Please enter the following captcha to complete registration. type \"  new  \" for a new captcha :\n" + partOfCaptchaToShowUser);
        else
            System.out.println("Please enter the following captcha to enter your account. type \"  new  \" for a new captcha :\n" + partOfCaptchaToShowUser);

        String input = Menu.getScanner().nextLine();

        if(input.matches("^new$"))
            return false;

        return Objects.equals(input, answerOfCaptcha);
    }

}
