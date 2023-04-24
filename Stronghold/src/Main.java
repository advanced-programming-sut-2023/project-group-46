import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        switch (a) {
            case 1, 2, 3 -> {
                System.out.println("1");
                System.out.println("4");
            }
            case 4 -> System.out.println(5);
        }

    }
}