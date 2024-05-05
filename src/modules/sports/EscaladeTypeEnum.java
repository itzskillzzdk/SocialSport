package modules.sports;

public enum EscaladeTypeEnum {
    ESCALADE_EN_SALLE("Escalade en salle"),
    ESCALADE_DE_BLOC("Escalade de bloc"),
    ESCALADE_EN_MOULINETTE("Escalade en moulinette");
    private final String valeur;

    private EscaladeTypeEnum(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
}
