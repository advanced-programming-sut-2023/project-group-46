package View;

import Controller.ProfileMenuController;
import Enums.Commands.ProfileMenuCommands;

import java.util.regex.Matcher;

public class ProfileMenu {
    private final ProfileMenuController profileMenuController;

    public ProfileMenu(ProfileMenuController profileMenuController) {
        this.profileMenuController = profileMenuController;
    }

    public void run() throws Exception {

        Matcher matcher;
        String command;

        while (true) {
            command = Menu.getScanner().nextLine();

            if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_USERNAME)) != null)
                System.out.println(profileMenuController.changeUsername(matcher));

            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_NICKNAME)) != null)
                System.out.println(profileMenuController.changeNickname(matcher));

            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_PASSWORD)) != null)
                System.out.println(profileMenuController.changePassword(matcher));

            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_EMAIL)) != null)
                System.out.println(profileMenuController.changeEmail(matcher));

            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_SLOGAN)) != null)
                System.out.println(profileMenuController.changeSlogan(matcher));

            else if (command.matches("^profile display slogan$"))
                System.out.println(profileMenuController.showUserSlogan());

            else if (command.matches("^profile display rank$"))
                System.out.println(profileMenuController.showUserRank());

            else if (command.matches("^profile remove slogan$"))
                System.out.println(profileMenuController.removeSlogan());

            else if (command.matches("^profile display highscore$"))
                System.out.println(profileMenuController.showUserHighScore());

            else if (command.matches("^profile display$")) System.out.println(profileMenuController.showProfileInfo());

            else if (command.matches("^back$")) return;

            else System.out.println("invalid command!");

        }

    }
}