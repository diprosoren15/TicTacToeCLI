enum XO {
    O,
    X;
    public static String toString(XO xo) {
        if(xo == null)
            return "_";

        switch (xo) {
            case O : return "O";
            case X : return "X";
            default : return " ";
        }
    }
}

enum Outcome {
    WIN,
    DRAW;
}

public class Model {
    XO[][] grid = new XO[3][3];
    XO activePLayer = XO.O;

    public String toString() {
        StringBuilder frame = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                XO xo = grid[i][j];
                frame.append(XO.toString(xo));
                frame.append(" ");
                if (j == 2) {
                    frame.append("\n");
                }
            }
        }
        return frame.toString();
    }

    public static void main(String[] args) {
        Model model = new Model();
        System.out.println(model.toString());
    }
}
