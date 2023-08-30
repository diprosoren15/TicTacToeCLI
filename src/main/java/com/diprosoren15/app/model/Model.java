package com.diprosoren15.app.model;

public class Model {

   public enum Move {
      O,
      X;
   }

   public enum Outcome {
      WIN,
      DRAW;
   }

   public static class Square {
      public int idx;

      public Square(int idx) {
         this.idx = idx;
      }
   }

   public enum ValidationFlag {
      EmptyPosition,
      CantConvertToNumber,
      InvalidPosition,
      PositionAlreadyPlayed;
   } 

   public Move [] grid = new Move[9];
   public Move activePlayer = Move.O;

   public Move moveAt(int idx) {
      return grid[idx-1];
   }

}
