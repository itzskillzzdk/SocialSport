package modules;

import modules.donnees.Donnee;
import modules.sports.Sport;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String nom;
    private String motDePasse;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = Sport.class)
    private List<Sport> sports = new ArrayList<Sport>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = User.class)
    private List<User> amis = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = Donnee.class)
    private List<Donnee> donnee = new ArrayList<Donnee>();
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = DemandeAmis.class)
    private List<DemandeAmis> demandes = new ArrayList<DemandeAmis>();
    @Temporal(TemporalType.DATE)
    private Date inscriptionDate;
    public User() {}

    public User(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.inscriptionDate = new Date();
    }
    public User(String nom, String motDePasse, List<Sport> sports) {
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.sports = sports;
    }

    public int getId() { return id; }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Sport> getSportList() {
        return sports;
    }

    public void setSportList(List<Sport> sportList) {
        this.sports = sportList;
    }

    public List<User> getAmis() {
        return amis;
    }

    public void setAmis(List<User> amis) {
        this.amis = amis;
    }

    public List<Donnee> getDonnee() {
        return donnee;
    }

    public void setDonnee(List<Donnee> donnee) {
        this.donnee = donnee;
    }
    public List<DemandeAmis> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<DemandeAmis> demandes) {
        this.demandes = demandes;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }
    public String sportListToString() {
        StringJoiner sj = new StringJoiner(" · ");
        for (Sport sport : sports)
            sj.add(sport.getNom().getValeur());
        return sj.toString();
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
