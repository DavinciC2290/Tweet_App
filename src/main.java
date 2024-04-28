import java.util.Scanner;
import ui.MainMenu;

public class main {

    public static void main(String[] args){
        Scanner inputData = new Scanner(System.in);
        MainMenu.mainMenu(inputData);
        inputData.close();
        
    }
    
}
