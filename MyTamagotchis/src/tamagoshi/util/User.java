package tamagoshi.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class User {
    /**
    Reads a line of text input from the user through standard input.
            @return the line of text input by the user.
     */
    public static String askUser(){

/* there is no need to manage exceptions because standard input can be not available,
 inputStreamReader class Constructor can rise an exception.
 */
        try{
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            return clavier.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
    /**
     Prompts the user to enter an integer value through standard input and returns the value entered.
     If the user enters a non-integer value, prompts the user to try again.
     @return the integer value entered by the user.
     */
    public static int askIntToUser(){
        boolean isOk = false;
        String asking;
        int asked = 0;
        while (!isOk){
            try {
                asking = User.askUser();
                asked = Integer.parseInt(asking);
                isOk = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrée non valide. Veuillez rentrer un nombre : ");
            }
        }
        return asked;
    }
    // a main to test this class.
    public static void main(String[] args) {
        String asking = User.askUser();
        System.out.println("la saisie est : "+asking);
    }
}