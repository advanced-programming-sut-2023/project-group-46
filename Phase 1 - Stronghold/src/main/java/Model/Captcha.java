package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

public class Captcha {
    private static ArrayList<String> captcha= new ArrayList<>();
    private static int num;

    public static ArrayList<String> getCaptcha() {
        captcha.add("/Image/Captcha/1181.png");
        captcha.add("/Image/Captcha/1381.png");
        captcha.add("/Image/Captcha/1491.png");
        captcha.add("/Image/Captcha/1722.png");
        captcha.add("/Image/Captcha/1959.png");
        captcha.add("/Image/Captcha/2163.png");
        captcha.add("/Image/Captcha/2177.png");
        captcha.add("/Image/Captcha/2723.png");
        captcha.add("/Image/Captcha/2785.png");
        return captcha;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Captcha.num = num;
    }
}
