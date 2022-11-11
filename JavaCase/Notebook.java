import java.math.BigDecimal;

public class Notebook extends Product{
    private int storage;

    public Notebook(Long id, BigDecimal price, double discount, int stock, String name, Brand brand, int ram, double screen, int storage) {
        super(id, price, discount, stock, name, brand, ram, screen);
        this.storage = storage;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}
