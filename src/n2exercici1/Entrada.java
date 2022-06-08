package n2exercici1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrada {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean correct = false;


    /**
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |I|n|p|u|t|M|i|s|m|a|t|c|h|E|x|c|e|p|t|i|o|n|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+*
     */

    // Int
    public static int llegirInt(String missatge) {

        while (true) {
            try {
                System.out.print(missatge);
                return scanner.nextInt();

            } catch (InputMismatchException e) {
                scanner.nextLine();
                errorMessage();
            }
        }
    }

    // Float
    public static float llegirFloat(String missatge) {

        while (true) {
            try {
                System.out.print(missatge);
                return scanner.nextFloat();

            } catch (InputMismatchException e) {
                scanner.nextLine();
                errorMessage();
            }
        }
    }


    // Byte
    public static byte llegirByte(String missatge) {

        while (true)
            try {
                System.out.print(missatge);
                return scanner.nextByte();

            } catch (InputMismatchException e) {
                scanner.nextLine();
                errorMessage();
            }
    }

    // Double
    public static double llegirDouble(String missatge) {

        while (true) {
            try {
                System.out.print(missatge);
                return scanner.nextDouble();

            } catch (InputMismatchException e) {
                scanner.nextLine();
                errorMessage();
            }
        }
    }


    /* +-+-+-+-+-+-+-+-+-+
     |E|x|c|e|p|t|i|o|n|
     +-+-+-+-+-+-+-+-+-+*/
    // Char
    public static char llegirChar(String missatge) {
        // Avoid blanks
        scanner.useDelimiter("");
        String resulta = "";
        scanner.nextLine();
        while (true) {
            System.out.print(missatge);
            try {
                resulta = scanner.nextLine();

                if (resulta.length() > 1) {
                    throw new Exception();
                } else {
                    return resulta.charAt(0);
                }
            } catch (Exception e) {
                errorMessage();
            }
        }
    }

    // String
    public static String llegirString(String missatge) {
        // Avoid blanks
        scanner.useDelimiter("");
        String resulta = "";
        char value;

        while (!correct) {
            System.out.print(missatge);

            try {
                resulta = scanner.nextLine();
                return (String.valueOf(resulta.charAt(11)).length() == 1
                        ? "mes 11 caracteres!"
                        : "");
            } catch (Exception e) {
                System.out.println("Error de format. \nHas introduït mens de 11 caracteres.");
            }
        }

        return resulta;
    }

    // Boolean
    public static boolean llegirSiNo(String missatge) throws Exception {
        // Avoid blanks
        scanner.useDelimiter("");
        String resulta = "";

        while (!correct && resulta.isEmpty()) {
            System.out.print(missatge);

            try {
                resulta = scanner.nextLine();

                if (resulta.length() > 1) {
                    throw new Exception();
                } else {
                    if (resulta.equalsIgnoreCase("S")) {
                        return true;
                    } else if (resulta.equalsIgnoreCase("N")) {
                        return false;
                    }
                }

            } catch (Exception e) {
                System.out.println("Has introduït mes de una lletra!");
                errorMessage();
            }
        }
        throw new Exception("Nun se");
    }

    /* ALL IN ONE */
    public static void allInOne() {
        while (!scanner.hasNext("exit")) {
            System.out.println(
                    scanner.hasNextInt() ? "(int) " + scanner.nextInt() :
                            scanner.hasNextLong() ? "(long) " + scanner.nextLong() :
                                    scanner.hasNextDouble() ? "(double) " + scanner.nextDouble() :
                                            scanner.hasNextBoolean() ? "(boolean) " + scanner.nextBoolean() :
                                                    "(String) " + scanner.next()
            );
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println();

        System.out.println(getTextRespond() + llegirInt("Introdueix dada int: "));
        System.out.println(getTextRespond()
                + formatting(llegirFloat("Introdueix dada float: ")));
        System.out.println(getTextRespond() + llegirByte("Introdueix dada byte: "));
        System.out.println(getTextRespond()
                + formatting(llegirDouble("Introdueix dada double: ")));

        System.out.println(getTextRespond() + llegirChar("Introdueix dada char: "));
        System.out.println(getTextRespond() + llegirString("Introdueix mes de 11 caracteres: "));
        System.out.println(getTextRespond() + llegirSiNo("Vols terminar este exercici? (S) Si - (N) No : "));
        //  allInOne();
    } // End of main

    private static String getTextRespond() {
        return "Has introduït ";
    }

    public static void errorMessage() {
        System.out.println("Error de format");
    }

    public static String formatting(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d).replace('.', ',');
    }
}
