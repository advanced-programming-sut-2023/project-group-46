package Controller;

import View.*;

public class RunnerController {
    private GameMenu gameMenu;
    private EditMapMenu editMapMenu;
    private LoginMenu loginMenu;
    private EmpireMenu empireMenu;
    private MainMenu mainMenu;
    private MapMenu mapMenu;
    private ProfileMenu profileMenu;
    private ShopMenu shopMenu;
    private SignupMenu signupMenu;
    private TradeMenu tradeMenu;

    public RunnerController() {
        gameMenu = new GameMenu(new GameMenuController());
        editMapMenu = new EditMapMenu(new EditMapMenuController());
        loginMenu = new LoginMenu(new LoginMenuController());
        empireMenu = new EmpireMenu(new EmpireMenuController());
        mainMenu = new MainMenu();
        mapMenu = new MapMenu(new MapMenuController());
        profileMenu = new ProfileMenu(new ProfileMenuController());
        shopMenu = new ShopMenu(new ShopMenuController());
        signupMenu = new SignupMenu(new SignUpMenuController());
        tradeMenu = new TradeMenu(new TradeMenuController());
    }

    public void run() {
//TODO sync controller
    }
}
