package n3exercici1.Exceptions;

public class ExcepcioButacaLliure extends Exception{
    public ExcepcioButacaLliure(String message) {
        super("\t\t\u001B[32m" + message + "\u001B[0m");
    }
}
