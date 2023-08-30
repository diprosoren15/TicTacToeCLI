package com.diprosoren15.app;

import com.diprosoren15.app.controller.Controller;
import com.diprosoren15.app.model.*;
import com.diprosoren15.app.model.Model.*;
import com.diprosoren15.app.view.View;

public class App {

   public void run() {
      Model model = new Model();
      View view = new View();
      Controller controller = new Controller(model, view);

      while (true) {
         view.render(model);
         Square sqr = controller.getSquare();
         controller.fillSquare(sqr);
         Outcome outcome = controller.checkOutcome();
         if (outcome != null) {
            view.clear();
            view.render(model);
            view.render(outcome, model.activePlayer);
            break;
         }
         controller.togglePlayer();
         view.clear();
      }
   }

   public static void main(String[] args) {
      App tictactoe = new App();
      tictactoe.run();
   }

}
