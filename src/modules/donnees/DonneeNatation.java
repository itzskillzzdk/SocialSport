package modules.donnees;

import modules.User;
import modules.sports.NatationStyleEnum;
import modules.sports.Sport;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class DonneeNatation extends Donnee{
    private String duree;
    private Float distance;
    private NatationStyleEnum style;

    public DonneeNatation() {
    }

    public DonneeNatation(Sport sport, User utilisateur, String duree, Float distance, NatationStyleEnum style, Date date) {
        super(sport, utilisateur, date);
        this.duree = duree;
        this.distance = distance;
        this.style = style;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public NatationStyleEnum getStyle() {
        return style;
    }

    public void setStyle(NatationStyleEnum style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return super.toString() + "-DonneeNatation{" +
                "duree=" + duree.toString() +
                ", distance=" + distance +
                ", style='" + style + '\'' +
                '}';
    }
}
