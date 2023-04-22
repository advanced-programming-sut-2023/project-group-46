package View;

import Controller.ProfileMenuController;

import java.util.regex.Matcher;

public class ProfileMenu {
    private final ProfileMenuController profileMenuController;

    public ProfileMenu(ProfileMenuController profileMenuController) {this.profileMenuController = profileMenuController;}

    public void run() throws Exception {

        Matcher matcher;
        String command;

        while (true)
        {
            command = Menu.getScanner().nextLine();

            if ((matcher = Menu.getMatcher(command, "^profile change \\-u (?<username>.+)$")) != null)
                System.out.println(profileMenuController.changeUsername(matcher));

            else if ((matcher = Menu.getMatcher(command, "^profile change \\-n (?<nickname>.+)$")) != null)
                System.out.println(profileMenuController.changeNickname(matcher));

            else if ((matcher = Menu.getMatcher(command, "^profile change password \\-o (?<oldPassword>.+) \\-n (?<newPassword>.+)$")) != null)
                System.out.println(profileMenuController.changePassword(matcher));

            else if ((matcher = Menu.getMatcher(command, "^profile change \\-e (?<email>.+)$")) != null)
                System.out.println(profileMenuController.changeEmail(matcher));

            else if(command.matches("^profile display highscore$"))
                System.out.println(profileMenuController.showUserHighScore());


        }

    }
}