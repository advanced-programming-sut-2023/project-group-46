package View;

import Controller.ProfileMenuController;

import java.util.regex.Matcher;

public class ProfileMenu {
    private final ProfileMenuController profileMenuController;

    public ProfileMenu(ProfileMenuController profileMenuController) {this.profileMenuController = profileMenuController;}

    public void run() {

        Matcher matcher;
        String command;

        while (true)
        {
            command = Menu.getScanner().nextLine();

            if ((matcher = Menu.getMatcher(command, "^\\s*profile\\s+change\\s+\\-u\\s+(?<username>.+)\\s*$")) != null)
                System.out.println(profileMenuController.changeUsername(matcher));

            else if ((matcher = Menu.getMatcher(command, "^\\s*profile\\s+change\\s+\\-n\\s+(?<nickname>.+)\\s*$")) != null)
                System.out.println(profileMenuController.changeNickname(matcher));

            else if ((matcher = Menu.getMatcher(command, "^\\s*profile\\s+change\\s+password\\s+\\-o\\s+(?<oldPassword>.+)\\s+\\-n\\s+(?<newPassword>.+)\\s*$")) != null)
                System.out.println(profileMenuController.changePassword(matcher));

            else if ((matcher = Menu.getMatcher(command, "^\\s*profile\\s+change\\s+\\-e\\s+(?<email>.+)\\s*$")) != null)
                System.out.println(profileMenuController.changeEmail(matcher));

            else if(command.matches("^\\s*profile\\s+display\\s+highscore\\s*$"))
                System.out.println(profileMenuController.showUserHighScore());


        }

    }
}