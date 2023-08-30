package com.diprosoren15.app.engine;

import com.diprosoren15.app.model.*;
import com.diprosoren15.app.model.Model.*;

public class Engine {

   private static final int[][] idxl = {{ 7,8,9 },
                                        { 7,4,1 },
                                        { 4,5,6 },
                                        { 8,5,2 },
                                        { 1,2,3 },
                                        { 9,6,3 },
                                        { 7,5,3 },
                                        { 1,5,9 }};

   public static boolean checkWin(Model model) {
      for (int[] idxs : idxl) {
         if (model.moveAt(idxs[0]) != null &&
             model.moveAt(idxs[0]) == model.moveAt(idxs[1]) &&
             model.moveAt(idxs[1]) == model.moveAt(idxs[2])) {
            return true;
         }
      }
      return false;
   }

   public static boolean checkDraw(Model model) {
      for (Move move: model.grid) {
         if (move == null) {
            return false;
         }
      }
      return true;
   }

   public static int minMax(Model model) {
      return 5;
   }
   
}
