package tamagoshi.game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.text.DecimalFormat;

import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.User;

public class TamaGame {
    private ArrayList<Tamagoshi> tamaBeginList;
    private ArrayList<Tamagoshi> tamaAliveList;
    private int cycle = 10;

    public TamaGame() {
        this.tamaBeginList = new ArrayList<Tamagoshi>();
        this.tamaAliveList = new ArrayList<Tamagoshi>();
    }

    public ArrayList<Tamagoshi> getTamaBeginList() {
        return tamaBeginList;
    }

    public ArrayList<Tamagoshi> getTamaAliveList() {
        return tamaAliveList;
    }

    public void setTamaBeginList(ArrayList<Tamagoshi> tamaBeginList) {
        this.tamaBeginList = tamaBeginList;
    }

    public void setTamaAliveList(ArrayList<Tamagoshi> tamaAliveList) {
        this.tamaBeginList= tamaAliveList;
    }
    public int getCycle() {
        return cycle;
    }
    public void setCycle(int cycle) {
        this.cycle = cycle;
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
                isOk=false;
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
            if (tamagoshi.getEnergy() <= 0)
                System.out.print("\n\t" + tamagoshi.getName() + " n'est pas arrivé au bout" +
                        " et ne vous félicite pas :( ");
            else if (tamagoshi.getAge() >= tamagoshi.getLifeTime())
                System.out.print("\n\t" + tamagoshi.getName() + " a bien vécu grâce à vous :)");
            else
                System.out.print("\n\t" + tamagoshi.getName() + " a survécu et vous remercie :)");
            }


        DecimalFormat df = new DecimalFormat("#.00");
        System.out.print("\n\tScore obtenu : " + df.format(score()) + "%");

    }

    public void initialisation() {
        String asking = "";
        int nbTamagoshi = 0;
        while(nbTamagoshi == 0) {
            System.out.print("Entrez le nombre de tamagoshis désiré : ");
            nbTamagoshi = User.askIntToUser();
        }

        for (int i = 0; i < nbTamagoshi; i++) {
            System.out.print("Veuillez entrer le nom du tamagoshi " + (i + 1) + " : ");
            asking = User.askUser();
            Tamagoshi tamagoshi = new Tamagoshi(asking);
            this.tamaBeginList.add(tamagoshi);
            this.tamaAliveList.add(tamagoshi);
        }
    }


    public void play(){
        this.initialisation();
        int asking;
        int cycle = 1;


        while(!tamaAliveList.isEmpty() && cycle <= this.cycle){

            System.out.println("------------Cycle n°" + cycle + "-------------");
            for ( int i = 0; i < tamaAliveList.size(); i++) {
                Tamagoshi t = tamaAliveList.get(i);
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