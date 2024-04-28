package ui;

import java.util.Scanner;
import model.User;
import service.TweetService;
import service.UserService;

public class SessionMenu {

    public static void sessionMenu(Scanner inputData, User loggedUser){
        int option = 0;

        do{
            try{
                System.out.println();
                System.out.println("Welcome 🐦 " + loggedUser.getName() + " " + loggedUser.getLastName() );
                System.out.println("Menu Session.");
                System.out.println("1.- Write a tweet");
                System.out.println("2.- Delete a tweet");
                System.out.println("3.- Update a tweet");
                System.out.println("4.- List all tweets");
                System.out.println("5.- List my tweets");
                System.out.println("6.- Edit profile");
                System.out.println("7.- Logout");
                System.out.println("Choose an option: ");

                option = Integer.valueOf(inputData.nextLine());
                switch(option){
                    case 1:
                        TweetService.writeTweet(inputData, loggedUser);
                        break;
                    case 2:
                        TweetService.deleteTweet(inputData, loggedUser);
                        break;
                    case 3:
                        TweetService.updateTweet(inputData, loggedUser);
                        break;
                    case 4:
                        TweetService.listAllTweets();
                        break;
                    case 5:
                        TweetService.listMyTweets(loggedUser);
                        break;
                    case 6:
                        int editOption = 0;
                        do{
                            try{
                                System.out.println("🪪 What do you want to change in your profile?");
                                System.out.println("1.- User name.");
                                System.out.println("2.- Last name.");
                                System.out.println("3.- Email.");
                                System.out.println("4.- Password.");
                                System.out.println("5.- Exit.");
                                System.out.println("Choose an option: ");
                                editOption = Integer.parseInt(inputData.nextLine());

                                UserService.editUser(inputData, loggedUser, editOption);

                            }catch(NumberFormatException e){
                                System.out.println("🚫You typed a word, you must enter a number");
                
                            }

                        }while(editOption != 5);

                        break;

                    case 7:
                        System.out.println("👋 BYE! "+ loggedUser.getName());
                        break;
                    default:
                        System.out.println("⚠️ Enter an available option!!");

                }
            }catch(NumberFormatException ex){
                System.out.println("🚫You typed an word, you must enter an available number!!");
            }


        }while(option != 7);
    }
    
}
