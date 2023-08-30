package com.diprosoren15.app.view;

import java.util.Scanner;

import com.diprosoren15.app.model.Model;
import com.diprosoren15.app.model.Model.*;

public class View {
   
   private final Scanner scanner = new Scanner(System.in);
   private final int[] idxs = {7,8,9,
                               4,5,6,
                               1,2,3};

   public String getInput() {
      return scanner.nextLine();
   }

   public void clear() {
      System.out.print("\033[H\033[2J");
   }

   public void clearLine(int n) {
      System.out.print("\033[" + n + "F\33[K");
   }

   public void render(Model model) {
      StringBuilder frame = new StringBuilder();
      for (int i: idxs) {
         Move move = model.moveAt(i);
         frame.append(" ");
         frame.append(render(move));
         frame.append(" ");
         if (i == 3) continue;
         if (i % 3 == 0) {
            frame.append("\n---|---|---\n");
            continue;
         }
         frame.append("|");
      }
      frame.append("\n");
      System.out.println(frame.toString());      
   }

   public void render(ValidationFlag flag) {
      String str = switch (flag) {
         case EmptyPosition -> "Position can't be empty.";
         case CantConvertToNumber -> "Couldn't convert turn to Int.";
         case InvalidPosition -> "Position is invalid.";
         case PositionAlreadyPlayed -> "Position already played.";
      };
      System.out.println(str);
      clearLine(2);
   }
   
   public void render(Outcome outcome, Move activePlayer) {
      switch (outcome) {
         case WIN -> System.out.println(render(activePlayer) + " wins!");
         case DRAW -> System.out.println("Its a draw!");
      };
   }
   
   public String render(Move move) {
      if (move == null)
         return " ";

      return switch (move) {
         case O -> "O";
         case X -> "X";
      };
   }

   public void log(String msg) {
      System.out.println(msg);
   }

}
