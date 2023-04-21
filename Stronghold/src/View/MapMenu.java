package View;

import Controller.MapMenuController;
import Controller.ShopMenuController;
import Model.EditMapMenuCommands;
import Model.MapMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final MapMenuController mapMenuController;

    public MapMenu(MapMenuController mapMenuController) {
        this.mapMenuController = mapMenuController;
    }

    public void run() {
        Matcher matcher;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("back")) {
            if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.REGEX1)) != null) {
                System.out.println(mapMenuController.showMap(matcher));
            } else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.REGEX2)) != null) {
                System.out.println(mapMenuController.moveInMap(matcher));
            } else if ((matcher = MapMenuCommands.getMatcher(command, MapMenuCommands.REGEX3)) != null) {
                System.out.println(mapMenuController.showDetail(matcher));
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
    }
}
