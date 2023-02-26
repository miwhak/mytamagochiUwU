package tamagoshi.tamagoshis;

import java.util.Random;

public class Tamagoshi {
    private int age;
    private int maxEnergy;
    private int energy;
    private String name;
    private static int lifeTime;
    private int fun;
    private int maxfun;
    private Random random = new Random();

    public Tamagoshi(String name) {
        this.name = name;
        this.age = 0;
        this.maxfun = random.nextInt(5) + 5;
        this.fun = random.nextInt(5) + 3;
        this.maxEnergy = random.nextInt(5)+5;
        this.energy = random.nextInt(5) + 3;
        this.lifeTime = random.nextInt(5) + 6;
        if(this.lifeTime > 10)
            this.lifeTime = 10;
    }

    // Getters and setters for the private variables
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public Random getRandom() {
        return random;
    }
    public void speak() {
        if (energy > 4)
            System.out.print("\n\t" + name + " : tout va bien !");
        else
            System.out.print("\n\t" + name + " : je suis affamé !");

        if (fun > 4)
            System.out.print(" et je m'amuse bien !");
        else
            System.out.println(" et je suis déprimé :s ");

    }
    public void play(){
        if(fun < maxfun){
            int entertainment = random.nextInt(3) + 1;
            fun += entertainment;
            if(fun > maxfun)
                fun = maxfun;
            System.out.println("\t" + name + " : Yeee je m'amuse ! (+" + entertainment + ")");

        }
        else
            System.out.println("\t" + name + " : Ca suffit ! Je veux me reposer !");
    }
    public void eat() {
        if (energy < maxEnergy) {
            int food = random.nextInt(3) + 1;
            energy += food;

            if(energy > maxEnergy)
                energy = maxEnergy;
            System.out.println("\t" + name + " : Miam c'est bon ! (+" + food + ")");

        } else
            System.out.println("\t" + name + " : je n'ai pas faim !");

    }
    public boolean spendEnergy() {
        energy -= 1;
        fun -=1;
        if (energy <= 0 || fun <= 0)  {
            System.out.println("\t" + name + " : Je suis KO ! Arrrggh !");
            return false;
        } else {
            return true;
        }
    }
    public boolean getOld(){
        age++;
        if (age >= lifeTime) {
            System.out.println("\t" + name + " : Je meurt de veillesse ! haaaaa !");
            return false;
        } else return true;
    }
    public String toString() {
        return "Tamagoshi [ nom=" + name + ", âge=" + age + ", durée de vie = " + lifeTime + ", maxEnergy=" + maxEnergy + ", energy=" + energy + " ]";
    }

    public static void main(String[] args) {
        Tamagoshi monTamagoshi = new Tamagoshi("Tom");
        System.out.println(monTamagoshi);

        monTamagoshi.speak();
        monTamagoshi.eat();
        System.out.println(monTamagoshi);
        monTamagoshi.spendEnergy();
        System.out.println(monTamagoshi);
        monTamagoshi.eat();
        System.out.println(monTamagoshi);
        monTamagoshi.spendEnergy();
        System.out.println(monTamagoshi);
    }
}
