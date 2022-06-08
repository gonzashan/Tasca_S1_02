package n3exercici1;

public class Butaca {

    private int row;
    private int seat;
    private String customer;

    // Constructor
    public Butaca(int row, int seat, String customer) {
        this.row = row;
        this.seat = seat;
        this.customer = customer;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return this.seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getCustomerName() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /*
    @Override
    public boolean equals(Object butaca) {

        Butaca butacaNova = (Butaca) butaca;
        return butacaNova.getRow() == this.getRow() && butacaNova.getSeat() == this.getSeat();
    }

    @Override
    public int hashCode() {

        return Objects.hash(row, seat);
    }
*/
    public String uniqueCustomer(){
        return "\u001B[32m" + customer + "\u001B[0m" ;
    }

    @Override
    public String toString() {
        return  "Fila: " + "\u001B[32m" + row +  "\u001B[0m" +
                ",  Seient: " + "\u001B[32m" + seat + "\u001B[0m" +
                ", Persona: " + "\u001B[32m" + customer + "\u001B[0m" ;
    }
}


