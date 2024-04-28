package service;

import java.util.ArrayList;
import java.util.Scanner;

import model.User;
import model.Tweet;
import dao.TweetDAO;

public class TweetService{
    
    public static void writeTweet(Scanner inputData, User loggedUser){
        System.out.println("📝 Type your tweet: ");
        String tweet = inputData.nextLine();
        Tweet userTweet = new Tweet(tweet, loggedUser.getId());
        TweetDAO.writeTweetOnDB(userTweet);
    }


    public static void deleteTweet(Scanner inputData, User loggedUser){
        int id;
        try{
           
            System.out.println("Enter the ID of the tweet to delete: ");
            id = Integer.valueOf(inputData.nextLine());
            if(TweetDAO.existsId(id, loggedUser)){
                System.out.println("❔Are you sure you want to delete the tweet? (Enter yes or no)");
                String response = inputData.nextLine();
                if(response.equals("no") || response.equals("NO") || response.equals("No")){
                        return;
                }
            }else{
                System.out.println("There is no tweet with that ID. 👻");
                return;
            }

            Tweet deletedTweet = new Tweet();
            deletedTweet.setId(id);
            deletedTweet.setUserId(loggedUser.getId());
            TweetDAO.deleteTweetOnDB(deletedTweet);

        }catch(NumberFormatException ex){
            System.out.println("🚫You typed a word, you must enter an ID number");
            deleteTweet(inputData, loggedUser);
        }
       

    }


    public static void updateTweet(Scanner inputData, User loggedUser){
        int id;

        try{
            System.out.println("Enter tweet ID to update: ");
            id = Integer.valueOf(inputData.nextLine());
            
            if(TweetDAO.existsId(id, loggedUser)){

                 System.out.println("Type the new Tweet:");
                 String tweet = inputData.nextLine();

                Tweet updatedTweet = new Tweet();
                updatedTweet.setId(id);
                updatedTweet.setMessage(tweet);
                updatedTweet.setUserId(loggedUser.getId());
                TweetDAO.updateTweetOnDB(updatedTweet);

            }else{
                System.out.println("There is no tweet with that ID 👻 ");
                return;
            }
           
        }catch(NumberFormatException ex){
            System.out.println("🚫You typed a word, you must enter an ID number");
            updateTweet(inputData, loggedUser);
        }
    }


    public static void listAllTweets(){
        TweetDAO.listAllTweetsOnDB();
    }

    public static void listMyTweets(User loggedUser){
        ArrayList<Tweet> tweets = TweetDAO.listMyTweetsOnDB(loggedUser);
        for(Tweet t: tweets){
            System.out.println();
            System.out.println("ID: "+ t.getId());
            System.out.println("🐦 Tweet: "+ t.getMessage());
            System.out.println("🕑 Date tweet: "+ t.getTweetDate());

        }

    }
}