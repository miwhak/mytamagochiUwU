package tamagoshi.game;

import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Arrays;

import tamagoshi.tamagoshis.BigEater;
import tamagoshi.tamagoshis.BigPlayer;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.User;

public class TamaGame {
    private final ArrayList<Tamagoshi> tamaBeginList;
    private final ArrayList<Tamagoshi> tamaAliveList;
    private final Random random = new Random();

    public TamaGame() {
        this.tamaBeginList = new ArrayList<>();
        this.tamaAliveList = new ArrayList<>();
    }

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
    private double score(){
        double score=0;
        double maxScore=0;
        for(Tamagoshi tamagoshi : tamaBeginList){
            score += tamagoshi.getAge();

        }
        for(Tamagoshi tamagoshi : tamaBeginList){
            maxScore += tamagoshi.getLifeTime();

        }
        return (score/maxScore)*100;
    }

    private void resultat(){
        System.out.println("\n--------- fin de partie !! ----------------");
        System.out.print("-------------bilan------------");
        for(Tamagoshi tamagoshi : tamaBeginList) {
            System.out.print("\n\t" + tamagoshi.getName() + ", qui était un " + tamagoshi.getType() +", ");
            if (tamagoshi.getEnergy() <= 0 || tamagoshi.getFun() <= 0)
                System.out.print("n'est pas arrivé au bout" +
                        " et ne vous félicite pas :( ");
            else if (tamagoshi.getAge() > tamagoshi.getLifeTime() )
                System.out.print("a bien vécu grâce à vous :)");
            else
                System.out.print("a survécu et vous remercie :)");
            }


        DecimalFormat df = new DecimalFormat("#.00");
        System.out.print("\n\tScore obtenu : " + df.format(score()) + "%");

    }

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
    public static void main(String[] args) {
        TamaGame game = new TamaGame();
        game.play();


    }
}