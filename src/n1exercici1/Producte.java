package n1exercici1;

public class Producte{

    private String nameItem;
    private double priceItem;

    public Producte(String nameItem, double priceItem) {
        this.nameItem = nameItem;
        this.priceItem = priceItem;
    }

    public double getPriceItem() {
        return priceItem;
    }
}
