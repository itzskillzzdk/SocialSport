package modules.sports;
public enum SportEnum {
    TENNIS("Tennis"),
    NATATION("Natation"),
    MUSCULATION("Musculation"),
    ESCALADE("Escalade"),
    JOGGING("Jogging"),
    CYCLISME("Cyclisme");
    private final String valeur;

    private SportEnum(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
}
