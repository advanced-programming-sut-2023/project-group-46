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
        do {
            System.out.println("name the players that you want to play with (pattern : FirstUsername/SecondUsername/ThirdUsername...)");
            command = Menu.getScanner().nextLine();
            if (command.equals("back")) {
                return;
            }
            result = gameMenuController.startANewGame(command);
            System.out.println(result);
        } while (!result.contains("success"));
        while (true) {
            System.out.println("please enter the numbers of the turns that you want to play");
            command = Menu.getScanner().nextLine();
            if ((matcher = Menu.getMatcher(command, "(?<num>\\d+)")) != null) {
                result = gameMenuController.checkNumberOfTheTurns(Integer.parseInt(matcher.group("num")));
                System.out.println(result);
                if (result.contains("Game Started")) {
                    break;
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.equalsIgnoreCase("EmpireMenu")) {
                EmpireMenu empireMenu = new EmpireMenu(new EmpireMenuController());
                System.out.println("Entered EmpireMenu");
                empireMenu.run();
            } else if (command.equalsIgnoreCase("MapMenu")) {
                MapMenu mapMenu = new MapMenu(new MapMenuController());
                System.out.println("Entered MapMenu");
                mapMenu.run();
            } else if (command.equalsIgnoreCase("TradeMenu")) {
                TradeMenu tradeMenu = new TradeMenu(new TradeMenuController());
                System.out.println("Entered TradeMenu");
                tradeMenu.run();
            } else if (command.equalsIgnoreCase("ShopMenu")) {
                ShopMenu shopMenu = new ShopMenu(new ShopMenuController());
                System.out.println("Entered ShopMenu");
                shopMenu.run();
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_UNIT_REGEX)) != null) {
                System.out.println(gameMenuController.selectUnit(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK_ARCHER_REGEX)) != null) {
                System.out.println(gameMenuController.attackLocation(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.BUILD_REGEX)) != null) {
                System.out.println(gameMenuController.buildEquipment(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK_SPECIAL_ENEMY_REGEX)) != null) {
                System.out.println(gameMenuController.attackEnemy(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_TUNNEL_REGEX)) != null) {
                System.out.println(gameMenuController.digTunnel(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DROP_BUILDING_REGEX)) != null) {
                System.out.println(gameMenuController.dropBuilding(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.PATROL_UNIT_REGEX)) != null) {
                //gameMenuController.patrolUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_UNIT_REGEX)) != null) {
                //gameMenuController.moveUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_BUILDING_REGEX)) != null) {
                System.out.println(gameMenuController.selectBuilding(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.POUR_OIL_REGEX)) != null) {
                System.out.println(gameMenuController.pourOil(matcher.group("direction")));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SET_UNIT_MODE_REGEX)) != null) {
                System.out.println(gameMenuController.setUnitMode(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CREATE_UNIT_REGEX)) != null) {
                System.out.println(gameMenuController.createUnit(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SET_OIL_FOR_ENGINEER)) != null) {
                System.out.println(gameMenuController.setOilForEngineers(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CHANGE_MODE_ARMOUR_BUILDING_REGEX)) != null) {
                System.out.println(gameMenuController.changeBuildingMode(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DROP_UNIT_REGEX)) != null) {
                System.out.println(gameMenuController.dropUnit(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK_MACHINES_REGEX)) != null) {
                System.out.println(gameMenuController.attackMachines(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_MOAT)) != null) {
                System.out.println(gameMenuController.digMoat(matcher));
            } else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.NO_MOAT)) != null) {
                System.out.println(gameMenuController.noMoat(matcher));
            } else if (command.matches("repair")) {
                System.out.println(gameMenuController.repair());
            } else if (command.matches("disband unit")) {
                gameMenuController.disbandUnit();
            } else if (command.matches("where is my keep")) {
                System.out.println(gameMenuController.showKeepCoordinates());
            } else if (command.matches("show current menu")) {
                System.out.println("GameMenu");
            } else if (command.matches("next turn")) {
                result = gameMenuController.nextTurn();
                System.out.println(result);
                if (result.contains("end of the game")) {
                    return;
                }
            } else if (command.equals("back")) {
                System.out.println("Are you sure you want to exit the game?");
                result = Menu.getScanner().nextLine();
                if (result.equalsIgnoreCase("yes")) {
                    return;
                } else {
                    System.out.println("Invalid command!");
                }
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}