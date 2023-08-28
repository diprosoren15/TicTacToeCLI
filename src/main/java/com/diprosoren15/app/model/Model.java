package com.diprosoren15.app.model;

public class Model {

   public enum Move {
      O,
      X;
      public static String toString(Move xo) {
         if(xo == null)
            return " ";

         return switch (xo) {
            case O -> "O";
            case X -> "X";
         };
      }
   }

   public enum Outcome {
      WIN,
      DRAW;
      public String toString(Move activePlayer) {
         return switch (this) {
            case WIN -> activePlayer.toString()+ " wins!";
            case DRAW -> "Its a draw!";
         };
      }
   }

   public Move [] grid = new Move[9];
   public Move activePlayer = Move.O;

   public void togglePlayer() {
      switch (activePlayer) {
         case X -> activePlayer = Move.O;
         case O -> activePlayer = Move.X;
      }
   }

   public String toString() {
      StringBuilder frame = new StringBuilder();
      for (int i = 0; i < grid.length; i++) {
         Move xo = grid[i];
         frame.append(" ");
         frame.append(Move.toString(xo));
         frame.append(" ");
         if (i == 8) continue;
         if ((i + 1) % 3 == 0) {
            frame.append("\n---|---|---\n");
            continue;
         }
         frame.append("|");
      }
      frame.append("\n");
      frame.append(activePlayer.toString());
      frame.append("'s move: ");
      return frame.toString();
   }

   public static void main(String[] args) {
      Model model = new Model();
      if (!model.toString().equals("   |   |   \n" +
                                   "---|---|---\n" +
                                   "   |   |   \n" +
                                   "---|---|---\n" +
                                   "   |   |   \n" +
                                   "O's move: "))
         System.out.print("AssertionError: Model.toString failed");
   }

}
