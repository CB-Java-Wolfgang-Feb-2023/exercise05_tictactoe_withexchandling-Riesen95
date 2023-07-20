package gameKlassen;

public class Player {
    // Statische Spieler-Objekte für X, O und den Computer
    public static final Player X = new Player("X", 'X', true);
    public static final Player O = new Player("O", 'O', true);
    public static final Player COMPUTER = new Player("Computer", 'O', false);

    private String name;        // Der Name des Spielers
    private final char symbol;  // Das Symbol des Spielers (X oder O)
    private final boolean isHuman;  // Gibt an, ob der Spieler menschlich ist

    // Konstruktor für die Spielerklasse
    public Player(String name, char symbol, boolean isHuman) {
        this.name = name;
        this.symbol = symbol;
        this.isHuman = isHuman;
    }

    // Getter für den Namen des Spielers
    public String getName() {
        return name;
    }

    // Getter für das Symbol des Spielers
    public char getSymbol() {
        return symbol;
    }

    // Getter, um zu überprüfen, ob der Spieler menschlich ist
    public boolean isHuman() {
        return isHuman;
    }

    // Setter für den Namen des Spielers
    public void setName(String name) {
        this.name = name;
    }
}
