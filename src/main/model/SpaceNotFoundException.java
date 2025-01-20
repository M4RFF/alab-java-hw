package src.main.model;

// Create Custom Exception
public class SpaceNotFoundException extends Exception {
    public SpaceNotFoundException(String message) {
        super(message);
    }
}
