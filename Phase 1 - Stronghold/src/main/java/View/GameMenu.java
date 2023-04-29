package View;

import Controller.*;
import Enums.Commands.GameMenuCommands;

import java.util.regex.Matcher;

public class GameMenu {
    private final GameMenuController gameMenuController;

    public GameMenu(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
    }

    public void run() throws Exception {
        Matcher matcher;
        String command, result;
        while (true) {
            System.out.println("name the players that you want to play with (pattern : FirstUsername/SecondUsername/ThirdUsername...)");
            command = Menu.getScanner().nextLine();
            if (command.equals("back")) {
                return;
            }
            result = gameMenuController.startANewGame(command);
            System.out.println(result);
            if (result.contains("Game Started")) {
                break;
            }
        }
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("EmpireMenu")) {
                EmpireMenu empireMenu = new EmpireMenu(new EmpireMenuController());
                empireMenu.run();
            } else if (command.matches("MapMenu")) {
                MapMenu mapMenu = new MapMenu(new MapMenuController());
                mapMenu.run();
            } else if (command.matches("TradeMenu")) {
                TradeMenu tradeMenu = new TradeMenu(new TradeMenuController());
                tradeMenu.run();
            } else if (command.matches("ShopMenu")) {
                ShopMenu shopMenu = new ShopMenu(new ShopMenuController());
                shopMenu.run();
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_UNIT_REGEX)) != null) {
                gameMenuController.selectUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK_ARCHER_REGEX)) != null) {
                gameMenuController.attackLocation(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.BUILD_REGEX)) != null) {
                System.out.println(gameMenuController.buildEquipment(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK_SPECIAL_ENEMY_REGEX)) != null) {
                gameMenuController.attackEnemy(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_TUNNEL_REGEX)) != null) {
                gameMenuController.digTunnel(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DROP_BUILDING_REGEX)) != null) {
                System.out.println(gameMenuController.dropBuilding(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.PATROL_UNIT_REGEX)) != null) {
                gameMenuController.patrolUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_UNIT_REGEX)) != null) {
                gameMenuController.moveUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_BUILDING_REGEX)) != null) {
                System.out.println(gameMenuController.selectBuilding(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.POUR_OIL_REGEX)) != null) {
                gameMenuController.pourOil(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SET_UNIT_MODE_REGEX)) != null) {
                gameMenuController.setUnitMode(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CREATE_UNIT_REGEX)) != null) {
                System.out.println(gameMenuController.createUnit(matcher));
            } else if (command.matches("repair")) {
                System.out.println(gameMenuController.repair());
            } else if (command.matches("disband unit")) {
                gameMenuController.disbandUnit();
            } else if (command.matches("next turn")) {
                gameMenuController.nextTurn();
            } else {
                System.out.println("Invalid command!");
            }//TODO find a way for invalid numbers like this 12a2
            //TODO add command for changing the mode of the armour producers

        }
    }
}