package n3exercici1;

import n3exercici1.Exceptions.ExcepcioButacaLliure;
import n3exercici1.Exceptions.ExcepcioButacaOcupada;

import java.util.ArrayList;

public class GestioButaques {

    private final ArrayList<Butaca> butacas;


    public GestioButaques() {
        this.butacas = new ArrayList<>();

    }

    public ArrayList<Butaca> getButaques() {
        return this.butacas;
    }


    public void afegirButaca(Butaca butaca) {

        try {
            if (cercarButaca(butaca.getRow(), butaca.getSeat()) == -1) {

                this.butacas.add(butaca);

            } else {

                throw new ExcepcioButacaOcupada("Avis: ¡Butaca ocupada!");
            }

        } catch (Exception e) {

            System.out.println("\t\t" + e.getMessage());
        }
    }

    public void eliminarButaca(int row, int seat) {

        int indexToDelete = cercarButaca(row, seat);
        try {

            if (indexToDelete == -1) {

                throw new ExcepcioButacaLliure("¡Butaca lliure!");

            } else {

                this.butacas.remove(indexToDelete);

            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public int cercarButaca(int row, int seat) {

        int i = 0;

        while (i < butacas.size()) {

            if (row == butacas.get(i).getRow()) {

                if (seat == butacas.get(i).getSeat()) {

                    return i;
                }
            }
            i++;
        }
        return -1;
    }

}
