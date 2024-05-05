package modules.sports;
import javax.persistence.*;

@Entity
public class Sport {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private SportEnum nom;
    public Sport() {

    }

    public Sport(SportEnum nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SportEnum getNom() {
        return nom;
    }

    public void setNom(SportEnum nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", nom=" + nom +
                '}';
    }
}
