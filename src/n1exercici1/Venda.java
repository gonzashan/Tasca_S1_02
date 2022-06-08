package n1exercici1;

import java.util.ArrayList;

public class Venda {

    private ArrayList<Producte> productes;
    private double amountSales = 0;

    public Venda() {
        productes = new ArrayList<>();
    }

    public void setProductes(Producte productes) {
        this.productes.add(productes);
    }

    public void calcularTotal(int error) throws VendaBuidaException {

        try {
            if (!productes.iterator().hasNext()) {
                // Throwing Custom Exception
                throw new VendaBuidaException("Items: [" +productes.size() + "] Per fer una venda " +
                        "primer has d’afegir productes.\n");
            } else {

                for (int i = 0; i < productes.size() + error; ++i) {
                    amountSales += productes.get(i).getPriceItem();
                }
                System.out.println("Items: [" +productes.size() + "] Total: " + amountSales + "\n");
            }

        } catch (IndexOutOfBoundsException e) {

            System.out.println(e.getMessage());
        }
    }
}
