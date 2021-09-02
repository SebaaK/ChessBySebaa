package my.sebaa.chess.game.figure;

public enum Color {

    WHITE("white"),
    BLACK("black");

    private String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
