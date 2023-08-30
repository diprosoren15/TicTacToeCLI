package com.diprosoren15.app;

import com.diprosoren15.app.engine.*;
import com.diprosoren15.app.model.*;
import com.diprosoren15.app.model.Model.*;
import com.diprosoren15.app.view.View;

public class App {

   Model model = new Model();
   View view = new View();

   public void togglePlayer() {
      switch (model.activePlayer) {
         case X -> model.activePlayer = Move.O;
         case O -> model.activePlayer = Move.X;
      }
   }

   Square getPlayerSqr() {
      view.log(model.activePlayer.toString() + "'s move: ");
      while (true) {
         String input = view.getInput();
         Object result = parseAndValidate(input);
         if (result instanceof Square) {
            return (Square) result;
         }
         view.render((ValidationFlag) result);
      }
   }

   Square getComputerSqr() {
      int idx = Engine.minMax(model);
      return new Square(idx);
   }

   Square getSquare() {
      return switch (model.activePlayer) {
         case O -> getPlayerSqr();
         case X -> getPlayerSqr();
      };
   }

   public Object parseAndValidate(String input) {
      if (input == null || 
          input.isEmpty() ||
          input.isBlank()) {
         return ValidationFlag.EmptyPosition;
      } 
      
      int i;
      try {
         i = Integer.parseInt(input);
      } catch (NumberFormatException e) {
         return ValidationFlag.CantConvertToNumber;
      }

      if (i < 1 || i > 9) {
         return ValidationFlag.InvalidPosition;
      }

      if (model.moveAt(i) != null) {
         return ValidationFlag.PositionAlreadyPlayed;
      }
      
      return new Square(i);
   }

   void fillSquare(Square sqr) {
      model.grid[sqr.idx-1] = model.activePlayer;
   }

   Outcome checkOutcome() {
      if (Engine.checkWin(model)) {
         return Outcome.WIN;
      }
      if (Engine.checkDraw(model)) {
         return Outcome.DRAW;
      }
      return null;
   }

   public void run() {
      while (true) {
         view.render(model);
         Square sqr = getSquare();
         fillSquare(sqr);
         Outcome outcome = checkOutcome();
         if (outcome != null) {
            view.clear();
            view.render(model);
            view.render(outcome, model.activePlayer);
            break;
         }
         togglePlayer();
         view.clear();
      }
   }

   public static void main(String[] args) {
      App tictactoe = new App();
      tictactoe.run();
   }

}
