package n3exercici1.Exceptions;

public class ExcepcioSeientIncorrecte extends Exception{
    public ExcepcioSeientIncorrecte(String message) {

        super("\u001B[31m" + message.toUpperCase() + "\u001B[0m");
    }
}
