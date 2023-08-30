package com.diprosoren15.app.controller;

import com.diprosoren15.app.engine.Engine;
import com.diprosoren15.app.model.Model;
import com.diprosoren15.app.model.Model.*;
import com.diprosoren15.app.view.View;

public class Controller {
   Model model; 
   View view;
   
   public Controller(Model model, View view) {
      this.model = model;
      this.view = view;
   }

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

   public Square getSquare() {
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

   public void fillSquare(Square sqr) {
      model.grid[sqr.idx-1] = model.activePlayer;
   }

   public Outcome checkOutcome() {
      if (Engine.checkWin(model)) {
         return Outcome.WIN;
      }
      if (Engine.checkDraw(model)) {
         return Outcome.DRAW;
      }
      return null;
   }

}
