package service;

import java.util.Scanner;

import dao.UserDAO;
import model.User;

public class UserService {

    public static void createUser(Scanner inputData) {
        System.out.println("Type your name: ");
        String name = inputData.nextLine();
        System.out.println("Type your last name: ");
        String lastName = inputData.nextLine();
        System.out.println("Type your email: ");
        String email;
        boolean repeated = true;
        do{
            email = inputData.nextLine();
            //return true or false
            if(UserDAO.existsEmail(email)){
                System.out.println("📧 The email already exists, plese type other email!!");
            }else{
                repeated = false;
            }
            
        }while(repeated);
    
        System.out.println("Type your password: ");
        String password = inputData.nextLine();

        User newUser = new User(0, name, lastName, email, password);
        UserDAO.createUserOnDB(newUser);

    }


    public static User login(Scanner inputData) {
        User loggedUser;
        String email;
        boolean correctEmail = true;
        String password;
        boolean correctPassword = true;

        do{
            System.out.println("Enter your email: ");
            email = inputData.nextLine();
            if(UserDAO.existsEmail(email)){
                correctEmail = false;

            }else{
                System.out.println(" 👻 This email is not registered.");
            }

        }while(correctEmail);

        do{
            System.out.println("Enter your password:");
            password = inputData.nextLine();

            User user = new User(email, password);
            loggedUser = UserDAO.loginOnDB(user);

            if(loggedUser == null){
                System.out.println("❌ The password is incorrect");
            }else{
                correctPassword = false;
            }

        }while(correctPassword);
        
        return loggedUser;
    }


    public static void editUser(Scanner inputData, User loggedUser, int option)
    {
        User user = new User();
            switch(option){
                case 1:
                    System.out.println("Type your new name: ");
                    String name = inputData.nextLine();
                    user.setName(name);
                    user.setId(loggedUser.getId());
                    UserDAO.editProfileOnDB(user, option);
                    break;
                case 2:
                    System.out.println("Type your new last name: ");
                    String lastName = inputData.nextLine();
                    user.setLastName(lastName);
                    user.setId(loggedUser.getId());
                    UserDAO.editProfileOnDB(user, option);
                    break;
                case 3:
                    boolean repeatedEmail = true;
                    String email;
                    do{
                        System.out.println("Typed your new email: ");
                        email = inputData.nextLine();
                        if(UserDAO.existsEmail(email)){
                            System.out.println("🚫 The email already exists, please enter other email.");

                        }
                        else{
                            repeatedEmail = false;
                        }

                    }while(repeatedEmail);

                    user.setEmail(email);
                    user.setId(loggedUser.getId());
                    UserDAO.editProfileOnDB(user, option);

                    break;

                case 4:
                    boolean correctPass = false;
                    boolean correctNewPass = false;
                    User userPassword;
                    
                    do{
                        System.out.println("🪪 Please, enter your password to authenticate: ");
                        String oldPassword = inputData.nextLine();

                        User userPass = new User(loggedUser.getEmail(), oldPassword);

                        userPassword = UserDAO.loginOnDB(userPass);
                        if(userPassword == null){
                            System.out.println("❌ The password is incorrect.");
                        }
                        else{
                            correctPass = true;
                        }

                    }while(!correctPass);

                    do{
                        System.out.println("Enter your new password: ");
                        String newPassword = inputData.nextLine();
                        System.out.println("Enter again your new password to check: ");
                        String newPassword2 = inputData.nextLine();

                        if(newPassword.equals(newPassword2)){
                            userPassword.setPassword(newPassword);
                            UserDAO.editProfileOnDB(userPassword, option);
                            correctNewPass = true;

                        }else{
                            System.out.println("❌The passwords do not match.");
                        }

                    }while(!correctNewPass);

                  

                    

            }
            
      
    }

}
