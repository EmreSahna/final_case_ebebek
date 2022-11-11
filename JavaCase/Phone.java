import java.math.BigDecimal;

public class Phone extends Product{
    private int memory;
    private int battery;
    private Color color;
    public Phone(Long id, BigDecimal price, double discount, int stock, String name, Brand brand, int ram, double screen, int memory, int battery, Color color) {
        super(id, price, discount, stock, name, brand, ram, screen);
        this.memory = memory;
        this.battery = battery;
        this.color = color;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
