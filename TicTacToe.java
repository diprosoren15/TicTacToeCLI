public class TicTacToe {
    Model model = new Model();
    XO xo = XO.O;
    boolean checkDraw() {

    }

    boolean checkWin() {

    }
    Outcome checkOutcome() {

        if (checkWin()) {
            return Outcome.WIN;
        }
        if (checkDraw()) {
            return Outcome.DRAW;
        }
        return null;
    }


    public static void main(String[] args) {

    }
}
