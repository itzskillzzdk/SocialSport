package modules.donnees;

import modules.User;
import modules.sports.Sport;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class DonneeTennis extends Donnee{
    private String duree;
    private Integer jeuxGagne;
    private Integer nbServiceReussi;

    public DonneeTennis() {
    }

    public DonneeTennis(Sport sport, User utilisateur, String duree, Integer jeuxGagne, Integer nbServiceReussi, Date date) {
        super(sport, utilisateur, date);
        this.duree = duree;
        this.jeuxGagne = jeuxGagne;
        this.nbServiceReussi = nbServiceReussi;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Integer getJeuxGagne() {
        return jeuxGagne;
    }

    public void setJeuxGagne(Integer jeuxGagne) {
        this.jeuxGagne = jeuxGagne;
    }

    public Integer getNbServiceReussi() {
        return nbServiceReussi;
    }

    public void setNbServiceReussi(Integer nbServiceReussi) {
        this.nbServiceReussi = nbServiceReussi;
    }

    @Override
    public String toString() {
        return super.toString() + "-DonneeTennis{" +
                "duree=" + duree.toString() +
                ", jeuxGagne=" + jeuxGagne +
                ", nbServiceReussi=" + nbServiceReussi +
                '}';
    }
}
