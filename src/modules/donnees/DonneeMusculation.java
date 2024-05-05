package modules.donnees;

import modules.User;
import modules.sports.MusculationExerciceEnum;
import modules.sports.Sport;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class DonneeMusculation extends Donnee{
    private int repetition;
    private float poidsLeve;
    private MusculationExerciceEnum exercice;

    public DonneeMusculation() {

    }

    public DonneeMusculation(Sport sport, User utilisateur, int repetition, float poidsLeve, MusculationExerciceEnum exercice, Date date) {
        super(sport, utilisateur, date);
        this.repetition = repetition;
        this.poidsLeve = poidsLeve;
        this.exercice = exercice;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public float getPoidsLeve() {
        return poidsLeve;
    }

    public void setPoidsLeve(float poidsLeve) {
        this.poidsLeve = poidsLeve;
    }

    public MusculationExerciceEnum getExercice() {
        return exercice;
    }

    public void setExercice(MusculationExerciceEnum exercice) {
        this.exercice = exercice;
    }

    @Override
    public String toString() {
        return super.toString() + "-DonneeMusculation{" +
                "repetition=" + repetition +
                ", poidsLeve=" + poidsLeve +
                ", exercice='" + exercice + '\'' +
                '}';
    }
}
