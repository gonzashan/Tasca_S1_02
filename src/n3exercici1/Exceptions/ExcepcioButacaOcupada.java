package n3exercici1.Exceptions;

public class ExcepcioButacaOcupada extends Exception{
    public ExcepcioButacaOcupada(String message) {

        super("\u001B[31m" + message.toUpperCase() + "\u001B[0m");
    }
}
