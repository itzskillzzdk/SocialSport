package modules.donnees;

import modules.User;
import modules.sports.EscaladeTypeEnum;
import modules.sports.Sport;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class DonneeEscalade extends Donnee{
    private String duree;

    private Float hauteur;
    private EscaladeTypeEnum type;
    public DonneeEscalade() {
    }

    public DonneeEscalade(Sport sport, User utilisateur, String duree, Float hauteur, EscaladeTypeEnum style, Date date) {
        super(sport, utilisateur, date);
        this.duree = duree;
        this.hauteur = hauteur;
        this.type = style;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Float getHauteur() {
        return hauteur;
    }

    public void setHauteur(Float hauteur) {
        this.hauteur = hauteur;
    }

    public EscaladeTypeEnum getType() {
        return type;
    }

    public void setType(EscaladeTypeEnum style) {
        this.type = style;
    }

    @Override
    public String toString() {
        return super.toString() + "-DonneeEscalade{" +
                "duree=" + duree +
                ", hauteur=" + hauteur +
                ", type='" + type + '\'' +
                '}';
    }
}
