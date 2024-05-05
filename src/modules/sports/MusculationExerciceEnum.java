package modules.sports;

public enum MusculationExerciceEnum {
    SQUAT("Squat"),
    DEVELOPPE_COUCHE("Développé couché"),
    FENTE_BULGARE("Fente bulgare"),
    SOULEVE_DE_TERRE("Soulevé de terre"),
    LEG_CURL("Leg curl");
    private final String valeur;

    private MusculationExerciceEnum(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
}
