package modules.donnees;

import modules.User;
import modules.sports.Sport;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Donnee {
    @TableGenerator(name = "DONNEE_GEN", table = "ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "DONNEE_ID", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DONNEE_GEN")
    private int id;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Sport.class)
    private Sport sport;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = User.class)
    private User utilisateur;
    @Temporal(TemporalType.DATE)
    private Date date;
    public Donnee(){

    }
    public Donnee(Sport sport, User utilisateur, Date date){
        this.utilisateur = utilisateur;
        this.sport = sport;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Donnee{" +
                "id=" + id +
                ", sport=" + sport.toString() +
                ", utilisateur=" + utilisateur.toString() +
                ", date=" + date.toString() +
                '}';
    }
}
