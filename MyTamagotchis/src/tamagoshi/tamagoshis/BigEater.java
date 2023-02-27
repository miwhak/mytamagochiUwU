package tamagoshi.tamagoshis;

public class BigEater extends Tamagoshi {

    public BigEater(String name) {
        super(name);
    }

    /**
     * Decreases the energy level of the Tamagotchi by 2 and the fun level by 1, and checks whether the energy and fun levels
     * have fallen below 0. If either energy or fun level is less than or equal to 0, the Tamagotchi is considered to be knocked
     * out and the method returns false. Otherwise, the method returns true.
     * @return a boolean indicating whether the Tamagotchi has energy and fun levels greater than 0 after the energy is spent.
     */
    public boolean spendEnergy() {
        super.setEnergy(super.getEnergy()-2) ;
        super.setFun(super.getFun()-1);
        if (super.getEnergy() <= 0 || super.getFun() <= 0)  {
            System.out.println("\t" + getName() + " : Je suis KO ! Arrrggh !");
            return false;
        } else {
            return true;
        }
    }
    /**
     * Returns a string representation of the Tamagotchi, including its name, age, type, remaining lifetime, maximum energy level,
     * and current energy level.
     * @return a string representation of the Tamagotchi, including its attributes
     */
    public String toString() {
        return "Tamagoshi [ nom=" + super.getName() + ", âge=" +
                super.getAge() + ", type = Big Eater" +
                ", durée de vie = " +
                super.getLifeTime() + ", maxEnergy=" +
                super.getMaxEnergy() + ", energy=" +
                super.getEnergy() + " ]";
    }
    /**
     * Returns a string representing the type of the Tamagotchi, which is "gros mangeur".
     * @return a string representing the type of the Tamagotchi
     */
    public String getType(){
        return "gros mangeur";
    }
}

