package Controller;

import Model.*;
import View.GameMenu;

import java.util.regex.Matcher;

public class GameMenuController {
    private static Game game;
    private static Empire currentEmpire;
    private Building selectedBuilding;
    private Unit selectedUnit;
    private Machine selectedMachine;
   // private final GameMenu gameMenu;

   // public GameMenuController() {
      //  gameMenu = new GameMenu(this);
    ///}

    public void run() {

    }

    private void startANewGame(Matcher matcher) {
        game = new Game(Map.getMap1());
        game.getEmpires().add(new Empire(User.getUserByUsername("a"), 0, 0, 0, 0, 0, 0, 0));
    }

    private void dropBuilding(Matcher matcher) {
     /*   Map.getMap().get(0).setBuilding(new Building(BuildingType.getBuildingByName(type),CurrentEmpire));
        Map.getMap().get(0).getBuilding().getBuildingType().getGold();
        game.getMapGame().getMap()[0][0].getBuilding().getBuildingType().getCapacity(*/
    }

    private Building selectBuilding(Matcher matcher) {
        return null;
    }

    private void createUnit(Matcher matcher) {
    }

    private void repair() {
    }

    private Unit selectUnit(Matcher matcher) {
        return null;
    }

    private void moveUnit(Matcher matcher) {
    }

    private void patrolUnit(Matcher matcher) {
    }

    private void setUnitMode(Matcher matcher) {
    }

    private void attackEnemy(Matcher matcher) {
    }

    private void attackLocation(Matcher matcher) {
    }

    private void pourOil(Matcher matcher) {
    }// به این صورت که در arraylist سربازا ها فور زده بشه و همه مهندسا برن نفتشونو بریزن

    private void burnOil(Matcher matcher)
    {

    }

    private void digTunnel(Matcher matcher) {
    }

    private void buildEquipment(Matcher matcher) {

    }

    private void disbandUnit() {
    }

    private String dropUnit(Matcher matcher) {
        return null;
    }

    private String nextTurn() {
        return null;
    }

    private void checkFightUnits() {
    }

    private void checkFightMachines() {
    }

    private void checkProductiveBuildings() {
    }

    private void removeDestroyedThings() {
    }

    private void findDirectionForMovements() {
    }

    private void checkEndOfTheGame() {
    }

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public static Game getGame() {
        return game;
    }
}