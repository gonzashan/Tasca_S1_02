package n3exercici1;
//package N3exercici1.Exceptions;

import n3exercici1.Exceptions.ExcepcioButacaOcupada;
import n3exercici1.Exceptions.ExcepcioFilaIncorrecta;
import n3exercici1.Exceptions.ExcepcioNomPersonaIncorrecte;
import n3exercici1.Exceptions.ExcepcioSeientIncorrecte;


import java.util.*;

public class Cine {

    private int rowsCinema = 0;
    private int seatsCinema = 0;
    private GestioButaques renoir;

    private static Scanner scanner = new Scanner(System.in);
    private static char[][] arrayMap;
    // 'arrayMap' Two – dimensional array what contains info about available seats at time to
    // print it on console and controlling reservation method, warning you seats in row you can make
    // reservation.

    /*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+*
                                                                   |N|o|n| |C|l|a|s|s| |V|a|r|i|a|b|l|e|s|
    -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+*/
    public static final String C_RESET = "\u001B[0m", C_YELLOW = "\u001B[33m", C_GREEN = "\u001B[32m", C_RED = "\u001B[31m";
    public static final int NTABS = 4;
    //public static boolean loadData = false, second_row = false, switcher = false;
    private final char OCCUPED = '█', FREE = '░';


     /*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*
                                                                             |C|l|a|s|s| |M|e|t|h|o|d|s|
    -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+*/

    // Constructor
    public Cine() {
        //Creating Cinema
        renoir = new GestioButaques();

        flushingScreen();
        System.out.print(tabulate());

        int respond = inputDataInt("Vols dadas de exemple? Si [1]  No [2]  ");
        if (respond == 1) {
            this.rowsCinema = 3;
            this.seatsCinema = 7;
            setArrayMap();
            progressBar('▀', 120);
            loadingDataSamples();
        } else {
            demanarDadesInicials();
            setArrayMap();
        }
        flushingScreen();

    }

    public void setArrayMap() {
        arrayMap = new char[this.rowsCinema][this.seatsCinema];
        setArrayMapInitialization();
    }

    public int getRowsCinema() {
        return rowsCinema;
    }

    public int getSeatsCinema() {
        return seatsCinema;
    }


     /*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*
                                                             |S|c|r|e|e|n|  |P|r|i|t|n|  |M|e|t|h|o|d|s|
    -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+*/

    public static void screenMenu() {

        System.out.println();
        System.out.println(tabulate() + "╔══════════════════════════════════════════════════╗");
        System.out.println(tabulate() + "║  " + "\033[1;34m " + "CINE XIN        雷諾阿 **"+"\033[0m" +"                       ║");
        System.out.println(tabulate() + "╠══════════════════════════════════════════════════╣");
        System.out.println(tabulate() + "║                                                  ║");
        System.out.println(tabulate() + "║    [ " + C_YELLOW + "1" + C_RESET + " ] - Mostrar les butaques reservades       ║");
        System.out.println(tabulate() + "║    [ " + C_YELLOW + "2" + C_RESET + " ] - Mostrar reservas per persona          ║");
        System.out.println(tabulate() + "║    [ " + C_YELLOW + "3" + C_RESET + " ] - Reservar una butaca                   ║");
        System.out.println(tabulate() + "║    [ " + C_YELLOW + "4" + C_RESET + " ] - Anular la reserva d’una butaca        ║");
        System.out.println(tabulate() + "║    [ " + C_YELLOW + "5" + C_RESET + " ] - Anular totes reserves d’una persona   ║");
        System.out.println(tabulate() + "║                                                  ║");
        System.out.println(tabulate() + "║    [ " + C_YELLOW + "0" + C_RESET + " ] - Sortir                                ║");
        System.out.println(tabulate() + "║                                                  ║");
        System.out.println(tabulate() + "║                                                  ║");
        System.out.println(tabulate() + "║                                                  ║");
        System.out.println(tabulate() + "╚══════════════════════════════════════════════════╝\n");
        //  System.out.println(tabulate() + fillString((edificis.size() + " Registros"), 11) + ANSI_BLUE + "\t\t\t\t\tSoftware Æ¾®\n\n" + C_RESET);
    }

    public static void progressBar(char e, int nTimes) {
        System.out.print(tabulate() + "\t\t" + ' ');
        for (int i = 0; i < nTimes; i++) {
            timeDelay(61 - (i));
            System.out.printf("%c", e);
        }
    }

    public static void flushingScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static String tabulate() {
        return "\t".repeat(NTABS);
    }

    public static void printHeadSection(String s) {
        System.out.println("\n" + C_YELLOW + printSymbol('─', 132) + C_RESET);
        System.out.println(s + " *** " + C_RESET + "");
        System.out.println(C_YELLOW + printSymbol('─', 132) + C_RESET);
    }

    public static String printSymbol(char e, int nTimes) {
        return String.valueOf(e).repeat(nTimes);
    }

    public void printArrayMapWithSymbol() {

        printHeadSection("Butaques reservadas".toLowerCase());

        System.out.println("\nOcupada: " + "\u001B[31m" + OCCUPED
                + "  " + "\u001B[0m" + "\t Lliure: "
                + "\u001B[32m" + FREE + "  " + "\u001B[0m" + "\n");

        for (int i = 0; i < arrayMap.length; i++) {
            System.out.print((i + 1) + "   ");
            for (int j = 0; j < arrayMap[i].length; j++) {

                if (arrayMap[i][j] == FREE) {
                    System.out.print("\u001B[32m" + arrayMap[i][j] + "   " + "\u001B[0m");
                } else {
                    System.out.print("\u001B[31m" + arrayMap[i][j] + "   " + "\u001B[0m");
                }
            }
            System.out.println("\n");
        }

        System.out.print("    ");
        for (int j = 0; j < arrayMap[0].length; j++) {
            System.out.print((j + 1) + "   ");
        }
    }

    public void printArrayMapWithText() {

        printHeadSection("Detall per fila");
        for (int i = 0; i < arrayMap.length; i++) {

            askForFreeSeatsInRow(i, 1);
        }
        System.out.println("\n\n\n\n");
        pressEnterToContinue();
    }

    public int askForFreeSeatsInRow(int rowCinema, int actionToDo) {

        // For string builder to print if seat is occupied
        String occupiedSeats = " ";
        String finalMessage1 = "";
        // Counter if any seat from array is occupied
        int nonFreeSeats = 0;

        if (actionToDo == 2) {
            System.out.print(tabulate() + "");
        }
        for (int j = 0; j < arrayMap[0].length; j++) {

            if (arrayMap[rowCinema][j] == OCCUPED) {
                nonFreeSeats++;
                occupiedSeats = occupiedSeats.concat(
                        "[" + (String.valueOf(j + 1)).concat("]")
                                + (" "));
                // if seat is occupied, it'll print it number seat in red.
                if (actionToDo == 2) {
                    finalMessage1 = finalMessage1.concat(C_RED + "[" + (String.valueOf(j + 1)).concat("]" + C_RESET)
                            + (" "));
                }
            } else {
                // if seat is FREE, it'll print it number seat in green.
                if (actionToDo == 2) {
                    finalMessage1 = finalMessage1.concat(C_GREEN + "[" + (String.valueOf(j + 1)).concat("]" + C_RESET)
                            + (" "));
                }
            }
        }

        if (actionToDo == 2) {
            printHeadSection((
                    nonFreeSeats == arrayMap[0].length
                            ? "Fila " + (rowCinema + 1) + C_RED + "  ----> Complerta!! \n" + C_RESET
                            : "Fila " + (rowCinema + 1) + "  ----> N'hi ha de lliures \n") +
                    finalMessage1);
        }

        if (actionToDo == 1) {

            System.out.println(tabulate() + C_YELLOW + "Fila " + (rowCinema + 1) + C_RESET
                    + " Seients reservat: "
                    + (nonFreeSeats == arrayMap[0].length ? C_RED + " Complerta!!" + C_RESET
                    : nonFreeSeats == 0 ? "\u001B[32m" + " Lliure!!" + C_RESET : " " + occupiedSeats));
        }


        return nonFreeSeats == arrayMap[0].length ? 1 : -1;

    }


    /*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*
                                                                  |A|u|x|i|l|i|a|r|      |M|e|t|h|o|d|s|
    -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+*/

    public static void timeDelay(int sleep) {
        // Sleeping a while
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void pressEnterToContinue() {
        System.out.print("\t\tPrem Enter per continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadingDataSamples() {

        renoir.afegirButaca(new Butaca(2, 4, "Marc"));
        setButacaInArrayMapOccupied(1, 3);

        renoir.afegirButaca(new Butaca(1, 1, "Manolo"));
        setButacaInArrayMapOccupied(0, 0);

        renoir.afegirButaca(new Butaca(2, 3, "Manolo"));
        setButacaInArrayMapOccupied(1, 2);
        renoir.afegirButaca(new Butaca(3, 4, "Anna"));
        setButacaInArrayMapOccupied(2, 3);
        renoir.afegirButaca(new Butaca(3, 5, "Jordi"));
        setButacaInArrayMapOccupied(2, 4);

        renoir.afegirButaca(new Butaca(3, 1, "Manolo"));
        setButacaInArrayMapOccupied(2, 0);
        renoir.afegirButaca(new Butaca(3, 2, "Anna"));
        setButacaInArrayMapOccupied(2, 1);
        renoir.afegirButaca(new Butaca(3, 3, "Oriol"));
        setButacaInArrayMapOccupied(2, 2);
    }

    public void setButacaInArrayMapOccupied(int x, int y) {

        arrayMap[x][y] = OCCUPED;
    }

    public void setButacaInArrayMapFree(int x, int y) {
        arrayMap[x][y] = FREE;
    }

    public void setArrayMapInitialization() {
        // Paint the arrayMap in green before load samples
        for (char[] chars : arrayMap) {
            Arrays.fill(chars, FREE);
        }
    }

    public static int inputDataInt(String missatge) {
        // Collect data of Integer type
        int dataInput = -1;
        boolean correct = false;

        while (!correct && dataInput < 0) {
            try {
                System.out.print(tabulate() + missatge);
                dataInput = scanner.nextInt();
                if (dataInput < 1) {
                    System.out.println(tabulate() + "\u001B[31m" + "¡Número mes grand de 0!" + "\u001B[0m");

                } else {
                    return dataInput;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(tabulate() + "\u001B[31m" + "¡Error de formato de entrada!" + "\u001B[0m");

            }
        }
        return dataInput;
    }

    public void demanarDadesInicials() {

        this.rowsCinema = inputDataInt("Número de filas: ");
        this.seatsCinema = inputDataInt("Número de seients: ");
    }

    public HashSet<String> uniqueCustomersList(){
        HashSet<String> onlyCostumer = new HashSet<String>();
        for (Butaca butaca: renoir.getButaques()
        ) {
            onlyCostumer.add(butaca.getCustomerName());
        }
        return onlyCostumer;
    }

    public int introduirFilaOrSeient(int commuter) {
        // Re-using the same method to asking for two data, Row and Seat.
        int validRow;

        while (true) {
            try {
                System.out.print(tabulate() + "Introduir nº de ".toUpperCase()
                        + (commuter == 1 ? "fila" : "seient").toUpperCase() + ": ".toUpperCase());
                validRow = Integer.parseInt(scanner.nextLine());
                if (validRow >= 1 && validRow <= (commuter == 1 ?
                        this.getRowsCinema() :
                        this.getSeatsCinema())) {
                    return validRow;

                } else {
                    if (commuter == 1) {
                        throw new ExcepcioFilaIncorrecta("Avis. ¡Fila introduïda incorrecta!\n"
                                + tabulate() + "Hi ha " + this.getRowsCinema() + " filas a la sala.");
                    } else {
                        throw new ExcepcioSeientIncorrecte("Avis. ¡Seient introduït incorrecta!\n"
                                + tabulate() + "Hi ha " + this.getSeatsCinema() + " seients per fila.".toUpperCase());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(tabulate() + tabulate() + "\u001B[31m"
                        + "Avis. !Has introduït lletres!" + "\u001B[0m".toUpperCase());

            } catch (Exception e) {
                System.out.println(tabulate() + e.getMessage());
            }
        }
        /*Demana el nombre de fila/seient, si aquest està entre 1 i el nombre de files/seients totals,
        el retorna. Si no, retorna una excepció personalitzada ExcepcióFilaIncorrecta.*/
    }

    public String introduirNomPersona() {

        String validName;

        while (true) {
            try {

                System.out.print(tabulate() + "Introduir nom client: ".toUpperCase());
                validName = scanner.nextLine();

                // Avoid blanks
                if (validName.isEmpty()) {
                    throw new RuntimeException(tabulate() + "¡Has de introduir un nom!");

                } else if (!validName.matches(".*[0-9].*")) {
                    return validName;

                } else {

                    throw new ExcepcioNomPersonaIncorrecte(tabulate() + "Avis. ¡El 'Nom' conté números!");
                }

            } catch (Exception e) {

                System.out.println(tabulate() + C_RED + e.getMessage() + C_RESET);
            }

        }
        /*demana a l’usuari el nom de la persona i el retorna si és correcte.
        Si conté números, llença una l’excepció personalitzada ExcepcióNomPersonaIncorrecte.*/
    }



    /*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-*
                                                                                |M|e|n|u| |M|e|t|h|o|d|s|
    +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+*/


    public void iniciar() {

        int optionSelected;
        do {
            screenMenu();
            optionSelected = inputDataInt("SELECCIONA OPCIÓ: ");
            scanner.nextLine();
            switch (optionSelected) {

                case 1:
                    flushingScreen();
                    mostrarTotesButaquesReservadas();
                    printArrayMapWithText();
                    break;
                case 2:
                    flushingScreen();
                    mostrarAnularButaquesXPersona(1);
                    break;
                case 3:
                    flushingScreen();
                    reservarButaca();
                    break;
                case 4:
                    flushingScreen();
                    anularReservaPerPosicicioButaca();
                    break;
                case 5:
                    flushingScreen();
                    mostrarAnularButaquesXPersona(2);
                    break;
                case 0:
                    System.out.print(" i  A-D-É-U-");
                    flushingScreen();
                    scanner.close();
                    break;

            }
        } while (optionSelected != 0);
    }


    public void mostrarTotesButaquesReservadas() {
        printArrayMapWithSymbol();
        // Mostra totes les butaques reservades
    }

    // Multipurpose function, both to show seats per person
    // and delete their records in one fell swoop.
    public void mostrarAnularButaquesXPersona(int actionToDo) {

        int counter = 0;
        // This List saved my ass. I swear it.!!
        List<Integer[]> values = new ArrayList<>();

        //Listing all customers with reservations
        System.out.println("Llistat de persones amb reservas : "
                + uniqueCustomersList());

        printHeadSection("*** " + (actionToDo == 1 ? "Mostrar reservas "
                : "Anular totes les reserves client ") + "---------------");
        String customerToFind = introduirNomPersona().toUpperCase();

        printHeadSection((actionToDo == 1 ? "Butaques reservadas per "
                : "Anular totes les reserves del client ")
                + customerToFind);

        for (Butaca butaca : renoir.getButaques()) {

            if (butaca.getCustomerName().equalsIgnoreCase(customerToFind)) {
                counter++;

                if (actionToDo == 1) {
                    // Print customer and his/her reservations
                    System.out.println(butaca);

                } else if (actionToDo == 2) {
                    // add to a List to delete them later these mother fu****s
                    values.add(new Integer[]{butaca.getRow(), butaca.getSeat()});
                    System.out.print(butaca);
                    // Css homemade
                    progressBar('▒', 10);
                    System.out.print(butaca.getRow() + "," + butaca.getSeat() + " Anul·lat\n");
                }
            }
        }

        if (counter == 0) {

            System.out.println("No hi ha registres de " + customerToFind);

        } else {
            // At least, it's the moment to delete these cheeky bastards
            if (actionToDo == 2) {

                for (int i = 0; i < counter; i++) {

                    renoir.eliminarButaca(values.get(i)[0], values.get(i)[1]);
                    // Update arrayMap with seat deleted
                    setButacaInArrayMapFree(values.get(i)[0] - 1, values.get(i)[1] - 1);
                }
            }
        }
        System.out.println("\n\n\n\n");
        pressEnterToContinue();
        flushingScreen();

    /*
    Demana el nom de la persona que ha fet la reserva
    i mostra totes les butaques reservades per aquesta persona.*/
    }

    public void reservarButaca() {

        int ifPossibleToAdd, rowSeat, counter = 0;

        printHeadSection("*** RESERVAR BUTACA ");
        // It'll leave the loop when user choose a row with FREE seats
        do {
            counter++;
            if (counter > 1) {
                System.out.print("Si us plau, ");
            }
            rowSeat = introduirFilaOrSeient(1);

            // print the seats available and occupied in the row asked
            ifPossibleToAdd = askForFreeSeatsInRow((rowSeat - 1), 2);
        } while (ifPossibleToAdd == 1);


        // DoWhile{}  Do not let user get an occupied seat
        int colSeat = 0;

        do {
            try {
                colSeat = introduirFilaOrSeient(2);
                ifPossibleToAdd = renoir.cercarButaca((rowSeat), (colSeat));
                if (ifPossibleToAdd != -1) {
                    throw new ExcepcioButacaOcupada("Avis: ¡Butaca ocupada!");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (ifPossibleToAdd != -1);

        // If works fine, it'll ask for name and finally add to the ArrayList Butaques
        String name = introduirNomPersona();
        renoir.afegirButaca(new Butaca(rowSeat, colSeat, name));
        // Update screen map of seats
        setButacaInArrayMapOccupied((rowSeat - 1), (colSeat - 1));

        //Demana a l’usuari un número de fila (crida al mètode introduirFilaOrSeient),
        // un número de seient (crida al mètode introduirSeient),
        // el nom de la persona que fa la reserva (crida al mètode introduirNomPersona)
        // i reserva la butaca.*/
    }

    public void anularReservaPerPosicicioButaca() {
        printHeadSection("Anular reserva per butaca");
        int rowSeat = introduirFilaOrSeient(1);
        int colSeat = introduirFilaOrSeient(2);
        int respond = renoir.cercarButaca(rowSeat, colSeat);

        if (respond == 1) {
            // Set FREE seat at arrayMap
            setButacaInArrayMapFree(rowSeat - 1, colSeat - 1);
            System.out.println("\n" + tabulate() + C_GREEN + "Butaca alliberada!" + C_RESET);
            // Delete elem. into the Butaques ArrayList<>
            renoir.eliminarButaca(rowSeat, colSeat);

        } else {
            System.out.println();
            renoir.eliminarButaca(rowSeat, colSeat);
        }

        /*Demana a l’usuari un número de fila (crida al mètode introduirFilaOrSeient),
        un número de seient (crida al mètode introduirSeient)
        i elimina la reserva de la butaca*/
    }


}
