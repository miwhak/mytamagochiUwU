package tamagoshi.tamagoshis;

import java.util.Random;

public class Tamagoshi {
    private int age;  // age starting to 0, when age > lifetime, tamagoshi die
    private final int maxEnergy; // max food capacity
    private int energy; // current level of hungriness
    private final String name;
    private static int lifeTime; // max age of a tamagoshi
    private int fun;
    private final int maxfun;
    private final Random random = new Random();

    /**
     * Constructs a new Tamagotchi object with the specified name, age 0, randomly generated maximum fun, current fun,
     * maximum energy, current energy, and lifetime. The lifetime is randomly generated between 4 and 15, but is set to 10
     * if it is greater than 10.
     * @param name the name of the new Tamagotchi object
     */
    public Tamagoshi(String name) {
        this.name = name;
        this.age = 0;
        this.maxfun = random.nextInt(5) + 5;
        this.fun = random.nextInt(5) + 3;
        this.maxEnergy = random.nextInt(5)+5;
        this.energy = random.nextInt(5) + 3;
        lifeTime = random.nextInt(12) + 4;
        if(lifeTime > 10)
            lifeTime = 10;
    }

    // Getters and setters for the private variables
    /**
     * Returns the age of the Tamagotchi.
     * @return the age of the Tamagotchi
     */
    public int getAge() {
        return age;
    }
    /**
     * Returns the maximum energy level of the Tamagotchi.
     * @return the maximum energy level of the Tamagotchi
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }
    /**
     Returns the current value of energy.
     @return the current value of energy.
     */
    public int getEnergy() {
        return energy;
    }
    /**
     Sets the value of energy to the specified value.
     @param energy the new value of energy.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    /**
     Returns the name of this object.
     @return the name of this object.
     */
    public String getName() {
        return name;
    }
    /**
     Returns the current value of lifeTime.
     @return the current value of lifeTime.
     */
    public int getLifeTime() {
        return lifeTime;
    }
    /**
     Returns the current value of fun.
     @return the current value of fun.
     */
    public int getFun() {
        return fun;
    }
    /**
     Sets the value of fun to the specified value.
     @param fun the new value of fun.
     */
    public void setFun(int fun) {
        this.fun = fun;
    }
    /**
     Prints out the state of the Tamagotchi by checking its energy and fun levels.
     If energy level is greater than 4, it is considered "all good". Otherwise, it is considered "not good".
     If fun level is greater than 4, it is considered "happy". Otherwise, it is considered "depressed".
     */
    public void speak() { // tamagoshi tell to us there state
        if (energy > 4) // all good
            System.out.print("\n\t" + name + " : tout va bien !");
        else // not good
            System.out.print("\n\t" + name + " : je suis affamé !");

        if (fun > 4) // it's happy
            System.out.print(" et je m'amuse bien !");
        else // it's depress
            System.out.print(" et je suis déprimé :s ");

    }


    /**
     Plays with the Tamagotchi, increasing its fun level by a random number between 1 and 3.
     If the fun level exceeds the maximum allowed value, it is capped at the maximum value.
     */
    public void play(){ // when we play with a tamagoshi it gains happiness
        if(fun < maxfun){
            int entertainment = random.nextInt(3) + 1;
            fun += entertainment;
            if(fun > maxfun) // limit overload
                fun = maxfun;
            System.out.println("\t" + name + " : Yeee je m'amuse ! (+" + entertainment + ")");

        }
        else
            System.out.println("\t" + name + " : Ca suffit ! Je veux me reposer !");
    }
    /**
    Feeds the Tamagotchi, increasing its energy level by a random number between 1 and 3.
    If the energy level exceeds the maximum allowed value, it is capped at the maximum value.
    */
    public void eat() { // feed tamagoshi to prevent starving
        if (energy < maxEnergy) {
            int food = random.nextInt(3) + 1;
            energy += food;

            if(energy > maxEnergy) // limit overload
                energy = maxEnergy;
            System.out.println("\t" + name + " : Miam c'est bon ! (+" + food + ")");

        } else
            System.out.println("\t" + name + " : je n'ai pas faim !");

    }
    /**
    Decreases the energy and fun levels of the Tamagotchi by 1 each turn.
    If the energy level or fun level reaches 0 or less, the Tamagotchi dies and returns false.
    Otherwise, it returns true.
     */
    public boolean spendEnergy() { // each turn fun and energy dicrease
        energy -= 1;
        fun -= 1;
        if (energy <= 0 || fun <= 0)  { // if it's starving or depressed too much, it die.
            System.out.println("\t" + name + " : Je suis KO ! Arrrggh !");
            return false;
        } else {
            return true;
        }
    }
    /**
    Increases the age of the Tamagotchi by 1, representing one day passing.
    If the age of the Tamagotchi reaches its maximum lifetime, it dies and returns false.
    Otherwise, it returns true.
     */
    public boolean getOld(){ // take a day of existence
        age++;
        if (age > lifeTime) {
            System.out.println("\t" + name + " : Je meurt de veillesse ! haaaaa !");
            return false;
        } else return true;
    }

    /**
     Returns a string representation of the Tamagotchi, including its name, age, maximum lifetime, maximum energy level, and current energy level.
     This method is mainly used for testing and debugging purposes.
     @return a string representation of the Tamagotchi.
     */
    public String toString() { // control function to verify if all work
        return "Tamagoshi [ nom=" + name + ", âge=" + age +
                ", durée de vie = " + lifeTime + ", maxEnergy=" +
                maxEnergy + ", energy=" + energy + " ]";
    }
    /**
    Returns the type of the Tamagotchi.
    This method is meant to be overridden by child classes to return their specific type.
    @return the type of the Tamagotchi.
    */
    public String getType(){ // return king of tamagoshi, useful with child
        return "tamagoshi sans type";
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
