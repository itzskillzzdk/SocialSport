package modules;
import modules.sports.Sport;

import javax.persistence.*;
import java.util.List;
@Entity
public class DemandeAmis {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = User.class)
    private User demandeur;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = User.class)
    private User destinataire;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut; // Enum pour représenter l'état de la demande

    public void setDemandeur(User demandeur) {
        this.demandeur = demandeur;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public DemandeAmis(User demandeur, User destinataire) {
        this.demandeur = demandeur;
        this.destinataire = destinataire;
        this.statut = StatutDemande.EN_ATTENTE; // Par défaut, la demande est en attente
        //this.demanderAmis();
    }

    public DemandeAmis() {

    }

    public void accepterDemande() {
        this.statut = StatutDemande.ACCEPTEE;
    }

    public void refuserDemande() {
        this.statut = StatutDemande.REFUSEE;
    }

    public User getDemandeur() {
        return demandeur;
    }

    public User getDestinataire() {
        return destinataire;
    }

    public StatutDemande getStatut() {
        return statut;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Enum pour représenter l'état de la demande
    public enum StatutDemande {
        EN_ATTENTE,
        ACCEPTEE,
        REFUSEE
    }

    @Override
    public String toString() {
        return "DemandeAmis{" +
                "id=" + id +
                ", demandeur=" + demandeur +
                ", destinataire=" + destinataire +
                ", statut=" + statut +
                '}';
    }
}
