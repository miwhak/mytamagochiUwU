package tamagoshi.tamagoshis;

public class BigEater extends Tamagoshi {

    public BigEater(String name) {
        super(name);
    }

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
    public String toString() {
        return "Tamagoshi [ nom=" + super.getName() + ", âge=" +
                super.getAge() + ", type = Big Eater" +
                ", durée de vie = " +
                super.getLifeTime() + ", maxEnergy=" +
                super.getMaxEnergy() + ", energy=" +
                super.getEnergy() + " ]";
    }
    public String getType(){
        return "gros mangeur";
    }
}

