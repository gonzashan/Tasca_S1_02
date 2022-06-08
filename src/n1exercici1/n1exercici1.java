package n1exercici1;


public class n1exercici1 {

    public static void main(String[] args) {


        Venda venda = new Venda();
        Venda vend1 = new Venda();
        Venda vend3 = new Venda();

        Producte producte = new Producte("Grifo", 120.33);
        Producte product1 = new Producte("Bidés", 230.50);
        Producte product2 = new Producte("Manos", 630.50);

        // Adding products to venda
        venda.setProductes(producte);
        venda.setProductes(product1);

        // Adding products to venda3
        vend3.setProductes(product2);
        vend3.setProductes(product1);

        // Forcing to catch with custom Exception ____________________
        // vend1 Obj
        try {
            vend1.calcularTotal(0);

        } catch (VendaBuidaException e) {

            System.out.println(e.getMessage());
        }

        // Non errors ___________________ __________________________
        // vend3 Obj
        try {
            vend3.calcularTotal( 0);

        } catch (VendaBuidaException e) {

            System.out.println(e.getMessage());
        }

        // Default catch Exception __________________________
        // venda Obj
        try {
            venda.calcularTotal(1);

        } catch (VendaBuidaException e) {

            System.out.println(e.getMessage());
        }

    }
}
