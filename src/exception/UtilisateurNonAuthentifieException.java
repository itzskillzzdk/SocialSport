package exception;

public class UtilisateurNonAuthentifieException extends RuntimeException {
    public UtilisateurNonAuthentifieException(String message) {
        super(message);
    }
}
