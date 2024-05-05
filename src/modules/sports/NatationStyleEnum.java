package modules.sports;

public enum NatationStyleEnum {
    PAPILLON("Papillon"),
    CRAWL("Crawl"),
    BRASSE("Brasse"),
    DOS_CRAWLE("Dos crawlé");
    private final String valeur;

    private NatationStyleEnum(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
}
