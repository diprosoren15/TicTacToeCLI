package com.diprosoren15.app;

import java.util.Scanner;
import com.diprosoren15.app.model.*;
import com.diprosoren15.app.model.Model.*;

public class App {

   Model model = new Model();
   private final Scanner scanner = new Scanner(System.in);

   Integer getPlayerMove() {
      return 1;
   }

   Integer getComputerMove() {
      return 2;
   }

   Integer getMove() {
      return switch (model.activePlayer) {
         case O -> getPlayerMove();
         case X -> getComputerMove();
      };
   }

   void playMove(Integer move) {
      Move xo = switch (model.activePlayer) {
         case X -> Move.X;
         case O -> Move.O;
      };
      model.grid[move-1] = xo;
   }

   boolean checkDraw() {
      return false;
   }

   boolean checkWin() {
      return false;
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

   void run() {
      System.out.println("WELCOME TO TIC TAC TOE");

      while (true) {
         System.out.println(this.model.toString());
         Integer move = getMove();
         if (move == null) {
            System.out.println("Invalid Move. Please retry!");
            continue;
         }
         playMove(move);            
         Outcome outcome = checkOutcome();
         if (outcome != null) {
            System.out.println(outcome.toString(model.activePlayer));
            break;
         }
         model.togglePlayer();
      }
   }

   public static void main(String[] args) {
      App tictactoe = new App();
      tictactoe.run();
   }

}
