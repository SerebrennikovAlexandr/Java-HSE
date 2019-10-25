package battleship;

/*
This Project was made by Serebrennikov Alexander, BSE181.
October, 2019.
 */

import java.util.Scanner;

class BattleshipGame {
    private static Scanner in = new Scanner(System.in);

    /**
     * The main method of the program. Makes it work.
     * Generates the Ocean class instance, places ships.
     * @param args
     */
    public static void main(String[] args) {
        printMainInfo();
        System.out.println("Press Enter to start a new game or print \"quit\" to quit:");
        String input = in.nextLine();

        while(!input.equals("quit")) {
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            ocean.print();
            int[] coordinates;
            do {
                try {
                    coordinates = getCoordinates();
                    boolean isSunk = ocean.getShipArray()[coordinates[0]][coordinates[1]].isSunk();
                    if(ocean.shootAt(coordinates[0], coordinates[1])) {
                        System.out.println("Hit!");
                    } else {
                        System.out.println("Miss");
                    }
                    if(!isSunk && ocean.getShipArray()[coordinates[0]][coordinates[1]].isSunk()) {
                        System.out.println("You just sank a " +
                                ocean.getShipArray()[coordinates[0]][coordinates[1]].getShipType() +
                                ".");
                    }
                    ocean.print();
                } catch (RuntimeException e) {
                    return;
                }
            } while (!ocean.isGameOver());
            System.out.println("Congratulations! All ships are sunk!");
            System.out.println("Hits: " + ocean.getHitCount());
            System.out.println("Total shots: " + ocean.getShotsFired());
            System.out.println("Press Enter to start a new game or print \"quit\" to quit:");
            input = in.nextLine();
            input = in.nextLine();
        }
    }

    /**
     * Method, that lets user input a row and a column index to
     * shoot at.
     * @return Integer array, size = 2, with row and column coordinates
     *         to shoot at.
     * @throws RuntimeException if user wants to quit the program
     */
    private static int[] getCoordinates() throws RuntimeException{
        System.out.println("Commander, enter new coordinates for gunfire:");
        String row = in.next();
        if(row.equals("quit")) {
            throw new RuntimeException();
        }
        if(notCoordinate(row)) {
            do {
                System.out.println("Please, enter row index again:");
                row = in.next();
                if(row.equals("quit")) {
                    throw new RuntimeException();
                }
            } while (notCoordinate(row));
            System.out.println("Please, enter column index again:");
        }
        String column = in.next();
        if(notCoordinate(column)) {
            do {
                System.out.println("Please, enter column index again:");
                System.out.println("<row> <column>");
                column = in.next();
            } while (notCoordinate(row) || notCoordinate(column));
        }
        return new int[] {Integer.parseInt(row), Integer.parseInt(column)};
    }

    /**
     * Checks if the given string can be an integer number
     * and a coordinate in Ocean.
     * @param   str input string.
     * @return      false, if <code>str<code/>
     *              can be converted to Integer and is
     *              in the [0;9] interval.
     *              true, if not.
     */
    private static boolean notCoordinate(String str) {
        try {
            int res = Integer.parseInt(str);
            if(res < 0 || res > 9) {
                System.out.println("No such coordinate!");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Not an integer number's given!");
            return true;
        }
    }

    /**
     * Prints the main information and the rules of the game,
     * when the program is loaded.
     */
    private static void printMainInfo() {
        System.out.println("DEAR USER, WELCOME TO THE BATTLESHIP GAME!");
        System.out.println("---------------------------------------------------------");
        System.out.println("The rules:");
        System.out.println("The computer places ten ships on the ocean (10x10 field)\n" +
                "so that no ships are immediately adjacent to each other,\n" +
                "either horizontally, vertically, or diagonally. When the\n" +
                "game starts, you don't know where the ships are.");
        System.out.println("The aim is to sink the computer's fleet by shooting (0-9)\n" +
                "coordinates of the row and the column. A ship is sunk\n" +
                "when every square of the ship has been hit. Thus, it    \n" +
                "takes four hits (in four different places) to sink a    \n" +
                "battleship, three to sink a cruiser, two for a destroyer,\n" +
                "and one for a submarine.");
        System.out.println("Your goal is to sink the fleet with as few shots as\n" +
                "possible; the best possible score would be 20.");
        System.out.println("---------------------------------------------------------");
        System.out.println("How to play:");
        System.out.println(" - when asked, print the coordinates (0-9) of the field\n" +
                "you want to shoot at in the format:\n<row> <column>");
        System.out.println(" - if you want to quit, print \"quit\"");
        System.out.println("---------------------------------------------------------");
        System.out.println("GOOD LUCK, ADMIRAL!");
        System.out.println();
    }
}
