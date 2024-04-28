package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connection.DBconnection;
import model.Tweet;
import model.User;

public class TweetDAO{

    private static  DBconnection dbConnection = new DBconnection();
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void writeTweetOnDB(Tweet tweet){
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "INSERT INTO tweets(message, tweet_date, user_id) VALUES(?, CURRENT_TIMESTAMP, ?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, tweet.getMessage());
                ps.setInt(2, tweet.getUserId());
                ps.executeUpdate();
                System.out.println("📥Tweet published!!");

            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("Server error. Cannot create the tweet, please try again. 🫠");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Server error. Cannot create the tweet, please try again. 🫠");
        }
    }


    public static void deleteTweetOnDB(Tweet deletedTweet){
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "DELETE FROM tweets WHERE id = ? AND user_id = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, deletedTweet.getId());
                ps.setInt(2, deletedTweet.getUserId());
                ps.executeUpdate();
                System.out.println("✅ Tweet deleted successfully!");

            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("Server error. Cannot delete the tweet, please try again. 🫠");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Server error. Cannot delete the tweet, please try again. 🫠");
        }

    }


    public static void updateTweetOnDB(Tweet updatedTweet){
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "UPDATE tweets SET message = ?, tweet_date = CURRENT_TIMESTAMP "+
                "WHERE id = ? AND user_id = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, updatedTweet.getMessage());
                ps.setInt(2, updatedTweet.getId());
                ps.setInt(3, updatedTweet.getUserId());
                ps.executeUpdate();
                System.out.println("🔄️ Tweet updated succesfully!");

            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("Server error. Can't update tweet, please try again. 🫠");

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Server error. Can't update tweet, please try again. 🫠");
        }

    }


    public static void listAllTweetsOnDB(){
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "SELECT t.message, t.tweet_date, u.user_name, u.last_name "+
                "FROM tweets t "+
                "INNER JOIN users u "+
                "ON t.user_id = u.id ";
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                while(rs.next()){
                    System.out.println();
                    System.out.println("🐦 Tweet of " +rs.getString("u.user_name") +" "+ rs.getString("u.last_name")+ ": ");
                    System.out.println(rs.getString("t.message"));
                    System.out.println("🕑 Date: "+ rs.getString("t.tweet_date"));

                }

            }catch(SQLException e){
                 System.out.println(e.getMessage());
                System.out.println("Server error. Cannot list the tweets, please try again 🫠");
               

            }

        }catch(SQLException e){
            System.out.println("Server error. Cannot list the tweets, please try again 🫠");
            System.out.println(e.getMessage());
        }
    }


    public static ArrayList<Tweet> listMyTweetsOnDB(User loggedUser){
        ArrayList<Tweet> tweets = new ArrayList<>();
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "SELECT id, message, tweet_date FROM tweets WHERE user_id = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, loggedUser.getId());
                rs = ps.executeQuery();
                while(rs.next()){
                    Tweet myTweet = new Tweet();
                    myTweet.setId(rs.getInt("id"));
                    myTweet.setMessage(rs.getString("message"));
                    myTweet.setTweetDate(rs.getString("tweet_date"));
                    tweets.add(myTweet);
                }
                return tweets;

            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("Server error. Cannot list your tweets, please try again. 🫠");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Server error. Cannot list your tweets, please try again. 🫠");
        }

        return null;
    }


    public static boolean existsId(int id, User loggedUser){
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "SELECT * FROM tweets WHERE id = ? AND user_id = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, id);
                ps.setInt(2, loggedUser.getId());
                rs = ps.executeQuery();
                if(rs.next()){
                    return true;
                }else{
                    return false;
                }

            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("Server error. Please try again. 🫠");

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
             System.out.println("Server error. Please try again. 🫠");
        }

        return false;
    }
}