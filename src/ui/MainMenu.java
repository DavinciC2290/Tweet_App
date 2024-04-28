package ui;

import java.util.Scanner;
import model.User;
import service.UserService;


public class MainMenu {

    public static void mainMenu(Scanner inputData) {

        int option = 0;
        do {
            try {
                System.out.println();
                System.out.println("Welcome to the social network 'TWEET' 🐦");
                System.out.println("1.- Register");
                System.out.println("2.- Login");
                System.out.println("3.- Exit");
                System.out.println("Choose an option: ");

                option = Integer.valueOf(inputData.nextLine());

                switch (option) {
                    case 1:
                        UserService.createUser(inputData);
                        break;
                    case 2:
                        User loggedUser = UserService.login(inputData);
                        if(loggedUser != null)
                        {
                            SessionMenu.sessionMenu(inputData, loggedUser);
                        }
                        break;
                    case 3:
                        System.out.println("¡Thanks for your visit!");
                        break;
                    default:
                        System.out.println("⚠️ Enter an available option!!");

                }
            } catch (NumberFormatException ex) {
                System.out.println("🚫You typed an word, you must enter an available number!!");
            }

        } while (option != 3);

    }
}