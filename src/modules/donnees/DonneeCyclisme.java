package modules.donnees;

import modules.User;
import modules.sports.Sport;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class DonneeCyclisme extends Donnee{
    @Column
    private String duree;
    private Float distance;

    public DonneeCyclisme() {
    }

    public DonneeCyclisme(Sport sport, User utilisateur, String duree, Float distance, Date date) {
        super(sport, utilisateur, date);
        this.duree = duree;
        this.distance = distance;
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

    @Override
    public String toString() {
        return super.toString() + "-DonneeCyclisme{" +
                "duree=" + duree +
                ", distance=" + distance +
                '}';
    }
}
