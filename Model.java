import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

enum XO {
    O,
    X;
    public static String toString(@Nullable XO xo) {
        if(xo == null)
            return "_";

        return switch (xo) {
            case O -> "O";
            case X -> "X";
        };
    }
}

enum Outcome {
    WIN,
    DRAW;
    public String toString(XO activePlayer) {
        return switch (this) {
            case WIN -> activePlayer.toString()+ " wins!";
            case DRAW -> "Its a draw!";
        };
    }

}

public class Model {
    @Nullable XO @NotNull [] grid = new XO[9];
    XO activePlayer = XO.O;

    void togglePlayer() {
        switch (activePlayer) {
            case X -> activePlayer = XO.O;
            case O -> activePlayer = XO.X;
        }
    }

    public String toString() {
        StringBuilder frame = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            XO xo = grid[i];
            frame.append(XO.toString(xo));
            frame.append(" ");
            if ((i+1)%3 == 0) {
                frame.append("\n");
            }
        }
        frame.append("\n");
        frame.append(activePlayer.toString());
        frame.append("'s move: ");
        return frame.toString();
    }

    public static void main(String[] args) {
        Model model = new Model();
        assert model.toString().equals("_ _ _ \n_ _ _ \n_ _ _ \n\nO's move : ") : "Model.toString failed";
    }
}
