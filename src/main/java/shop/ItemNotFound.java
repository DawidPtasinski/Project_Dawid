package shop;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound(String itemName,  Long id) {
        super(String.format("%s with %d not found!", itemName, id));
    }
}
