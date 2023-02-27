package tamagoshi.game;

import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Arrays;

import tamagoshi.tamagoshis.*;
import tamagoshi.util.User;

public class TamaGame {
    private final ArrayList<Tamagoshi> tamaBeginList;
    private final ArrayList<Tamagoshi> tamaAliveList;
    private final Random random = new Random();

    public TamaGame() {
        this.tamaBeginList = new ArrayList<>();
        this.tamaAliveList = new ArrayList<>();
    }
    /**
    Checks if a Tamagotchi exists in the list of alive Tamagotchis, given its index.
    If the index is out of bounds, prompts the user to choose an existing index.
    @param index The index of the Tamagotchi to check.
    @return The index of the existing Tamagotchi in the list of alive Tamagotchis.
    */
    private int checkTamaExistInAliveList(int index){
        boolean isOk = false;

        while (!isOk){
            try {
                this.tamaAliveList.get(index);
                isOk = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Entrée non valide. Veuillez en choisir un existant : ");
                index = User.askIntToUser();
            }
        }
        return index;
    }

    /**
     Calculates the score of the game based on the age of Tamagoshi objects in the tamaBeginList.
     The score is calculated as a percentage of the total possible score,
     where the total possible score is the sum of
     the maximum lifetime of all Tamagoshi objects in the tamaBeginList.
     @return The score of the game as a double value.
     */
    private double score(){
        double score=0;
        double maxScore=0;
        for(Tamagoshi tamagoshi : tamaBeginList){
            score += tamagoshi.getAge();
            maxScore += tamagoshi.getLifeTime();
        }
        return (score/maxScore)*100;
    }
    /**
     Displays the end result of the game, including the status of each Tamagoshi and the overall score achieved.
     The status of each Tamagoshi is determined by its energy, fun, age and lifetime attributes.
     If the energy or fun of a Tamagoshi reaches 0, its status is
     "n'est pas arrivé au bout et ne vous félicite pas :("
     If the age of a Tamagoshi exceeds its lifetime, its status is "a bien vécu grâce à vous :)"
     Otherwise, the status of a Tamagoshi is "a survécu et vous remercie :)"
     The score is calculated by summing up the age attribute of all Tamagoshis at the beginning of the game,
     and dividing it by the sum of the lifetime attribute of all Tamagoshis at the beginning of the game,
     multiplied by 100.
     The resulting score is rounded to two decimal places using DecimalFormat.
     */
    private void resultat(){
        System.out.println("\n--------- fin de partie !! ----------------");
        System.out.println("-------------bilan------------");
        String status;
        for(Tamagoshi tamagoshi : tamaBeginList) {

            if (tamagoshi.getEnergy() <= 0 || tamagoshi.getFun() <= 0) {
                status = "n'est pas arrivé au bout et ne vous félicite pas :(";
            } else if (tamagoshi.getAge() > tamagoshi.getLifeTime()) {
                status = "a bien vécu grâce à vous :)";
            } else {
                status = "a survécu et vous remercie :)";
            }
            System.out.println("\t" + tamagoshi.getName() + ", qui était un "
                    + tamagoshi.getType() + ", " + status);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.print("\n\tScore obtenu : " + df.format(score()) + "%");
    }

    /**
     This method initializes the game by asking the user for the desired number of tamagoshis,
     and creating a list of tamagoshis with random names and types (regular, big eater or big player).
     The maximum number of tamagoshis is 30, and the name list is predefined.
     */
    public void initialisation() {
        int nbTamagoshi = 0;
        int randomTamagoshiType;
        while(nbTamagoshi == 0) {
            System.out.print("Entrez le nombre de tamagoshis désiré : ");
            nbTamagoshi = User.askIntToUser();
            if(nbTamagoshi > 30) {
                nbTamagoshi = 30;
                System.out.println("\n Nombre de tamagoshis bloqués à 30 !");
            }
        }
        List<String> nameList = new ArrayList<>(Arrays.asList(
                "Emma", "Noah", "Olivia", "Liam", "Ava",
                "William", "Sophia", "Mason", "Isabella",
                "James", "Mia", "Benjamin", "Charlotte",
                "Jacob", "Amelia", "Michael", "Harper",
                "Elijah", "Evelyn", "Ethan", "Abigail",
                "Alexander", "Emily", "Daniel", "Elizabeth",
                "Matthew", "Mila", "Aiden", "Ella", "Henry"
        ));
        int index;
        for (int i = 0; i < nbTamagoshi; i++) {
            index = random.nextInt(nameList.size());
            String name = nameList.get(index);
            nameList.remove(index);
            randomTamagoshiType = random.nextInt(3) + 1;
            Tamagoshi tamagoshi = switch (randomTamagoshiType) {
                case 2 -> new BigEater(name);
                case 3 -> new BigPlayer(name);
                default -> new Tamagoshi(name);
            };
            this.tamaBeginList.add(tamagoshi);
            this.tamaAliveList.add(tamagoshi);
        }
    }

    /**
     The method starts the game by initializing the tamagoshis and executing the game cycles
     until all tamagoshis die or the maximum number of cycles is reached.
     */
    public void play(){
        this.initialisation();
        int asking;
        int cycle = 1;
        int cycle1 = 10;
        while(!tamaAliveList.isEmpty() && cycle <= cycle1){

            System.out.println("------------Cycle n°" + cycle + "-------------");
            for (Tamagoshi t : tamaAliveList) {
                t.speak();
            }

            System.out.println("\n\t" + "Nourrir quel tamagoshi ?");

            for (int i = 0; i < tamaAliveList.size(); i++) {

                System.out.print("\t (" + i +") " + tamaAliveList.get(i).getName());
            }

            System.out.print("\nEntrez un choix : ");
            asking = checkTamaExistInAliveList(User.askIntToUser());
            tamaAliveList.get(asking).eat();

            System.out.println("\n\t" + " Jouer avec quel tamagochi ?");

            for (int i = 0; i < tamaAliveList.size(); i++) {

                System.out.print("\t (" + i +") " + tamaAliveList.get(i).getName());
            }

            System.out.print("\nEntrez un choix : ");
            asking = checkTamaExistInAliveList(User.askIntToUser());
            tamaAliveList.get(asking).play();

            for ( int i = 0; i < tamaAliveList.size(); i++) {
                Tamagoshi t = tamaAliveList.get(i);
                if (!t.spendEnergy()) {
                    tamaAliveList.remove(i);
                    i--;
                }
                if (!t.getOld()) {
                    tamaAliveList.remove(i);
                    i--;
                }
                System.out.println(t);

            }
            cycle++;
        }

        resultat();
    }
    /**
     * The main method for the TamaGame program. Creates a new instance of the TamaGame class and calls its play() method.
     * @param args an array of String arguments passed to the program from the command line
     */
    public static void main(String[] args) {
        TamaGame game = new TamaGame();
        game.play();
    }
}
