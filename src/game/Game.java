package gameKlassen;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int BOARD_SIZE = 3;
    private final char[][] board;
    private Player currentPlayer;
    private final Scanner scanner;

    // Konstruktor der Klasse Game.
    // Initialisiert das Spielbrett und den Scanner.
    public Game() {
        board = new char[BOARD_SIZE][BOARD_SIZE]; // hier wird das Spielbrett initialisiert
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '-';
            }
        }
        scanner = new Scanner(System.in);
    }

    // Startet das Spiel.
    public void play() {
        boolean isComputerOpponent = chooseOpponent();

        System.out.println("Bitte geben Sie den Namen für Spieler X ein: ");
        Player.X.setName(scanner.nextLine());

        if (isComputerOpponent) {
            System.out.println("Bitte geben Sie den Namen für den Computer ein: ");
            Player.COMPUTER.setName(scanner.nextLine());
            currentPlayer = Player.X;
        } else {
            System.out.println("Bitte geben Sie den Namen für Spieler O ein: ");
            Player.O.setName(scanner.nextLine());
            currentPlayer = Player.X;
        }

        while (true) {
            printBoard();
            try {
                if (currentPlayer.isHuman()) {
                    makePlayerMove();
                } else {
                    makeComputerMove();
                }
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (checkWinner()) {
                printBoard();
                System.out.println(currentPlayer.getName() + " hat gewonnen!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("Das Spiel endet unentschieden!");
                break;
            }

            switchPlayer(isComputerOpponent);
        }
    }

    // Lässt den menschlichen Spieler einen Zug machen.
    // @throws InvalidMoveException wenn der Zug ungültig ist.
    private void makePlayerMove() throws InvalidMoveException {
        System.out.println("Bitte geben Sie die Zeile ein: ");
        int row = getCoordinate("Zeile");
        System.out.println("Bitte geben Sie die Spalte ein: ");
        int col = getCoordinate("Spalte");

        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer.getSymbol();
        } else {
            throw new InvalidMoveException("Ungültiger Zug. Bitte wählen Sie ein anderes Feld.");
        }
    }

    // Überprüft, ob ein Zug gültig ist.
    // @param row die Zeile des Zugs.
    // @param col die Spalte des Zugs.
    // @return true, wenn der Zug gültig ist, false sonst.
    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }

        return board[row][col] == '-';
    }

    // Überprüft, ob ein Spieler gewonnen hat.
    // @return true, wenn ein Spieler gewonnen hat, false sonst.
    private boolean checkWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    // Überprüft, ob es eine Gewinnkombination in den Spalten gibt.
    // @return true, wenn es eine Gewinnkombination in den Spalten gibt, false sonst.
    private boolean checkColumns() {
        for (int i = 0

             ; i < BOARD_SIZE; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }

    // Überprüft, ob es eine Gewinnkombination in den Zeilen gibt.
    // @return true, wenn es eine Gewinnkombination in den Zeilen gibt, false sonst.
    private boolean checkRows() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Überprüft, ob das Spielfeld voll ist.
    // @return true, wenn das Spielfeld voll ist, false sonst.
    private boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Wechselt den aktuellen Spieler.
    // @param isComputerOpponent true, wenn der Gegner der Computer ist, false, wenn es ein anderer menschlicher Spieler ist.
    private void switchPlayer(boolean isComputerOpponent) {
        if (isComputerOpponent) {
            currentPlayer = currentPlayer == Player.X ? Player.COMPUTER : Player.X;
        } else {
            currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
        }
    }

    // Erlaubt dem Benutzer, den Spielmodus zu wählen.
    // @return true, wenn der Gegner der Computer ist, false, wenn es ein anderer menschlicher Spieler ist.
    private boolean chooseOpponent() {
        System.out.println("Wählen Sie den Spielmodus:\n1. Spieler gegen Spieler\n2. Spieler gegen Computer");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice == 2;
    }

    // Gibt den aktuellen Zustand des Spielfelds aus.
    private void printBoard() {
        System.out.println("-------------------");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("|  ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + "  |  ");
            }
            System.out.println();
            System.out.println("-------------------");
        }
    }

    // Fordert den Benutzer auf, eine Koordinate (Zeile oder Spalte) einzugeben.
    // @param coordinateName der Name der Koordinate.
    // @return der eingegebene Koordinatenwert.
    private int getCoordinate(String coordinateName) {
        System.out.print("Bitte geben Sie die " + coordinateName + " (0-" + (BOARD_SIZE - 1) + ") ein: ");
        return scanner.nextInt();
    }

    // Führt einen Zug für den Computer aus.
    private void makeComputerMove() {
        System.out.println("Der Computer macht seinen Zug.");

        while (true) {
            Random random = new Random();
            int row = random.nextInt(BOARD_SIZE);
            int col = random.nextInt(BOARD_SIZE);

            if (board[row][col] == '-') {
                board[row][col] = currentPlayer.getSymbol();
                break;
            }


        }
    }

    // Überprüft, ob es eine Gewinnkombination in den Diagonalen gibt.
    // @return true, wenn es eine Gewinnkombination in den Diagonalen gibt, false sonst.
    private boolean checkDiagonals() {
        return checkMainDiagonal() || checkSecondaryDiagonal();
    }

    // Überprüft, ob es eine Gewinnkombination in der Hauptdiagonale gibt.
    // @return true, wenn es eine Gewinnkombination in der Hauptdiagonale gibt, false sonst.
    private boolean checkMainDiagonal() {
        char symbol = currentPlayer.getSymbol();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][i] != symbol) {
                return false;
            }
        }
        return true;
    }

    // Überprüft, ob es eine Gewinnkombination in der Nebendiagonale gibt.
    // @return true, wenn es eine Gewinnkombination in der Nebendiagonale gibt, false sonst.
    private boolean checkSecondaryDiagonal() {
        char symbol = currentPlayer.getSymbol();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][BOARD_SIZE - i - 1] != symbol) {
                return false;
            }
        }
        return true;
    }
}
