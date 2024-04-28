package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_connection.DBconnection;
import model.User;


public class UserDAO {

    private static DBconnection dbConnection = new DBconnection();
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static void createUserOnDB(User user) {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                String query = "INSERT INTO users(user_name, last_name, email, user_key) VALUES" +
                        "(?, ?, ?, ?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, user.getName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.executeUpdate();
                System.out.println("✅Successful register");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public static User loginOnDB(User user) {
        User login = new User();
        try(Connection connection = dbConnection.getConnection()){
            
            try{
                
                String query = "SELECT * FROM users WHERE email = ? AND user_key = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                rs = ps.executeQuery();
                
                if(rs.next())
                {
                    login.setId(rs.getInt("id"));
                    login.setName(rs.getString("user_name"));
                    login.setLastName(rs.getString("last_name"));
                    login.setEmail(rs.getString("email"));
                }
                else{
                    return null;
                }

                

            }catch(SQLException e){
                System.out.println(e.getMessage());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return login;
                    
    }


    public static void editProfileOnDB(User user, int option){

        try(Connection connection = dbConnection.getConnection()){
            String query;

            try{
                switch(option){
                    case 1: 
                        query = "UPDATE users SET user_name = ? WHERE id = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, user.getName());
                        ps.setInt(2, user.getId());
                        ps.executeUpdate();
                        System.out.println("🔄️ The update of the name was successful");
                        break;
                    case 2:
                        query = "UPDATE users SET last_name = ? WHERE id = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, user.getLastName());
                        ps.setInt(2, user.getId());
                        ps.executeUpdate();
                        System.out.println("🔄️ The update of the last name was successful");
                        break;
                    case 3:
                        query = "UPDATE users SET email = ? WHERE id = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, user.getEmail());
                        ps.setInt(2, user.getId());
                        ps.executeUpdate();
                        System.out.println("🔄️ The update of the email was succesful");
                        break;
                    case 4:
                        query = "UPDATE users SET user_key = ? WHERE id = ?";
                        ps = connection.prepareStatement(query);
                        ps.setString(1, user.getPassword());
                        ps.setInt(2, user.getId());
                        ps.executeUpdate();
                        System.out.println("🔄️ The update of the password was succesful");
                        

                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("Server error. Please try again. 🫠");
            }

        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println("Server error. Please try again. 🫠");
        }

    }



    public static boolean existsEmail(String email){
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "SELECT * FROM users WHERE email = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if(rs.next()){
                    return true;
                }else{
                    return false;
                }

            }catch(SQLException e){
                System.out.println(e.getMessage());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return false;

    }

}
